package com.jweb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.User;
import com.jweb.dao.DAOFactory;
import com.jweb.dao.DAOUser;

public class AdminDeleteUser extends HttpServlet {
	public static final String FIELD_RES = "res";
	public static String VIEW = "/WEB-INF/admin/deleteUser.jsp";
	public static final String CONFIG_DAO_FACTORY = "daoFactory";

	private DAOUser daoUser;

	public void init() throws ServletException {
		daoUser = ((DAOFactory) getServletContext().getAttribute(
				CONFIG_DAO_FACTORY)).getDAOUser();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User u = daoUser.findUser(Integer.valueOf(request.getParameter("id")));
		if (u != null) {
			daoUser.supprUser(u);
			request.setAttribute(FIELD_RES, "Delete success");
		} else {
			request.setAttribute("err", "Delete fail");
		}
		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}
}
