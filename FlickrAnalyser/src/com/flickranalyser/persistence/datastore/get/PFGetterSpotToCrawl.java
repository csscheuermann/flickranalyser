package com.flickranalyser.persistence.datastore.get;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.flickranalyser.persistence.datastore.common.PMF;
import com.flickranalyser.pojo.SpotToCrawl;

public class PFGetterSpotToCrawl {

	/**
	 * Gets one spot from the Datastore. In case there is nothing in the datastore it will return null.
	 * It serves like a queue. Users are able to put a spot in the datastore and the cron job
	 * will handle this queue until there is nothing inside anymore.
	 * @return Spot or null in case nothing is inside Datastore
	 */
	private static final Logger LOGGER = Logger.getLogger(PFGetterSpotToCrawl.class.getName());

	/**
	 * Returns one spot to crawl from Queue
	 * @return
	 */
	public static SpotToCrawl getOneSpotFromDataStore(){

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query q = pm.newQuery(SpotToCrawl.class);
		q.setOrdering("name desc");

		try{
			@SuppressWarnings("unchecked")
			List<SpotToCrawl> results = (List<SpotToCrawl>) q.execute();
			if (!results.isEmpty()){
				return results.get(0);
			}else{
				LOGGER.log(Level.INFO, "COULD NOT FIND SPOTS TO CRAWL, WILL RELAX :)");
				return null;
			}

		}finally{
			q.closeAll();
			pm.close();
		}
	
	}
	
	public static SpotToCrawl getSpotToCrawlByName(String nameOfSpot){

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query q = pm.newQuery(SpotToCrawl.class);
		q.setFilter("name == nameParam");
		q.setOrdering("name desc");
		q.declareParameters("String nameParam");

		try{
			@SuppressWarnings("unchecked")
			List<SpotToCrawl> results = (List<SpotToCrawl>) q.execute(nameOfSpot);
			if (!results.isEmpty()){
				return results.get(0);
			}else{
				LOGGER.log(Level.INFO, "SPOTTOCRAWL DOES NOT EXIST IN DATASTORE (CRAWLQUEUE) YET");
				return null;
			}

		}finally{
			q.closeAll();
		}
	}


}
