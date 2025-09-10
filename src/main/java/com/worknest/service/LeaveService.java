package com.worknest.service;

import java.util.List;

import com.worknest.entity.LeaveRequest;

public interface LeaveService {

	void saveLeave(LeaveRequest leave);
	
	public List<LeaveRequest> getAllRequests();

	public LeaveRequest updateStatus(Long id, LeaveRequest.LeaveStatus status);
	
	public long countPendingByDepartment(String department);
}
