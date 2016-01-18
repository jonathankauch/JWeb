package com.jweb.forms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.jweb.beans.News;
import com.jweb.beans.Product;
import com.jweb.dao.DAOException;
import com.jweb.dao.DAONews;

public class NewsForm {
	private static final String FIELD_TITLE = "title";
	private static final String FIELD_ARTICLE = "article";
	private static final String FIELD_IMAGE = "image";
	private static final String FIELD_OLDIMAGE = "oldimage";
		
	private String result;
	private Map<String, String> error;
	private DAONews daoNews;
	
	public NewsForm(DAONews daoNews) {
		error = new HashMap<String, String>();
		this.daoNews = daoNews;
	}
	
	public News updateNews(int id, HttpServletRequest request, HttpServletResponse response, String path) {
		String title = request.getParameter(FIELD_TITLE);
		String img = request.getParameter(FIELD_OLDIMAGE);
		String article = request.getParameter(FIELD_ARTICLE);
		
		try {
			nameFieldIsValid(title);
		} catch (Exception e) {
			error.put(FIELD_TITLE, e.getMessage());
		}

		String fileName = "";
		try {
			fileName = imageIsValid(request, response, path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		News n = new News();
		n.setTitle(title);
		n.setArticle(article);
		n.setId(id);

		if (fileName == null)
			n.setImage(img);
		else
			n.setImage(fileName);
		n.setId(id);

		if (error.isEmpty()) {
			result = "Success";
			daoNews.updateNews(n);
		} else {
			result = "Failure";
		}
		return n;
	}

	public News addNews(HttpServletRequest request, HttpServletResponse response, String path) {
		String title = request.getParameter(FIELD_TITLE);
		String article = request.getParameter(FIELD_ARTICLE);
		
		try {
			nameFieldIsValid(title);
		} catch (Exception e) {
			error.put(FIELD_TITLE, e.getMessage());
		}
		
		String fileName = "";
		try {
			fileName = imageIsValid(request, response, path);
		} catch (Exception e) {
			error.put(FIELD_IMAGE, "Error file");
		}
		
		News news = new News();
		news.setTitle(title);
		news.setArticle(article);
		news.setImage(fileName);

		if (error.isEmpty()) {
			result = "Success";
			daoNews.createNews(news);
		} else {
			result = "Failure";
		}
		return news;
	}

	private void nameFieldIsValid(String field) throws Exception {
		if (field != null && field.trim().length() < 3) {
			throw new Exception(
					"The username must contain at least 3 characters");
		}
	}
	
	private String imageIsValid(HttpServletRequest request,
			HttpServletResponse response, String path) throws Exception {
		final Part filePart = request.getPart(FIELD_IMAGE);
		final String fileName = getFileName(filePart);

		OutputStream out = null;
		InputStream filecontent = null;

		try {
			out = new FileOutputStream(new File(path + File.separator
					+ fileName));
			filecontent = filePart.getInputStream();

			int read = 0;
			final byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		} catch (FileNotFoundException fne) {
			return null;
		} finally {
			if (out != null) {
				out.close();
			}
			if (filecontent != null) {
				filecontent.close();
			}
		}
		return fileName;
	}

	private static String getFileName(Part part) {
		for (String contentDisposition : part.getHeader("content-disposition")
				.split(";")) {
			if (contentDisposition.trim().startsWith("filename")) {
				return contentDisposition
						.substring(contentDisposition.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
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
