package com.flickranalyser.persistence.datastore.get;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import com.flickranalyser.persistence.datastore.common.PMF;
import com.flickranalyser.pojo.SeekretUser;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class PFGetterUser {


	private static final Logger LOGGER = Logger.getLogger(PFGetterUser.class.getName());

	public static SeekretUser getUserByEmail(String eMail){

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try{
			Key k = KeyFactory.createKey(SeekretUser.class.getSimpleName(), eMail);
			SeekretUser user = pm.getObjectById(SeekretUser.class, k);
			if (user != null){
				LOGGER.log(Level.INFO, "FOUND USER IN DATASTORE." );
				return user;
			}else{
				LOGGER.log(Level.INFO, "USER NOT IN DATASTORE.");
				return null;
			}

		} catch (Exception ex) {
			//TODO COS: EXCEPTION HANDLING talk to daniel
			LOGGER.log(Level.SEVERE, "EXCEPTION WHILE FETCHING USER.");
			return null;
		}
		finally{
			pm.close();
		}
	}
}
