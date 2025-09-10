package com.worknest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.worknest.dto.LeaveStatsDTO;
import com.worknest.entity.LeaveRequest;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveRequest, Long>{
	
	@Query("SELECT COUNT(l) FROM LeaveRequest l WHERE l.status = 'PENDING'")
	long countPendingLeaveRequests();

	@Query(value = "SELECT DATE_FORMAT(STR_TO_DATE(l.start_date, '%Y-%m-%d'), '%b') AS month, " +
            "COUNT(DISTINCT l.employee_id) AS totalRequests, " +
            "SUM(CASE WHEN l.status = 'APPROVED' THEN 1 ELSE 0 END) AS approvedRequests " +
            "FROM leave_request l " +
            "GROUP BY MONTH(STR_TO_DATE(l.start_date, '%Y-%m-%d')), " +
            "DATE_FORMAT(STR_TO_DATE(l.start_date, '%Y-%m-%d'), '%b') " +
            "ORDER BY MONTH(STR_TO_DATE(l.start_date, '%Y-%m-%d'))",
    nativeQuery = true)
	List<Object[]> getMonthlyStatsNative();
	
	default List<LeaveStatsDTO> getMonthlyStats() {
	    return getMonthlyStatsNative().stream()
	        .map(row -> new LeaveStatsDTO(
	                (String) row[0],                // month
	                ((Number) row[1]).longValue(), // totalRequests
	                ((Number) row[2]).longValue()  // approvedRequests
	        ))
	        .toList();
	}
	
	List<LeaveRequest> findByDepartmentAndStatus(String department, LeaveRequest.LeaveStatus status);

	
}
