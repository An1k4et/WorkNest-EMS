package com.worknest.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.worknest.entity.LeaveRequest.LeaveStatus;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Step 1: Basic Information
    @NotBlank(message = "Employee ID is required")
    @Column(unique = true)
    private String employeeId;

    @NotBlank(message = "First name is required")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Date of birth is required")
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender is required")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @NotNull(message = "Join date is required")
    private LocalDate joinDate;

    // Step 2: Contact Information
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    
    @NotBlank(message = "Please Enter Password")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must be at least 8 characters long and include uppercase, lowercase, number, and special character"
        )
    private String password;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
    private String phone;

    private String altPhone;

    @NotBlank(message = "Address line 1 is required")
    private String address1;

    private String address2;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Zip code is required")
    private String zipCode;

    private String nationality;

    @NotBlank(message = "Country is required")
    private String country;

    // Step 3: Employment Details
	/* @Enumerated(EnumType.STRING) */
    @NotNull(message = "Department is required")
    private String department;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role is required")
    private Role role;

    @NotBlank(message = "Position is required")
    private String position;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Employment type is required")
    private EmploymentType employmentType;

    private String workLocation;
    private String manager;

    @DecimalMin(value = "0.0", message = "Salary must be positive")
    private BigDecimal salary;

    // Step 4: Educational Background
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Highest education is required")
    private EducationLevel highestEducation;

    private String fieldOfStudy;
    private String institution;
    private Integer graduationYear;

    @Lob
    private String skills;

    // Step 5: Documents
    @Enumerated(EnumType.STRING)
    @NotNull(message = "ID type is required")
    private IdType idType;

    @NotBlank(message = "ID number is required")
    private String idNumber;

    private String ssn;
    private String profilePhotoPath;
    private String idDocumentPath;

    // Step 6: Banking Details
    @NotBlank(message = "Bank name is required")
    private String bankName;

    private String branchName;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Account type is required")
    private AccountType accountType;

    @NotBlank(message = "Account number is required")
    private String accountNumber;

    @NotBlank(message = "IFSC code is required")
    private String ifscCode;
    
    private String status;

    // Enums
    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public enum MaritalStatus {
        SINGLE, MARRIED, DIVORCED, WIDOWED, OTHER
    }

    
    public enum Role {
    	ADMIN, SUPERADMIN, EMPLOYEE, SALES, HR
    }

    public enum EmploymentType {
        FULLTIME, PARTTIME, CONTRACT, INTERN, TEMP
    }

    public enum EducationLevel {
        HIGHSCHOOL, ASSOCIATE, BACHELOR, MASTER, DOCTORATE, OTHER
    }

    public enum IdType {
        ADHARCARD, DRIVERLICENSE, NATIONALID, OTHER
    }

    public enum AccountType {
        CHECKING, SAVINGS
    }
    

    public EmployeeDTO(String firstName, String lastName, String status, String department, String position, String city, LocalDate joinDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.department = department;
        this.position = position;
        this.city = city;
        this.joinDate = joinDate;
    }

	


	


    
    // Constructors, getters, setters
    // Consider using Lombok @Data annotation if available
}
