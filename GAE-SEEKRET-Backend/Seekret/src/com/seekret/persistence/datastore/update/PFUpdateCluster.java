package com.seekret.persistence.datastore.update;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import javax.ws.rs.core.Response;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.seekret.persistence.datastore.common.PMF;
import com.seekret.pojo.Cluster;

public class PFUpdateCluster{
	private static final Logger LOGGER = Logger.getLogger(PFUpdateCluster.class.getName());
	
	public static Response updateCluster(Key datastoreClusterKey){
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Transaction tx = pm.currentTransaction();
		try{
			tx.begin();
			Cluster cluster = (Cluster) pm.getObjectById(Cluster.class,datastoreClusterKey );
			cluster.incrementDismissCounter();
			tx.commit();
			LOGGER.log(Level.INFO, "Increased Dismisscount for Cluster " +  KeyFactory.keyToString(datastoreClusterKey));
		}
		catch (Exception e){
			LOGGER.log(Level.SEVERE, "EXCEPTION : " + e.getMessage(), e);
			
			if (tx.isActive()){
				tx.rollback();
			}
			return Response.status(400).entity("CLUSTER DISMISSCOUNTER COULD NOT BE UPDATED").build();	
		}
		return Response.status(200).build();	
	}
}