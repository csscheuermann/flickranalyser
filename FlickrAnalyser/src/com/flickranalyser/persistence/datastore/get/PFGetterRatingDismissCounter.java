package com.flickranalyser.persistence.datastore.get;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.flickranalyser.persistence.datastore.common.PMF;
import com.flickranalyser.pojo.RatingDismissCounter;

public class PFGetterRatingDismissCounter {

	
	private static final Logger LOGGER = Logger.getLogger(PFGetterRatingDismissCounter.class.getName());
	
	public static boolean hasUserAlreadyDissmissedCluster(String userPrimaryKey, String clusterPrimaryKey){

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try{
			String datastoreKey = userPrimaryKey + clusterPrimaryKey;

			Query newQuery = pm.newQuery("select datastoreRatingKey from " + RatingDismissCounter.class.getName());
			newQuery.setFilter("datastoreRatingKey == datastoreKey");
			newQuery.declareParameters("String datastoreKey");
			
			List<?> resultList = (List<?>) newQuery.execute(datastoreKey);
			
			if(resultList.size() >0){
				LOGGER.log(Level.INFO, "USER ALREADY DISMISSED THIS CLUSTER.");
				return true;
			}else{
				LOGGER.log(Level.INFO, "USER CAN DISMISS CLUSTER.");
				return false;
			}


		} catch (Exception ex) {
			//TODO COS: EXCEPTION HANDLING talk to daniel
			LOGGER.log(Level.SEVERE, "EXCEPTION WHILE FETCHING DISMISS COUNTER.");
		
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			String stacktrace = sw.toString();

			final String errorMsg = "An error occured: \n" + ex.getMessage() + "\n\nStacktrace: \n" + stacktrace;
			LOGGER.log(Level.SEVERE, errorMsg);
			return false;
		
		}
		finally{
			pm.close();
		}
	}
}
