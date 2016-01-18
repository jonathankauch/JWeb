package com.jweb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.Product;
import com.jweb.dao.DAOFactory;
import com.jweb.dao.DAOProduct;

public class AdminProducts extends HttpServlet {
	public static final String FIELD_PRODUCTS = "products";
	public static String VIEW = "/WEB-INF/admin/products.jsp";
	public static final String CONFIG_DAO_FACTORY = "daoFactory";

	private DAOProduct daoProduct;

	public void init() throws ServletException {
		daoProduct = ((DAOFactory) getServletContext().getAttribute(
				CONFIG_DAO_FACTORY)).getDAOProduct();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Product> list = daoProduct.getAllProduct();
		if (!list.isEmpty()) {
			request.setAttribute(FIELD_PRODUCTS, list);
		}
		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}
}
