package com.flickranalyser.persistence.datastore.save;

import javax.jdo.PersistenceManager;
import javax.ws.rs.core.Response;

import com.flickranalyser.persistence.datastore.common.PMF;
import com.flickranalyser.pojo.SeekretUser;

public class PFSaverUser {
	public static Response saveUserToDatastore(SeekretUser user)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(user);
			return Response.status(200).entity("USER ADDED").build();
		}catch(Exception ex){
			return Response.status(400).entity("USER COULD NOT BE ADDED.").build();	
		}finally {
			pm.close();
		}
	}

}