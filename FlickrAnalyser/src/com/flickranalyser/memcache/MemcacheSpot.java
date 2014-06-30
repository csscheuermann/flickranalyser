package com.flickranalyser.memcache;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.persistence.datastore.common.MemcacheNameStoreEnum;
import com.flickranalyser.persistence.datastore.get.PFGetterSpot;
import com.flickranalyser.pojo.Spot;
import com.google.appengine.api.memcache.Expiration;

public class MemcacheSpot {

	private static final Logger LOGGER = Logger.getLogger(PFGetterSpot.class.getName());

	public static Spot getSpotForSpotName(String location) {

		String memcachKey = MemcacheNameStoreEnum.SPOT.toString() + location;

		//First check if persistens must be asked
		if(checkIfPersistenceMustBeAsked(location, memcachKey)){

			Spot spot = PFGetterSpot.getSpotByName(location);
			MemcacheHelperMethods.getSyncCache().put(memcachKey, spot, Expiration.onDate(MemCacheConstants.MEMCACH_EXPIRE_DATE));
			return spot;
		}
		Spot spot = (Spot)  MemcacheHelperMethods.getSyncCache().get(memcachKey);
		LOGGER.log(Level.INFO, "NAME OF SPOT: " + spot.getName() );
		LOGGER.log(Level.INFO, "NUMBER OF CLUSTER: " + spot.getCluster().size() );
		return spot;
	}

	private static boolean checkIfPersistenceMustBeAsked(String location, String memcacheKey) {
		if (!MemcacheHelperMethods.getSyncCache().contains(memcacheKey)){
			LOGGER.log(Level.INFO, "I HAVE TO ASK PERSISTENCE FOR: " + location);
			return true;
		}

		if (MemcacheHelperMethods.getSyncCache().contains(memcacheKey + MemcacheNameStoreEnum.CLUSTER.toString())){
			MemcacheHelperMethods.getSyncCache().delete(memcacheKey + MemcacheNameStoreEnum.CLUSTER.toString());
			return true;
		}
		LOGGER.log(Level.INFO, "STILL IN MEMCACHE: " + location);
		return false;
	}




}