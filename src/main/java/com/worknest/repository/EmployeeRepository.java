package com.worknest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.worknest.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findByEmail(String email);

	@Query("SELECT e.employeeId FROM Employee e ORDER BY e.employeeId DESC")
    List<String> findLastEmpIdList(Pageable pageable);

    default String findLastEmpId() {
        List<String> list = findLastEmpIdList(PageRequest.of(0, 1));
        return list.isEmpty() ? null : list.get(0);
    }

    @Query("SELECT COUNT(e) FROM Employee e WHERE e.status = 'ACTIVE'")
	Long countActiveEmpToday();
    
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.status = 'ONLEAVE'")
    long countLeaveEmpToday();

}
