package com.seekret.endpoints;

import javax.ws.rs.core.Response;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.seekret.persistence.datastore.get.PFGetterUser;
import com.seekret.persistence.datastore.save.PFSaverUser;
import com.seekret.pojo.SeekretUser;

@Api(name="userAPI", 
version="v1",
description="API for User.",
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
audiences = {Constants.ANDROID_AUDIENCE}
)
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