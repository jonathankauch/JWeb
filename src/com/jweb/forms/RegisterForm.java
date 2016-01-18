package com.jweb.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jweb.beans.User;
import com.jweb.dao.DAOUser;

public class RegisterForm {
	private static final String FIELD_EMAIL = "email";
	private static final String FIELD_PASSWORD = "password";
	private static final String FIELD_CHECKPASSWORD = "checkPassword";
	private static final String FIELD_LASTNAME = "lastname";
	private static final String FIELD_FIRSTNAME = "firstname";
	private static final String FIELD_ADDRESS = "address";
	
	private String result;
	private Map<String, String> error;
	private DAOUser daoUser;
	
	public RegisterForm(DAOUser daoUser) {
		error = new HashMap<String, String>();
		this.daoUser = daoUser;
	}

	public User registerUser(HttpServletRequest request) {
		String email = getField(request, FIELD_EMAIL);
		String password = getField(request, FIELD_PASSWORD);
		String checkPassword = getField(request, FIELD_CHECKPASSWORD);
		String lastname = getField(request, FIELD_LASTNAME);
		String firstname = getField(request, FIELD_FIRSTNAME);
		String address = getField(request, FIELD_ADDRESS);
		
		try {
			emailIsValid(email);
		} catch (Exception e) {
			error.put(FIELD_EMAIL, e.getMessage());
		}

		try {
			passwordIsValid(password, checkPassword);
		} catch (Exception e) {
			error.put(FIELD_PASSWORD, e.getMessage());
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

		if (error.isEmpty()) {
			result = "Success you are registered";
			daoUser.createUser(user);
		} else {
			result = "Register failure";
		}

		return user;
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

	private void passwordIsValid(String password, String checkPassword)
			throws Exception {
		if (password != null && password.trim().length() != 0
				&& checkPassword != null && checkPassword.trim().length() != 0) {
			if (!password.equals(checkPassword)) {
				throw new Exception(
						"Password and confirmation is different, try again ty");
			} else if (password.trim().length() < 3) {
				throw new Exception(
						"Password must contain at least 3 characters");
			}
		} else {
			throw new Exception(
					"Password and confirmation is different, try again");
		}
	}

	private void nameFieldIsValid(String field) throws Exception {
		if (field != null && field.trim().length() < 3) {
			throw new Exception(
					"The username must contain at least 3 characters");
		}
	}
	
	private void addressIsValid(String field) throws Exception {
		if (field != null && field.trim().length() < 10) {
			throw new Exception("The address is too short");
		}
	}
	
	private String getField(HttpServletRequest request, String fieldName) {
	    String value = request.getParameter(fieldName);
	    if (value == null || value.trim().length() == 0 ) {
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
	
	public void setError(Map<String, String> err) {
		error = err;
	}
}
