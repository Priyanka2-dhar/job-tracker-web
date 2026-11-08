package com.jobtracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {

    public List<JobApplication> getAll() throws Exception {
        List<JobApplication> list = new ArrayList<>();
        String sql = "SELECT id, company, role, applied_date, status, follow_up_date "
                + "FROM applications ORDER BY id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new JobApplication(
                        rs.getInt("id"),
                        rs.getString("company"),
                        rs.getString("role"),
                        rs.getDate("applied_date").toLocalDate(),
                        rs.getString("status"),
                        rs.getDate("follow_up_date").toLocalDate()));
            }
        }
        return list;
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