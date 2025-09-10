package com.worknest.service;

import java.util.Map;

public interface DashboardService {

	public Map<String, String> statsDashBoard();
	
	public Map<String, Object> getMonthlyStats();
}
