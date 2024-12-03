package com.edutecno.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.edutecno.dao.HoroscopoDAO;
import com.edutecno.dao.UsuarioDAO;
import com.edutecno.modelo.Horoscopo;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	HoroscopoDAO horoscopoDAO = new HoroscopoDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", null);
		RequestDispatcher dispatcher = request.getRequestDispatcher("createUser.jsp");
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
		String rePassword = request.getParameter("rePassword");
		System.out.println(name);
		System.out.println(email);
		System.out.println(userName);
		System.out.println(birthDate);
		System.out.println(password);
		System.out.println(rePassword);

        // Calcular el animal basado en la fecha de nacimiento
        List<Horoscopo> listaHoroscopo = null;
        try {
            listaHoroscopo = horoscopoDAO.obtenerHoroscopo();
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al obtener los signos del horóscopo desde la base de datos.");
            request.setAttribute("successMessage", null);
            RequestDispatcher dispatcher = request.getRequestDispatcher("createUser.jsp");
            dispatcher.forward(request, response);
            return; // Retornar de una si falla la consulta de horóscopo
        }

        String animal = null;
        try {
            animal = determinarAnimal(birthDate, listaHoroscopo);
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("successMessage", null);
            RequestDispatcher dispatcher = request.getRequestDispatcher("createUser.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Enviar los datos al DAO para crear el usuario, el DAO responde con un boolean
        boolean isCreated = false;
        isCreated = usuarioDAO.createUser(name, userName, email, birthDate, password, animal);

        // Manejo de alerta del JSP
        if (isCreated) {
            request.setAttribute("successMessage", "Usuario creado correctamente");
            request.setAttribute("errorMessage", null);
        } else {
            request.setAttribute("successMessage", null);
            request.setAttribute("errorMessage", "Error al crear el usuario. Intente nuevamente.");
        }

        // Redirigir al JSP de resultados
        RequestDispatcher dispatcher = request.getRequestDispatcher("createUser.jsp");
        dispatcher.forward(request, response);
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
