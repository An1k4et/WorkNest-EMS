package com.worknest.seviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worknest.dto.LeaveStatsDTO;
import com.worknest.repository.EmployeeRepository;
import com.worknest.repository.LeaveRepository;
import com.worknest.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService{
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private LeaveRepository leaveRepo;
	
	@Override
	public Map<String, String> statsDashBoard(){
		Map<String, String> stats = new HashMap<>();
		stats.put("totalEmployees", String.valueOf(empRepo.count()));
		stats.put("activeEmployees", String.valueOf(empRepo.countActiveEmpToday()));
		stats.put("onLeaveToday", String.valueOf(empRepo.countLeaveEmpToday()));
		stats.put("pendingRequests", String.valueOf(leaveRepo.countPendingLeaveRequests()));
		return stats;
	}
	
	@Override
	public Map<String, Object> getMonthlyStats() {
	    List<LeaveStatsDTO> stats = leaveRepo.getMonthlyStats();

	    List<String> labels = new ArrayList<>();
	    List<Long> totalEmployees = new ArrayList<>();
	    List<Long> activeEmployees = new ArrayList<>();
	    List<Long> onLeave = new ArrayList<>();

	    for (LeaveStatsDTO dto : stats) {
	        labels.add(dto.getMonth());

	        long total = dto.getTotalRequests();
	        long leave = dto.getApprovedRequests(); 
	        long active = total - leave;

	        totalEmployees.add(total);
	        onLeave.add(leave);
	        activeEmployees.add(active);
	    }

	    Map<String, Object> response = new HashMap<>();
	    response.put("labels", labels);
	    response.put("totalEmployees", totalEmployees);
	    response.put("activeEmployees", activeEmployees);
	    response.put("onLeave", onLeave);

	    return response;
	}

}
