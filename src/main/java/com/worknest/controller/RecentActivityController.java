package com.worknest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worknest.dto.RecentActivity;
import com.worknest.service.RecentActivityService;

@RestController
@RequestMapping("/api/activities")
public class RecentActivityController {

    @Autowired
    private RecentActivityService service;

    @GetMapping
    public List<RecentActivity> getRecentActivities() {
        return service.getRecentActivities();
    }

    // Example: trigger manually (you will call from employee/leave services later)
    @PostMapping("/add")
    public void addActivity(@RequestBody Map<String, String> req) {
        service.addActivity(req.get("text"), req.get("type"));
    }
}
