package com.flickranalyser.businesslogic;

import com.flickranalyser.pojo.Spot;


public interface ISecretPlacesFacade {

	Spot getSpotInformationForLocation(long latidute, long longitude, int numberOfPages);
	
	
	Spot getSpotInformationForName(String name, int numberOfPages);
}
