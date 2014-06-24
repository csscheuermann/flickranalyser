package com.flickranalyser.businesslogic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.flickranalyser.businesslogic.impl.SpotCalculationHandler;
import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.PointOfInterest;
import com.flickranalyser.pojo.Spot;
import com.javadocmd.simplelatlng.LatLng;

public class SpotCalculationHandlerTest {

	private final Set<PointOfInterest> pointOfInterestsToTest = new HashSet<PointOfInterest>();

	@Before
	public void setUp() {
		// 48.1333° N, 11.5667
		// 48.1334° N, 11.5668
		pointOfInterestsToTest.add(new PointOfInterest(4, new LatLng(48.1333,
				11.5667),"test"));
		pointOfInterestsToTest.add(new PointOfInterest(3, new LatLng(48.1334,
				11.5668),"test"));
		pointOfInterestsToTest.add(new PointOfInterest(3, new LatLng(48.1335,
				11.5669),"test"));

		pointOfInterestsToTest.add(new PointOfInterest(10, new LatLng(48.2333,
				11.4667),"test"));
		pointOfInterestsToTest.add(new PointOfInterest(10, new LatLng(48.2334,
				11.4668),"test"));
		pointOfInterestsToTest.add(new PointOfInterest(10, new LatLng(48.2335,
				11.4669),"test"));

		pointOfInterestsToTest.add(new PointOfInterest(10, new LatLng(48.3333,
				11.5667),"test"));
		pointOfInterestsToTest.add(new PointOfInterest(3, new LatLng(48.3334,
				11.5668),"test"));
		pointOfInterestsToTest.add(new PointOfInterest(2, new LatLng(48.3335,
				11.5669),"test"));
	}

	@Test
	public void getAllImages_WithinMunich_MunichPictureWillGetReturned() {
		// Munich
		// Long: 11.5667
		// Lat: 48.1333
		Spot hardcodedSpot = new Spot(new LatLng(48.1333, 11.5667), "Munich",
				"This is our first try");
		SpotCalculationHandler spotCalculationHandler = new SpotCalculationHandler();
		Spot spot = spotCalculationHandler.getSpot(pointOfInterestsToTest,
				hardcodedSpot);

		Assert.assertEquals(spot.getCluster().size(), 3);
		List<Cluster> clusterList = spot.getCluster();
		boolean found10Views = false;
		boolean found30Views = false;
		boolean found15Views = false;
		for (Cluster cluster : clusterList) {
			if (cluster.getOverallViews() == 10) {
				found10Views = !found10Views;
			}else if(cluster.getOverallViews() == 15)			{
				found15Views = !found15Views;
			}else if(cluster.getOverallViews() == 30){
				found30Views = !found30Views;
			}
		}
		
		Assert.assertTrue(found15Views && found10Views && found30Views); 
	}
}
