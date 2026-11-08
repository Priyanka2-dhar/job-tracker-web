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
}