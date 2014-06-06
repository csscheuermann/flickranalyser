package com.flickranalyser.businesslogic.spotfinder.impl;

import com.flickranalyser.businesslogic.spotfinder.ISpotFinder;
import com.flickranalyser.pojo.Spot;
import com.javadocmd.simplelatlng.LatLng;

public class MunichSpotFinder implements ISpotFinder {
	private Spot munichSpot;

	public MunichSpotFinder() {
		munichSpot = new Spot(new LatLng(48.1333, 11.5667), "Munich", "This is our first try");
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
