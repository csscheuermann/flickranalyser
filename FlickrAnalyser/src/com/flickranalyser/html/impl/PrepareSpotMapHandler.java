package com.flickranalyser.html.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.flickranalyser.businesslogic.common.ParameterConstants;
import com.flickranalyser.businesslogic.filter.IFilterStrategy;
import com.flickranalyser.html.IHtmlRequestHandler;
import com.flickranalyser.html.common.HelperMethods;
import com.flickranalyser.memcache.MemcacheSpot;
import com.flickranalyser.pojo.Spot;

public class PrepareSpotMapHandler implements IHtmlRequestHandler{

	private static final Logger LOGGER = Logger.getLogger(PrepareSpotMapHandler.class.getName());
	@Override
	public String performActionAndGetNextView(HttpServletRequest pRequest, HttpSession pSession) {
		//GET THE PARAMS
		String location = pRequest.getParameter(ParameterConstants.REQUEST_PARAM_LOCATION);
		String filterStrategy = pRequest.getParameter(ParameterConstants.FILTER_STRATEGY);

		LOGGER.log(Level.INFO, location);
		LOGGER.log(Level.INFO, filterStrategy);
		
		StringBuilder fullClassPath = new StringBuilder();
		fullClassPath.append("com.flickranalyser.businesslogic.filter.impl.");
		fullClassPath.append(filterStrategy);

		IFilterStrategy choosenFilterStrategy = HelperMethods.instantiate(fullClassPath.toString(), IFilterStrategy.class);
		LOGGER.log(Level.INFO, choosenFilterStrategy.getClass().getName());
		LOGGER.log(Level.INFO, "NAME: " + location);
		Spot spot = MemcacheSpot.getSpotForSpotName(location);
		LOGGER.log(Level.INFO, "CLUSTER SIZE " + spot.getCluster().size());
		LOGGER.log(Level.INFO, "LAT LONG " + spot.getLatitude() + ", " +spot.getLongitude());

		
		spot.setCluster(choosenFilterStrategy.filterCluster(spot.getCluster()));
	
		pRequest.setAttribute("spot", spot );
		LOGGER.log(Level.INFO, "AFTER CLUSTER SIZE " + spot.getCluster().size());
		LOGGER.log(Level.INFO, "AFTER LAT LONG " + spot.getLatitude() + ", " +spot.getLongitude());
		
		return null;
	}
}
