package com.jweb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.News;
import com.jweb.beans.Product;
import com.jweb.dao.DAOFactory;
import com.jweb.dao.DAONews;
import com.jweb.dao.DAOProduct;

public class Home extends HttpServlet {
	
	private static final String FIELD_PRODUCTS = "products";
	private static final String FIELD_NEWS = "news";
	private static final String VIEW = "/WEB-INF/home.jsp";
	private static final String CONFIG_DAO_FACTORY = "daoFactory";
	
	private DAONews daoNews;
	private DAOProduct daoProduct;
		
	public void init() throws ServletException {
		daoProduct = ((DAOFactory) getServletContext().getAttribute(
				CONFIG_DAO_FACTORY)).getDAOProduct();
		daoNews = ((DAOFactory) getServletContext().getAttribute(CONFIG_DAO_FACTORY)).getDAONews();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<News> newsList = daoNews.getListNews(3);
		if (!newsList.isEmpty()) {
			request.setAttribute(FIELD_NEWS, newsList);
		}
		
		List<Product> productList = daoProduct.getListProduct(3);
		if (!productList.isEmpty()) {
			request.setAttribute(FIELD_PRODUCTS, productList);
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp")
				.forward(request, response);
	}
}
