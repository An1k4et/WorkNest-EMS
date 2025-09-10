package com.worknest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worknest.dto.RecentActivity;

@Service
public interface RecentActivityService {

	public void addActivity(String text, String type);
	
	public List<RecentActivity> getRecentActivities();
}
