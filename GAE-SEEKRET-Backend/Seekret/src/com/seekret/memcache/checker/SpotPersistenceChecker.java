package com.seekret.memcache.checker;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.seekret.memcache.Constants;
import com.seekret.memcache.MemcacheHelperMethods;
import com.seekret.persistence.datastore.common.MemcacheNameStoreEnum;

public class SpotPersistenceChecker {

	private static final Logger LOGGER = Logger.getLogger(SpotPersistenceChecker.class.getName());
	

	public static boolean checkIfPersistenceMustBeAsked(String memcacheKey, String memcachKeyTimestamp) {
		if (!MemcacheHelperMethods.getSyncCache().contains(memcacheKey)){
			LOGGER.log(Level.INFO, "I HAVE TO ASK PERSISTENCE FOR: " + memcacheKey);
			return true;
		}

		if (MemcacheHelperMethods.getSyncCache().contains(memcacheKey + MemcacheNameStoreEnum.CLUSTER.toString())){
			MemcacheHelperMethods.getSyncCache().delete(memcacheKey + MemcacheNameStoreEnum.CLUSTER.toString());
			return true;
		}
		
		if (!MemcacheHelperMethods.getSyncCache().contains(memcacheKey+memcachKeyTimestamp)){
			LOGGER.log(Level.INFO, "I HAVE TO ASK PERSISTENCE FOR: " + memcacheKey+memcachKeyTimestamp);
			return true;
		}
		
		
		long time = ((Long)MemcacheHelperMethods.getSyncCache().get(memcacheKey+memcachKeyTimestamp)).longValue();
		if ((time + Constants.VALID_TIME_SPOT_IN_MS) - new Date().getTime() < 0){
			LOGGER.log(Level.INFO,"SPOT "+ memcacheKey +" IS OUTDATED");
			MemcacheHelperMethods.getSyncCache().delete(memcacheKey+memcachKeyTimestamp);
			return true;
		}else{
			LOGGER.log(Level.INFO,"SPOT "+ memcacheKey +" STILL VALID.");
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
		if ((time + Constants.VALID_TIME_TOPSPOTS_IN_MS) - new Date().getTime() < 0){
			LOGGER.log(Level.INFO,"TOP TEN LIST IS OUTDATED.");
			MemcacheHelperMethods.getSyncCache().delete(memcachKeyTimestamp);
			return true;
		}else{
			LOGGER.log(Level.INFO,"TOP TEN LIST STILL VALID.");
		}

		return false;
	}
	
}
