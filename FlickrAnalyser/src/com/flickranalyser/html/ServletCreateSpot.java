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

import com.flickranalyser.businesslogic.SpotCalculationHandlerTest;
import com.flickranalyser.businesslogic.filter.IFilterStrategy;
import com.flickranalyser.businesslogic.filter.impl.DoNotFilterStrategy;
import com.flickranalyser.businesslogic.impl.SecretPlacesFacade;
import com.flickranalyser.businesslogic.impl.SpotCalculationHandler;
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
		
<<<<<<< HEAD
//		//Munich 
//		// Long: 	11.5667
//		// Lat: 	48.1333
//		Spot hardcodedSpot = new Spot(new LatLng(48.1333, 11.5667), "Munich", "This is our first try");
		
		
		//Munich 
		// Long: 	11.5667
		// Lat: 	48.1333
		Spot hardcodedSpot = new Spot(new LatLng(-23.944841, -46.330376), "Sanots", "This is our first try");
=======
		IFilterStrategy filterStrategy = new DoNotFilterStrategy();
		SecretPlacesFacade secretPlacesFacade = new SecretPlacesFacade(filterStrategy);
>>>>>>> FETCH_HEAD
		
		Spot spotAttribute = secretPlacesFacade.getSpotInformationForName("Munich") ;

		//Call Logic
		
		log.log(Level.INFO,"Cluster Size: "+ spotAttribute.getCluster().size() );
		//Set the Attributes (POJOS) for the JSP
		req.setAttribute("spot", spotAttribute );
		
		
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}


	}


}
