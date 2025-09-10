package com.worknest.seviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worknest.entity.LeaveRequest;
import com.worknest.repository.LeaveRepository;
import com.worknest.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService{

	@Autowired
	private LeaveRepository leaveRepo; 
	
	Logger logger = LoggerFactory.getLogger(LeaveServiceImpl.class);
	
	@Override
	public void saveLeave(LeaveRequest leave) {
		leave.setStatus(LeaveRequest.LeaveStatus.PENDING);
		logger.info("Leave Apply: "+leave);
		leaveRepo.save(leave);
	}
	
	@Override
	public List<LeaveRequest> getAllRequests() {
        return leaveRepo.findAll();
    }

	@Override
    public LeaveRequest updateStatus(Long id, LeaveRequest.LeaveStatus status) {
        LeaveRequest request = leaveRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
        request.setStatus(status);
        return leaveRepo.save(request);
    }

	@Override
    public long countPendingByDepartment(String department) {
        return leaveRepo.findByDepartmentAndStatus(department, LeaveRequest.LeaveStatus.PENDING).size();
    }

}
