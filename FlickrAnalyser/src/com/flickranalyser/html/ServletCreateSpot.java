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
import com.flickranalyser.pojo.Spot;

public class ServletCreateSpot extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(ServletCreateSpot.class.getName());
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {


		//GET THE PARAMS
		String location = req.getParameter(ParameterConstants.REQUEST_PARAM_LOCATION);
		String strategy = req.getParameter(ParameterConstants.REQUEST_PARAM_FILTER_STRATEGY);
		String numberOfClusterString = req.getParameter(ParameterConstants.REQUEST_PARAM_NUMBER_OF_CLUSTER);
		int numberOfCluster = 500;
		try{
			numberOfCluster = Integer.parseInt(numberOfClusterString);	
		}catch(NumberFormatException e){
			log.log(Level.FINEST, "error while parsing number of clusters: " + numberOfCluster);
		}
		
		if(location == null || location.length() == 0){
			location = "munich";
		}
		if(strategy == null || strategy.length() == 0){
			location = "noFilter";
		}
		
		
		String url = "/flickranalyser.jsp";
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);

		
		IFilterStrategy filterStrategy;
		if(strategy.equalsIgnoreCase("ManyViewsAndFewPOIs")){
			filterStrategy = new ManyViewsAndFewPOIsFilter(numberOfCluster);
		}else{
			filterStrategy = new DoNotFilterStrategy();	
		}

		SecretPlacesFacade secretPlacesFacade = new SecretPlacesFacade(filterStrategy);

		
		Spot spotAttribute = secretPlacesFacade.getSpotInformationForName(location) ;

		//Set the Attributes (POJOS) for the JSP
		req.setAttribute("spot", spotAttribute );
		req.setAttribute(ParameterConstants.REQUEST_PARAM_LOCATION, location );
		req.setAttribute(ParameterConstants.REQUEST_PARAM_FILTER_STRATEGY, strategy );
		req.setAttribute(ParameterConstants.REQUEST_PARAM_NUMBER_OF_CLUSTER, numberOfCluster );
		
		
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}


	}


}
