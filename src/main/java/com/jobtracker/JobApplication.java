package com.jobtracker;

import java.time.LocalDate;

public class JobApplication {
    private int id;
    private String company;
    private String role;
    private LocalDate appliedDate;
    private String status;
    private LocalDate followUpDate;
    private String jobLink;
    private String location;
    private String notes;

    public JobApplication(int id, String company, String role, LocalDate appliedDate,
                          String status, LocalDate followUpDate,
                          String jobLink, String location, String notes) {
        this.id = id;
        this.company = company;
        this.role = role;
        this.appliedDate = appliedDate;
        this.status = status;
        this.followUpDate = followUpDate;
        this.jobLink = jobLink;
        this.location = location;
        this.notes = notes;
    }

    public int getId() { return id; }
    public String getCompany() { return company; }
    public String getRole() { return role; }
    public LocalDate getAppliedDate() { return appliedDate; }
    public String getStatus() { return status; }
    public LocalDate getFollowUpDate() { return followUpDate; }
    public String getJobLink() { return jobLink; }
    public String getLocation() { return location; }
    public String getNotes() { return notes; }

    // True when the application is still open and its follow-up date has passed
    public boolean isOverdue() {
        boolean open = "Applied".equals(status) || "Interview".equals(status);
        return open && followUpDate.isBefore(LocalDate.now());
    }
}