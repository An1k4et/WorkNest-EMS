package com.worknest.ai.service;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QueryService {

	private final OllamaService ollama;
	private final JdbcTemplate jdbcTemplate;
	
	private final String schemaContext = """
		    Tables:
		    employees (
		        id BIGINT, 
		        employee_id VARCHAR, 
		        first_name VARCHAR, 
		        middle_name VARCHAR, 
		        last_name VARCHAR, 
		        email VARCHAR, 
		        password VARCHAR, 
		        dob DATE, 
		        gender ENUM('FEMALE','MALE','OTHER'), 
		        join_date DATE, 
		        status ENUM('ACTIVE','INACTIVE','ONLEAVE'),
		        PRIMARY KEY(id),
		        UNIQUE(employee_id)
		    )

		    employee_bank (
		        id BIGINT, 
		        account_number VARCHAR, 
		        account_type ENUM('CHECKING','SAVINGS'), 
		        bank_name VARCHAR, 
		        branch_name VARCHAR, 
		        field_of_study VARCHAR, 
		        graduation_year INT, 
		        highest_education ENUM('ASSOCIATE','BACHELOR','DOCTORATE','HIGHSCHOOL','MASTER','OTHER'), 
		        id_document_path VARCHAR, 
		        id_number VARCHAR, 
		        id_type ENUM('ADHARCARD','DRIVERLICENSE','NATIONALID','OTHER'), 
		        ifsc_code VARCHAR, 
		        institution VARCHAR, 
		        profile_photo_path VARCHAR, 
		        skills LONGTEXT, 
		        ssn VARCHAR, 
		        employee_id VARCHAR [FK -> employees.employee_id],
		        PRIMARY KEY(id),
		        UNIQUE(employee_id)
		    )

		    employee_departement (
		        id BIGINT, 
		        department ENUM('FINANCE','HR','IT','MARKETING','OPERATIONS','RD','SALES'), 
		        employment_type ENUM('CONTRACT','FULLTIME','INTERN','PARTTIME','TEMP'), 
		        manager VARCHAR, 
		        position VARCHAR, 
		        role ENUM('ADMIN','EMPLOYEE','HR','SALES','SUPERADMIN','DEVELOPER'), 
		        salary DECIMAL(38,2), 
		        work_location VARCHAR, 
		        employee_id VARCHAR [FK -> employees.employee_id],
		        PRIMARY KEY(id),
		        UNIQUE(employee_id)
		    )

		    employees_info (
		        id BIGINT, 
		        address1 VARCHAR, 
		        address2 VARCHAR, 
		        alt_phone VARCHAR, 
		        city VARCHAR, 
		        country VARCHAR, 
		        marital_status ENUM('DIVORCED','MARRIED','OTHER','SINGLE','WIDOWED'), 
		        nationality VARCHAR, 
		        phone VARCHAR, 
		        state VARCHAR, 
		        zip_code VARCHAR, 
		        employee_id VARCHAR [FK -> employees.employee_id],
		        PRIMARY KEY(id),
		        UNIQUE(employee_id)
		    )

		    leave_request (
		        id BIGINT, 
		        department VARCHAR, 
		        employee_id VARCHAR, 
		        employee_name VARCHAR, 
		        end_date VARCHAR, 
		        leave_type ENUM('CASUAL','PAID','SICK','UNPAID'), 
		        reason VARCHAR, 
		        requested_at DATETIME, 
		        start_date VARCHAR, 
		        status ENUM('APPROVED','PENDING','REJECTED'),
		        PRIMARY KEY(id)
		    )

		    recent_activity (
		        id BIGINT, 
		        activity_text VARCHAR, 
		        activity_type VARCHAR, 
		        activity_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
		        PRIMARY KEY(id)
		    )
		""";

	
	public QueryService(OllamaService ollama, JdbcTemplate jdbcTemplate) {
		this.ollama = ollama;
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Map<String, Object> runUserQuery(String question) {
		String sqlPrompt = "You are an expert MySQL assistant. "
			    + "You MUST use only the given schema and strictly respect column locations. "
			    + "Do NOT invent columns. "
			    + "Important rules: "
			    + "- join_date is ONLY in employees, not in employee_departement. "
			    + "- For department information, use employee_departement. "
			    + "- Use employees.employee_id to join with other tables. "
			    + "Schema: \n" + schemaContext
			    + "\nQuestion: " + question
			    + "\nReturn ONLY a valid SQL query, nothing else.";


	    String sql = null;
	    List<Map<String, Object>> result = null;
	    int retryCount = 0;

	    while (retryCount < 5) { // Max 5 attempts (SQL fix + blank handling)
	        try {
	            if (sql == null) {
	                sql = ollama.queryModel(sqlPrompt).trim();
	            }

	            System.out.println("SQL generated: " + sql);
	            result = jdbcTemplate.queryForList(sql);

	            if (!result.isEmpty()) {
	                // ✅ Got data, now convert to chat response
	                String jsonData = new ObjectMapper().writeValueAsString(result);

	                String chatPrompt = """
	                		You are a SQL result rephraser. 
	                		I have this query result in JSON format: %s

	                		Rules:
	                		1. ONLY describe the JSON factually in a short, clear sentence.
	                		2. If it is a count, say it like: "There are X pending leave requests in the IT department."
	                		3. Do NOT add greetings, extra stories, or made-up details.
	                		4. Never invent table names, columns, or ids.
	                		5. Output ONLY the sentence.
	                		""".formatted(jsonData);



	                String responseText = ollama.queryModel(chatPrompt).trim();
	                return Map.of("sql", sql, "result", responseText);
	            } else {
	                // ⚠️ Empty result → retry up to 3 times
	                retryCount++;
	                if (retryCount >= 3) {
	                    return Map.of("sql", sql, "result", 
	                        "I couldn't find any matching records even after checking multiple times. please try again later");
	                }
	                // Regenerate SQL if blank
	                sql = ollama.queryModel(sqlPrompt + "\nPrevious result was empty, try refining.").trim();
	            }

	        } catch (Exception e) {
	            // SQL error → regenerate SQL
	            retryCount++;
	            String retryPrompt = sqlPrompt +
	                    "\nYour previous SQL failed with error: " + e.getMessage() +
	                    ". Fix it and return corrected SQL only.";
	            sql = ollama.queryModel(retryPrompt).trim();

	            if (retryCount >= 5) {
	                return Map.of("sql", sql, "result",
	                        "I'm sorry, but I couldn't process your query after multiple attempts. Please rephrase your question.");
	            }
	        }
	    }

	    // Fallback if something weird happens
	    return Map.of("sql", "", "result", "Unexpected error occurred.");
	}

}
