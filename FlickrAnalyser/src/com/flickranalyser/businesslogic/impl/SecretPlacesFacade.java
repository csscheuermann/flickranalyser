package com.flickranalyser.businesslogic.impl;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.businesslogic.ISecretPlacesFacade;
import com.flickranalyser.businesslogic.filter.IFilterStrategy;
import com.flickranalyser.businesslogic.spotfinder.ISpotFinder;
import com.flickranalyser.data.flickr.FlickrRequestHandler;
import com.flickranalyser.pojo.PointOfInterest;
import com.flickranalyser.pojo.Spot;

public class SecretPlacesFacade implements ISecretPlacesFacade {

	private ISpotFinder spotFinder;
	private FlickrRequestHandler flickrRequestHandler;
	private SpotCalculationHandler spotCalculationHandler;
	private IFilterStrategy filterStrategy;

	private static final Logger log = Logger.getLogger(SecretPlacesFacade.class.getName());
	
	public SecretPlacesFacade(IFilterStrategy filterStrategy, ISpotFinder spotFinder) {
		this.filterStrategy = filterStrategy;
		this.spotFinder = spotFinder;
		flickrRequestHandler = new FlickrRequestHandler();
		spotCalculationHandler = new SpotCalculationHandler();
	}

	@Override
	public Spot getSpotInformationForName(String name) {
		Spot spotToSearchFor = spotFinder.findSpotByName(name);
		return getSpotInformation(spotToSearchFor);
	}

	@Override
	public Spot getSpotInformationForLocation(long latitude, long longitude) {
		Spot spotToSearchFor = spotFinder.findSpotByLocation(latitude,
				longitude);
		return getSpotInformation(spotToSearchFor);
	}

	private Spot getSpotInformation(Spot spotToSearchFor) {
		
		Set<PointOfInterest> allPOIsForSpot = flickrRequestHandler.getPOIsForSpot(spotToSearchFor);
		log.log(Level.INFO, " Number of POIs: " + allPOIsForSpot.size());
		Spot spot = spotCalculationHandler.getSpot(allPOIsForSpot,spotToSearchFor);
		spot.setCluster(filterStrategy.filterCluster(spot.getCluster()));
		return spot;
	}

}
