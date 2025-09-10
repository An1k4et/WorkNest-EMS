package com.worknest.entity;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "leave_request")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveRequest {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String employeeId;
	
	private String employeeName;
	
	@Enumerated(EnumType.STRING)
    private LeaveType leaveType;
	
	private String department;
	
	private String startDate;
	
	private String endDate;
	
	private String reason;
	
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;


    private LocalDateTime requestedAt = LocalDateTime.now();
    
    public enum LeaveType {
        CASUAL, SICK, PAID, UNPAID
    }
    
    public enum LeaveStatus {
        PENDING, APPROVED, REJECTED
    }

    
}
