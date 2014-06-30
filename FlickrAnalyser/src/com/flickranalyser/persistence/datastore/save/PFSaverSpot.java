package com.flickranalyser.persistence.datastore.save;

import javax.jdo.PersistenceManager;

import com.flickranalyser.persistence.datastore.common.PMF;
import com.flickranalyser.pojo.Spot;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class PFSaverSpot {

	public static void saveSpotToDatastore(Spot spot){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			Key dataStoreKey = KeyFactory.createKey(Spot.class.getSimpleName(), spot.getName());
			spot.setDataStoreKey(dataStoreKey);
			pm.makePersistent(spot);
		} finally {
			pm.close();
		}
		
	}
}
