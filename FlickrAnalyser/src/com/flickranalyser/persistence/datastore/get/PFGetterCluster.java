package com.flickranalyser.persistence.datastore.get;

import com.flickranalyser.persistence.datastore.common.DatastoreHelper;
import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.common.PojoHelperMethods;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;

public class PFGetterCluster {

	
	
	public static Cluster getClusterByDatastoreKey(Key datastoreClusterKey){
		Entity clusterEntity = null;
		try {
			clusterEntity = DatastoreHelper.getDatastore().get(datastoreClusterKey);
		} catch (EntityNotFoundException e) {	
			e.printStackTrace();
			return null;
		} 
		return PojoHelperMethods.createClusterFromEntity(clusterEntity);
	}
	
	public static Entity getClusterEntityByDatastoreKey(Key datastoreClusterKey){
		Entity clusterEntity = null;
		try {
			clusterEntity = DatastoreHelper.getDatastore().get(datastoreClusterKey);
		} catch (EntityNotFoundException e) {	
			e.printStackTrace();
			return null;
		} 
		return clusterEntity;
	}
	
}
