package com.flickranalyser.persistence.datastore.get;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.flickranalyser.persistence.datastore.common.PMF;
import com.flickranalyser.pojo.Spot;
import com.flickranalyser.pojo.TopTenSpots;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class PFGetterSpot {

	private static final Logger LOGGER = Logger.getLogger(PFGetterSpot.class.getName());

	public static Spot getSpotByName(String nameOfSpot){

		PersistenceManager pm = PMF.get().getPersistenceManager();


		try{
			Key k = KeyFactory.createKey(Spot.class.getSimpleName(), nameOfSpot);
			Spot spot = pm.getObjectById(Spot.class, k);
			if (spot != null){
				LOGGER.log(Level.INFO, "NAME OF SPOT: " + spot.getName() );
				LOGGER.log(Level.INFO, "NUMBER OF CLUSTER: " + spot.getCluster().size() );
				if (spot.getCluster().size() > 1){
					LOGGER.log(Level.INFO, " FIRST CLUSTER " + spot.getCluster().get(0).toString());
				}
				return spot;
			}else{
				LOGGER.log(Level.INFO, "SPOT DOES NOT EXIST IN DATASTORE YET");
				return null;
			}

		} catch (Exception ex) {
			LOGGER.log(Level.INFO, "SPOT DOES NOT EXIST IN DATASTORE YET");
			return null;
		}
		finally{
			pm.close();
		}
	}

	public static TopTenSpots getTopTenSpots(){
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query q = pm.newQuery(Spot.class);
		q.setOrdering("overallMaxViewNumberPerCluster desc");
		q.setRange(0, 10);

		try{
			@SuppressWarnings("unchecked")
			List<Spot> results = (List<Spot>) q.execute();
			//TODO COS: Discuss with Daniel
			//Needed to be done due to serializable issues, returning the result
			// itself it is not serializable
			
			LinkedList<Spot> resultList = new LinkedList<Spot>();
			if (!results.isEmpty()){
				 for (Spot spot : results) {
					 resultList.add(spot);
				}
				return new TopTenSpots(resultList);
			}else{
				LOGGER.log(Level.INFO, "NO TOP TEN SPOTS FOUND.");
				return new TopTenSpots(resultList);
			}

		}finally{
			q.closeAll();
			pm.close();
			LOGGER.log(Level.INFO, "CLOSED QUERY AND PM.");
		}


	}
}
