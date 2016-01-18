package com.jweb.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.jweb.dao.DAOFactory;

public class InitDAOFactory implements ServletContextListener {

    private static final String DAO_FACTORY = "daoFactory";

    private DAOFactory          daoFactory;

    @Override
    public void contextInitialized( ServletContextEvent event ) {
        ServletContext servletContext = event.getServletContext();
        this.daoFactory = DAOFactory.getInstance();
        servletContext.setAttribute(DAO_FACTORY, this.daoFactory);
    }

    @Override
    public void contextDestroyed( ServletContextEvent event ) {
    	// Do nothing here
    }
}