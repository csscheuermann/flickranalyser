package com.flickranalyser.businesslogic.impl;

import java.util.Set;

import com.flickranalyser.businesslogic.ISecretPlacesFacade;
import com.flickranalyser.businesslogic.filter.IFilterStrategy;
import com.flickranalyser.businesslogic.spotfinder.ISpotFinder;
import com.flickranalyser.businesslogic.spotfinder.impl.MunichSpotFinder;
import com.flickranalyser.data.flickr.FlickrRequestHandler;
import com.flickranalyser.pojo.PointOfInterest;
import com.flickranalyser.pojo.Spot;

public class SecretPlacesFacade implements ISecretPlacesFacade {

	private ISpotFinder munichSpotFinder;
	private FlickrRequestHandler flickrRequestHandler;
	private SpotCalculationHandler spotCalculationHandler;
	private IFilterStrategy filterStrategy;

	public SecretPlacesFacade(IFilterStrategy filterStrategy) {
		this.filterStrategy = filterStrategy;
		munichSpotFinder = new MunichSpotFinder();
		flickrRequestHandler = new FlickrRequestHandler();
		spotCalculationHandler = new SpotCalculationHandler();
	}

	@Override
	public Spot getSpotInformationForName(String name, int numberOfPages) {
		Spot spotToSearchFor = munichSpotFinder.findSpotByName(name);
		return getSpotInformation(spotToSearchFor, numberOfPages);
	}

	@Override
	public Spot getSpotInformationForLocation(long latitude, long longitude, int numberOfPages) {
		Spot spotToSearchFor = munichSpotFinder.findSpotByLocation(latitude,
				longitude);
		return getSpotInformation(spotToSearchFor, numberOfPages);
	}

	private Spot getSpotInformation(Spot spotToSearchFor, int numberOfPages) {
		Set<PointOfInterest> allPOIsForSpot = flickrRequestHandler
				.getPOIsForSpot(spotToSearchFor, numberOfPages);
		Spot spot = spotCalculationHandler.getSpot(allPOIsForSpot,
				spotToSearchFor);
		spot.setCluster(filterStrategy.filterCluster(spot.getCluster()));
		return spot;
	}

}
