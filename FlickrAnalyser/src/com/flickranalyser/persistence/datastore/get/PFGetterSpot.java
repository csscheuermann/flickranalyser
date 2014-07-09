package com.flickranalyser.persistence.datastore.get;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.flickranalyser.persistence.datastore.common.PMF;
import com.flickranalyser.pojo.Spot;
import com.flickranalyser.pojo.SpotResultList;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class PFGetterSpot {

	private static final Logger LOGGER = Logger.getLogger(PFGetterSpot.class.getName());

	public static Spot getSpotByName(String nameOfSpot){

		PersistenceManager pm = PMF.get().getPersistenceManager();

		LOGGER.log(Level.INFO, "NAME OF SPOT TO SEARCH: " + nameOfSpot );
		try{
			Key k = KeyFactory.createKey(Spot.class.getSimpleName(), nameOfSpot);
			Spot spot = pm.getObjectById(Spot.class, k);
			if (spot != null){

				//TODO COS DVV: Strange here normal detach shoudl work, but only thing that works is that
				//Solves the Problem with the empty spots while loading
				Spot spotToReturn = new Spot(spot.getLatitude(), spot.getLongitude(),  spot.getName(), spot.getDescription(),spot.getClusterRadiusInKm(), spot.getSpotRadiusInKm(), k, spot.getMaxNumberOfPOIsPerCluster(), spot.getMaxNumberOfViewsPerCluster());
				spotToReturn.setCluster(spot.getCluster());

				LOGGER.log(Level.INFO, "NAME OF SPOT: " + spotToReturn.getName() );
				LOGGER.log(Level.INFO, "NUMBER OF CLUSTER: " + spotToReturn.getCluster().size() );
				if (spotToReturn.getCluster().size() > 1){
					LOGGER.log(Level.INFO, " FIRST CLUSTER " + spot.getCluster().get(0).toString());
				}
				return spotToReturn;
			}else{
				LOGGER.log(Level.INFO, "SPOT DOES NOT EXIST IN DATASTORE YET");
				return null;
			}

		} catch (Exception ex) {
			//TODO COS DVV: Exception Handling
			LOGGER.log(Level.SEVERE, "Exception");
			return null;
		}
		finally{
			pm.close();
		}
	}

	public static SpotResultList getTopTenSpots(){
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

			LinkedList<Spot> resultList = new LinkedList<Spot>();
			if (!results.isEmpty()){
				for (Spot spot : results) {
					resultList.add(spot);
				}
				return new SpotResultList(resultList);
			}else{
				LOGGER.log(Level.INFO, "NO TOP TEN SPOTS FOUND.");
				return new SpotResultList(resultList);
			}

		}finally{
			q.closeAll();
			pm.close();
			LOGGER.log(Level.INFO, "CLOSED QUERY AND PM.");
		}


	}
	
	/**
	 * This Q
	 * @return
	 */
	public static SpotResultList getSpotByCoordinates(double latitude, double longitude){
		
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query q = pm.newQuery(Spot.class);
		try{
			q.setFilter("latitude == " + latitude);
			q.setFilter("longitude == " + longitude);
			q.setRange(0, 5);
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
				return new SpotResultList(resultList);
			}else{
				LOGGER.log(Level.INFO, "NO SPOTS FOUND.");
				return new SpotResultList(resultList);
			}

		}finally{
			q.closeAll();
			pm.close();
			LOGGER.log(Level.INFO, "CLOSED QUERY AND PM.");
		}
		
	}
}
