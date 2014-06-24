package com.flickranalyser.businesslogic.impl;

import java.util.List;
import java.util.Set;

import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.PointOfInterest;
import com.flickranalyser.pojo.Spot;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class SpotCalculationHandler {

	public Spot getSpot(Set<PointOfInterest> pointOfInterests, Spot hardcodedSpot){
		List<Cluster> clusters = hardcodedSpot.getCluster();

		for (PointOfInterest pointOfInterest : pointOfInterests) {
			if (!isPointIntrestInCluster(hardcodedSpot, clusters, pointOfInterest)){
				//Add new Cluster, no Cluster found or List was empty
				Cluster cluster = new Cluster(pointOfInterest.getLocation(), "", "");
				cluster.addPointOfInterestToList(pointOfInterest);
				cluster.addViewCount(pointOfInterest.getCountOfViews());
				hardcodedSpot.addClusterTo(cluster);

			}
		}

		//Now set an image URL for the Cluster
		for (Cluster cluster : hardcodedSpot.getCluster()) {
			List<PointOfInterest> pointOfInterestList = cluster.getPointOfInterestList();
			int maxNumberOfViews = 0;
			for (PointOfInterest pointOfInterest : pointOfInterestList) {
				if(pointOfInterest.getCountOfViews() > maxNumberOfViews){
					maxNumberOfViews = pointOfInterest.getCountOfViews();
					cluster.setUrlOfMostViewedPicture(pointOfInterest.getPictureUrl());
				}
			}
		}
		return hardcodedSpot;
	}

	private boolean isPointIntrestInCluster(Spot hardcodedSpot, List<Cluster> clusterList,
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
