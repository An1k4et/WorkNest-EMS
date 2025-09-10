package com.worknest.entity;

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
@Table(name = "employees_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeInfo {
	// Step 2: Contact Information
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    private String altPhone;

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String zipCode;

    private String nationality;

    private String country;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;


    public enum MaritalStatus {
        SINGLE, MARRIED, DIVORCED, WIDOWED, OTHER
    }
}
