package com.worknest.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Step 1: Basic Information
    @Column(unique = true)
    private String employeeId;

    private String firstName;

    private String middleName;

    private String lastName;
    
    private String email;
    
    private String password;

    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Status status;
   
    private LocalDate joinDate;
    
    // Enums
    public enum Gender {
        MALE, FEMALE, OTHER
    }
    
    public enum Status {
        ACTIVE, INACTIVE, ONLEAVE
    }
    
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private EmployeeInfo employeeInfo;
    
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private EmployeeDepartement employeeDepartment; 
    
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private EmployeeBank employeeBank;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(employeeDepartment.getRole().name()));
	}

	@Override
	public String getUsername() {
		return this.email;
	}
    

    // Constructors, getters, setters
    // Consider using Lombok @Data annotation if available
}
