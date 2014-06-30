package com.flickranalyser.persistence.datastore.save;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.ws.rs.core.Response;

import com.flickranalyser.persistence.datastore.common.PMF;
import com.flickranalyser.persistence.datastore.get.PFGetterSpot;
import com.flickranalyser.persistence.datastore.get.PFGetterSpotToCrawl;
import com.flickranalyser.pojo.Spot;
import com.flickranalyser.pojo.SpotToCrawl;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class PFSaverSpotToCrawl {

	private static final Logger log = Logger.getLogger(PFSaverSpotToCrawl.class.getName());

	public static Response saveSpotToDatastore(SpotToCrawl spot){

		if (!checkIfSpotAlreadyExists(spot)){
			log.log(Level.INFO, "SPOT DOES NOT EXIST IN SPOTS DATASTORE " + spot.getName());
			PersistenceManager pm = PMF.get().getPersistenceManager();
			log.log(Level.INFO, "BEGINNING TRANSACTION TO SAVE IN DATASTORE FOR SPOT: " + spot.getName());

			try{
				Key dataStoreKey = KeyFactory.createKey(SpotToCrawl.class.getSimpleName(), spot.getName());
				spot.setDataStoreKey(dataStoreKey);
				pm.makePersistent(spot);
			} finally {
				pm.close();
			}
		}else{
			log.log(Level.INFO, "SPOT ALREADY EXISTS");
			return Response.status(400).entity("Spot already exists").build();
		}
		return Response.status(200).build();
	}

	private static boolean checkIfSpotAlreadyExists(SpotToCrawl spot) {
		Spot spotByName = PFGetterSpot.getSpotByName(spot.getName());
		SpotToCrawl spotToCrawlByName = PFGetterSpotToCrawl.getSpotToCrawlByName(spot.getName());
		if ((spotByName != null) || (spotToCrawlByName != null)){
			return true;
		}
		return false;
	}
}
