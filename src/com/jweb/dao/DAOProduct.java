package com.jweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jweb.beans.Product;

public class DAOProduct {

	private DAOFactory daoFactory;

	private static final String SQL_SELECT_ALL_PRODUCT = "select * from product where 1 order by date desc;";
	private static final String SQL_UPDATE_PRODUCT = "update product set name = ?, description = ?, price = ?, image = ?, date = NOW() where id = ?;";
	private static final String SQL_SELECT_NB_PRODUCT = "select * from product order by date desc limit ?;";
	private static final String SQL_SELECT_PER_ID = "select * from product where id = ?";
	private static final String SQL_INSERT_PRODUCT = "insert into product(name, description, price, image, date) values (?, ?, ?, ?, NOW())";
	private static final String SQL_DELETE_PER_ID = "delete from product where id = ?";

	public DAOProduct(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void createProduct(Product product) throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;
		ResultSet res = null;

		try {
			co = daoFactory.getConnection();
			ps = daoFactory.initQuery(co, SQL_INSERT_PRODUCT, true,
					product.getName(), product.getDescription(),
					product.getPrice(), product.getImage());
			int statut = ps.executeUpdate();
			if (statut == 0) {
				throw new DAOException("Create product failed");
			}
			res = ps.getGeneratedKeys();
			if (res.next()) {
				product.setId(res.getInt(1));
			} else {
				throw new DAOException(
						"Create prodcut failed, any auto generate ID return.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeAll(res, ps, co);
		}
	}

	public void updateProduct(Product product) throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;

		try {
			co = daoFactory.getConnection();
			ps = daoFactory.initQuery(co, SQL_UPDATE_PRODUCT, true,
					product.getName(), product.getDescription(),
					product.getPrice(), product.getImage(), product.getId());
			int statut = ps.executeUpdate();
			if (statut == 0) {
				throw new DAOException("Update product failed.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeStatement(ps);
			daoFactory.closeConnexion(co);
		}
	}

	public Product findProduct(int id) throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		Product p = null;

		try {
			co = daoFactory.getConnection();
			ps = daoFactory.initQuery(co, SQL_SELECT_PER_ID, false, id);
			res = ps.executeQuery();
			if (res.next()) {
				p = translateResultToProduct(res);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeAll(res, ps, co);
		}
		return p;
	}

	public List<Product> getAllProduct() throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		List<Product> productList = new ArrayList<Product>();

		try {
			co = daoFactory.getConnection();
			ps = co.prepareStatement(SQL_SELECT_ALL_PRODUCT);
			res = ps.executeQuery();
			while (res.next()) {
				productList.add(translateResultToProduct(res));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeAll(res, ps, co);
		}
		return productList;
	}

	public List<Product> getListProduct(int nb) throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		List<Product> productList = new ArrayList<Product>();

		try {
			co = daoFactory.getConnection();
			ps = daoFactory.initQuery(co, SQL_SELECT_NB_PRODUCT, false, nb);
			res = ps.executeQuery();
			while (res.next()) {
				productList.add(translateResultToProduct(res));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeAll(res, ps, co);
		}
		return productList;
	}

	private Product translateResultToProduct(ResultSet res) throws SQLException {
		Product p = new Product();
		p.setId(res.getInt("id"));
		p.setName(res.getString("name"));
		p.setDescription(res.getString("description"));
		p.setPrice(res.getString("price"));
		p.setImage(res.getString("image"));
		return p;
	}

	public void supprProduct(Product product) throws DAOException {
		Connection co = null;
		PreparedStatement ps = null;

		try {
			co = daoFactory.getConnection();
			ps = daoFactory.initQuery(co, SQL_DELETE_PER_ID, true,
					product.getId());
			int statut = ps.executeUpdate();
			if (statut == 0) {
				throw new DAOException("Suppress product failed");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			daoFactory.closeStatement(ps);
			daoFactory.closeConnexion(co);
		}
	}

}
