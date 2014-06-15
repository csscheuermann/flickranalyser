package com.flickranalyser.businesslogic.spotfinder.impl;

import com.flickranalyser.businesslogic.spotfinder.ISpotFinder;
import com.flickranalyser.pojo.Spot;
import com.javadocmd.simplelatlng.LatLng;

public class SaoPauloSpotFinder implements ISpotFinder {
	private Spot santosSpotFinder;

	public SaoPauloSpotFinder() {
		santosSpotFinder = new Spot(new LatLng( -23.5475000, -46.6361100), "Sao Paulo", "Sao Paulo Spots");
	}

	@Override
	public Spot findSpotByLocation(long latitude, long longitude) {
		return santosSpotFinder;
	}

	@Override
	public Spot findSpotByName(String name) {
		return santosSpotFinder;
	}

	
	
}
