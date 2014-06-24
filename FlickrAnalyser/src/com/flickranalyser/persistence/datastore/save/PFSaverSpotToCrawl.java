package com.flickranalyser.persistence.datastore.save;

import java.util.ConcurrentModificationException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.persistence.datastore.common.EntityNameStoreEnum;
import com.flickranalyser.persistence.datastore.common.properties.PropertiesSpotToCrawl;
import com.flickranalyser.persistence.datastore.get.PFGetterSpot;
import com.flickranalyser.pojo.Spot;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Transaction;

public class PFSaverSpotToCrawl {

	private static final Logger log = Logger.getLogger(PFSaverSpotToCrawl.class.getName());

	public static void saveSpotToDatastore(Spot spot){
		int retries = 3;

		if (!checkIfSpotAlreadyExists(spot)){
			log.log(Level.INFO, "SPOT DOES NOT EXIST IN SPOTS DATASTORE " + spot.getName());

			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			log.log(Level.INFO, "BEGINNING TRANSACTION TO SAVE IN DATASTORE FOR SPOT: " + spot.getName());

			while (true) {
				Transaction txn = datastore.beginTransaction();
				try {
					Entity spotEntity = new Entity(EntityNameStoreEnum.SPOT_TO_CRAWL.toString());
					spotEntity.setProperty(PropertiesSpotToCrawl.CLUSTER_RADIUS_IN_KM.toString(), spot.getClusterRadiusInKm());
					spotEntity.setProperty(PropertiesSpotToCrawl.SPOT_RADIUS_IN_KM.toString(), spot.getSpotRadiusInKm());
					spotEntity.setProperty(PropertiesSpotToCrawl.DESCRIPTION.toString(), spot.getDescription());
					spotEntity.setProperty(PropertiesSpotToCrawl.LATITUDE.toString(), spot.getLatLngPoint().getLatitude());
					spotEntity.setProperty(PropertiesSpotToCrawl.LONGITUDE.toString(), spot.getLatLngPoint().getLongitude());
					spotEntity.setProperty(PropertiesSpotToCrawl.NAME.toString(), spot.getName());
					datastore.put(txn, spotEntity);
					txn.commit();
					log.log(Level.INFO, "END TRANSACTION: " + spot.getName());
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
		}else{
			log.log(Level.INFO, "SPOT ALREADY EXISTS");
		}
	}

	private static boolean checkIfSpotAlreadyExists(Spot spot) {
		Spot spotByName = PFGetterSpot.getSpotByName(spot.getName());
		if (spotByName != null){
			return true;
		}
		return false;
	}
}
