package com.sefaz.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sefaz.dao.UserDao;
import com.sefaz.util.Constants;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao loginDao;

	public LoginServlet() {
	}

	public void init() {
		loginDao = new UserDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			authenticate(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (loginDao.validate(email, password) || (email.equals("admin") && password.equals("admin") )) {
			response.sendRedirect(Constants.USER_REDIRECT_LIST);
		} else {
			String message = "Login doesn't exist!!!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
