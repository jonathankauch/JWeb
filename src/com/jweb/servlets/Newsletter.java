package com.jweb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.dao.DAOFactory;
import com.jweb.dao.DAONewsletterList;
import com.jweb.forms.NewsletterListForm;

public class Newsletter extends HttpServlet {
	public static final String FIELD_FORM = "form";
	public static final String VIEW = "/WEB-INF/newsletter.jsp";
	public static final String CONFIG_DAO_FACTORY = "daoFactory";

	private DAONewsletterList daoNewsletterList; 
	
	public void init() throws ServletException {
		daoNewsletterList = ((DAOFactory) getServletContext().getAttribute(CONFIG_DAO_FACTORY)).getDAONewsletterList();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		NewsletterListForm f = new NewsletterListForm(daoNewsletterList);
		
		f.registerNewsletter(request);

		request.setAttribute(FIELD_FORM, f);

		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}
}