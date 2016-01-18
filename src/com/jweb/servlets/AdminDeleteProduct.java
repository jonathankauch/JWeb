package com.jweb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.Product;
import com.jweb.dao.DAOFactory;
import com.jweb.dao.DAOProduct;

public class AdminDeleteProduct extends HttpServlet {
	public static final String FIELD_RES = "res";
	public static String VIEW = "/WEB-INF/admin/deleteProduct.jsp";
	public static final String CONFIG_DAO_FACTORY = "daoFactory";

	private DAOProduct daoProduct;

	public void init() throws ServletException {
		daoProduct = ((DAOFactory) getServletContext().getAttribute(
				CONFIG_DAO_FACTORY)).getDAOProduct();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Product p = daoProduct.findProduct(Integer.valueOf(request.getParameter("id")));
		if (p != null) {
			daoProduct.supprProduct(p);
			request.setAttribute(FIELD_RES, "Delete success");
		} else {
			request.setAttribute("err", "Delete fail");
		}
		this.getServletContext().getRequestDispatcher(VIEW)
		.forward(request, response);
	}
}
