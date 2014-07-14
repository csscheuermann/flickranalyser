package com.flickranalyser.persistence.datastore.save;

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


public class PFSaverCluster {



	public static Response evaluateTouristicness(Key datasoreKeyForCluster, int touristicnessRatingFrom1To10, String spotName){

		Cluster cluster = PFGetterCluster.getClusterByDatastoreKey(datasoreKeyForCluster);

		if(cluster != null){
			double overallTouristicnessInPointsFrom1To10 = cluster.getOverallTouristicnessInPointsFrom1To10();
			int overallTouristicnessVotes = cluster.getOverallTouristicnessVotes();

			//Increase the overall rating
			overallTouristicnessVotes++;
			double newOverallTouristicnessInPointsFrom1To10;
			if(overallTouristicnessVotes == 1){
				newOverallTouristicnessInPointsFrom1To10 = (overallTouristicnessInPointsFrom1To10 + touristicnessRatingFrom1To10);
			}else{
				//Rating from now + new Rating, divided by 2 to get the average
				newOverallTouristicnessInPointsFrom1To10 = (overallTouristicnessInPointsFrom1To10 + touristicnessRatingFrom1To10)/2.0;				
			}
			cluster.setOverallTouristicnessInPointsFrom1To10(newOverallTouristicnessInPointsFrom1To10);
			cluster.setOverallTouristicnessVotes(overallTouristicnessVotes);
			
			

			PersistenceManager pm = PMF.get().getPersistenceManager();
			try{
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
