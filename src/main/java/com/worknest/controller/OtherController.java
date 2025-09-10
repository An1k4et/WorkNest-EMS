package com.worknest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.worknest.entity.LeaveRequest;
import com.worknest.seviceImpl.LeaveServiceImpl;

@Controller
public class OtherController {
	
	@GetMapping("/employee-request")
	public String employeeRequest(Model model) {
		model.addAttribute("activePage", "employee-request");
		return "other/employee-request";
	}
	
	@GetMapping("/profile")
	public String viewProfile(Model model) {
		model.addAttribute("activePage", "view-profile");
		return "other/view-profile";
	}
}
