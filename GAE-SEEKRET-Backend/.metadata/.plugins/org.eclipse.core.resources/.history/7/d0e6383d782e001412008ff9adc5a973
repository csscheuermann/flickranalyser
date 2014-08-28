package com.seekret.endpoints;

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





@Api(name="clusterAPI", version="v1", description="This API serves everything needed to update a cluster.")
public class ClusterService
{

	@ApiMethod(name="evaluateCluster")
	public Response evaluateCluster(@Named("datastoreKeyOfCluster") String datastoreKeyOfCluster, @Named("touristicnessRatingFrom1To10") int touristicnessRatingFrom1To10, @Named("spotName") String spotName){
		return PFSaverCluster.evaluateTouristicness(KeyFactory.stringToKey(datastoreKeyOfCluster), touristicnessRatingFrom1To10, spotName);
	}

	@ApiMethod(name="getAddressFromLatLng", scopes={"https://www.googleapis.com/auth/userinfo.email"}, clientIds={"1099379908084-erlt14509li8acjpd7m20770t9gi5c0g.apps.googleusercontent.com", "292824132082.apps.googleusercontent.com"})
	public Address getAddressFromLatLng(User user, @Named("latitude") double latitude, @Named("longitude") double longitude)
			throws UnauthorizedException{
		if (user == null) throw new UnauthorizedException("User is Not Valid");

		NearestSpotFinder nsf = new NearestSpotFinder();
		String findAddressByLatLng = nsf.findAddressByLatLng(latitude, longitude);
		return new Address(findAddressByLatLng);
	}

	@ApiMethod(name="incrementDismissCount", scopes={"https://www.googleapis.com/auth/userinfo.email"}, clientIds={"1099379908084-erlt14509li8acjpd7m20770t9gi5c0g.apps.googleusercontent.com", "292824132082.apps.googleusercontent.com"})
	public Response incrementDismissCount(User user, @Named("datastoreKeyOfCluster") String datastoreKeyOfCluster)
			throws UnauthorizedException {
		if (user == null) {
			throw new UnauthorizedException("USER IS NOT VALID");
		}

		String userKey = user.getEmail();
		if (PFGetterRatingDismissCounter.hasUserAlreadyDissmissedCluster(userKey, datastoreKeyOfCluster)) {
			return Response.status(200).entity("YOU HAVE ALREADY DISMISSED THIS SPOT.").build();
		}

		return PFSaverRatingDismissCounter.saveRatingToDatastore(userKey, datastoreKeyOfCluster);
	}



}