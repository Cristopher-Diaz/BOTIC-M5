package com.edutecno.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		System.out.println(userName);
		String password = request.getParameter("password");
		System.out.println(password);
		
		boolean existUserInDB = false;
		try {
			existUserInDB = usuarioDAO.getUserToLogin(userName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		boolean isValidUser = false;
		try {
			isValidUser = usuarioDAO.validateUserCredentials(userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println("Existe usuario en la DB");
//		System.out.println(existUserInDB);
//		
//		System.out.println("El usuario esta logeado de forma correcta");
//		System.out.println(isValidUser);
		
		if (!existUserInDB) {
			System.out.println("El usuario no existe en la DB");
			return;
		}
		
		if (existUserInDB && !isValidUser) {
			System.out.println("Contraseña incorrecta");
			return;
		}
		
		if(existUserInDB && isValidUser) {
			System.out.println("El usuario esta logeado de forma correcta");
		}
		
	}

}
