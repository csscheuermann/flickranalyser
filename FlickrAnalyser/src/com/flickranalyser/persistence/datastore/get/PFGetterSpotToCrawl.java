package com.flickranalyser.persistence.datastore.get;

import com.flickranalyser.persistence.datastore.common.EntityNameStoreEnum;
import com.flickranalyser.pojo.Spot;
import com.flickranalyser.pojo.common.PojoHelperMethods;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class PFGetterSpotToCrawl {

/**
 * Gets one spot from the Datastore. In case there is nothing in the datastore it will return null.
 * It serves like a queue. Users are able to put a spot in the datastore and the cron job
 * will handle this queue until there is nothing inside anymore.
 * @return Spot or null in case nothing is inside Datastore
 */

	public static Spot getSpotOnwSpotFromDataStore(){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity singleSpotEntity = null;
		final Query getAllQuery = new Query(EntityNameStoreEnum.SPOT_TO_CRAWL.toString());
		final PreparedQuery getAllPreparedQuery = datastore.prepare(getAllQuery);
		singleSpotEntity = getAllPreparedQuery.asSingleEntity();

		if (singleSpotEntity != null){
			return PojoHelperMethods.createSpotFromEntity(singleSpotEntity);
		}
		return null;
	}
}
