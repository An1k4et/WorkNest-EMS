package com.worknest.entity;

import java.math.BigDecimal;

import com.worknest.dto.EmployeeDTO.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee_departement")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDepartement {
	// Step 3: Employment Details
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Enumerated(EnumType.STRING)
    private Department department;
    
    @Enumerated(EnumType.STRING)
    private Role role;


    private String position;

    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    private String workLocation;
    private String manager;

    private BigDecimal salary;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
    private Employee employee;

    public enum Department {
        HR, IT, FINANCE, MARKETING, OPERATIONS, SALES, RD
    }

    public enum Role {
    	ADMIN, SUPERADMIN, EMPLOYEE, SALES, HR, DEVELOPER
    }
    
    public enum EmploymentType {
        FULLTIME, PARTTIME, CONTRACT, INTERN, TEMP
    }
}
