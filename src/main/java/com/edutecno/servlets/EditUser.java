package com.edutecno.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.edutecno.dao.HoroscopoDAO;
import com.edutecno.dao.UsuarioDAO;
import com.edutecno.modelo.Horoscopo;
import com.edutecno.modelo.Usuario;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	HoroscopoDAO horoscopoDAO = new HoroscopoDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String userName = (String) session.getAttribute("userName");
		Usuario user = null;
		try {
			user = UsuarioDAO.getUser(userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("userData", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("editUser.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		String birthDate = request.getParameter("birthDate");
		String password = request.getParameter("password");

		// Obtener el animal basado en la fecha de nacimiento
		List<Horoscopo> listaHoroscopo = null;
		try {
			listaHoroscopo = horoscopoDAO.obtenerHoroscopo();
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Error al obtener los signos del horóscopo desde la base de datos.");
			request.setAttribute("successMessage", null);
			doGet(request, response);
			return;
		}

		String animal = null;
		try {
			animal = determinarAnimal(birthDate, listaHoroscopo);
		} catch (IllegalArgumentException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute("successMessage", null);
			doGet(request, response);
			return;
		}

		// Crear objeto Usuario con los datos actualizados
		Usuario usuarioActualizado = new Usuario(0, name, userName, email, LocalDate.parse(birthDate), password,
				animal);

		// Actualizar el usuario en la base de datos
		boolean isUpdated = false;
		try {
			isUpdated = usuarioDAO.updateUser(usuarioActualizado);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Error al actualizar el usuario. Por favor, inténtelo nuevamente.");
			request.setAttribute("successMessage", null);
			doGet(request, response);
			return;
		}

		// Manejo de mensajes de éxito o error
		if (isUpdated) {
			request.setAttribute("successMessage", "Usuario actualizado correctamente.");
			request.setAttribute("errorMessage", null);
		} else {
			request.setAttribute("successMessage", null);
			request.setAttribute("errorMessage", "Error al actualizar el usuario. Por favor, inténtelo nuevamente.");
		}

		// Redirigir nuevamente al formulario de edición
		doGet(request, response);
	}

	private String determinarAnimal(String birthDate, List<Horoscopo> listaHoroscopo) {
		LocalDate fechaNacimiento = LocalDate.parse(birthDate); // Convertir la fecha a LocalDate

		for (Horoscopo horoscopo : listaHoroscopo) {
			// Verificar si la fecha de nacimiento está dentro del rango del horóscopo
			if (!fechaNacimiento.isBefore(horoscopo.getFechaInicio())
					&& !fechaNacimiento.isAfter(horoscopo.getFechaFin())) {
				return horoscopo.getAnimal(); // Retornar el animal correspondiente
			}
		}

		// Si no se encuentra un animal, lanzar una excepción o retornar un valor por
		// defecto
		throw new IllegalArgumentException("No se pudo determinar el animal para la fecha proporcionada.");
	}

}
