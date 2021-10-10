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

import com.sefaz.dao.PhoneDao;
import com.sefaz.dao.UserDao;
import com.sefaz.model.Phone;
import com.sefaz.model.User;
import com.sefaz.util.Constants;

/**
 * Servlet implementation class PhoneServlet
 */
@WebServlet("/phone/*")
public class PhoneServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PhoneDao phoneDao;
	private UserDao userDao;

	public void init() {
		userDao = new UserDao();
		phoneDao = new PhoneDao();
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
				insertPhone(request, response);
				break;
			case Constants.DELETE_ACTION:
				deletePhone(request, response);
				break;
			case Constants.EDIT_ACTION:
				showEditForm(request, response);
				break;
			case Constants.UPDATE_ACTION:
				updatePhone(request, response);
				break;
			default:
				listPhone(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listPhone(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int userId = Integer.parseInt(request.getParameter(Constants.USER_ID_COL_NAME));
		User user = userDao.getUser(userId);

		List<Phone> listPhone = phoneDao.getByUserId(userId);
		request.setAttribute("listPhone", listPhone);
		request.setAttribute("user", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("list-phone.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter(Constants.USER_ID_COL_NAME));
		User user = userDao.getUser(userId);
		request.setAttribute("user", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("create-phone.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter(Constants.USER_ID_COL_NAME));
		int id = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));
		
		Phone selectPhone = phoneDao.getPhone(id);
		User selectedUser = userDao.getUser(userId);
		
		request.setAttribute("user", selectedUser);
		request.setAttribute("phone", selectPhone);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("create-phone.jsp");
		dispatcher.forward(request, response);

	}

	private void insertPhone(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int ddd = Integer.parseInt(request.getParameter(Constants.DDD_ID_COL_NAME));
		String number = request.getParameter(Constants.NUMBER_COL_NAME);
		String type = request.getParameter(Constants.TYPE_COL_NAME);
		int userId = Integer.parseInt(request.getParameter(Constants.USER_ID_COL_NAME));

		User user = userDao.getUser(userId);
		Phone newPhone = new Phone(0, ddd, number, type, user);

		phoneDao.save(newPhone);
		response.sendRedirect(request.getContextPath() + Constants.PHONE_REDIRECT_LIST + userId);
	}
	
	private void updatePhone(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));
		int userId = Integer.parseInt(request.getParameter(Constants.USER_ID_COL_NAME));
		
		int ddd = Integer.parseInt(request.getParameter(Constants.DDD_ID_COL_NAME));
		String number = request.getParameter(Constants.NUMBER_COL_NAME);
		String type = request.getParameter(Constants.TYPE_COL_NAME);
		
		Phone phone = phoneDao.getPhone(id);
		
		phone.setDdd(ddd);
		phone.setNumber(number);
		phone.setType(type);
		phoneDao.update(phone);
		response.sendRedirect(request.getContextPath() + Constants.PHONE_REDIRECT_LIST + userId);
	}
	
	private void deletePhone(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter(Constants.ID_COL_NAME));
		int userId = Integer.parseInt(request.getParameter(Constants.USER_ID_COL_NAME));
		phoneDao.delete(id);
		response.sendRedirect(request.getContextPath() + Constants.PHONE_REDIRECT_LIST + userId);
	}

}
