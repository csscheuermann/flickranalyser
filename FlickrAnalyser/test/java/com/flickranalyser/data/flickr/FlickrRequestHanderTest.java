package com.flickranalyser.data.flickr;

import org.junit.Before;
import org.junit.Test;

import com.flickranalyser.pojo.Spot;
import com.javadocmd.simplelatlng.LatLng;

public class FlickrRequestHanderTest {

	
	private Spot munichSpot;
	
	@Before
	public void setUp(){
		LatLng point = new LatLng(48.1333, 11.5667);
		munichSpot = new Spot(point, "Munich", "Munich");
	}
	

	@Test
	public void getAllImages_WithinMunich_MunichPictureWillGetReturned(){
		FlickrRequestHandler flickrRequestHandler = new FlickrRequestHandler();
		flickrRequestHandler.getAllImagesForSpot(munichSpot);
	}
}
