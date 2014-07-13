package com.flickranalyser.businesslogic.spotfinder;

import javax.ws.rs.core.Response;

import com.flickranalyser.pojo.Spot;

public interface ISpotFinder {

	
	Spot findSpotByName(String name);
	
	Spot findSpotByLocation(long latitude, long longitude);
	
	Response getSpotByNamePutToCrawlQueue(String name);
	
	String findAddressBySearchString(String searchAdress);
}
