package com.jobtracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ApplicationDAO {

    private JobApplication mapRow(ResultSet rs) throws Exception {
        return new JobApplication(
                rs.getInt("id"),
                rs.getString("company"),
                rs.getString("role"),
                rs.getDate("applied_date").toLocalDate(),
                rs.getString("status"),
                rs.getDate("follow_up_date").toLocalDate());
    }

    public List<JobApplication> getAll() throws Exception {
        List<JobApplication> list = new ArrayList<>();
        String sql = "SELECT id, company, role, applied_date, status, follow_up_date "
                + "FROM applications ORDER BY id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    // Open applications (Applied / Interview) whose follow-up date is today or earlier
    public List<JobApplication> getFollowUps() throws Exception {
        List<JobApplication> list = new ArrayList<>();
        String sql = "SELECT id, company, role, applied_date, status, follow_up_date "
                + "FROM applications "
                + "WHERE status IN ('Applied', 'Interview') AND follow_up_date <= CURDATE() "
                + "ORDER BY follow_up_date";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    // How many applications are in each status
    public Map<String, Integer> getStatusCounts() throws Exception {
        Map<String, Integer> counts = new LinkedHashMap<>();
        counts.put("Applied", 0);
        counts.put("Interview", 0);
        counts.put("Offer", 0);
        counts.put("Rejected", 0);
        String sql = "SELECT status, COUNT(*) AS total FROM applications GROUP BY status";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                counts.put(rs.getString("status"), rs.getInt("total"));
            }
        }
        return counts;
    }

    public void add(String company, String role) throws Exception {
        String sql = "INSERT INTO applications (company, role, applied_date, status, follow_up_date) "
                + "VALUES (?, ?, CURDATE(), 'Applied', DATE_ADD(CURDATE(), INTERVAL 7 DAY))";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, company);
            ps.setString(2, role);
            ps.executeUpdate();
        }
    }

    public void updateStatus(int id, String status) throws Exception {
        String sql = "UPDATE applications SET status = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM applications WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}