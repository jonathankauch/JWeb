package com.jweb.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jweb.beans.User;
import com.jweb.dao.DAOUser;

public final class ConnexionForm {
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_PASSWORD = "password";

    private String              result;
    private Map<String, String> error;
    private DAOUser daoUser;
    
    public ConnexionForm(DAOUser daoUser) {
    	error = new HashMap<String, String>();
    	this.daoUser = daoUser;
    }

    public User connectUser(HttpServletRequest request) {
        String email = getField(request, FIELD_EMAIL);
        String password = getField(request, FIELD_PASSWORD);

        try {
            emailIsValid(email);
        } catch (Exception e) {
            error.put(FIELD_EMAIL, e.getMessage());
        }
        try {
        	passwordIsValid(password);
        } catch (Exception e) {
        	error.put(FIELD_PASSWORD, e.getMessage());
        }
        
        User user = daoUser.connectUser(email, password);
        if (user == null) {
        	error.put("check", "You are already connected, or your account doesn't exist");
        }

        if (error.isEmpty()) {
            result = "Connection success !";
        } else {
            result = "Connection fail !";
        }

        return user;
    }
    
    public User connectAdminUser(HttpServletRequest request) {
        String email = getField(request, FIELD_EMAIL);
        String password = getField(request, FIELD_PASSWORD);

        try {
            emailIsValid(email);
        } catch (Exception e) {
            error.put(FIELD_EMAIL, e.getMessage());
        }
        try {
        	passwordIsValid(password);
        } catch (Exception e) {
        	error.put(FIELD_PASSWORD, e.getMessage());
        }
        
        User user = null;
        if (email.equals("admin@admin.fr") && password.equals("admin")) {
            user = daoUser.connectUser(email, password);
        } else {
        	return null;
        }
        if (user == null) {
        	error.put("check", "You are already connected, or your account doesn't exist");
        }

        if (error.isEmpty()) {
            result = "Connection success !";
        } else {
            result = "Connection fail !";
        }

        return user;
    }

    private void emailIsValid(String email) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Please enter a valid email" );
        }
    }

    private void passwordIsValid(String password) throws Exception {
        if (password != null ) {
            if (password.length() < 3 ) {
                throw new Exception( "Password must contain at least 3 characters" );
            }
        } else {
            throw new Exception( "Password fail, try again" );
        }
    }

    private static String getField(HttpServletRequest request, String fieldName) {
        String value = request.getParameter(fieldName);
        if (value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value;
        }
    }
    
    public String getResult() {
        return result;
    }

    public Map<String, String> getError() {
        return error;
    }
}