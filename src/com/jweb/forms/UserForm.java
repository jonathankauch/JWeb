package com.jweb.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.Product;
import com.jweb.beans.User;
import com.jweb.dao.DAOUser;

public class UserForm {
	private static final String FIELD_EMAIL = "email";
	private static final String FIELD_PASSWORD = "password";
	private static final String FIELD_LASTNAME = "lastname";
	private static final String FIELD_FIRSTNAME = "firstname";
	private static final String FIELD_ADDRESS = "address";

	private String result;
	private Map<String, String> error;
	private DAOUser daoUser;

	public UserForm(DAOUser u) {
		error = new HashMap<String, String>();
		daoUser = u;
	}
	
	public User updateUser(int id, HttpServletRequest request) {
		String email = getField(request, FIELD_EMAIL);
		String password = getField(request, FIELD_PASSWORD);
		String lastname = getField(request, FIELD_LASTNAME);
		String firstname = getField(request, FIELD_FIRSTNAME);
		String address = getField(request, FIELD_ADDRESS);
		
		try {
			emailIsValid(email);
		} catch (Exception e) {
			error.put(FIELD_EMAIL, e.getMessage());
		}

		try {
			nameFieldIsValid(lastname);
		} catch (Exception e) {
			error.put(FIELD_LASTNAME, e.getMessage());
		}

		try {
			nameFieldIsValid(firstname);
		} catch (Exception e) {
			error.put(FIELD_FIRSTNAME, e.getMessage());
		}
		
		try {
			addressIsValid(address);
		} catch (Exception e) {
			error.put(FIELD_ADDRESS, e.getMessage());
		}
		
		User user = new User(email, password, lastname, firstname, address);
		user.setId(id);
		
		if (error.isEmpty()) {
			result = "Success";
			daoUser.updateUser(user);
		} else {
			result = "Failure";
		}
		return user;
	}
	
	private void nameFieldIsValid(String field) throws Exception {
		if (field != null && field.trim().length() < 3) {
			throw new Exception(
					"The username must contain at least 3 characters");
		}
	}
	
	private void emailIsValid(String email) throws Exception {
		if (email != null && email.trim().length() != 0) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Please enter a valid email");
			}
		} else {
			throw new Exception("Please enter a valid email");
		}
	}

	private void addressIsValid(String field) throws Exception {
		if (field != null && field.trim().length() < 10) {
			throw new Exception("The address is too short");
		}
	}

	private String getField(HttpServletRequest request, String fieldName) {
		String value = request.getParameter(fieldName);
		if (value == null || value.trim().length() == 0) {
			return null;
		} else {
			return value.trim();
		}
	}
	
    public String getResult() {
        return result;
    }
    
    public void setResult(String res) {
        result = res;
    }
    
    public Map<String, String> getError() {
        return error;
    }
    
    public void setError(Map<String, String> error) {
        this.error = error;
    }
}