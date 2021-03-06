package com.seekret.persistence.datastore.delete;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.seekret.persistence.datastore.common.PMF;
import com.seekret.pojo.SpotToCrawl;

public class PFDeleterSpotToCrawl {

	
	public static void deleteSpotByKey(Key key){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.deletePersistent(pm.getObjectById(SpotToCrawl.class, key));
		pm.close();
		
	}
}
