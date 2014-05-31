package com.flickranalyser.businesslogic;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.flickranalyser.pojo.PointOfInterest;
import com.flickranalyser.pojo.Spot;
import com.javadocmd.simplelatlng.LatLng;

public class SpotCalculationHandlerTest {

	private List<PointOfInterest> pointOfInterestsToTest = new LinkedList<PointOfInterest>();
	private static final Logger log = Logger.getLogger(SpotCalculationHandlerTest.class.getName());
	
	
	@Before
	public void setUp(){		
		//48.1333° N, 11.5667
		//48.1334° N, 11.5668
		pointOfInterestsToTest.add(new PointOfInterest(30, new LatLng(48.1333, 11.5667)));
		pointOfInterestsToTest.add(new PointOfInterest(30, new LatLng(48.1334, 11.5668)));
		pointOfInterestsToTest.add(new PointOfInterest(30, new LatLng(48.1335, 11.5669)));
		
		pointOfInterestsToTest.add(new PointOfInterest(30, new LatLng(48.2333, 11.4667)));
		pointOfInterestsToTest.add(new PointOfInterest(30, new LatLng(48.2334, 11.4668)));
		pointOfInterestsToTest.add(new PointOfInterest(30, new LatLng(48.2335, 11.4669)));
		
		pointOfInterestsToTest.add(new PointOfInterest(30, new LatLng(48.3333, 11.5667)));
		pointOfInterestsToTest.add(new PointOfInterest(30, new LatLng(48.3334, 11.5668)));
		pointOfInterestsToTest.add(new PointOfInterest(30, new LatLng(48.3335, 11.5669)));
	}
	

	@Test
	public void getAllImages_WithinMunich_MunichPictureWillGetReturned(){
		SpotCalculationHandler spotCalculationHandler = new SpotCalculationHandler();
		Spot spot = spotCalculationHandler.getSpot(pointOfInterestsToTest);
		log.log(Level.INFO, "Cluster Size: " + spot.getClusterList().size() );
		Assert.assertEquals(spot.getClusterList().size(), 3);
	}
}
