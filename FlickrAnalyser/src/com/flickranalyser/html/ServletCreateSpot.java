package com.flickranalyser.html;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletCreateSpot extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {


		String url = "/flickranalyser.jsp";
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		
		//Call Logic
		
		//Set the Attributes (POJOS) for the JSP
		//req.setAttribute("accountList", POJO );
		
		
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}


	}


}
