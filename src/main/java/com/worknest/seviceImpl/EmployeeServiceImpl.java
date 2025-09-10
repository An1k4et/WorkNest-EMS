package com.worknest.seviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.worknest.dto.EmployeeDTO;
import com.worknest.entity.Employee;
import com.worknest.entity.EmployeeBank;
import com.worknest.entity.EmployeeDepartement;
import com.worknest.entity.EmployeeInfo;
import com.worknest.repository.EmployeeRepository;
import com.worknest.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired 
	private EmployeeRepository employeeRepo;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Override
	public void saveEmployee(EmployeeDTO employee) {
		String employeeId = generateNewEmpId();
		
		Employee emp = new Employee();
		emp.setId(emp.getId());
		emp.setEmployeeId(employeeId);
		emp.setFirstName(employee.getFirstName());
		emp.setMiddleName(employee.getMiddleName());
		emp.setLastName(employee.getLastName());
		emp.setEmail(employee.getEmail());
		emp.setPassword(passwordEncoder.encode(employee.getPassword()));
		emp.setDob(employee.getDob());
		emp.setGender(Employee.Gender.valueOf(employee.getGender().name()));
		emp.setJoinDate(employee.getJoinDate());
		emp.setStatus(Employee.Status.ACTIVE);
		
		EmployeeInfo empInfo = new EmployeeInfo();
		empInfo.setPhone(employee.getPhone());
		empInfo.setAltPhone(employee.getAltPhone());
		empInfo.setAddress1(employee.getAddress1());
		empInfo.setAddress2(employee.getAddress2());
		empInfo.setCity(employee.getCity());
		empInfo.setState(employee.getState());
		empInfo.setZipCode(employee.getZipCode());
		empInfo.setNationality(employee.getNationality());
		empInfo.setCountry(employee.getCountry());
		empInfo.setMaritalStatus(EmployeeInfo.MaritalStatus.valueOf(employee.getMaritalStatus().name()));
		empInfo.setEmployee(emp);
		
		emp.setEmployeeInfo(empInfo);
		
		EmployeeBank empBank = new EmployeeBank();
		empBank.setHighestEducation(EmployeeBank.EducationLevel.valueOf(employee.getHighestEducation().name()));
		empBank.setFieldOfStudy(employee.getFieldOfStudy());
		empBank.setInstitution(employee.getInstitution());
		empBank.setGraduationYear(employee.getGraduationYear());
		empBank.setSkills(employee.getSkills());
		empBank.setIdType(EmployeeBank.IdType.valueOf(employee.getIdType().name()));
		empBank.setIdNumber(employee.getIdNumber());
		empBank.setSsn(employee.getSsn());
		empBank.setProfilePhotoPath(employee.getProfilePhotoPath());
		empBank.setIdDocumentPath(employee.getIdDocumentPath());
		empBank.setBankName(employee.getBankName());
		empBank.setBranchName(employee.getBranchName());
		empBank.setAccountType(EmployeeBank.AccountType.valueOf(employee.getAccountType().name()));
		empBank.setAccountNumber(employee.getAccountNumber());
		empBank.setIfscCode(employee.getIfscCode());
		empBank.setEmployee(emp);
		
		emp.setEmployeeBank(empBank);


		EmployeeDepartement empDept = new EmployeeDepartement();
		empDept.setDepartment(EmployeeDepartement.Department.valueOf(employee.getDepartment()));
		empDept.setPosition(employee.getPosition());
		empDept.setEmploymentType(EmployeeDepartement.EmploymentType.valueOf(employee.getEmploymentType().name()));
		empDept.setWorkLocation(employee.getWorkLocation());
		empDept.setRole(EmployeeDepartement.Role.valueOf(employee.getRole().name()));
		empDept.setManager(employee.getManager());
		empDept.setSalary(employee.getSalary());
		empDept.setEmployee(emp);
		
		emp.setEmployeeDepartment(empDept);
		
		employeeRepo.save(emp);

	}

	private String generateNewEmpId() {
		String lastId = employeeRepo.findLastEmpId();
		int idNumber = 1;
		if(lastId != null && lastId.startsWith("EMP")) {
			String id = lastId.substring(3);
			idNumber = Integer.parseInt(id)+1;
		}
		return String.format("EMP%04d", idNumber);
	}

	@Override
	public Map<String, Object> fetchEmployee(int page, int size, String keyword) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Employee> employee = employeeRepo.findAll(pageable);
		
		List<EmployeeDTO> employeeDTOs = employee.stream()
			    .map(emp -> new EmployeeDTO(
			        emp.getFirstName(),
			        emp.getLastName(),
			        emp.getStatus() != null ? emp.getStatus().name() : "",
			        emp.getEmployeeDepartment() != null ? emp.getEmployeeDepartment().getDepartment().name() : "", // Convert enum to string directly
			        emp.getEmployeeDepartment() != null && emp.getEmployeeDepartment().getPosition() != null ? emp.getEmployeeDepartment().getPosition() : "", // Handle null position
			        emp.getEmployeeInfo() != null ? emp.getEmployeeInfo().getCity() : "", // Handle null city
			        emp.getJoinDate()
			    ))
			    .collect(Collectors.toList());

		
		System.out.println(employeeDTOs);
		
		Map<String, Object> response = new HashMap<>();
		response.put("employees", employeeDTOs);
		response.put("totalPages", employee.getTotalPages());
		response.put("currentPage", employee.getNumber());
		
		System.out.println(employee.getContent());
		
		return response;
	}
}
