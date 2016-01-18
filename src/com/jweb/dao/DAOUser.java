package com.jweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jweb.beans.User;

public class DAOUser {

	private DAOFactory daoFactory;

	private static final String SQL_SELECT_ALL_USER = "SELECT * FROM User where id != 1 ORDER BY id";
	private static final String SQL_SELECT_PER_ID = "SELECT * FROM User WHERE id = ?;";
	private static final String SQL_UPDATE_USER = "update user set email = ?, password = ?, lastname = ?, firstname = ?, address = ? where id = ?;";
	private static final String SQL_INSERT_USER = "INSERT INTO User (email, password, lastname, firstname, address, date_inscription) VALUES (?, MD5(?), ?, ?, ?, NOW())";
	private static final String SQL_DELETE_PER_ID = "DELETE FROM User WHERE id = ?;";
	private final static String SQL_SEARCH_USER_BY_EMAIL = "select * from user where email = ?";
	private final static String UPDATE_USER_NEWSLETTER = "update user set newsletter = 1 where id = ?";
	private final static String SQL_CONNECT = "select * from user where email = ? and password = MD5(?);";

	public DAOUser(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void createUser(User user) throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;
		ResultSet res = null;

		try {
			co = daoFactory.getConnection();
			ps = daoFactory.initQuery(co, SQL_INSERT_USER, true,
					user.getEmail(), user.getPassword(), user.getLastName(),
					user.getFirstName(), user.getAddress());
			int statut = ps.executeUpdate();
			if (statut == 0) {
				throw new DAOException("Create user failed");
			}
			res = ps.getGeneratedKeys();
			if (res.next()) {
				user.setId(res.getInt(1));
			} else {
				throw new DAOException(
						"Create user failed, any auto generated ID return.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeAll(res, ps, co);
		}
	}

	public User connectUser(String email, String password) throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		User u = null;

		try {
			co = daoFactory.getConnection();
			ps = daoFactory.initQuery(co, SQL_CONNECT, false, email, password);
			res = ps.executeQuery();
			if (res.next()) {
				u = translateResultToUser(res);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeAll(res, ps, co);
		}
		return u;
	}

	public void supprUser(User user) throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;

		try {
			co = daoFactory.getConnection();
			ps = daoFactory
					.initQuery(co, SQL_DELETE_PER_ID, true, user.getId());
			int statut = ps.executeUpdate();
			if (statut == 0) {
				throw new DAOException(
						"Échec de la suppression du client, aucune ligne supprimée de la table.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeStatement(ps);
			daoFactory.closeConnexion(co);
		}
	}

	public void updateNewsletterList(int id) throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;

		try {
			co = daoFactory.getConnection();
			ps = daoFactory.initQuery(co, UPDATE_USER_NEWSLETTER, true, id);
			int statut = ps.executeUpdate();
			if (statut == 0) {
				throw new DAOException(
						"Échec update champs newsletter, aucune ligne supprimée de la table.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeStatement(ps);
			daoFactory.closeConnexion(co);
		}
	}

	public User findUser(int id) throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		User u = null;

		try {
			co = daoFactory.getConnection();
			ps = daoFactory.initQuery(co, SQL_SELECT_PER_ID, false, id);
			res = ps.executeQuery();
			if (res.next()) {
				u = translateResultToUser(res);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeAll(res, ps, co);
		}
		return u;
	}

	public User findUserByEmail(String email) throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		User u = null;

		try {
			co = daoFactory.getConnection();
			ps = daoFactory.initQuery(co, SQL_SEARCH_USER_BY_EMAIL, false,
					email);
			res = ps.executeQuery();
			if (res.next()) {
				u = translateResultToUser(res);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeAll(res, ps, co);
		}
		return u;
	}

	public User updateUser(User user) throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;
		ResultSet res = null;

		try {
			co = daoFactory.getConnection();
			ps = daoFactory.initQuery(co, SQL_UPDATE_USER, true,
					user.getEmail(), user.getPassword(), user.getLastName(),
					user.getFirstName(), user.getAddress(), user.getId());
			int statut = ps.executeUpdate();
			if (statut == 0) {
				throw new DAOException(
						"Échec de l'edition du client, aucune ligne update de la table.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeStatement(ps);
			daoFactory.closeConnexion(co);
		}
		return user;
	}

	public List<User> getAllUser() throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		List<User> userList = new ArrayList<User>();

		try {
			co = daoFactory.getConnection();
			ps = co.prepareStatement(SQL_SELECT_ALL_USER);
			res = ps.executeQuery();
			while (res.next()) {
				userList.add(translateResultToUser(res));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeAll(res, ps, co);
		}
		return userList;
	}

	public static User translateResultToUser(ResultSet res) throws SQLException {
		User user = new User();
		user.setId(res.getInt("id"));
		user.setEmail(res.getString("email"));
		user.setPassword(res.getString("password"));
		user.setLastName(res.getString("lastname"));
		user.setFirstName(res.getString("firstname"));
		user.setDateInscription(res.getDate("date_inscription"));
		user.setAddress(res.getString("address"));
		return user;
	}

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
}
