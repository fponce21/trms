package com.francisco.trms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {
	public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/trms";
        String user = "postgres";
        String password = "Illisddts_1021";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL successfully!");
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }
}
