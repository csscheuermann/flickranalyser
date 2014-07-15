package com.flickranalyser.persistence.datastore.save;

import javax.jdo.PersistenceManager;
import javax.ws.rs.core.Response;

import com.flickranalyser.persistence.datastore.common.PMF;
import com.flickranalyser.pojo.Rating;
import com.flickranalyser.pojo.Spot;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class PFSaverRating {

	public static Response saveRatingToDatastore(String userKey, String clusterKey){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			String datastoreKey = userKey + clusterKey;
			Rating rating = new Rating(datastoreKey);
			pm.makePersistent(rating);
		} finally {
			pm.close();
		}
		return Response.status(200).entity("RATING ADDED").build();
	}
}
