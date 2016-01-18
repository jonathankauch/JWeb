package com.jweb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.News;
import com.jweb.beans.Product;
import com.jweb.dao.DAOFactory;
import com.jweb.dao.DAOProduct;
import com.jweb.forms.NewsForm;
import com.jweb.forms.ProductForm;

public class AdminAddProduct extends HttpServlet {

	private static final String VIEW = "/WEB-INF/admin/addProduct.jsp";
	private static final String FIELD_PRODUCT = "p";
	private static final String FIELD_FORM = "form";
	private static final String CONFIG_DAO_FACTORY = "daoFactory";
	private static final String PATH_FILE = "path";

	private DAOProduct daoProduct;

	public void init() throws ServletException {
		daoProduct = ((DAOFactory) getServletContext().getAttribute(
				CONFIG_DAO_FACTORY)).getDAOProduct();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = this.getServletConfig().getInitParameter(PATH_FILE);
		
		ProductForm f = new ProductForm(daoProduct);

		Product p = f.addProduct(request, response, path);

		request.setAttribute(FIELD_FORM, f);
		request.setAttribute(FIELD_PRODUCT, p);

		this.getServletContext().getRequestDispatcher(VIEW)
		.forward(request, response);
	}
}
