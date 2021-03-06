package com.seekret.endpoints;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.seekret.businesslogic.spotfinder.impl.NearestSpotFinder;
import com.seekret.persistence.datastore.get.PFGetterRatingDismissCounter;
import com.seekret.persistence.datastore.save.PFSaverCluster;
import com.seekret.persistence.datastore.save.PFSaverRatingDismissCounter;
import com.seekret.pojo.responses.Address;





@Api(name="clusterAPI",
version="v1", 
description="This API serves everything needed to update a cluster.",
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
audiences = {Constants.ANDROID_AUDIENCE}
)



public class ClusterService
{

	private static final Logger LOGGER = Logger.getLogger(ClusterService.class.getName());

	
	
	@ApiMethod(name="evaluateCluster")
	public Response evaluateCluster(User user, @Named("datastoreKeyOfCluster") String datastoreKeyOfCluster, @Named("touristicnessRatingFrom1To10") int touristicnessRatingFrom1To10, @Named("spotName") String spotName) throws UnauthorizedException{
		 if (user == null) {
		      throw new UnauthorizedException("User is Not Valid");
		  }
		 RatingService ratingService = new RatingService();
	
		Response hasUserAlreadyVoted = ratingService.hasUserAlreadyVoted(user, datastoreKeyOfCluster);

			if (hasUserAlreadyVoted.getStatus() == 200) {
				boolean hasUserAlreadyVotedResult = ((Boolean)hasUserAlreadyVoted.getEntity()).booleanValue();
				if (hasUserAlreadyVotedResult) {					
					LOGGER.log(Level.INFO, "ALREADY VOTED.");
					return Response.status(200).entity("YOU HAVE ALREADY VOTED.").build();			
				}else{
					ratingService.addNewRating(user, datastoreKeyOfCluster);
					LOGGER.log(Level.INFO, "ADDED RATING");
					return PFSaverCluster.evaluateTouristicness(KeyFactory.stringToKey(datastoreKeyOfCluster), touristicnessRatingFrom1To10, spotName);		
				}
			}
		return Response.status(400).entity("SOMETHING WENT WRONG DURING evaluateCluster.").build();		
	}
	
	@ApiMethod(name="getAddressFromLatLng")
	public Address getAddressFromLatLng(User user, @Named("latitude") double latitude, @Named("longitude") double longitude)
			throws UnauthorizedException{
		if (user == null) throw new UnauthorizedException("User is Not Valid");

		NearestSpotFinder nsf = new NearestSpotFinder();
		String findAddressByLatLng = nsf.findAddressByLatLng(latitude, longitude);
		return new Address(findAddressByLatLng);
	}
	@ApiMethod(name="incrementDismissCount")
	public Response incrementDismissCount(User user, @Named("datastoreKeyOfCluster") String datastoreKeyOfCluster)
			throws UnauthorizedException {
		if (user == null) {
			throw new UnauthorizedException("USER IS NOT VALID");
		}

		String userKey = user.getEmail();
		if (Boolean.valueOf(PFGetterRatingDismissCounter.hasUserAlreadyDissmissedCluster(userKey, datastoreKeyOfCluster).getEntity().toString())) {
			return Response.status(200).entity("YOU HAVE ALREADY DISMISSED THIS SPOT.").build();
		}

		return PFSaverRatingDismissCounter.saveRatingToDatastore(userKey, datastoreKeyOfCluster);
	}



}