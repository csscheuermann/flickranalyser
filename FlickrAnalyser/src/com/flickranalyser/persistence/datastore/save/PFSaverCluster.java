package com.flickranalyser.persistence.datastore.save;

import javax.ws.rs.core.Response;

import com.flickranalyser.memcache.MemcacheHelperMethods;
import com.flickranalyser.persistence.datastore.common.DatastoreHelper;
import com.flickranalyser.persistence.datastore.common.EntityNameStoreEnum;
import com.flickranalyser.persistence.datastore.common.properties.PropertiesCluster;
import com.flickranalyser.persistence.datastore.get.PFGetterCluster;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class PFSaverCluster {



	public static Response evaluateTouristicness(Key datasoreKeyForCluster, int touristicnessRatingFrom1To10, String spotName){

		Entity clusterEntityByDatastoreKey = PFGetterCluster.getClusterEntityByDatastoreKey(datasoreKeyForCluster);

		if(clusterEntityByDatastoreKey != null){
			double overallTouristicnessInPointsFrom1To10 = ((Double)clusterEntityByDatastoreKey.getProperty(PropertiesCluster.AVARAGE_TOURISTICNESS_IN_POINTS_FROM_1_TO_10.toString())).doubleValue();
			int overallTouristicnessVotes = ((Number)clusterEntityByDatastoreKey.getProperty(PropertiesCluster.COUNT_OF_TOURISTICNESS_EVALUATION.toString())).intValue();

			//Increase the overall rating
			overallTouristicnessVotes++;
			double newOverallTouristicnessInPointsFrom1To10;
			if(overallTouristicnessVotes == 1){
				newOverallTouristicnessInPointsFrom1To10 = (overallTouristicnessInPointsFrom1To10 + touristicnessRatingFrom1To10);
			}else{
				//Rating from now + new Rating, divided by 2 to get the average
				newOverallTouristicnessInPointsFrom1To10 = (overallTouristicnessInPointsFrom1To10 + touristicnessRatingFrom1To10)/2.0;				
			}

			clusterEntityByDatastoreKey.setProperty(PropertiesCluster.AVARAGE_TOURISTICNESS_IN_POINTS_FROM_1_TO_10.toString(), newOverallTouristicnessInPointsFrom1To10);
			clusterEntityByDatastoreKey.setProperty(PropertiesCluster.COUNT_OF_TOURISTICNESS_EVALUATION.toString(),overallTouristicnessVotes); 

			DatastoreHelper.getDatastore().put(clusterEntityByDatastoreKey);

			//Marks the memcache, that it needs to be updated
			MemcacheHelperMethods.getSyncCache().put(EntityNameStoreEnum.SPOT.toString() + spotName + EntityNameStoreEnum.CLUSTER.toString(), 1 );
			
			return Response.status(200).entity("Updated").build();
		}
		return Response.status(200).entity("Entity was not found.").build();
	}

}
