package com.seekret.memcache;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.memcache.Expiration;
import com.seekret.memcache.checker.SpotPersistenceChecker;
import com.seekret.persistence.datastore.common.MemcacheNameStoreEnum;
import com.seekret.persistence.datastore.get.PFGetterSpot;
import com.seekret.pojo.Cluster;
import com.seekret.pojo.Spot;
import com.seekret.pojo.SpotResultList;

public class MemcacheSpot
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = Logger.getLogger(PFGetterSpot.class.getName());

  public static Spot getSpotForSpotName(String spotName)
  {
    
	  
	  String memcachKey = MemcacheNameStoreEnum.SPOT.toString() + spotName;
	    String memcachKeyTimestamp = "spotRefreshTime";
    
	  
	  if (SpotPersistenceChecker.checkIfPersistenceMustBeAsked(memcachKey, memcachKeyTimestamp)) {
      Spot spot = PFGetterSpot.getSpotByName(spotName);
  
      if (spot != null) {
          MemcacheHelperMethods.getSyncCache().put(memcachKey + memcachKeyTimestamp, Long.valueOf(new Date().getTime()));
          LOGGER.log(Level.INFO, "PUT TIMESTAMP IN CACHE: " + memcachKey+memcachKeyTimestamp);
          
        MemcacheHelperMethods.getSyncCache().put(memcachKey, spot, Expiration.onDate(MemCacheConstants.MEMCACH_EXPIRE_DATE));
      }
      return spot;
    }

    Spot spot = (Spot)MemcacheHelperMethods.getSyncCache().get(memcachKey);
    LOGGER.log(Level.INFO, "NAME OF SPOT: " + spot.getName());
    LOGGER.log(Level.INFO, "NUMBER OF CLUSTER: " + spot.getCluster().size());
    if (spot.getCluster().size() > 1) {
      LOGGER.log(Level.INFO, " FIRST CLUSTER " + ((Cluster)spot.getCluster().get(0)).toString());
    }
    return spot;
  }

  public static SpotResultList getTopSpots()
  {
    String memcachKey = "topTenSpotList";
    String memcachKeyTimestamp = "topSpotsRefreshTime";

    if (SpotPersistenceChecker.checkIfPersistenceMustBeAskedForTop10Spots(memcachKey, memcachKeyTimestamp)) {
      SpotResultList topTenSpotsFromPersistence = PFGetterSpot.getTopSpots();

      MemcacheHelperMethods.getSyncCache().put(memcachKeyTimestamp, Long.valueOf(new Date().getTime()));
      LOGGER.log(Level.INFO, "PUT TIMESTAMP IN CACHE: " + memcachKeyTimestamp);
      MemcacheHelperMethods.getSyncCache().put(memcachKey, topTenSpotsFromPersistence, Expiration.onDate(MemCacheConstants.MEMCACH_EXPIRE_DATE));
      return topTenSpotsFromPersistence;
    }
    return (SpotResultList)MemcacheHelperMethods.getSyncCache().get(memcachKey);
  }
}