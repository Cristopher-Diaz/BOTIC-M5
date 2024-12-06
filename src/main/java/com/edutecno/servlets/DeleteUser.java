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
 * Servlet implementation class DeleteUser
 */
@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioDAO usuarioDAO = new UsuarioDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("deleteUser.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		System.out.println(userName);
		boolean existUserInDB = false;
		boolean isDeletedUser = false;

		try {
			// Comprobar si el usuario existe em la db
			existUserInDB = usuarioDAO.validateExistUser(userName);

		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		if (!existUserInDB) {
			// Usuario no existe
			request.setAttribute("alertVariant", "danger");
			request.setAttribute("alertMessage", "Error - ¡El usuario no existe en nuestros sistemas!");
			forwardToLogin(request, response);
		} else {

			isDeletedUser = usuarioDAO.deleteUser(userName);
		    if (isDeletedUser) {
		        // Redirigir a /Logout después de eliminar
		        response.sendRedirect(request.getContextPath() + "/Logout");
		    }

		}
	}
	
	// Método auxiliar para redirigir al login
	private void forwardToLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("deleteUser.jsp");
		dispatcher.forward(request, response);
	}

}
