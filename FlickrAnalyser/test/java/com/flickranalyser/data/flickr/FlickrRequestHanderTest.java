package com.flickranalyser.data.flickr;

import org.junit.Before;
import org.junit.Test;

import com.flickranalyser.pojo.Spot;

public class FlickrRequestHanderTest {

	
	private Spot munichSpot;
	
	@Before
	public void setUp(){
		munichSpot = new Spot(-23.95403,-46.335487, "Munich", "Munich");
	}
	

	//@Test
	public void getAllImages_WithinMunich_MunichPictureWillGetReturned(){
		FlickrRequestHandler flickrRequestHandler = new FlickrRequestHandler(new TagBasedFotoExcluder());
		flickrRequestHandler.getPOIsForSpot(munichSpot);
	}
	@Test
	public void testNewSpotcalculation(){
		FlickrRequestHandler flickrRequestHandler = new FlickrRequestHandler(new TagBasedFotoExcluder());
		flickrRequestHandler.calculateNewPoint(munichSpot);
	}
	
	

	
}
