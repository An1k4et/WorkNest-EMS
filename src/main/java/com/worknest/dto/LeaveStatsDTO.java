package com.worknest.dto;

public class LeaveStatsDTO {
    private String month;
    private Long totalRequests;
    private Long approvedRequests;

    //  Yeh constructor EXACTLY isi order aur type ka hona chahiye
    public LeaveStatsDTO(String month, Long totalRequests, Long approvedRequests) {
        this.month = month;
        this.totalRequests = totalRequests;
        this.approvedRequests = approvedRequests;
    }

    // Getters & Setters
    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }

    public Long getTotalRequests() { return totalRequests; }
    public void setTotalRequests(Long totalRequests) { this.totalRequests = totalRequests; }

    public Long getApprovedRequests() { return approvedRequests; }
    public void setApprovedRequests(Long approvedRequests) { this.approvedRequests = approvedRequests; }
}
