package com.flickranalyser.html;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flickranalyser.businesslogic.common.ParameterConstants;
import com.flickranalyser.businesslogic.filter.IFilterStrategy;
import com.flickranalyser.businesslogic.filter.impl.DoNotFilterStrategy;
import com.flickranalyser.businesslogic.filter.impl.ManyViewsAndFewPOIsFilter;
import com.flickranalyser.businesslogic.impl.SecretPlacesFacade;
import com.flickranalyser.memcache.MemcacheSpot;
import com.flickranalyser.pojo.Spot;

public class ServletCreateSpot extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(ServletCreateSpot.class.getName());
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {


		//GET THE PARAMS
		String location = req.getParameter(ParameterConstants.REQUEST_PARAM_LOCATION);
		
		Spot spot = MemcacheSpot.getSpotForSpotName(location);
		
		
		
		String url = "/flickranalyser.jsp";
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);

		
		//Set the Attributes (POJOS) for the JSP
		req.setAttribute("spot", spot );
		
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}


	}


}
