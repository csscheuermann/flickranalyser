package com.flickranalyser.persistence.datastore.get;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.flickranalyser.persistence.datastore.common.PMF;
import com.flickranalyser.pojo.Spot;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class PFGetterSpot {

	private static final Logger LOGGER = Logger.getLogger(PFGetterSpot.class.getName());

	public static Spot getSpotByName(String nameOfSpot){

		PersistenceManager pm = PMF.get().getPersistenceManager();

		// the cluster of the spot should be loaded eager 
		pm.getFetchPlan().addGroup("eagerClusterLoading");

		LOGGER.log(Level.INFO, "NAME OF SPOT TO SEARCH: " + nameOfSpot );
		try{
			Key k = KeyFactory.createKey(Spot.class.getSimpleName(), nameOfSpot);
			Spot spot = pm.getObjectById(Spot.class, k);
			if (spot != null){


				LOGGER.log(Level.INFO, "NAME OF SPOT: " + spot.getName() );
				LOGGER.log(Level.INFO, "NUMBER OF CLUSTER: " + spot.getCluster().size() );
				if (spot.getCluster().size() > 2){
					LOGGER.log(Level.INFO, " FIRST CLUSTER " + spot.getCluster().get(0).toString());
					LOGGER.log(Level.INFO, " SECOND CLUSTER " + spot.getCluster().get(1).toString());
				}
				return spot;
			}else{
				LOGGER.log(Level.INFO, "SPOT DOES NOT EXIST IN DATASTORE YET");
				return null;
			}

		} catch (Exception ex) {
			//TODO COS DVV: Exception Handling
			LOGGER.log(Level.INFO, "COULD NOT FIND SPOT WITH NAME " + nameOfSpot);
			return null;
		}
		finally{
			pm.close();
		}
	}

	public static ArrayList<String> getTopSpots(){
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query q = pm.newQuery(Spot.class);
		try{
			q.setOrdering("overallMaxViewNumberPerCluster desc");
			q.setRange(0, 20);
			@SuppressWarnings("unchecked")
			List<Spot> results = (List<Spot>) q.execute();
			//TODO COS: Discuss with Daniel
			//Needed to be done due to serializable issues, returning the result
			// itself it is not serializable

			ArrayList<String> resultList = new ArrayList<String>();
			if (!results.isEmpty()){
				for (Spot spot : results) {
					resultList.add(spot.getName());
				}
				return resultList;
			}
			LOGGER.log(Level.INFO, "NO TOP TEN SPOTS FOUND.");
			return resultList;
		}finally{
			q.closeAll();
			pm.close();
			LOGGER.log(Level.INFO, "CLOSED QUERY AND PM.");
		}
	}
}
