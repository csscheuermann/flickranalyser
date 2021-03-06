package com.seekret.persistence.datastore.get;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.seekret.persistence.datastore.common.PMF;
import com.seekret.pojo.Cluster;
import com.seekret.pojo.Spot;
import com.seekret.pojo.SpotResultList;

public class PFGetterSpot
{
  private static final Logger LOGGER = Logger.getLogger(PFGetterSpot.class.getName());

  public static Spot getSpotByName(String nameOfSpot)
  {
    PersistenceManager pm = PMF.get().getPersistenceManager();

    pm.getFetchPlan().addGroup("eagerClusterLoading");

    LOGGER.log(Level.INFO, "NAME OF SPOT TO SEARCH: " + nameOfSpot);
    try {
      Key k = KeyFactory.createKey(Spot.class.getSimpleName(), nameOfSpot);
      Spot spot = (Spot)pm.getObjectById(Spot.class, k);
      if (spot != null)
      {
        LOGGER.log(Level.INFO, "NAME OF SPOT: " + spot.getName());
        LOGGER.log(Level.INFO, "NUMBER OF CLUSTER: " + spot.getCluster().size());
        if (spot.getCluster().size() > 1) {
          LOGGER.log(Level.INFO, " FIRST CLUSTER " + ((Cluster)spot.getCluster().get(0)).toString());
          LOGGER.log(Level.INFO, " POI Size " + ((Cluster)spot.getCluster().get(0)).getPointOfInterestList().size());
        }
        return spot;
      }
      LOGGER.log(Level.INFO, "SPOT DOES NOT EXIST IN DATASTORE YET");
      return null;
    }
    catch (Exception ex)
    {
      LOGGER.log(Level.INFO, "COULD NOT FIND SPOT WITH NAME " + nameOfSpot);
      return null;
    }
    finally {
      pm.close();
    }
  }

  @SuppressWarnings("unchecked")
public static SpotResultList getTopSpots() {
    PersistenceManager pm = PMF.get().getPersistenceManager();

    Query q = pm.newQuery(Spot.class);
    try {
      q.setOrdering("overallMaxViewNumberPerCluster desc");
      q.setRange(0, 40);

  	List<Spot> results = (List<Spot>) q.execute();
	//TODO COS: Discuss with Daniel
	//Needed to be done due to serializable issues, returning the result
	// itself it is not serializable
 
	LinkedList<String> resultList = new LinkedList<String>();
	if (!results.isEmpty()){
		for (Spot spot : results) {
			resultList.add(spot.getName());
		}
		return new SpotResultList(resultList);
	}
      LOGGER.log(Level.INFO, "NO TOP TEN SPOTS FOUND.");
      return new SpotResultList(resultList);
    }
    finally
    {
      q.closeAll();
      pm.close();
      LOGGER.log(Level.INFO, "CLOSED QUERY AND PM.");
    }
  }
}