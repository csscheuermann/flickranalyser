package com.flickranalyser.persistence.datastore.get;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.flickranalyser.persistence.datastore.common.PMF;
import com.flickranalyser.pojo.Spot;

public class PFGetterSpot {

	private static final Logger LOGGER = Logger.getLogger(PFGetterSpot.class.getName());

	public static Spot getSpotByName(String nameOfSpot){

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query q = pm.newQuery(Spot.class);
		q.setFilter("name == nameParam");
		q.setOrdering("name desc");
		q.declareParameters("String nameParam");

		try{
			@SuppressWarnings("unchecked")
			List<Spot> results = (List<Spot>) q.execute(nameOfSpot);
			if (!results.isEmpty()){
				LOGGER.log(Level.INFO, "NAME OF SPOT: " + results.get(0).getName() );
				LOGGER.log(Level.INFO, "NUMBER OF CLUSTER: " + results.get(0).getCluster().size() );
				return results.get(0);
			}else{
				LOGGER.log(Level.INFO, "SPOT DOES NOT EXIST IN DATASTORE YET");
				return null;
			}

		}finally{
			q.closeAll();
			pm.close();
		}
	}
}
