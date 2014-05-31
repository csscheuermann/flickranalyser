package com.flickranalyser.html;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flickranalyser.businesslogic.SpotCalculationHandler;
import com.flickranalyser.businesslogic.SpotCalculationHandlerTest;
import com.flickranalyser.data.flickr.FlickrRequestHandler;
import com.flickranalyser.pojo.PointOfInterest;
import com.flickranalyser.pojo.Spot;
import com.javadocmd.simplelatlng.LatLng;

public class ServletCreateSpot extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(ServletCreateSpot.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {


		String url = "/flickranalyser.jsp";
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		
		//Munich 
		// Long: 	11.5667
		// Lat: 	48.1333
		Spot hardcodedSpot = new Spot(new LatLng(48.1333, 11.5667), "Munich", "This is our first try");
		
		//Get all Point of Interests Daniel
		FlickrRequestHandler  flickrRequestHandler = new FlickrRequestHandler();
		
		SpotCalculationHandler spotCalculationHandler = new SpotCalculationHandler();
		Spot spotAttribute = spotCalculationHandler.getSpot(flickrRequestHandler.getAllImagesForSpot(hardcodedSpot),hardcodedSpot );
		//Call Logic
		
		log.log(Level.INFO,"Cluster Size: "+ spotAttribute.getClusterList().size() );
		//Set the Attributes (POJOS) for the JSP
		req.setAttribute("spot", spotAttribute );
		
		
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}


	}


}
