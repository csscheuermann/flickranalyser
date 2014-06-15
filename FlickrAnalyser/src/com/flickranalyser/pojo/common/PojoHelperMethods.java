package com.flickranalyser.pojo.common;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	public static Set<Cluster> createClusterListFromEntity(List<Entity> asList) {
		Set<Cluster> setOfCluster = new HashSet<Cluster>();
		for (Entity clusterEntity : asList) {
			String description = (String) clusterEntity.getProperty(PropertiesCluster.DESCRIPTION.toString());
			String name = (String) clusterEntity.getProperty(PropertiesCluster.NAME.toString());
			double latitude = ((Double)clusterEntity.getProperty(PropertiesCluster.LATITUDE.toString())).doubleValue();
			double longitude = ((Double)clusterEntity.getProperty(PropertiesCluster.LONGITUDE.toString())).doubleValue();
			int overallViews = ((Number)clusterEntity.getProperty(PropertiesCluster.OVERALL_VIEWS.toString())).intValue();
			LatLng latLongPoint = new LatLng(latitude, longitude);
			String url = (String) clusterEntity.getProperty(PropertiesCluster.URL_OF_MOST_VIEWED_PICTURE.toString());
		
			
			setOfCluster.add(new Cluster(latLongPoint, name, description,overallViews,url));
		}
		return setOfCluster;
	}
}
