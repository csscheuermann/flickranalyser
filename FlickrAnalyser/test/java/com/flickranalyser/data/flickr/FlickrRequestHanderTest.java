package com.flickranalyser.data.flickr;

import org.junit.Before;
import org.junit.Test;

import com.flickranalyser.pojo.Spot;

public class FlickrRequestHanderTest {

	
	private Spot munichSpot;
	
	@Before
	public void setUp(){
		munichSpot = new Spot(48.1333, 11.5667, "Munich", "Munich");
	}
	

	@Test
	public void getAllImages_WithinMunich_MunichPictureWillGetReturned(){
		FlickrRequestHandler flickrRequestHandler = new FlickrRequestHandler();
		flickrRequestHandler.getPOIsForSpot(munichSpot);
	}
}
