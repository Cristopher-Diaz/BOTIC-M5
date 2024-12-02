package com.edutecno.procesaconexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/m5_final_drilling_db";
	private static final String USER = "root";
	private static final String PASSWORD = "admin123";

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new SQLException("Database Driver not found!");
		}
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
