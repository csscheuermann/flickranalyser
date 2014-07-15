package com.flickranalyser.persistence.datastore.get;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.ws.rs.core.Response;

import com.flickranalyser.persistence.datastore.common.PMF;
import com.flickranalyser.pojo.Rating;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class PFGetterRating {

	
	private static final Logger LOGGER = Logger.getLogger(PFGetterRating.class.getName());
	

	
	public static Response hasUserAlreadyRated(String userPrimaryKey, String clusterPrimaryKey){

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try{

			String datastoreKey = userPrimaryKey + clusterPrimaryKey;

			Query newQuery = pm.newQuery("select datastoreRatingKey from " + Rating.class.getName());
			newQuery.setFilter("datastoreRatingKey == datastoreKey");
			newQuery.declareParameters("String datastoreKey");
			
			List<?> resultList = (List<?>) newQuery.execute(datastoreKey);
			
			if(resultList.size() >0){
				
				//already voted
				LOGGER.log(Level.INFO, "USER ALREADY VOTED FOR THIS CLUSTER.");
				return Response.status(200).entity(true).build();
			}else{
				LOGGER.log(Level.INFO, "USER CAN VOTE.");
				return Response.status(200).entity(false).build();
			}


		} catch (Exception ex) {
			//TODO COS: EXCEPTION HANDLING talk to daniel
			LOGGER.log(Level.SEVERE, "EXCEPTION WHILE FETCHING RATING.");
		
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			String stacktrace = sw.toString();

			final String errorMsg = "An error occured: \n" + ex.getMessage() + "\n\nStacktrace: \n" + stacktrace;
			LOGGER.log(Level.SEVERE, errorMsg);
			return Response.status(200).entity(false).build();
		
		}
		finally{
			pm.close();
		}
	}
}
