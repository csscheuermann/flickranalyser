package com.flickranalyser.endpoints;

import javax.ws.rs.core.Response;

import com.flickranalyser.persistence.datastore.get.PFGetterUser;
import com.flickranalyser.persistence.datastore.save.PFSaverUser;
import com.flickranalyser.pojo.SeekretUser;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;

@Api(name="userAPI", version="v1", description="API for User.")
public class UserService {
	  
  public UserService() {
  }

  @ApiMethod(name="addUserToDatastore")
  public Response addUserToDatastore(@Named("email") String email,
		  @Named("fullName") String fullName, 
		  @Named("givenName") String givenName,
		  @Named("profileLink") String profileLink,
		  @Named("picture") String picture) {
    return PFSaverUser.saveUserToDatastore(new SeekretUser(email, fullName, givenName, profileLink, picture));
  }
  
  
  @ApiMethod(name="doesUserExist")
  public SeekretUser getUserByEmail(@Named("email") String email) {
    return PFGetterUser.getUserByEmail(email);
  }
  
  
}