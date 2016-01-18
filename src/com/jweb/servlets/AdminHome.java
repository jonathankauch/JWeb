package com.jweb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jweb.beans.News;
import com.jweb.beans.Product;
import com.jweb.dao.DAOFactory;
import com.jweb.dao.DAONews;
import com.jweb.dao.DAOProduct;

public class AdminHome extends HttpServlet {
	
	private static final String FIELD_PRODUCTS = "products";
	private static final String FIELD_NEWS = "news";
	private static final String VIEW = "/WEB-INF/admin/home.jsp";
	private static final String CONFIG_DAO_FACTORY = "daoFactory";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}
}
