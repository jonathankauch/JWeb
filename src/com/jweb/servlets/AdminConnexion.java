package com.jweb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jweb.beans.User;
import com.jweb.dao.DAOFactory;
import com.jweb.dao.DAOUser;
import com.jweb.forms.ConnexionForm;

public class AdminConnexion extends HttpServlet {
	private static final String FIELD_USER = "user";
	private static final String FIELD_FORM = "form";
	private static final String FIELD_SESSION_USER = "sessionAdmin";
	private static final String VIEW = "/WEB-INF/admin/connexion.jsp";
	private static final String CONFIG_DAO_FACTORY = "daoFactory";
	
	private DAOUser daoUser;
	
	public void init() throws ServletException {
		daoUser = ((DAOFactory) getServletContext().getAttribute(CONFIG_DAO_FACTORY)).getDAOUser();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ConnexionForm form = new ConnexionForm(daoUser);

		User user = form.connectAdminUser(request);

		HttpSession session = request.getSession();

		if (form.getError().isEmpty()) {
			session.setAttribute(FIELD_SESSION_USER, user);
		} else {
			session.setAttribute(FIELD_SESSION_USER, null);
		}

		request.setAttribute(FIELD_FORM, form);
			this.getServletContext().getRequestDispatcher(VIEW)
			.forward(request, response);
	}
}