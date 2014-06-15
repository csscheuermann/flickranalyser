package com.flickranalyser.businesslogic.spotfinder.impl;

import com.flickranalyser.businesslogic.spotfinder.ISpotFinder;
import com.flickranalyser.pojo.Spot;
import com.javadocmd.simplelatlng.LatLng;

public class SantosSpotFinder implements ISpotFinder {
	private Spot santosSpotFinder;

	public SantosSpotFinder() {
		santosSpotFinder = new Spot(new LatLng(-23.944841, -46.330376), "Santos", "Santos Spots");
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
