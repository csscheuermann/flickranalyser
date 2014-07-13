package com.flickranalyser.businesslogic.spotfinder.impl;

import com.flickranalyser.businesslogic.spotfinder.ISpotFinder;
import com.flickranalyser.memcache.MemcacheSpot;
import com.flickranalyser.pojo.Spot;

public class NearestSpotFinder implements ISpotFinder {

	@Override
	public Spot findSpotByName(String name) {
		
		return  MemcacheSpot.getSpotForSpotName(name);
	}

	@Override
	public Spot findSpotByLocation(long latitude, long longitude) {
		// TODO Auto-generated method stub
		return null;
	}

}
