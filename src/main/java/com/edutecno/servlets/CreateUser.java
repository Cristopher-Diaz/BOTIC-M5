package com.edutecno.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("errorMessage", null);
		RequestDispatcher dispatcher = request.getRequestDispatcher("createUser.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
        if (password == null || rePassword == null || !password.equals(rePassword)) {
            request.setAttribute("errorMessage", "Las contraseñas no coinciden. Por favor, inténtelo de nuevo.");
    		RequestDispatcher dispatcher = request.getRequestDispatcher("createUser.jsp");
    		dispatcher.forward(request, response);
        }
	}

}
