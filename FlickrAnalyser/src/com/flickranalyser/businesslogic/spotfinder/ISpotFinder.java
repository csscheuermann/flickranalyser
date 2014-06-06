package com.flickranalyser.businesslogic.spotfinder;

import com.flickranalyser.pojo.Spot;

public interface ISpotFinder {

	
	Spot findSpotByName(String name);
	
	Spot findSpotByLocation(long latitude, long longitude);
}
