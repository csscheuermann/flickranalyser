package com.flickranalyser.endpoints;

import javax.ws.rs.core.Response;

import com.flickranalyser.persistence.datastore.save.PFSaverSpotToCrawl;
import com.flickranalyser.pojo.Spot;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.javadocmd.simplelatlng.LatLng;


@Api(name="spotAPI", version="v1", description="API for Spots.")
public class SpotService {

	
	
	@ApiMethod(name="evaluateCluster")
	public Response putSpotInCronQueue(
			@Named("name") String name, 
			@Named("latitude") double latitude,
			@Named("longitude")double longitude,
			@Named("clusterRadius")double clusterRadius,
			@Named("description") String description,
			@Named("spotRadius")double spotRadius
			) {
		LatLng geoPoint = new LatLng(latitude, longitude);
		Spot spot = new Spot(geoPoint,name,description,clusterRadius,spotRadius, null);
		
		return PFSaverSpotToCrawl.saveSpotToDatastore(spot);
		
	}
	
	
	
}
