package com.seekret.businesslogic.spotfinder;

import javax.ws.rs.core.Response;

import com.seekret.pojo.Spot;

public interface ISpotFinder {

	
	Spot findSpotByName(String name);
	
	Spot findSpotByLocation(long latitude, long longitude);
	
	Response getSpotByNamePutToCrawlQueue(String name, boolean onlyExcludedPictures);
	
	String findAddressBySearchString(String searchAdress);
	
	String findAddressByLatLng(double lat, double lng);
}
