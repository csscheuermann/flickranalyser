package com.flickranalyser.html;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletCreateSpot extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			  throws IOException {
			    resp.sendRedirect("/flickranalyser.jsp");
			  }
	
	
}
