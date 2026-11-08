package com.jobtracker;

import java.time.LocalDate;

public class JobApplication {
    private int id;
    private String company;
    private String role;
    private LocalDate appliedDate;
    private String status;
    private LocalDate followUpDate;

    public JobApplication(int id, String company, String role,
                          LocalDate appliedDate, String status, LocalDate followUpDate) {
        this.id = id;
        this.company = company;
        this.role = role;
        this.appliedDate = appliedDate;
        this.status = status;
        this.followUpDate = followUpDate;
    }

    public int getId() { return id; }
    public String getCompany() { return company; }
    public String getRole() { return role; }
    public LocalDate getAppliedDate() { return appliedDate; }
    public String getStatus() { return status; }
    public LocalDate getFollowUpDate() { return followUpDate; }

    // True when the application is still open and its follow-up date has passed
    public boolean isOverdue() {
        boolean open = "Applied".equals(status) || "Interview".equals(status);
        return open && followUpDate.isBefore(LocalDate.now());
    }
}