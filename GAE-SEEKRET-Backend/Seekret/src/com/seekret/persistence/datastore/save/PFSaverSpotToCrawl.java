package com.seekret.persistence.datastore.save;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.ws.rs.core.Response;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.seekret.persistence.datastore.common.PMF;
import com.seekret.persistence.datastore.get.PFGetterSpot;
import com.seekret.persistence.datastore.get.PFGetterSpotToCrawl;
import com.seekret.pojo.Spot;
import com.seekret.pojo.SpotToCrawl;

public class PFSaverSpotToCrawl
{
  private static final Logger LOGGER = Logger.getLogger(PFSaverSpotToCrawl.class.getName());
  private static String errorMessage;
  private static String successMessage;

  public static Response saveSpotToDatastore(SpotToCrawl spot)
  {
    if (!checkIfSpotAlreadyExists(spot)) {
      PersistenceManager pm = PMF.get().getPersistenceManager();
      try {
        Key dataStoreKey = KeyFactory.createKey(SpotToCrawl.class.getSimpleName(), spot.getName());
        spot.setDataStoreKey(dataStoreKey);
        pm.makePersistent(spot);
        successMessage = "SPOT " + spot.getName() + " SUCCESSFULLY ADDED TO CRAWLQUEUE.";
      } finally {
        pm.close();
      }
    } else {
      LOGGER.log(Level.INFO, errorMessage);
      return Response.status(400).entity(errorMessage).build();
    }

    return Response.status(200).entity(successMessage).build();
  }

  private static boolean checkIfSpotAlreadyExists(SpotToCrawl spot) {
    Spot spotByName = PFGetterSpot.getSpotByName(spot.getName());
    SpotToCrawl spotToCrawlByName = PFGetterSpotToCrawl.getSpotToCrawlByName(spot.getName());
    if (spotByName != null) {
      errorMessage = "SPOT ALREADY EXISTS IN DATASTORE.";
      return true;
    }if (spotToCrawlByName != null) {
      errorMessage = "SPOT ALREADY IS IN THE SEEKRET QUEUE.";
      return true;
    }
    return false;
  }
}