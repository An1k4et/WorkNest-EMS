package com.worknest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.worknest.entity.LeaveRequest;
import com.worknest.seviceImpl.LeaveServiceImpl;

@Controller
public class LeaveRequestController {

	@Autowired
	private LeaveServiceImpl leaveService;
	
	@GetMapping("/apply-leave")
	public String applyLeave(Model model) {
		model.addAttribute("activePage", "apply-leave");
		model.addAttribute("Leave", new LeaveRequest());
		return "other/apply-leave";
	}
	
	@PostMapping("/save-leave")
	public String saveLeave(@ModelAttribute LeaveRequest leave) {
		System.out.println("Save Leave");
		leaveService.saveLeave(leave);
		return "redirect:/apply-leave";
	}
	
	@GetMapping("/leave-requests")
	@ResponseBody
    public List<LeaveRequest> getAllRequests() {
        return leaveService.getAllRequests();
    }
	
	@PutMapping("/leave-requests/{id}/approve")
	@ResponseBody
	public LeaveRequest approveRequest(@PathVariable("id") Long id) {
	    return leaveService.updateStatus(id, LeaveRequest.LeaveStatus.APPROVED);
	}

	@PutMapping("/leave-requests/{id}/reject")
	@ResponseBody
	public LeaveRequest rejectRequest(@PathVariable("id") Long id) {
	    return leaveService.updateStatus(id, LeaveRequest.LeaveStatus.REJECTED);
	}

    @GetMapping("/pending/{department}")
    @ResponseBody
    public long countPending(@PathVariable String department) {
        return leaveService.countPendingByDepartment(department);
    }
}
