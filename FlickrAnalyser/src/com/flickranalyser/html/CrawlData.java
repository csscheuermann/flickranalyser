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
import com.flickranalyser.persistence.datastore.saver.PFSaverSpot;
import com.flickranalyser.pojo.Spot;

public class CrawlData extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(CrawlData.class.getName());
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {


		IFilterStrategy	filterStrategy = new ManyViewsAndFewPOIsFilter(50);
		SecretPlacesFacade secretPlacesFacade = new SecretPlacesFacade(filterStrategy);
		Spot spotAttribute = secretPlacesFacade.getSpotInformationForName("munich", 350) ;
		
		PFSaverSpot.saveSpotToDatastore(spotAttribute);
	}


}
