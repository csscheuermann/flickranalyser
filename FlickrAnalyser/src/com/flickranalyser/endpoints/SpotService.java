package com.flickranalyser.endpoints;

import javax.ws.rs.core.Response;

import com.flickranalyser.memcache.MemcacheSpot;
import com.flickranalyser.persistence.datastore.save.PFSaverSpotToCrawl;
import com.flickranalyser.pojo.Spot;
import com.flickranalyser.pojo.SpotToCrawl;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;


@Api(name="spotAPI", version="v1", description="API for Spots.")
public class SpotService {
	
	@ApiMethod(name="putSpotInCronQueue")
	public Response putSpotInCronQueue(@Named("spotName") String spotName, 
			@Named("latitude") double latitude,
			@Named("longitude")double longitude,
			@Named("clusterRadius")double clusterRadius,
			@Named("description") String description,
			@Named("spotRadius")double spotRadius
			) {
		SpotToCrawl spotToCrawl = new SpotToCrawl(latitude, longitude, spotName, description, clusterRadius, spotRadius, null,0,0);
		
		return PFSaverSpotToCrawl.saveSpotToDatastore(spotToCrawl);
		
	}
	
	
	@ApiMethod(name="getSpotByName")
	public Spot getSpotByName(@Named("spotName") String spotName) {
		return MemcacheSpot.getSpotForSpotName(spotName);
		
	}
	
	
	
}
