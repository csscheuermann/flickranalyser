package com.flickranalyser.businesslogic.impl;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.businesslogic.ISecretPlacesFacade;
import com.flickranalyser.data.flickr.FlickrRequestHandler;
import com.flickranalyser.data.flickr.IFotoExcluder;
import com.flickranalyser.data.flickr.TagBasedFotoExcluder;
import com.flickranalyser.pojo.PointOfInterest;
import com.flickranalyser.pojo.Spot;

public class SecretPlacesFacade implements ISecretPlacesFacade {

	private final Spot spot;
	private final FlickrRequestHandler flickrRequestHandler;
	private final SpotCalculationHandler spotCalculationHandler;

	private static final Logger LOGGER = Logger.getLogger(SecretPlacesFacade.class.getName());
	
	public SecretPlacesFacade(Spot spot, boolean onlyUnwantedTags) {
		this.spot = spot;
		IFotoExcluder fotoExcluder = new TagBasedFotoExcluder(onlyUnwantedTags);
		flickrRequestHandler = new FlickrRequestHandler(fotoExcluder);
		spotCalculationHandler = new SpotCalculationHandler();
	}

	@Override
	public Spot getSpotInformationForName(String name) {
		return getSpotInformation(spot);
	}

	@Override
	public Spot getSpotInformationForLocation(long latitude, long longitude) {
		return getSpotInformation(spot);
	}

	private Spot getSpotInformation(Spot spotToSearchFor) {
		
		Set<PointOfInterest> allPOIsForSpot = flickrRequestHandler.getPOIsForSpot(spotToSearchFor);
		LOGGER.log(Level.INFO, " Number of POIs: " + allPOIsForSpot.size());
		
		Spot spot = spotCalculationHandler.getSpot(allPOIsForSpot,spotToSearchFor);
		return spot;
	}

}
