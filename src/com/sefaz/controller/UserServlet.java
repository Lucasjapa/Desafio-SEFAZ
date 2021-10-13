package com.sefaz.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sefaz.dao.UserDao;
import com.sefaz.model.User;
import com.sefaz.util.Constants;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user/*")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	public void init() {
		userDao = new UserDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter(Constants.ACTION_KEY);

		try {
			switch (action) {
			case Constants.NEW_ACTION:
				showNewForm(request, response);
				break;
			case Constants.INSERT_ACTION:
				insertUser(request, response);
				break;
			case Constants.DELETE_ACTION:
				deleteUser(request, response);
				break;
			case Constants.EDIT_ACTION:
				showEditForm(request, response);
				break;
			case Constants.UPDATE_ACTION:
				updateUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = userDao.getAllUser();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.CREATE_USER_PAGE);
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));
		User existingUser = userDao.getUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.CREATE_USER_PAGE);
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String name = request.getParameter(Constants.NAME_COL_NAME);
		String email = request.getParameter(Constants.EMAIL_COL_NAME);
		String password = request.getParameter(Constants.PASSWORD_COL_NAME);
		User newUser = new User(0, name, email, password);
		
		if (userDao.validateEmail(email)) {
			userDao.save(newUser);
			response.sendRedirect(Constants.USER_REDIRECT_LIST);
		}else {
			String message = "Email exist!!!";
			request.setAttribute("message", message);
			request.getRequestDispatcher(Constants.CREATE_USER_PAGE).forward(request, response);
			System.out.println("ERROR:Email exist!!!");
		}
		
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));
		String name = request.getParameter(Constants.NAME_COL_NAME);
		String email = request.getParameter(Constants.EMAIL_COL_NAME);
		String password = request.getParameter(Constants.PASSWORD_COL_NAME);

		User user = new User(id, name, email, password);
		
		if (userDao.validateEmail(email)) {
			userDao.update(user);
			response.sendRedirect(Constants.USER_REDIRECT_LIST);
		}else {
			String message = "Email exist!!!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("user?action=edit&id=" + id).forward(request, response);
			System.out.println("ERROR:Email exist!!!");
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));
		userDao.delete(id);
		response.sendRedirect(Constants.USER_REDIRECT_LIST);
	}

}
