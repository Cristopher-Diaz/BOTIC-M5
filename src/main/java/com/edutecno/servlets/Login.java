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

import com.edutecno.dao.UsuarioDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioDAO usuarioDAO = new UsuarioDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtener la sesión
		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("userName") != null) {
			// Si hay sesión activa, redirigir al panel
			RequestDispatcher dispatcher = request.getRequestDispatcher("panelView.jsp");
			dispatcher.forward(request, response);
		} else {
			// No hay sesión activa, forzar el inicio de sesión
			request.setAttribute("alertMessage", null);
			request.setAttribute("alertVariant", null);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

//		Con estas variables manejo los errores, si existe el use, clave incorrecta etc.
		boolean existUserInDB = false;
		boolean isValidUser = false;

		try {
			// Comprobar si el usuario existe em la db
			existUserInDB = usuarioDAO.getUserToLogin(userName);
			// Si el usuario existe, validar las credenciales
			if (existUserInDB) {
				isValidUser = usuarioDAO.validateUserCredentials(userName, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("alertVariant", "danger");
			request.setAttribute("alertMessage", "Error interno del sistema. Por favor, intente más tarde.");
			forwardToLogin(request, response);
			return;
		}

		if (!existUserInDB) {
			// Usuario no existe
			request.setAttribute("alertVariant", "danger");
			request.setAttribute("alertMessage", "Error - ¡El usuario no existe en nuestros sistemas!");
			forwardToLogin(request, response);
		} else if (!isValidUser) {
			// Contraseña incorrecta
			request.setAttribute("alertVariant", "warning");
			request.setAttribute("alertMessage", "Alerta - ¡La contraseña es incorrecta!");
			forwardToLogin(request, response);
		} else {
			// Usuario válido, se guarda la sesion
			HttpSession session = request.getSession(true);
			session.setAttribute("userName", userName);

			// Redirigir a la vista privada
			RequestDispatcher dispatcher = request.getRequestDispatcher("panelView.jsp");
			dispatcher.forward(request, response);
		}
	}

	// Método auxiliar para redirigir al login
	private void forwardToLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

}
