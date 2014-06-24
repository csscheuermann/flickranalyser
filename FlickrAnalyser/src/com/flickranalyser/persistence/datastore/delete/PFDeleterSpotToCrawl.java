package com.flickranalyser.persistence.datastore.delete;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Key;

public class PFDeleterSpotToCrawl {

	
	public static void deleteSpotByKey(Key key){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		datastore.delete(key);
	}
}
