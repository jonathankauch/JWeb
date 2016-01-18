package com.jweb.beans;

import java.util.Date;

public class User {
	private int id;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private String address;
    private Date dateInscription;
    
    public User() {
    }

    public User(String email, String password) {
    	this.email = email;
    	this.password = password;
    }
    
    public User(String email, String password, String lastname, String firstname, String address) {
    	this.lastName = lastname;
    	this.firstName = firstname;
    	this.email = email;
    	this.password = password;
    	this.address = address;
    }
    
    public int getId() {
    	return id;
    }
    public void setId(int id) {
    	this.id = id;
    }
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String adress) {
		this.address = adress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    public String getPassword() {
    	return password;
    }
    public void setPassword(String pass) {
    	password = pass;
    }
    public Date getDateInscription() {
    	return dateInscription;
    }
    public void setDateInscription(Date date) {
    	this.dateInscription = date;
    }
}