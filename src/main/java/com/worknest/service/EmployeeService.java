package com.worknest.service;

import java.util.Map;

import com.worknest.dto.EmployeeDTO;

public interface EmployeeService {

	void saveEmployee(EmployeeDTO employee);

	Map<String, Object> fetchEmployee(int page, int size, String keyword);

}
