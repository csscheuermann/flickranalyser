package com.flickranalyser.persistence.datastore.save;

import javax.jdo.PersistenceManager;
import javax.ws.rs.core.Response;

import com.flickranalyser.persistence.datastore.common.PMF;
import com.flickranalyser.pojo.RatingDismissCounter;

public class PFSaverRatingDismissCounter {

	public static Response saveRatingToDatastore(String userKey, String clusterKey){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			String datastoreKey = userKey + clusterKey;
			RatingDismissCounter rating = new RatingDismissCounter(datastoreKey);
			pm.makePersistent(rating);
		} finally {
			pm.close();
		}
		return Response.status(200).entity("DISMISS ADDED").build();
	}
}
