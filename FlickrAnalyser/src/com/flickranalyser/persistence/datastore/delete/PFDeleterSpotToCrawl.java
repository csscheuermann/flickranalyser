package com.flickranalyser.persistence.datastore.delete;

import javax.jdo.PersistenceManager;

import com.flickranalyser.persistence.datastore.common.PMF;
import com.flickranalyser.pojo.SpotToCrawl;
import com.google.appengine.api.datastore.Key;

public class PFDeleterSpotToCrawl {

	
	public static void deleteSpotByKey(Key key){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.deletePersistent(pm.getObjectById(SpotToCrawl.class, key));
		pm.close();
		
	}
}
