package com.jweb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.News;
import com.jweb.dao.DAOFactory;
import com.jweb.dao.DAONews;

public class NewsPage extends HttpServlet {
	public static final String FIELD_NEWS = "news";
	public static String VIEW = "/WEB-INF/newsPage.jsp";
	public static final String CONFIG_DAO_FACTORY = "daoFactory";

	private DAONews daoNews;

	public void init() throws ServletException {
		daoNews = ((DAOFactory) getServletContext().getAttribute(
				CONFIG_DAO_FACTORY)).getDAONews();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<News> list = daoNews.getAllNews();
		if (!list.isEmpty()) {
			request.setAttribute(FIELD_NEWS, list);
		}
		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}
}
