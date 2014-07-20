package com.flickranalyser.endpoints;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import com.flickranalyser.businesslogic.spotfinder.ISpotFinder;
import com.flickranalyser.businesslogic.spotfinder.impl.NearestSpotFinder;
import com.flickranalyser.persistence.datastore.get.PFGetterSpot;
import com.flickranalyser.pojo.Spot;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.users.User;


@Api(name="spotAPI", version="v1", description="API for Spots.")
public class SpotService {
	
	private final ISpotFinder spotFinder;
	
	public SpotService() {
		spotFinder = new NearestSpotFinder();
	}
	
	@ApiMethod(name="getSpotByName")
	public Spot getSpotByName(@Named("spotName") String spotName) {
		return spotFinder.findSpotByName(spotName);
	}
	
	@ApiMethod(name="getSpotByNamePutToCrawlQueue")
	public Response getSpotByNamePutToCrawlQueue(@Named("spotName") String spotName) {
		return spotFinder.getSpotByNamePutToCrawlQueue(spotName);
	}
	
	
	@ApiMethod(name="getTopSpots",	
			scopes = {Constants.EMAIL_SCOPE},
			clientIds = {Constants.WEB_CLIENT_ID,  
			com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID})
	public ArrayList<String>getTopSpots(User user) {
		return (ArrayList<String>) PFGetterSpot.getTopSpots();
	}
	
	
}
