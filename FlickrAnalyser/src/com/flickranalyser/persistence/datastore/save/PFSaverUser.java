package com.flickranalyser.persistence.datastore.save;

import javax.jdo.PersistenceManager;

import com.flickranalyser.persistence.datastore.common.PMF;
import com.flickranalyser.pojo.User;

public class PFSaverUser
{
  public static void saveUserToDatastore(User user)
  {
    PersistenceManager pm = PMF.get().getPersistenceManager();
    try {
      pm.makePersistent(user);
    } finally {
      pm.close();
    }
  }
}