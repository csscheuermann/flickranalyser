package com.seekret.persistence.datastore.save;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.ws.rs.core.Response;

import com.seekret.persistence.datastore.common.PMF;
import com.seekret.pojo.Rating;

public class PFSaverRating{
	private static final Logger LOGGER = Logger.getLogger(PFSaverRating.class.getName());
	public static Response saveRatingToDatastore(String userKey, String clusterKey){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			LOGGER.log(Level.INFO, "USERMAIL " + userKey + " CLUSTERKEY " +  clusterKey);
			String datastoreKey = userKey + clusterKey;
			Rating rating = new Rating(datastoreKey, userKey);
			pm.makePersistent(rating);
		} finally {
			pm.close();
		}
		return Response.status(200).entity("RATING ADDED").build();
	}
}