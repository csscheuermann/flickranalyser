package com.flickranalyser.businesslogic.impl;

import java.util.Set;
import java.util.logging.Logger;

import com.flickranalyser.businesslogic.SpotCalculationHandlerTest;
import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.PointOfInterest;
import com.flickranalyser.pojo.Spot;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class SpotCalculationHandler {
	
	private static final String API_KEY = "";

	private static final Logger log = Logger.getLogger(SpotCalculationHandlerTest.class.getName());
	
	public Spot getSpot(Set<PointOfInterest> pointOfInterests, Spot hardcodedSpot){
		//First ask FlickrRequestHandler 
		//Cluster List of Spot - Empty at first
		Set<Cluster> clusters = hardcodedSpot.getCluster();
		
		for (PointOfInterest pointOfInterest : pointOfInterests) {
			if (!isPointIntrestInCluster(hardcodedSpot, clusters, pointOfInterest)){
				//Add new Cluster, no Cluster found or List was empty
				Cluster cluster = new Cluster(pointOfInterest.getLocation(), "", "");
				cluster.addPointOfInterestToList(pointOfInterest);
				cluster.addViewCount(pointOfInterest.getCountOfViews());
				hardcodedSpot.addClusterTo(cluster);
			}
		}
		return hardcodedSpot;
	}

	private boolean isPointIntrestInCluster(Spot hardcodedSpot, Set<Cluster> clusterList,
			PointOfInterest pointOfInterest) {
		for (Cluster currentCluster : clusterList) {
			double distance = LatLngTool.distance(currentCluster.getCenterOfCluster(), pointOfInterest.getLocation(), LengthUnit.KILOMETER);
			if (distance <= hardcodedSpot.getClusterRadiusInKm() ){
				currentCluster.addPointOfInterestToList(pointOfInterest);
				currentCluster.addViewCount(pointOfInterest.getCountOfViews());
				return true;
			}	
		}
		return false;
	}
}
