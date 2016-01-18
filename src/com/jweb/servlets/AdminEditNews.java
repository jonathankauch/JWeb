package com.jweb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.News;
import com.jweb.dao.DAOFactory;
import com.jweb.dao.DAONews;
import com.jweb.forms.NewsForm;

public class AdminEditNews extends HttpServlet {

	public static final String FIELD_NEWS = "n";
	private static final String FIELD_FORM = "form";
	public static String VIEW = "/WEB-INF/admin/editNews.jsp";
	public static final String CONFIG_DAO_FACTORY = "daoFactory";
	private static final String PATH_FILE = "path";

	private DAONews daoNews;
	private int id;

	public void init() throws ServletException {
		daoNews = ((DAOFactory) getServletContext().getAttribute(
				CONFIG_DAO_FACTORY)).getDAONews();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		News news = daoNews.findNews(Integer.valueOf(request
				.getParameter("id")));
		id = news.getId();
		if (news != null) {
			request.setAttribute(FIELD_NEWS, news);
		}
		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = this.getServletConfig().getInitParameter(PATH_FILE);

		NewsForm f = new NewsForm(daoNews);
		News n = f.updateNews(id, request, response, path);

		request.setAttribute(FIELD_FORM, f);
		request.setAttribute(FIELD_NEWS, n);

		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}
}
