package com.flickranalyser.persistence.datastore.common;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class DatastoreHelper {

	private static final String GETTING_ALL_ENTITIES_FROM_TYPE = "GETTING ALL ENTITIES FROM TYPE";
	private static final Logger log = Logger.getLogger(DatastoreHelper.class.getName()); 
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public static DatastoreService getDatastore() {
		return datastore;
	}

	public static List<Entity> getEntityListByEntitNameStoreEnum(String entityNameStore){
		log.log(Level.INFO, GETTING_ALL_ENTITIES_FROM_TYPE + entityNameStore);
		final Query getAllQuery = new Query(entityNameStore);
		final PreparedQuery getAllPreparedQuery = datastore.prepare(getAllQuery);
		return getAllPreparedQuery.asList(FetchOptions.Builder.withDefaults());
	}
	
}
