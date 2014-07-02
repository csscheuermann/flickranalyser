package com.flickranalyser.memcache;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.memcache.checker.SpotPersistenceChecker;
import com.flickranalyser.persistence.datastore.common.MemcacheNameStoreEnum;
import com.flickranalyser.persistence.datastore.get.PFGetterSpot;
import com.flickranalyser.pojo.Spot;
import com.flickranalyser.pojo.TopTenSpots;
import com.google.appengine.api.memcache.Expiration;

public class MemcacheSpot implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(PFGetterSpot.class.getName());
	
	/**
	 * 
	 * @param spotName
	 * @return Can return null in case Spot does not exist.
	 */
	public static Spot getSpotForSpotName(String spotName) {

		String memcachKey = MemcacheNameStoreEnum.SPOT.toString() + spotName;

		if(SpotPersistenceChecker.checkIfPersistenceMustBeAsked(memcachKey)){
			Spot spot = PFGetterSpot.getSpotByName(spotName);
			if (spot != null){
				MemcacheHelperMethods.getSyncCache().put(memcachKey, spot, Expiration.onDate(MemCacheConstants.MEMCACH_EXPIRE_DATE));
			}
			return spot;
		}
		
		Spot spot = (Spot)  MemcacheHelperMethods.getSyncCache().get(memcachKey);
		LOGGER.log(Level.INFO, "NAME OF SPOT: " + spot.getName() );
		LOGGER.log(Level.INFO, "NUMBER OF CLUSTER: " + spot.getCluster().size() );
		if (spot.getCluster().size() > 1){
			LOGGER.log(Level.INFO, " FIRST CLUSTER " + spot.getCluster().get(0).toString());
		}
		return spot;
	}


	
	public static TopTenSpots getTopTenSpots(){
		//First ask Memcache if there are already top ten spots contained
		//Make sure that they are reloaded every 10 Minutes
		String memcachKey = MemCacheConstants.TOP_TEN_SPOT_LIST;
		String memcachKeyTimestamp = MemCacheConstants.SPOT_LIST_REFRESH_TIME;

		if(SpotPersistenceChecker.checkIfPersistenceMustBeAskedForTop10Spots(memcachKey, memcachKeyTimestamp)){
			TopTenSpots topTenSpotsFromPersistence = PFGetterSpot.getTopTenSpots();
			
			MemcacheHelperMethods.getSyncCache().put(memcachKeyTimestamp,new Date().getTime() );
			LOGGER.log(Level.INFO, "PUT TIMESTAMP IN CACHE: " +memcachKeyTimestamp);
			MemcacheHelperMethods.getSyncCache().put(memcachKey, topTenSpotsFromPersistence, Expiration.onDate(MemCacheConstants.MEMCACH_EXPIRE_DATE));
			return topTenSpotsFromPersistence;
		}
		return (TopTenSpots) MemcacheHelperMethods.getSyncCache().get(memcachKey);
	}

}
