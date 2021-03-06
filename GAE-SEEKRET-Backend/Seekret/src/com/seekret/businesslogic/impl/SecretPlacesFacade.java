package com.seekret.businesslogic.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.seekret.businesslogic.ISecretPlacesFacade;
import com.seekret.data.flickr.FlickrRequestHandler;
import com.seekret.data.flickr.IFotoExcluder;
import com.seekret.data.flickr.TagBasedFotoExcluder;
import com.seekret.pojo.Cluster;
import com.seekret.pojo.PointOfInterest;
import com.seekret.pojo.Spot;

public class SecretPlacesFacade implements ISecretPlacesFacade {

	private final Spot spot;
	private final FlickrRequestHandler flickrRequestHandler;
	private final SpotCalculationHandler spotCalculationHandler;

	private static final Logger LOGGER = Logger.getLogger(SecretPlacesFacade.class.getName());

	public SecretPlacesFacade(Spot spot, boolean onlyUnwantedTags) {
		this.spot = spot;
		IFotoExcluder fotoExcluder = new TagBasedFotoExcluder(onlyUnwantedTags);
		flickrRequestHandler = new FlickrRequestHandler(fotoExcluder);
		spotCalculationHandler = new SpotCalculationHandler();
	}

	@Override
	public Spot getSpotInformationForName(String name) {
		return getSpotInformation(spot);
	}

	@Override
	public Spot getSpotInformationForLocation(long latitude, long longitude) {
		return getSpotInformation(spot);
	}

	private Spot getSpotInformation(Spot spotToSearchFor) {

		Set<PointOfInterest> allPOIsForSpot = flickrRequestHandler.getPOIsForSpot(spotToSearchFor);
		LOGGER.log(Level.INFO, " Number of POIs: " + allPOIsForSpot.size());

		Spot spot = spotCalculationHandler.getSpot(allPOIsForSpot, spotToSearchFor);
		handlePOIsWithFewPictures(spot);
		return spot;
	}

	private void handlePOIsWithFewPictures(Spot spot) {
		int numberPicturesLessClusters = 0;
		int numberClustersWhichHaveAPictureNow = 0;
		int numberNewPictures = 0;
		Set<Cluster> clusterFilledWithPictures = new HashSet<Cluster>();
		int numberClustersToHandle = 0;
		int handledClusters = 0;
		int logBarrier = 1;
		for (Cluster cluster : spot.getCluster()) {
			if (cluster.getUrlOfMostViewedPicture().isEmpty()) {
				numberClustersToHandle++;
			}
		}
		for (Cluster cluster : spot.getCluster()) {
			// only load pictures for empty clusters. otherwise we have the
			// timing issue with gae
			// if (cluster.getUrlOfMostViewedPicture().size() <
			// SpotCalculationHandler.NUMBER_MAX_PICTURES_PER_CLUSTER) {
			if (cluster.getUrlOfMostViewedPicture().isEmpty()) {
				if (cluster.getUrlOfMostViewedPicture().isEmpty()) {
					numberPicturesLessClusters++;
				}
				Set<PointOfInterest> picturesForCluster = flickrRequestHandler.getPicturesForCluster(cluster);
				if (!picturesForCluster.isEmpty()) {
					for (PointOfInterest pointOfInterest : picturesForCluster) {
						if (spotCalculationHandler.isPictureSuitedAsClusterProfilePicture(pointOfInterest)) {
							if (cluster.getUrlOfMostViewedPicture().isEmpty()) {
								numberClustersWhichHaveAPictureNow++;
							}
							cluster.addPictureUrl(pointOfInterest.getPictureUrl());
							clusterFilledWithPictures.add(cluster);
							numberNewPictures++;
							if (cluster.getUrlOfMostViewedPicture().size() < SpotCalculationHandler.NUMBER_MAX_PICTURES_PER_CLUSTER) {
								break;
							}
						}
					}
				}
				handledClusters++;
				if(logBarrier == handledClusters){
					logBarrier += 5*numberClustersToHandle/100;
					LOGGER.info("Status of handling pois with few pictures: "+ (100.0 * handledClusters)/numberClustersToHandle+" % completed" );
				}
			}
		}
		LOGGER.log(Level.INFO, "number new pictures:" + numberNewPictures + "; number pictureless clusters: " + numberPicturesLessClusters + "; number of clusters filled with pictures: "
				+ clusterFilledWithPictures.size() + "(" + 100.0 * clusterFilledWithPictures.size() / spot.getCluster().size() + "%); number of clusters which haven�t hat pictures before: of("
				+ 100.0 * numberClustersWhichHaveAPictureNow / numberPicturesLessClusters + "%)");

	}

}
