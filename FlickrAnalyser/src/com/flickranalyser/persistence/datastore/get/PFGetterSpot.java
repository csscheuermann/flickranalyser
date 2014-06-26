package com.flickranalyser.persistence.datastore.get;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.persistence.datastore.common.EntityNameStoreEnum;
import com.flickranalyser.persistence.datastore.common.properties.PropertiesCluster;
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
import com.google.appengine.api.datastore.Query.SortDirection;

public class PFGetterSpot {

	private static final Logger LOGGER = Logger.getLogger(PFGetterSpot.class.getName());

	public static Spot getSpotByName(String nameOfSpot){
		Spot spot = new Spot();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Entity singleSpotEntity = null;
		final Query getAllQuery = new Query(EntityNameStoreEnum.SPOT.toString());
		Filter teamNameFilter = new FilterPredicate(PropertiesSpot.NAME.toString(), FilterOperator.EQUAL, nameOfSpot  );
		getAllQuery.setFilter(teamNameFilter);
		final PreparedQuery getAllPreparedQuery = datastore.prepare(getAllQuery);
		singleSpotEntity = getAllPreparedQuery.asSingleEntity();
		
		if (singleSpotEntity == null){
			LOGGER.log(Level.INFO, "ENTITY IS NULL.");
			return null;
		}
		spot = PojoHelperMethods.createSpotFromEntity(singleSpotEntity);

		if (spot == null){
			LOGGER.log(Level.INFO, "SPOT IS NULL.");
			return null;
		}
		
		//Now get all Cluster in Spot
		final Query getAllCluster = new Query(EntityNameStoreEnum.CLUSTER.toString()).setAncestor(spot.getDataStoreKey());
		/*TODO COS: To discuss, it seems like not sorting the result causes diffent clustering and different results (After sort still same behavior)
		I think the problem is the Set, does not take care of order. I changed everything to List/Linkedlist and added sort filter
		to the PFGetter, no the results are stable ... discuss with daniel.
		*/
		getAllCluster.addSort(PropertiesCluster.NUMBER_OF_POIS.toString(), SortDirection.ASCENDING);
		getAllCluster.addSort(PropertiesCluster.OVERALL_VIEWS.toString(), SortDirection.ASCENDING);

		
		final PreparedQuery getAllClusterPreparedQuery = datastore.prepare(getAllCluster);
		List<Entity> asList = getAllClusterPreparedQuery.asList(FetchOptions.Builder.withDefaults());
		List<Cluster> clusterSet = PojoHelperMethods.createClusterListFromEntity(asList);
		spot.setCluster(clusterSet);

		return spot;
	}
}
