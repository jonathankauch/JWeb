package com.jweb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.User;
import com.jweb.dao.DAOFactory;
import com.jweb.dao.DAOUser;

public class AdminUsers extends HttpServlet {
	public static final String FIELD_USERS = "users";
	public static String VIEW = "/WEB-INF/admin/users.jsp";
	public static final String CONFIG_DAO_FACTORY = "daoFactory";

	private DAOUser daoUser;

	public void init() throws ServletException {
		daoUser = ((DAOFactory) getServletContext().getAttribute(
				CONFIG_DAO_FACTORY)).getDAOUser();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<User> list = daoUser.getAllUser();
		if (!list.isEmpty()) {
			request.setAttribute(FIELD_USERS, list);
		}
		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}
}
