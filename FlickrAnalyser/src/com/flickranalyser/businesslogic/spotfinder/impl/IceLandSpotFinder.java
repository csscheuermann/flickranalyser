package com.flickranalyser.businesslogic.spotfinder.impl;

import com.flickranalyser.businesslogic.spotfinder.ISpotFinder;
import com.flickranalyser.pojo.Spot;

public class IceLandSpotFinder implements ISpotFinder {
	private Spot munichSpot;

	public IceLandSpotFinder() {
		munichSpot = new Spot(65.608415, -18.764648, "IceLand", "This is our first try");
	}
	
	@Override
	public Spot findSpotByLocation(long latitude, long longitude) {
		return munichSpot;
	}

	@Override
	public Spot findSpotByName(String name) {
		return munichSpot;
	}

}