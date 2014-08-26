package com.seekret.persistence.datastore.save;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.ws.rs.core.Response;

import com.google.appengine.api.datastore.KeyFactory;
import com.seekret.persistence.datastore.common.PMF;
import com.seekret.persistence.datastore.update.PFUpdateCluster;
import com.seekret.pojo.RatingDismissCounter;

public class PFSaverRatingDismissCounter{
	
	private static final Logger LOGGER = Logger.getLogger(PFSaverRatingDismissCounter.class.getName());
	
	public static Response saveRatingToDatastore(String userKey, String clusterKey){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			String datastoreKey = userKey + clusterKey;
			RatingDismissCounter rating = new RatingDismissCounter(datastoreKey, userKey);
			pm.makePersistent(rating);
			PFUpdateCluster.updateCluster(KeyFactory.stringToKey(clusterKey));
		}catch (Exception e){
			LOGGER.log(Level.SEVERE, "EXCEPTION: " + e.getMessage(), e);
			return Response.status(400).entity("SAVE DISSMISS RATING FAILED").build();
		}
		finally {
			pm.close();
		}
		return Response.status(200).entity("DISMISS ADDED").build();
	}
}