package com.flickranalyser.memcache;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.persistence.datastore.common.EntityNameStoreEnum;
import com.flickranalyser.persistence.datastore.get.PFGetterSpot;
import com.flickranalyser.pojo.Spot;
import com.google.appengine.api.memcache.Expiration;

public class MemcacheSpot {

	private static final Logger log = Logger.getLogger(PFGetterSpot.class.getName());
	
	public static Spot getSpotForSpotName(String location) {
		
		String memcachKey = EntityNameStoreEnum.SPOT.toString() + location;
		
		//First check if persistens must be asked
		if(checkIfPersistenceMustBeAsked(location, memcachKey)){
			 Spot spot = PFGetterSpot.getSpotByName(location);
			 MemcacheHelperMethods.getSyncCache().put(memcachKey, spot, Expiration.onDate(MemCacheConstants.MEMCACH_EXPIRE_DATE));
			 return spot;
		}
		return (Spot)  MemcacheHelperMethods.getSyncCache().get(memcachKey);
	}

	private static boolean checkIfPersistenceMustBeAsked(String location, String memcacheKey) {
		if (!MemcacheHelperMethods.getSyncCache().contains(memcacheKey)){
			log.log(Level.INFO, "I HAVE TO ASK PERSISTENCE FOR: " + location);
			return true;
		}
		log.log(Level.INFO, "STILL IN MEMCACHE: " + location);
		return false;
	}

	
	
	
}
