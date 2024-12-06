package com.edutecno.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.edutecno.modelo.Horoscopo;
import com.edutecno.modelo.Usuario;
import com.edutecno.procesaconexion.DBConnection;

public class UsuarioDAO {
	public boolean validateExistUser(String userName) throws SQLException {
		boolean existUserInDB = false;
		String sql = "SELECT u.user_name AS user_name FROM usuarios u WHERE u.user_name = '" + userName + "' AND u.deleted_at IS NULL";

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

	public static Usuario getUser(String userName) throws SQLException {
		String sql = """
				SELECT *
				FROM usuarios u
				WHERE u.user_name = ?
				""";
		Usuario user = null;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, userName);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int id = resultSet.getInt("id");
					String nombre = resultSet.getString("nombre");
					String user_name = resultSet.getString("user_name");
					String email = resultSet.getString("email");
					LocalDate fechaNacimiento = null;
					if (resultSet.getDate("fecha_nacimiento") != null) {
						fechaNacimiento = resultSet.getDate("fecha_nacimiento").toLocalDate();
					}
					String password = resultSet.getString("password");
					String animal = resultSet.getString("animal");

					user = new Usuario(id, nombre, user_name, email, fechaNacimiento, password, animal);
				}
			}
		}

		return user;
	}

	public static ArrayList getAllUsers() {
		String sql = """
				SELECT *
				FROM usuarios u
				WHERE u.deleted_at IS null
				""";
		ArrayList<Usuario> usuarios = new ArrayList<>();

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nombre = resultSet.getString("nombre");
				String userName = resultSet.getString("user_name");
				String email = resultSet.getString("email");
				LocalDate fechaNacimiento = null;
				if (resultSet.getDate("fecha_nacimiento") != null) {
					fechaNacimiento = resultSet.getDate("fecha_nacimiento").toLocalDate();
				}
				String password = resultSet.getString("password");
				String animal = resultSet.getString("animal");

				Usuario usuario = new Usuario(id, nombre, userName, email, fechaNacimiento, password, animal);
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
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
	
	public boolean updateUser(Usuario user) throws SQLException {
	    String sql = """
	        UPDATE usuarios
	        SET nombre = ?, email = ?, user_name = ?, fecha_nacimiento = ?, password = ?, animal = ?
	        WHERE user_name = ? AND deleted_at IS NULL
	    """;

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	        preparedStatement.setString(1, user.getNombre());
	        preparedStatement.setString(2, user.getEmail());
	        preparedStatement.setString(3, user.getUsername());
	        preparedStatement.setDate(4, java.sql.Date.valueOf(user.getFechaNacimiento()));
	        preparedStatement.setString(5, user.getPassword());
	        preparedStatement.setString(6, user.getAnimal());
	        preparedStatement.setString(7, user.getUsername()); // Para el filtro en WHERE

	        int rowsAffected = preparedStatement.executeUpdate();
	        return rowsAffected > 0;
	    }
	}


	public boolean deleteUser(String userName) {
		String sql = """
				    UPDATE usuarios
				    SET deleted_at = NOW()
				    WHERE user_name = ? AND deleted_at IS NULL
				""";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, userName);

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0; 
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // En caso de error, retorna false
		}
	}
}
