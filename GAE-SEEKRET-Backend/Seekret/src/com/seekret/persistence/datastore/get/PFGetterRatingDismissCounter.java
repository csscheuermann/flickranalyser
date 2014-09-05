package com.seekret.persistence.datastore.get;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.ws.rs.core.Response;

import com.seekret.persistence.datastore.common.PMF;
import com.seekret.pojo.RatingDismissCounter;
import com.seekret.pojo.results.KeyResult;

public class PFGetterRatingDismissCounter
{
  private static final Logger LOGGER = Logger.getLogger(PFGetterRatingDismissCounter.class.getName());

  public static Response hasUserAlreadyDissmissedCluster(String userPrimaryKey, String clusterPrimaryKey)
  {
    PersistenceManager pm = PMF.get().getPersistenceManager();
    try
    {
      String datastoreKey = userPrimaryKey + clusterPrimaryKey;

      Query newQuery = pm.newQuery("select datastoreRatingKey from " + RatingDismissCounter.class.getName());
      newQuery.setFilter("datastoreRatingKey == datastoreKey");
      newQuery.declareParameters("String datastoreKey");

      List resultList = (List)newQuery.execute(datastoreKey);

      if (resultList.size() > 0) {
        LOGGER.log(Level.INFO, "USER ALREADY DISMISSED THIS CLUSTER.");
        return Response.status(200).entity(Boolean.valueOf(true)).build();
       
      }
      LOGGER.log(Level.INFO, "USER CAN DISMISS CLUSTER.");
      return Response.status(200).entity(Boolean.valueOf(false)).build();
    }
    catch (Exception ex)
    {
      LOGGER.log(Level.SEVERE, "EXCEPTION WHILE FETCHING DISMISS COUNTER.");

      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      ex.printStackTrace(pw);
      String stacktrace = sw.toString();

      String errorMsg = "An error occured: \n" + ex.getMessage() + "\n\nStacktrace: \n" + stacktrace;
      LOGGER.log(Level.SEVERE, errorMsg);
      return Response.status(200).entity(Boolean.valueOf(false)).build();
    }
    finally
    {
      pm.close();
    }
  }
  
  
  
  
  public static KeyResult getAllDismissKeysOfSpecifiedUser(String userPrimaryKey){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			Query newQuery = pm.newQuery("select datastoreRatingKey from " + RatingDismissCounter.class.getName());
			newQuery.setFilter("userEmail == userPrimaryKey");
			newQuery.declareParameters("String userPrimaryKey");

			@SuppressWarnings("unchecked")
			List<String> resultList = (List<String>)newQuery.execute(userPrimaryKey);
			
			LOGGER.log(Level.INFO, "RESULT LENGTH " + resultList.size());
			
			return new KeyResult(resultList);
		}
		catch (Exception ex){
			LOGGER.log(Level.SEVERE, "EXCEPTION WHILE FETCHING RATING." + ex.getMessage(), ex);
		}
		finally{
			pm.close();
		}
		return new KeyResult();
	}

  
}