package com.seekret.businesslogic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.seekret.businesslogic.common.AllowedLicenses;
import com.seekret.businesslogic.spotfinder.impl.NearestSpotFinder;
import com.seekret.pojo.Cluster;
import com.seekret.pojo.PointOfInterest;
import com.seekret.pojo.Spot;

public class SpotCalculationHandler {

	

	// private static final Logger LOGGER =
	// Logger.getLogger(SpotCalculationHandler.class.getName());
	public Spot getSpot(Set<PointOfInterest> pointOfInterests, Spot spotToSearchFor) {
		List<Cluster> clusters = spotToSearchFor.getCluster();

		for (PointOfInterest pointOfInterest : pointOfInterests) {
			if (!isPointIntrestInCluster(spotToSearchFor, clusters, pointOfInterest)) {
				// Add new Cluster, no Cluster found or List was empty
				double poiLatitude = pointOfInterest.getLocation().getLatitude();
				double poiLongitude = pointOfInterest.getLocation().getLongitude();
				Cluster cluster = new Cluster(poiLatitude, poiLongitude, "", "");
				NearestSpotFinder nsf = new NearestSpotFinder();
				String findAddressByLatLng = nsf.findAddressByLatLng(poiLatitude, poiLongitude);
				cluster.setName(findAddressByLatLng);
				cluster.addPointOfInterestToList(pointOfInterest);
				cluster.addViewCount(pointOfInterest.getCountOfViews());
				spotToSearchFor.addClusterTo(cluster);

			}
		}

		// Now set an image URL for the Cluster
		for (Cluster cluster : spotToSearchFor.getCluster()) {
			List<PointOfInterest> pointOfInterestList = cluster.getPointOfInterestList();

			// LOGGER.log(Level.INFO, "SIZE:" +pointOfInterestList.size() );
			// Add the top three POI URLs
			List<String> urls = new ArrayList<String>();
			Iterator<PointOfInterest> iterator = pointOfInterestList.iterator();
			int counter = 0;
			while ((iterator.hasNext()) && (counter < 3)) {

				PointOfInterest next = iterator.next();
				if (isPictureSuitedAsClusterProfilePicture(next)) {
					urls.add(next.getPictureUrl());
					counter++;
				}

			}

			cluster.setUrlOfMostViewedPicture(urls);

		}

		return spotToSearchFor;
	}

	public boolean isPictureSuitedAsClusterProfilePicture(PointOfInterest next) {
		return next.getPictureUrl() != null && !next.getPictureUrl().isEmpty() && AllowedLicenses.licenses.contains(next.getLicenseId()) && next.getWidth()!=0 && next.getHeight() != 0 && isLandscape(next);
	}

	private boolean isLandscape(PointOfInterest next) {
		return next.getWidth() > next.getHeight();
	}

	private boolean isPointIntrestInCluster(Spot hardcodedSpot, List<Cluster> clusterList, PointOfInterest pointOfInterest) {
		for (Cluster currentCluster : clusterList) {
			LatLng tmpGeoPoint = new LatLng(currentCluster.getLatitude(), currentCluster.getLongitude());

			double distance = LatLngTool.distance(tmpGeoPoint, pointOfInterest.getLocation(), LengthUnit.KILOMETER);
			if (distance <= hardcodedSpot.getClusterRadiusInKm()) {
				currentCluster.addPointOfInterestToList(pointOfInterest);
				currentCluster.addViewCount(pointOfInterest.getCountOfViews());
				return true;
			}
		}
		return false;
	}
}
