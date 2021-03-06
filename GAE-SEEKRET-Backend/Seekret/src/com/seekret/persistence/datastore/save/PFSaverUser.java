package com.seekret.persistence.datastore.save;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.ws.rs.core.Response;

import com.seekret.persistence.datastore.common.PMF;
import com.seekret.pojo.SeekretUser;

public class PFSaverUser {
	
	private static final Logger LOGGER = Logger.getLogger(PFSaverUser.class.getName());
	
	public static Response saveUserToDatastore(SeekretUser user)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			
			LOGGER.log(Level.INFO, "WILL STORE USER");
			pm.makePersistent(user);
			return Response.status(200).entity("USER ADDED").build();
		}catch(Exception ex){
			LOGGER.log(Level.SEVERE, "ERROR WHILE SAVING USER TO DATASTORE" + ex.getMessage(), ex);
			return Response.status(400).entity("USER COULD NOT BE ADDED.").build();	
		}finally {
			pm.close();
		}
	}

}