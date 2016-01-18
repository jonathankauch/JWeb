package com.jweb.beans;

import org.joda.time.DateTime;

public class Newsletter {

	private int id;
	private String title;
	private String article;
	private DateTime dateTime;
	private String image;
	
	public Newsletter(int id, String title, String article, DateTime dateTime,
			String image) {
		super();
		this.id = id;
		this.title = title;
		this.article = article;
		this.dateTime = dateTime;
		this.image = image;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public DateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
