package com.flickranalyser.persistence.datastore.save;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.ws.rs.core.Response;

import com.flickranalyser.memcache.MemCacheConstants;
import com.flickranalyser.memcache.MemcacheHelperMethods;
import com.flickranalyser.persistence.datastore.common.MemcacheNameStoreEnum;
import com.flickranalyser.persistence.datastore.common.PMF;
import com.flickranalyser.persistence.datastore.get.PFGetterCluster;
import com.flickranalyser.persistence.datastore.get.PFGetterSpot;
import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.Spot;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.memcache.Expiration;

public class PFSaverCluster
{
  private static final Logger LOGGER = Logger.getLogger(PFSaverCluster.class.getName());

  public static Response evaluateTouristicness(Key datasoreKeyForCluster, int touristicnessRatingFrom1To10, String spotName) {
    LOGGER.log(Level.INFO, "SPOT NAME: " + spotName);
    LOGGER.log(Level.INFO, "VOTE FROM USER: " + touristicnessRatingFrom1To10);
    Cluster cluster = PFGetterCluster.getClusterByDatastoreKey(datasoreKeyForCluster);

    if (cluster != null) {
      double overallTouristicnessInPointsFrom1To10 = cluster.getOverallTouristicnessInPointsFrom1To10();
      int overallTouristicnessVotes = cluster.getOverallTouristicnessVotes();

      LOGGER.log(Level.INFO, "OVERALL TOURISTICNESS FROM DATASTORE: " + overallTouristicnessInPointsFrom1To10);
      LOGGER.log(Level.INFO, "OVERALL VOTES :" + overallTouristicnessVotes);
      double newOverallTouristicnessInPointsFrom1To10;
      if (overallTouristicnessVotes == 0) {
        newOverallTouristicnessInPointsFrom1To10 = overallTouristicnessInPointsFrom1To10 + touristicnessRatingFrom1To10;
        overallTouristicnessVotes++;
      } else {
        double touristicnessUntilNow = overallTouristicnessInPointsFrom1To10 * overallTouristicnessVotes;
        LOGGER.log(Level.INFO, "TOURISTICNESS UNTIL NOW :" + touristicnessUntilNow);

        overallTouristicnessVotes++;
        newOverallTouristicnessInPointsFrom1To10 = (touristicnessUntilNow + touristicnessRatingFrom1To10) / overallTouristicnessVotes;
      }
      LOGGER.log(Level.INFO, "NEW TOURISTICNESS :" + newOverallTouristicnessInPointsFrom1To10);

      cluster.setOverallTouristicnessInPointsFrom1To10(newOverallTouristicnessInPointsFrom1To10);
      cluster.setOverallTouristicnessVotes(overallTouristicnessVotes);

      PersistenceManager pm = PMF.get().getPersistenceManager();
      try {
        pm.makePersistent(cluster);
      } finally {
        pm.close();
      }

      Spot spotByName = PFGetterSpot.getSpotByName(spotName);
      String memcachKey = MemcacheNameStoreEnum.SPOT.toString() + spotName;
      MemcacheHelperMethods.getSyncCache().put(memcachKey, spotByName, Expiration.onDate(MemCacheConstants.MEMCACH_EXPIRE_DATE));
      return Response.status(200).entity("Updated").build();
    }

    return Response.status(200).entity("Entity was not found.").build();
  }
  
  

}