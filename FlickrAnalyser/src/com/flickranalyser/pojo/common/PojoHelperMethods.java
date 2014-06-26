package com.flickranalyser.pojo.common;

import java.util.LinkedList;
import java.util.List;

import com.flickranalyser.persistence.datastore.common.properties.PropertiesCluster;
import com.flickranalyser.persistence.datastore.common.properties.PropertiesSpot;
import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.Spot;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.javadocmd.simplelatlng.LatLng;

public class PojoHelperMethods {

	public static Spot createSpotFromEntity(Entity spotAsEntity) {
		Key datastoreKey = spotAsEntity.getKey();
		double latitude = ((Double)spotAsEntity.getProperty(PropertiesSpot.LATITUDE.toString())).doubleValue();
		double longitude = ((Double)spotAsEntity.getProperty(PropertiesSpot.LONGITUDE.toString())).doubleValue();
		LatLng latLongPoint = new LatLng(latitude, longitude);
		String description = (String) spotAsEntity.getProperty(PropertiesSpot.DESCRIPTION.toString());
		String name = (String) spotAsEntity.getProperty(PropertiesSpot.NAME.toString());
		double spotRadiusInKm = ((Double)spotAsEntity.getProperty(PropertiesSpot.SPOT_RADIUS_IN_KM.toString())).doubleValue();
		double clusterRadiusInKm = ((Double)spotAsEntity.getProperty(PropertiesSpot.CLUSTER_RADIUS_IN_KM.toString())).doubleValue();

		return new Spot(latLongPoint,name, description, clusterRadiusInKm, spotRadiusInKm,datastoreKey);
	}

	public static List<Cluster> createClusterListFromEntity(List<Entity> asList) {
		List<Cluster> setOfCluster = new LinkedList<Cluster>();
		for (Entity clusterEntity : asList) {
			setOfCluster.add(createClusterFromEntity(clusterEntity));
		}
		return setOfCluster;
	}
	
	public static Cluster createClusterFromEntity(Entity clusterEntity){
		String description = (String) clusterEntity.getProperty(PropertiesCluster.DESCRIPTION.toString());
		String name = (String) clusterEntity.getProperty(PropertiesCluster.NAME.toString());
		double latitude = ((Double)clusterEntity.getProperty(PropertiesCluster.LATITUDE.toString())).doubleValue();
		double longitude = ((Double)clusterEntity.getProperty(PropertiesCluster.LONGITUDE.toString())).doubleValue();
		int overallViews = ((Number)clusterEntity.getProperty(PropertiesCluster.OVERALL_VIEWS.toString())).intValue();
		int numberofPOIs = ((Number)clusterEntity.getProperty(PropertiesCluster.NUMBER_OF_POIS.toString())).intValue();
		LatLng latLongPoint = new LatLng(latitude, longitude);
		String url = (String) clusterEntity.getProperty(PropertiesCluster.URL_OF_MOST_VIEWED_PICTURE.toString());
		double overallTouristicnessInPointsFrom1To10 = ((Double)clusterEntity.getProperty(PropertiesCluster.AVARAGE_TOURISTICNESS_IN_POINTS_FROM_1_TO_10.toString())).doubleValue();
		int overallTouristicnessVotes = ((Number)clusterEntity.getProperty(PropertiesCluster.COUNT_OF_TOURISTICNESS_EVALUATION.toString())).intValue();

		return new Cluster(clusterEntity.getKey(), latLongPoint, name, description,overallViews,url,numberofPOIs, overallTouristicnessInPointsFrom1To10, overallTouristicnessVotes);
	}
}
