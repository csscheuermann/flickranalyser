package com.flickranalyser.businesslogic;

import java.util.LinkedList;
import java.util.List;

import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.PointOfInterest;
import com.flickranalyser.pojo.Spot;
import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class SpotCalculationHandler {
	
	private static final String API_KEY = "";


	
	public Spot getSpot(){
		
		//Munich 
		// Long: 	11.5667
		// Lat: 	48.1333
		Spot hardcodedSpot = new Spot(new LatLng(48.1333, 11.5667), "Munich", "This is our first try");
		//First ask FlickrRequestHandler 
		
		//Cluster List of Spot - Empty at first
		List<Cluster> clusterList = hardcodedSpot.getClusterList();
		
		//Receives a List of PointOfInterests
		List<PointOfInterest> PointOfInterests = new LinkedList<PointOfInterest>();
		
		for (PointOfInterest pointOfInterest : PointOfInterests) {
			for (Cluster currentCluster : clusterList) {
				double distance = LatLngTool.distance(currentCluster.getCenterOfCluster(), pointOfInterest.getLocation(), LengthUnit.KILOMETER);
				if (distance <= hardcodedSpot.getClusterRadiusInKm() ){
					currentCluster.addPointOfInterestToList(pointOfInterest);
				}	
			}
			//Add new Cluster, no Cluster found or List was empty
			Cluster cluster = new Cluster(pointOfInterest.getLocation(), "", "");
			cluster.addPointOfInterestToList(pointOfInterest);
			hardcodedSpot.addClusterToList(cluster);
		}
		return hardcodedSpot;
		
		
	}
}
