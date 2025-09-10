package com.worknest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepartmentController {
	@GetMapping("/department-list")
	public String depratmentListPage(Model model) {
		model.addAttribute("activePage", "department-list");
		return "department/department-list";
	}
}
