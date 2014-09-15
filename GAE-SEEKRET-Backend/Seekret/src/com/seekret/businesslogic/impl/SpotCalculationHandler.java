package com.seekret.businesslogic.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.seekret.businesslogic.spotfinder.impl.NearestSpotFinder;
import com.seekret.pojo.Cluster;
import com.seekret.pojo.PointOfInterest;
import com.seekret.pojo.Spot;

public class SpotCalculationHandler {

	private static Set<Integer> allowedLicenseForFlickrPhotos;

	public SpotCalculationHandler() {
		allowedLicenseForFlickrPhotos = new HashSet<>();
		/*
		 * Photo licences we are allowed to use in a commercial way.
		 * 
		 * See
		 * https://www.flickr.com/services/api/flickr.photos.licenses.getInfo.html 
		 * for more information
		 */
		allowedLicenseForFlickrPhotos.add(4);
		allowedLicenseForFlickrPhotos.add(5);
		allowedLicenseForFlickrPhotos.add(6);
		allowedLicenseForFlickrPhotos.add(7);
	}

	// private static final Logger LOGGER =
	// Logger.getLogger(SpotCalculationHandler.class.getName());
	public Spot getSpot(Set<PointOfInterest> pointOfInterests, Spot hardcodedSpot) {
		List<Cluster> clusters = hardcodedSpot.getCluster();

		for (PointOfInterest pointOfInterest : pointOfInterests) {
			if (!isPointIntrestInCluster(hardcodedSpot, clusters, pointOfInterest)) {
				// Add new Cluster, no Cluster found or List was empty
				double poiLatitude = pointOfInterest.getLocation().getLatitude();
				double poiLongitude = pointOfInterest.getLocation().getLongitude();
				Cluster cluster = new Cluster(poiLatitude, poiLongitude, "", "");
				NearestSpotFinder nsf = new NearestSpotFinder();
				String findAddressByLatLng = nsf.findAddressByLatLng(poiLatitude, poiLongitude);
				cluster.setName(findAddressByLatLng);
				cluster.addPointOfInterestToList(pointOfInterest);
				cluster.addViewCount(pointOfInterest.getCountOfViews());
				hardcodedSpot.addClusterTo(cluster);

			}
		}

		// Now set an image URL for the Cluster
		for (Cluster cluster : hardcodedSpot.getCluster()) {
			List<PointOfInterest> pointOfInterestList = cluster.getPointOfInterestList();

			// LOGGER.log(Level.INFO, "SIZE:" +pointOfInterestList.size() );
			// Add the top three POI URLs
			List<String> urls = new ArrayList<String>();
			Iterator<PointOfInterest> iterator = pointOfInterestList.iterator();
			int counter = 0;
			while ((iterator.hasNext()) && (counter < 3)) {

				PointOfInterest next = iterator.next();
				if (next.getPictureUrl() != null && !next.getPictureUrl().isEmpty() && allowedLicenseForFlickrPhotos.contains(next.getLicenseId())) {
					urls.add(next.getPictureUrl());
					counter++;
				}

			}

			cluster.setUrlOfMostViewedPicture(urls);

		}

		return hardcodedSpot;
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
