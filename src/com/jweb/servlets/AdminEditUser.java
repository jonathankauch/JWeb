package com.jweb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.User;
import com.jweb.dao.DAOFactory;
import com.jweb.dao.DAOUser;
import com.jweb.forms.UserForm;

public class AdminEditUser extends HttpServlet {

	public static final String FIELD_USER = "u";
	private static final String FIELD_FORM = "form";
	public static String VIEW = "/WEB-INF/admin/editUser.jsp";
	public static final String CONFIG_DAO_FACTORY = "daoFactory";
	private static final String PATH_FILE = "path";

	private DAOUser daoUser;
	private int id;

	public void init() throws ServletException {
		daoUser = ((DAOFactory) getServletContext().getAttribute(
				CONFIG_DAO_FACTORY)).getDAOUser();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = daoUser.findUser(Integer.valueOf(request
				.getParameter("id")));
		id = user.getId();
		if (user != null) {
			request.setAttribute(FIELD_USER, user);
		}
		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserForm f = new UserForm(daoUser);
		User u = f.updateUser(id, request);

		request.setAttribute(FIELD_FORM, f);
		request.setAttribute(FIELD_USER, u);

		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}
}
