package com.flickranalyser.persistence.datastore.getter;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.flickranalyser.persistence.datastore.common.EntityNameStoreEnum;
import com.flickranalyser.persistence.datastore.common.properties.PropertiesSpot;
import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.Spot;
import com.flickranalyser.pojo.common.PojoHelperMethods;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class PFGetterSpot {



	public static Spot getSpotByName(String nameOfSpot){
		Spot spot = new Spot();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Entity singleSpotEntity = null;
		final Query getAllQuery = new Query(EntityNameStoreEnum.SPOT.toString());
		Filter teamNameFilter = new FilterPredicate(PropertiesSpot.NAME.toString(), FilterOperator.EQUAL, nameOfSpot  );
		getAllQuery.setFilter(teamNameFilter);
		final PreparedQuery getAllPreparedQuery = datastore.prepare(getAllQuery);
		singleSpotEntity = getAllPreparedQuery.asSingleEntity();
		spot = PojoHelperMethods.createSpotFromEntity(singleSpotEntity);

		//Now get all Cluster in Spot
		final Query getAllCluster = new Query(EntityNameStoreEnum.CLUSTER.toString()).setAncestor(spot.getDataStoreKey());
		final PreparedQuery getAllClusterPreparedQuery = datastore.prepare(getAllCluster);
		List<Entity> asList = getAllClusterPreparedQuery.asList(FetchOptions.Builder.withDefaults());
		Set<Cluster> clusterSet = PojoHelperMethods.createClusterListFromEntity(asList);
		spot.setCluster(clusterSet);

		return spot;
	}
}
