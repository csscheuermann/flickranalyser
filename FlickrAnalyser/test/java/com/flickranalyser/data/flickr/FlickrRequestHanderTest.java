package com.flickranalyser.data.flickr;

import org.junit.Before;
import org.junit.Test;

import com.flickranalyser.pojo.Spot;
import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class FlickrRequestHanderTest {

	
	private Spot munichSpot;
	
	@Before
	public void setUp(){
		munichSpot = new Spot(-23.95403,-46.335487, "Munich", "Munich");
	}
	

	//@Test
	public void getAllImages_WithinMunich_MunichPictureWillGetReturned(){
		FlickrRequestHandler flickrRequestHandler = new FlickrRequestHandler();
		flickrRequestHandler.getPOIsForSpot(munichSpot);
	}
	@Test
	public void testNewSpotcalculation(){
		FlickrRequestHandler flickrRequestHandler = new FlickrRequestHandler();
		flickrRequestHandler.calculateNewPoint(munichSpot);
	}
	
	

	
}
