package com.worknest.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.worknest.entity.Employee;
import com.worknest.seviceImpl.DashboardServiceImpl;

@Controller
public class WorkNestController {
	
	@Autowired
	DashboardServiceImpl dashboardService;
	
	private static final Logger LOGGER =   LoggerFactory.getLogger(WorkNestController.class);
	
	@GetMapping("/login")
	public String loginPage() {
		return "base/login";
	}
	
	@GetMapping("/signup")
	public String signupPage() {
		return "base/sign-up";
	}
	
	@GetMapping("/forget-password")
	public String forgetPassword() {
		return "base/forget-password";
	}
	
	@GetMapping("/left-navigation")
	public String leftNavigation() {
		
		return "base/left-navigation";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model,@AuthenticationPrincipal Employee employee) {
		model.addAttribute("activePage", "dashboard");
		Map<String, String> stats = dashboardService.statsDashBoard();
		model.addAttribute("stats",stats);
		
		LOGGER.info("Logged In User Details "+employee.getEmployeeId()+" "+employee.getFirstName()+" "+employee.getLastName()+" "+employee.getEmployeeDepartment().getRole()+" "+employee.getEmployeeDepartment().getDepartment());
		return "base/dashboard";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "base/login";
	}
	
	@GetMapping("/monthly")
	@ResponseBody
    public Map<String, Object> getMonthlyStats() {
        return dashboardService.getMonthlyStats();
    }
}
