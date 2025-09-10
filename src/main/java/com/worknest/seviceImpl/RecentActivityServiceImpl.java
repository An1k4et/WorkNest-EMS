package com.worknest.seviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.worknest.dto.RecentActivity;
import com.worknest.repository.RecentActivityRepository;
import com.worknest.service.RecentActivityService;

@Service
public class RecentActivityServiceImpl implements RecentActivityService{
	
	@Autowired
    private RecentActivityRepository repository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate; // for WebSocket push

    @Override
    public void addActivity(String text, String type) {
        RecentActivity activity = new RecentActivity();
        activity.setActivityText(text);
        activity.setActivityType(type);
        repository.save(activity);

        // Push update to WebSocket
        messagingTemplate.convertAndSend("/topic/activities", activity);
    }

    @Override
    public List<RecentActivity> getRecentActivities() {
        return repository.findTop10ByOrderByActivityTimeDesc();
    }
}
