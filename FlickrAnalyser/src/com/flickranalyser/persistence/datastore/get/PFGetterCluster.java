package com.flickranalyser.persistence.datastore.get;

import javax.jdo.PersistenceManager;

import com.flickranalyser.persistence.datastore.common.PMF;
import com.flickranalyser.pojo.Cluster;
import com.google.appengine.api.datastore.Key;


public class PFGetterCluster {



	public static Cluster getClusterByDatastoreKey(Key datastoreClusterKey){
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try{

			Cluster cluster = pm.getObjectById(Cluster.class, datastoreClusterKey);
			if (cluster != null){

				return cluster;
			}else{
				return null;
			}

		} catch (Exception ex) {
			//TODO COS DVV: Exception Handling
			return null;
		}
		finally{
			pm.close();
		}



	}
	//	
	//	public static Entity getClusterEntityByDatastoreKey(Key datastoreClusterKey){
	//		Entity clusterEntity = null;
	//		try {
	//			clusterEntity = DatastoreHelper.getDatastore().get(datastoreClusterKey);
	//		} catch (EntityNotFoundException e) {	
	//			e.printStackTrace();
	//			return null;
	//		} 
	//		return clusterEntity;
	//	}
	//	
}
