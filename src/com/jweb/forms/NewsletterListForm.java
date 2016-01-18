package com.jweb.forms;

import javax.servlet.http.HttpServletRequest;

import com.jweb.beans.NewsletterList;
import com.jweb.dao.DAONewsletterList;

public final class NewsletterListForm {
    private static final String FIELD_EMAIL = "email";

    private String              result;
    private String				error;
    private DAONewsletterList daoNewsletterList;
    
	public NewsletterListForm(DAONewsletterList daoNewsletterList) {
		error = "";
		this.daoNewsletterList = daoNewsletterList;
	}

    public boolean registerNewsletter(HttpServletRequest request) {
        String email = getField(request, FIELD_EMAIL);

        try {
            emailIsValid(email);
        } catch (Exception e) {
            error = FIELD_EMAIL + e.getMessage();
        }

        NewsletterList nl = new NewsletterList();
        nl.setEmail(email);

        
        if (daoNewsletterList.registerToNewsletterList(nl)) {
        	result = "You are register now ENJOY IT !";
        	return true;
        }
        error = "Your already register to the newsletter list";
        return false;
    }

    private void emailIsValid(String email) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Please enter a valid email" );
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

    public String getError() {
        return error;
    }
}