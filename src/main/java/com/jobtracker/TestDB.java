package com.jobtracker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDB {
    public static void main(String[] args) throws Exception {
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM applications")) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("company")
                        + " | " + rs.getString("role") + " | " + rs.getString("status"));
            }
        }
    }
}