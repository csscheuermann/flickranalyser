package com.flickranalyser.data.flickr;

import org.junit.Before;
import org.junit.Test;

import com.seekret.data.flickr.FlickrRequestHandler;
import com.seekret.data.flickr.TagBasedFotoExcluder;
import com.seekret.pojo.Spot;

public class FlickrRequestHanderTest {

	
	private Spot munichSpot;
	
	@Before
	public void setUp(){
		munichSpot = new Spot(-23.95403,-46.335487, "Munich", "Munich");
	}
	

	//@Test
	public void getAllImages_WithinMunich_MunichPictureWillGetReturned(){
		FlickrRequestHandler flickrRequestHandler = new FlickrRequestHandler(new TagBasedFotoExcluder(false));
		flickrRequestHandler.getPOIsForSpot(munichSpot);
	}
	@Test
	public void testNewSpotcalculation(){
		FlickrRequestHandler flickrRequestHandler = new FlickrRequestHandler(new TagBasedFotoExcluder(false));
		flickrRequestHandler.calculateNewPoint(munichSpot);
	}
	
	@Test
	public void test_GetInfoForPicture(){
		FlickrRequestHandler flickrRequestHandler = new FlickrRequestHandler(new TagBasedFotoExcluder(false));
		flickrRequestHandler.getInfoForPoi("5951678688");
	}
	

	
}
