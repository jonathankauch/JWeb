package com.jweb.beans;

import java.sql.Date;

import org.joda.time.DateTime;

public class News {

	private int id;
	private String title;
	private String article;
	private Date date;
	private String image;
	
	public News() {
		
	}
	
	public News(int id, String title, String article, Date dateTime,
			String image) {
		this.id = id;
		this.title = title;
		this.article = article;
		this.date = dateTime;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date2) {
		this.date = date2;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
