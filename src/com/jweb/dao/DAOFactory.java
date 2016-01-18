package com.jweb.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DAOFactory {

    private static final String PROPERTIES_FILE          = "/com/jweb/dao/dao.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_USERNAME		 = "username";
    private static final String PROPERTY_PASSWORD        = "password";

    private String url;
    private String username;
    private String password;
        
    DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String username;
        String password;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

        if (propertiesFile == null ) {
            throw new DAOConfigurationException("The file : " + PROPERTIES_FILE + " not found.");
        }

        try {
            properties.load(propertiesFile);
            url = properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            username = properties.getProperty(PROPERTY_USERNAME);
            password = properties.getProperty(PROPERTY_PASSWORD);
        } catch (FileNotFoundException e) {
            throw new DAOConfigurationException("The file : " + PROPERTIES_FILE + " not found." , e);
        } catch (IOException e) {
            throw new DAOConfigurationException("The file cannot be load : " + PROPERTIES_FILE, e);
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException("Drive not found in the classpath", e);
        }

        DAOFactory instance = new DAOFactory(url, username, password);
        return instance;
    }
    
	public PreparedStatement initQuery(Connection connexion, String sql, boolean returnGeneratedKeys, Object... obj) throws SQLException {
	    PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
	    for (int i = 0; i < obj.length; i++)
	    	preparedStatement.setObject(i + 1, obj[i]);
	    return preparedStatement;
	}

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public DAOProduct getDAOProduct() {
    	return new DAOProduct(this);
    }
    
    public DAOUser getDAOUser() {
        return new DAOUser(this);
    }
    
    public DAONews getDAONews() {
    	return new DAONews(this);
    }
    
    public DAONewsletterList getDAONewsletterList() {
    	return new DAONewsletterList(this);
    }
    
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("ResultSet close failed: " + e.getMessage() );
            }
        }
    }

    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Statement close failed: " + e.getMessage());
            }
        }
    }

    public void closeConnexion( Connection connexion ) {
        if (connexion != null) {
            try {
                connexion.close();
            } catch (SQLException e) {
                System.out.println( "Connection close failed: " + e.getMessage());
            }
        }
    }

    public void closeAll(ResultSet resultSet, Statement statement, Connection connexion) {
    	closeResultSet(resultSet);
        closeStatement(statement);
        closeConnexion(connexion);
    }
}