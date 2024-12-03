package com.edutecno.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.edutecno.procesaconexion.DBConnection;

public class UsuarioDAO {
	public boolean getUserToLogin(String userName) throws SQLException {
		boolean existUserInDB = false;
		String sql = "SELECT u.user_name AS user_name FROM usuarios u WHERE u.user_name = '" + userName + "'";

		try (Connection conn = DBConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			if (rs.next()) {
				existUserInDB = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return existUserInDB;
	}
	
	public boolean validateUserCredentials(String userName, String password) throws SQLException {
	    boolean isValidUser = false;
	    String sql = """
	            SELECT
	                u.user_name AS user_name
	            FROM usuarios u
	            WHERE u.user_name = ?
	            AND u.password = ?
	            """;

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setString(1, userName);
	        preparedStatement.setString(2, password);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	            	isValidUser = true;
	            }
	        }
	    }

	    return isValidUser;
	}

	public boolean createUser(String name, String userName, String email, String birthDate, String password,
			String animal) {
		String sql = """
				    INSERT INTO usuarios (nombre, user_name, email, fecha_nacimiento, password, animal)
				    VALUES (?, ?, ?, ?, ?, ?)
				""";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, name);
			preparedStatement.setString(2, userName);
			preparedStatement.setString(3, email);
			preparedStatement.setDate(4, Date.valueOf(birthDate));
			preparedStatement.setString(5, password);
			preparedStatement.setString(6, animal);

			// Ejecutar la consulta y comprobar si se insertaron filas
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
