package com.jweb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.User;
import com.jweb.dao.DAOFactory;
import com.jweb.dao.DAOUser;
import com.jweb.forms.RegisterForm;

public class Register extends HttpServlet {

	public static final String VIEW = "/WEB-INF/register.jsp";
	public static final String FIELD_USER = "user";
	public static final String FIELD_FORM = "form";
	public static final String CONFIG_DAO_FACTORY = "daoFactory";

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
		
		RegisterForm f = new RegisterForm(daoUser);
		
		User u = f.registerUser(request);
		
		request.setAttribute(FIELD_FORM, f);
		request.setAttribute(FIELD_USER, u);

		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}
}