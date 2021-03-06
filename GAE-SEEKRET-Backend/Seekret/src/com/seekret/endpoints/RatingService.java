package com.seekret.endpoints;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import com.seekret.persistence.datastore.get.PFGetterRating;
import com.seekret.persistence.datastore.get.PFGetterRatingDismissCounter;
import com.seekret.persistence.datastore.save.PFSaverRating;
import com.seekret.pojo.results.KeyResult;

@Api(name="ratingAPI",
version="v1",
description="This API serves everything needed for Ratings",
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
audiences = {Constants.ANDROID_AUDIENCE}
)

public class RatingService
{
	
	private static final Logger LOGGER = Logger.getLogger(RatingService.class.getName());
	
	
  @ApiMethod(name="addNewRating")
  public Response addNewRating(User user, @Named("clusterPrimaryKey") String clusterPrimaryKey) throws UnauthorizedException
  {
	  if (user == null) {
	      throw new UnauthorizedException("User is Not Valid");
	  }
    return PFSaverRating.saveRatingToDatastore(user.getEmail(), clusterPrimaryKey);
  }

  @ApiMethod(name="hasUserAlreadyVoted")
  public Response hasUserAlreadyVoted(User user, @Named("clusterPrimaryKey") String clusterPrimaryKey) throws UnauthorizedException
  {
	  if (user == null) {
	      throw new UnauthorizedException("User is Not Valid");
	  }
    return PFGetterRating.hasUserAlreadyRated(user.getEmail(), clusterPrimaryKey);
  }
  
  
  @ApiMethod(name="hasAlreadyDismissed")
  public Response hasAlreadyDismissed(User user, @Named("clusterPrimaryKey") String clusterPrimaryKey) throws UnauthorizedException
  {
	  if (user == null) {
	      throw new UnauthorizedException("User is Not Valid");
	  }
    return PFGetterRatingDismissCounter.hasUserAlreadyDissmissedCluster(user.getEmail(), clusterPrimaryKey);
  }
  
  @ApiMethod(name="hasAlreadyDismissedOrVoted")
  public Response hasAlreadyDismissedOrVoted(User user, @Named("clusterPrimaryKey") String clusterPrimaryKey) throws UnauthorizedException
  {
	  if (user == null) {
	      throw new UnauthorizedException("User is Not Valid");
	  }
	  
	Response dismissedResponse =  PFGetterRatingDismissCounter.hasUserAlreadyDissmissedCluster(user.getEmail(), clusterPrimaryKey);
	Response voteResponseResponse =  PFGetterRating.hasUserAlreadyRated(user.getEmail(), clusterPrimaryKey);  
	 
	String dismissedString = dismissedResponse.getEntity().toString();
	String alreadyVotedString = voteResponseResponse.getEntity().toString();
	
	LOGGER.log(Level.INFO, "DISMISSED VALUE STRING: " + dismissedString);
	LOGGER.log(Level.INFO, "VOTE VALUE STRING: " + alreadyVotedString);
	
	boolean dismissedResponseBoolean = Boolean.valueOf(dismissedString);
	boolean voteResponseResponseBoolean = Boolean.valueOf(alreadyVotedString);
	
	LOGGER.log(Level.INFO, "DISMISSED VALUE: " + dismissedResponseBoolean);
	LOGGER.log(Level.INFO, "VOTE VALUE: " + voteResponseResponseBoolean);
	
	if (dismissedResponseBoolean || voteResponseResponseBoolean ){
		
		LOGGER.log(Level.INFO, "RETURN: " + true); 
		 return Response.status(200).entity(Boolean.valueOf(true)).build();
	}
	LOGGER.log(Level.INFO, "RETURN: " + false);
    return Response.status(200).entity(Boolean.valueOf(false)).build();
  }
  
  
  @ApiMethod(name="getAllRatingKeysOfSpecifiedUser", path="getAllRatingKeysOfSpecifiedUser")
  public KeyResult getAllRatingKeysOfSpecifiedUser(@Named("userPrimaryKey") String userPrimaryKey){
    
	  return PFGetterRating.getAllRatingKeysOfSpecifiedUser(userPrimaryKey);
  }
  
  @ApiMethod(name="getAllDismissKeysOfSpecifiedUser", path="getAllDismissKeysOfSpecifiedUser")
  public KeyResult getAllDismissKeysOfSpecifiedUser(@Named("userPrimaryKey") String userPrimaryKey){
	  return PFGetterRatingDismissCounter.getAllDismissKeysOfSpecifiedUser(userPrimaryKey);
  }
  
  
  
}