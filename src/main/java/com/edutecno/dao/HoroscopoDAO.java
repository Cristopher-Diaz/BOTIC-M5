package com.edutecno.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.edutecno.modelo.Horoscopo;
import com.edutecno.procesaconexion.DBConnection;

public class HoroscopoDAO {
	public List<Horoscopo> obtenerHoroscopo() throws SQLException {
	    List<Horoscopo> listaHoroscopo = new ArrayList<>();

	    String sql = "SELECT animal, fecha_inicio, fecha_fin FROM horoscopo";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql);
	         ResultSet resultSet = preparedStatement.executeQuery()) {

	        while (resultSet.next()) {
	            String animal = resultSet.getString("animal");
	            LocalDate fechaInicio = resultSet.getDate("fecha_inicio").toLocalDate();
	            LocalDate fechaFin = resultSet.getDate("fecha_fin").toLocalDate();
	            listaHoroscopo.add(new Horoscopo(animal, fechaInicio, fechaFin));
	        }
	    }

	    return listaHoroscopo;
	}
}
