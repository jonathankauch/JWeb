package com.jweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jweb.beans.NewsletterList;
import com.jweb.beans.User;

public class DAONewsletterList {
	
	private DAOFactory daoFactory;
	private final static String SQL_INSERT_USER_ID = "insert into newsletterlist (id_user, email) values (?, ?);";
	
	public DAONewsletterList(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public boolean registerToNewsletterList(NewsletterList nl) {
		Connection co = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        User user = null;

        try {
        	co = daoFactory.getConnection();
        	DAOUser daoUser = new DAOUser(daoFactory);
        	user = daoUser.findUserByEmail(nl.getEmail());
        	daoUser.updateNewsletterList(user.getId());
            {
            	int id = user.getId();
	            ps = daoFactory.initQuery(co, SQL_INSERT_USER_ID, true, id, nl.getEmail());
	            int statut = ps.executeUpdate();
	            if (statut == 0) {
	                throw new DAOException( "Register email failed" );
	            }
	            res = ps.getGeneratedKeys();
	            if (res.next()) {
	            	nl.setId(res.getInt(1));
	            } else {
	                throw new DAOException("Add id failed, any auto-generated ID return ");
	            }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            daoFactory.closeAll(res, ps, co);
        }
        return true;
	}
}
