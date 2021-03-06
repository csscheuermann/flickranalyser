package com.seekret.persistence.datastore.get;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.seekret.persistence.datastore.common.PMF;
import com.seekret.pojo.Cluster;

public class PFGetterCluster
{
  public static Cluster getClusterByDatastoreKey(Key datastoreClusterKey)
  {
    PersistenceManager pm = PMF.get().getPersistenceManager();
    try
    {
      Cluster cluster = (Cluster)pm.getObjectById(Cluster.class, datastoreClusterKey);
      if (cluster != null)
      {
        return cluster;
      }
      return null;
    }
    catch (Exception ex)
    {
      return null;
    }
    finally {
      pm.close();
    }
  }
}