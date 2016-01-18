package com.jweb.forms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.jweb.beans.Product;
import com.jweb.dao.DAOProduct;

public class ProductForm {
	private static final String FIELD_NAME = "name";
	private static final String FIELD_IMAGE = "image";
	private static final String FIELD_OLD_IMAGE = "oldimage";
	private static final String FIELD_PRICE = "price";
	private static final String FIELD_DESCRIPTION = "description";

	private String result;
	private Map<String, String> err;
	private DAOProduct daoProduct;

	public ProductForm(DAOProduct d) {
		err = new HashMap<String, String>();
		daoProduct = d;
	}

	public Product addProduct(HttpServletRequest request,
			HttpServletResponse response, String path) {
		String name = request.getParameter(FIELD_NAME);
		String price = request.getParameter(FIELD_PRICE);
		String description = request.getParameter(FIELD_DESCRIPTION);

		try {
			nameFieldIsValid(name);
		} catch (Exception e) {
			err.put(FIELD_NAME, e.getMessage());
		}

		String fileName = "";
		try {
			fileName = imageIsValid(request, response, path);
		} catch (Exception e) {
			err.put(FIELD_IMAGE, "Error file");
		}

		Product n = new Product(name, description, price);
		n.setImage(fileName);

		if (err.isEmpty()) {
			result = "Success";
			daoProduct.createProduct(n);
		} else {
			result = "Failure";
		}
		return n;
	}

	public Product updateProduct(int id, HttpServletRequest request,
			HttpServletResponse response, String path) {
		String name = request.getParameter(FIELD_NAME);
		String img = request.getParameter(FIELD_OLD_IMAGE);
		String price = request.getParameter(FIELD_PRICE);
		String description = request.getParameter(FIELD_DESCRIPTION);

		String fileName = "";
		try {
			fileName = imageIsValid(request, response, path);
		} catch (Exception e) {
			//e.printStackTrace();
		}

		Product n = new Product(name, description, price);
		if (fileName == null)
			n.setImage(img);
		else
			n.setImage(fileName);
		n.setId(id);

		if (err.isEmpty()) {
			result = "Success";
			daoProduct.updateProduct(n);
		} else {
			result = "Failure";
		}
		return n;
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
		String value = (String) request.getAttribute(fieldName);
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

	public Map<String, String> getErr() {
		return err;
	}

	public void setErr(Map<String, String> err) {
		this.err = err;
	}
}
