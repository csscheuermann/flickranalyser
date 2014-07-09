package com.flickranalyser.html.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.flickranalyser.endpoints.SpotService;
import com.flickranalyser.pojo.Spot;

public class ActionSearchSpotsHandler extends AbstractHtmlRequestHandler{

	private static final String ADDRESS_PARAM = "address";
	private static final String LONGITUDE_PARAM = "longitude";
	private static final String LATITUDE_PARAM = "latitude";


	private static final Logger LOGGER = Logger.getLogger(ActionSearchSpotsHandler.class.getName());
	@Override
	public String performActionAndGetNextViewConcrete(
			HttpServletRequest pRequest, HttpSession pSession) {

		SpotService spotService = new SpotService();

		String crwalSpotName = pRequest.getParameter("crawlAddress");
		LOGGER.log(Level.INFO,"CRAWL QUEUE FULL SPOT NAME: " + crwalSpotName );

		if (crwalSpotName != null){

			double crawlLatitude = Double.parseDouble(pRequest.getParameter("crawlLatitude"));
			double crawlLogitude = Double.parseDouble(pRequest.getParameter("crawlLongitude"));

			LOGGER.log(Level.INFO,"CRAWL QUEUE LONGITUDE: " + crawlLogitude );
			LOGGER.log(Level.INFO,"CRAWL QUEUE LATITUDE: " + crawlLatitude );

			spotService.putSpotInCronQueue(crwalSpotName, crawlLatitude, crawlLogitude, 0.1, "YET EMPTY TO DO", 25.0);
			pRequest.setAttribute("successfulCron", "Spot added to the Crawl Queue." );	
			return "SearchSpots";
		}
		double latitude = Double.parseDouble(pRequest.getParameter(LATITUDE_PARAM));
		double longitude = Double.parseDouble(pRequest.getParameter(LONGITUDE_PARAM));
		String spotName = pRequest.getParameter(ADDRESS_PARAM);

		LOGGER.log(Level.INFO,"FULL SPOT NAME: " + spotName );
		LOGGER.log(Level.INFO,"LONGITUDE: " + longitude );
		LOGGER.log(Level.INFO,"LATITUDE: " + latitude );

		Spot spotByName = spotService.getSpotByName(spotName);

		if(spotByName == null){
			pRequest.setAttribute("error", "Spot was not found in Database" );
			pRequest.setAttribute(ADDRESS_PARAM,spotName);
			pRequest.setAttribute(LATITUDE_PARAM,latitude);
			pRequest.setAttribute(LONGITUDE_PARAM,longitude);
		}else{
			pRequest.setAttribute("successful", "Spot was found in Database" );
			pRequest.setAttribute("spot", spotByName);
		}

		return "SearchSpots";
	}
}
