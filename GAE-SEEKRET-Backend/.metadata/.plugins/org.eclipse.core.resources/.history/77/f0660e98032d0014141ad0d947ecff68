package com.flickranalyser.memcache.checker;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.memcache.MemcacheHelperMethods;
import com.flickranalyser.persistence.datastore.common.MemcacheNameStoreEnum;

public class SpotPersistenceChecker {

	private static final Logger LOGGER = Logger.getLogger(SpotPersistenceChecker.class.getName());
	

	public static boolean checkIfPersistenceMustBeAsked(String memcacheKey) {
		if (!MemcacheHelperMethods.getSyncCache().contains(memcacheKey)){
			LOGGER.log(Level.INFO, "I HAVE TO ASK PERSISTENCE FOR: " + memcacheKey);
			return true;
		}

		if (MemcacheHelperMethods.getSyncCache().contains(memcacheKey + MemcacheNameStoreEnum.CLUSTER.toString())){
			MemcacheHelperMethods.getSyncCache().delete(memcacheKey + MemcacheNameStoreEnum.CLUSTER.toString());
			return true;
		}
		LOGGER.log(Level.INFO, "STILL IN MEMCACHE: " + memcacheKey);
		return false;
	}
	
	
	public static boolean checkIfPersistenceMustBeAskedForTop10Spots(String memcacheKey, String memcachKeyTimestamp) {
		if (!MemcacheHelperMethods.getSyncCache().contains(memcacheKey)){
			LOGGER.log(Level.INFO, "I HAVE TO ASK PERSISTENCE FOR: " + memcacheKey);
			return true;
		}
		
		if (!MemcacheHelperMethods.getSyncCache().contains(memcachKeyTimestamp)){
			LOGGER.log(Level.INFO, "I HAVE TO ASK PERSISTENCE FOR: " + memcachKeyTimestamp);
			return true;
		}
		
		long time = ((Long)MemcacheHelperMethods.getSyncCache().get(memcachKeyTimestamp)).longValue();
		if (time - new Date().getTime() < 0){
			LOGGER.log(Level.INFO,"TOP TEN LIST IS OUTDATED.");
			MemcacheHelperMethods.getSyncCache().delete(memcachKeyTimestamp);
			return true;
		}

		return false;
	}
	
}
