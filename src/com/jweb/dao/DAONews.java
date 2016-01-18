package com.jweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jweb.beans.News;

public class DAONews {

	private DAOFactory daoFactory;

	private static final String SQL_SELECT_ALL_NEWS = "SELECT * FROM news ORDER BY date desc";
	private static final String SQL_UPDATE_NEWS = "update news set title = ?, article = ?, image = ? where id = ?;";
	private static final String SQL_SELECT_NB_NEWS = "select * from news order by date desc limit ?;";
	private static final String SQL_SELECT_PER_ID = "SELECT * FROM news WHERE id = ?";
	private static final String SQL_INSERT_NEWS = "INSERT INTO news (title, article, image, date) VALUES (?, ?, ?, NOW())";
	private static final String SQL_DELETE_PER_ID = "DELETE FROM news WHERE id = ?";

	public DAONews(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void createNews(News news) throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;
		ResultSet res = null;

		try {
			co = daoFactory.getConnection();
			ps = daoFactory.initQuery(co, SQL_INSERT_NEWS, true, news.getTitle(), news.getArticle(), news.getImage());
			int statut = ps.executeUpdate();
			if (statut == 0) {
				throw new DAOException(
						"Create news failed.");
			}
			res = ps.getGeneratedKeys();
			if (res.next()) {
				news.setId(res.getInt(1));
			} else {
				throw new DAOException(
						"Create failed no id generated");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeAll(res, ps, co);
		}
	}
	
	public void updateNews(News news) throws DAOException {
		Connection co = null;
        PreparedStatement ps = null;

        try {
            co = daoFactory.getConnection();
            ps = daoFactory.initQuery(co, SQL_UPDATE_NEWS, true, news.getTitle(), news.getArticle(), news.getImage(), news.getId());
            int statut = ps.executeUpdate();
			if (statut == 0) {
				throw new DAOException(
						"Update news failed");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeStatement(ps);
			daoFactory.closeConnexion(co);
		}
	}

	public void supprNews(News news) throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;

		try {
			co = daoFactory.getConnection();
			ps = daoFactory
					.initQuery(co, SQL_DELETE_PER_ID, true, news.getId());
			int statut = ps.executeUpdate();
			if (statut == 0) {
				throw new DAOException(
						"Suppress news failed");
			} else {
				news.setId(-1);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeStatement(ps);
			daoFactory.closeConnexion(co);
		}
	}

	public News findNews(int id) throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		News u = null;

		try {
			co = daoFactory.getConnection();
			ps = daoFactory.initQuery(co, SQL_SELECT_PER_ID, false, id);
			res = ps.executeQuery();
			if (res.next()) {
				u = translateResultToNews(res);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeAll(res, ps, co);
		}
		return u;
	}
	
	public List<News> getListNews(int nb) throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		List<News> newsList = new ArrayList<News>();

		try {
			co = daoFactory.getConnection();
			ps = daoFactory.initQuery(co, SQL_SELECT_NB_NEWS, false, nb);
			res = ps.executeQuery();
			while (res.next()) {
				newsList.add(translateResultToNews(res));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeAll(res, ps, co);
		}
		return newsList;
	}

	public List<News> getAllNews() throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		List<News> newsList = new ArrayList<News>();

		try {
			co = daoFactory.getConnection();
			ps = co.prepareStatement(SQL_SELECT_ALL_NEWS);
			res = ps.executeQuery();
			while (res.next()) {
				newsList.add(translateResultToNews(res));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeAll(res, ps, co);
		}
		return newsList;
	}

	public static News translateResultToNews(ResultSet res) throws SQLException {
		News news = new News();
		news.setId(res.getInt("id"));
		news.setTitle(res.getString("title"));
		news.setArticle(res.getString("article"));
		news.setImage(res.getString("image"));
		news.setDate(res.getDate("date"));
		return news;
	}

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}
	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
}
