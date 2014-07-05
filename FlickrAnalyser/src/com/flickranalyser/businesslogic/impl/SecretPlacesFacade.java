package com.flickranalyser.businesslogic.impl;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.businesslogic.ISecretPlacesFacade;
import com.flickranalyser.data.flickr.FlickrRequestHandler;
import com.flickranalyser.data.flickr.TagBasedRequestReducer;
import com.flickranalyser.pojo.PointOfInterest;
import com.flickranalyser.pojo.Spot;

public class SecretPlacesFacade implements ISecretPlacesFacade {

	private Spot spot;
	private FlickrRequestHandler flickrRequestHandler;
	private SpotCalculationHandler spotCalculationHandler;

	private static final Logger LOGGER = Logger.getLogger(SecretPlacesFacade.class.getName());
	private TagBasedRequestReducer tagBasedRequestReducer;
	
	public SecretPlacesFacade(Spot spot) {
		this.spot = spot;
		flickrRequestHandler = new FlickrRequestHandler();
		tagBasedRequestReducer = new TagBasedRequestReducer();
		spotCalculationHandler = new SpotCalculationHandler();
	}

	//TODO DVV & COS: Do we really need that here? I changed it due to queue mechanism. lets talk about that
	@Override
	public Spot getSpotInformationForName(String name) {
		return getSpotInformation(spot);
	}

	//TODO DVV & COS: Do we really need that here? I changed it due to queue mechanism. lets talk about that
	@Override
	public Spot getSpotInformationForLocation(long latitude, long longitude) {
		return getSpotInformation(spot);
	}

	private Spot getSpotInformation(Spot spotToSearchFor) {
		
		Set<PointOfInterest> allPOIsForSpot = flickrRequestHandler.getPOIsForSpot(spotToSearchFor);
		LOGGER.log(Level.INFO, " Number of POIs: " + allPOIsForSpot.size());
		
		//TODO COS DVV: We get heapspace Problems
		//Set<PointOfInterest> reduceResult = tagBasedRequestReducer.reduceResult(allPOIsForSpot);
		Spot spot = spotCalculationHandler.getSpot(allPOIsForSpot,spotToSearchFor);
		return spot;
	}

}
