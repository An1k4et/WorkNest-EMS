package com.worknest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.worknest.dto.EmployeeDTO;
import com.worknest.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/add-employee")
	public String addEmployeePage(Model model) {
		model.addAttribute("activePage", "add-employee");
		model.addAttribute("Employee", new EmployeeDTO());
		return "employee/add-employee";
	}
	
	@RequestMapping(value="/save-employee", method=RequestMethod.POST)
	public String saveEmployee(@ModelAttribute EmployeeDTO employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/add-employee";
	}
	
	@GetMapping("/employee-list")
	public String employeeListPage(Model model) {
		model.addAttribute("activePage", "employee-page");
		return "employee/employee-list";
	}
	
	@GetMapping("/edit-employee")
	public String editEmployeeListPage(Model model) {
		model.addAttribute("activePage", "edit-employee");
		return "employee/edit-employee";
	}
	
	@GetMapping("/fetchEmployee")
	@ResponseBody
	public Map<String, Object> fetchEmployee(
			@RequestParam(name="page", defaultValue = "0") int page,
			@RequestParam(name="size", defaultValue = "8") int size,
			@RequestParam(name="keyword", required = false) String keyword
			) {
		return employeeService.fetchEmployee(page, size, keyword);
	}
}
