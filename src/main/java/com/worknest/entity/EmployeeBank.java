package com.worknest.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee_bank")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeBank {
	// Step 4: Educational Background
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Enumerated(EnumType.STRING)
    private EducationLevel highestEducation;

    private String fieldOfStudy;
    private String institution;
    private Integer graduationYear;

    @Lob
    private String skills;

    // Step 5: Documents
    @Enumerated(EnumType.STRING)
    private IdType idType;

    private String idNumber;

    private String ssn;
    private String profilePhotoPath;
    private String idDocumentPath;

    // Step 6: Banking Details
    private String bankName;

    private String branchName;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String accountNumber;

    private String ifscCode;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
    private Employee employee;

    public enum EducationLevel {
        HIGHSCHOOL, ASSOCIATE, BACHELOR, MASTER, DOCTORATE, OTHER
    }

    public enum IdType {
        ADHARCARD, DRIVERLICENSE, NATIONALID, OTHER
    }

    public enum AccountType {
        CHECKING, SAVINGS
    }
}
