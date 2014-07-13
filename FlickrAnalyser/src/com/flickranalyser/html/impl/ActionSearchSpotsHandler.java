package com.flickranalyser.html.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import com.flickranalyser.endpoints.SpotService;
import com.flickranalyser.html.common.HelperMethods;
import com.flickranalyser.pojo.Spot;

public class ActionSearchSpotsHandler extends AbstractHtmlRequestHandler{

	private static final String VIEW_SEARCH_SPOTS = "SearchSpots";
	private static final String MESSAGE_CRAWLQUEUE_SPOT_NAME = "CRAWL QUEUE, FULL SPOT NAME: ";
	private static final Logger LOGGER = Logger.getLogger(ActionSearchSpotsHandler.class.getName());
	
	
	@Override
	public String performActionAndGetNextViewConcrete(HttpServletRequest pRequest, HttpSession pSession) {
		SpotService spotService = new SpotService();

		String crawlSpotName = pRequest.getParameter(HelperMethods.CRAWL_ADDRESS);
		LOGGER.log(Level.INFO,MESSAGE_CRAWLQUEUE_SPOT_NAME + crawlSpotName );

		if (crawlSpotName != null){
			return handleCrawlQueueBehavior(pRequest, spotService, crawlSpotName);
		}
		
		return handleSpotBehavior(pRequest, spotService);
	}


	private String handleSpotBehavior(HttpServletRequest pRequest, SpotService spotService) {
		String spotName = pRequest.getParameter(HelperMethods.ADDRESS_PARAM);
		Spot spotByName = spotService.getSpotByName(spotName);

		if(spotByName == null){
			//double latitude = Double.parseDouble(pRequest.getParameter(HelperMethods.LATITUDE_PARAM));
			//double longitude = Double.parseDouble(pRequest.getParameter(HelperMethods.LONGITUDE_PARAM));

			pRequest.setAttribute(HelperMethods.MESSAGE_ERROR, "Spot was not found in Database" );
			pRequest.setAttribute(HelperMethods.ADDRESS_PARAM,spotName);
			//pRequest.setAttribute(HelperMethods.LATITUDE_PARAM,latitude);
			//pRequest.setAttribute(HelperMethods.LONGITUDE_PARAM,longitude);
		}else{
			pRequest.setAttribute(HelperMethods.MESSAGE_SUCCESSFUL, "Spot was found in Database" );
			pRequest.setAttribute(HelperMethods.SPOT, spotByName);
		}

		return VIEW_SEARCH_SPOTS;
	}


	private String handleCrawlQueueBehavior(HttpServletRequest pRequest,
			SpotService spotService, String crawlSpotName) {
		
		LOGGER.log(Level.INFO, "HANDLE CRAWL QUEUE BEHAVIOR." );
		
		double crawlLatitude = Double.parseDouble(pRequest.getParameter(HelperMethods.CRAWL_LATITUDE));
		double crawlLogitude = Double.parseDouble(pRequest.getParameter(HelperMethods.CRAWL_LONGITUDE));

		Response putSpotInCronQueue = spotService.putSpotInCronQueue(crawlSpotName, crawlLatitude, crawlLogitude, 0.1, "YET EMPTY TO DO", 25.0);
		
		if (putSpotInCronQueue.getStatus() == 400){
			pRequest.setAttribute(HelperMethods.MESSAGE_ERROR_CRON, putSpotInCronQueue.getEntity().toString());	
		}else{
			pRequest.setAttribute(HelperMethods.MESSAGE_SUCCESSFUL_CRON, putSpotInCronQueue.getEntity().toString());	
		}
		return VIEW_SEARCH_SPOTS;
	}
}
