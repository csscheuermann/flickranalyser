package com.flickranalyser.persistence.datastore.saver;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.persistence.datastore.common.EntityNameStoreEnum;
import com.flickranalyser.persistence.datastore.common.properties.PropertiesCluster;
import com.flickranalyser.persistence.datastore.common.properties.PropertiesSpot;
import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.Spot;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class PFSaverSpot {

	private static final Logger log = Logger.getLogger(PFSaverSpot.class.getName());

	public static void saveSpotToDatastore(Spot spot){
		int retries = 3;

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		log.log(Level.INFO, "BEGINNING TRANSACTION TO SAVE IN DATASTORE FOR SPOT: " + spot.getName());

		while (true) {
			Transaction txn = datastore.beginTransaction();
			try {
				Entity spotEntity = new Entity(EntityNameStoreEnum.SPOT.toString());
				spotEntity.setProperty(PropertiesSpot.CLUSTER_RADIUS_IN_KM.toString(), spot.getClusterRadiusInKm());
				spotEntity.setProperty(PropertiesSpot.CLUSTER_RADIUS_IN_KM.toString(), spot.getClusterRadiusInKm());
				spotEntity.setProperty(PropertiesSpot.DESCRIPTION.toString(), spot.getDescription());
				spotEntity.setProperty(PropertiesSpot.LATITUDE.toString(), spot.getLatLngPoint().getLatitude());
				spotEntity.setProperty(PropertiesSpot.LONGITUDE.toString(), spot.getLatLngPoint().getLongitude());
				spotEntity.setProperty(PropertiesSpot.NAME.toString(), spot.getName());
				Key spotDatastoreKey = datastore.put(txn, spotEntity);
				
				
				saveCluster(spot, datastore, txn, spotDatastoreKey);
				
				txn.commit();
				break;

			} catch (ConcurrentModificationException e) {
				if (retries == 0) {
					throw e;
				}
				// Allow retry to occur
				--retries;
			}finally{
				if(txn.isActive()){
					txn.rollback();
					log.log(Level.SEVERE, "I TRIED BUT I HAVE TO DO A ROLLBACK - SAD BUT TRUE.");
				}
			}
		}
	}

	private static void saveCluster(Spot spot, DatastoreService datastore,
			Transaction txn, Key spotDatastoreKey) {
		//Now store all Cluster
		Set<Cluster> cluster = spot.getCluster();
		Iterator<Cluster> clusterIterator = cluster.iterator();
		
		while(clusterIterator.hasNext()){
			Cluster nextCluster = clusterIterator.next();
			Entity currentCluster = new Entity(EntityNameStoreEnum.CLUSTER.toString(), spotDatastoreKey);
			currentCluster.setProperty(PropertiesCluster.NAME.toString(), nextCluster.getName() );
			currentCluster.setProperty(PropertiesCluster.DESCRIPTION.toString(), nextCluster.getDescription() );
			currentCluster.setProperty(PropertiesCluster.LATITUDE.toString(), nextCluster.getCenterOfCluster().getLatitude() );
			currentCluster.setProperty(PropertiesCluster.LONGITUDE.toString(), nextCluster.getCenterOfCluster().getLongitude());
			currentCluster.setProperty(PropertiesCluster.OVERALL_VIEWS.toString(), nextCluster.getOverallViews() );
			Key clusterDatastoreKey = datastore.put(txn, currentCluster);
		}
	}
}
