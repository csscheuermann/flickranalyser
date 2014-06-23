package com.flickranalyser.html;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flickranalyser.businesslogic.filter.IFilterStrategy;
import com.flickranalyser.businesslogic.filter.impl.ManyViewsAndFewPOIsFilter;
import com.flickranalyser.businesslogic.impl.SecretPlacesFacade;
import com.flickranalyser.businesslogic.spotfinder.impl.IceLandSpotFinder;
import com.flickranalyser.businesslogic.spotfinder.impl.MunichSpotFinder;
import com.flickranalyser.persistence.datastore.saver.PFSaverSpot;
import com.flickranalyser.pojo.Spot;

public class CrawlData extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {


		IFilterStrategy	filterStrategy = new ManyViewsAndFewPOIsFilter(50);
		SecretPlacesFacade secretPlacesFacade = new SecretPlacesFacade(filterStrategy, new MunichSpotFinder());
		Spot spotAttribute = secretPlacesFacade.getSpotInformationForName("munich") ;
		
		PFSaverSpot.saveSpotToDatastore(spotAttribute);
	}


}
