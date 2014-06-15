package com.flickranalyser.businesslogic;

import com.flickranalyser.pojo.Spot;


public interface ISecretPlacesFacade {

	Spot getSpotInformationForLocation(long latidute, long longitude);
	
	
	Spot getSpotInformationForName(String name);
}
