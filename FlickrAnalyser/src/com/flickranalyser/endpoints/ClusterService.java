package com.flickranalyser.endpoints;

import javax.ws.rs.core.Response;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;

@Api(name="clusterAPI", version="v1", description="This API serves everything needed to update a cluster.")
public class ClusterService {

	
	
	@ApiMethod(name="evaluateCluster")
	public Response evaluateCluster(@Named("datastoreKeyOfCluster") String datastoreKeyOfCluster, 
			@Named("touristicnessRatingFrom1To10") int touristicnessRatingFrom1To10,
			@Named("spotName") String spotName
			) {
		return Response.ok().build();
		//return PFSaverCluster.evaluateTouristicness(KeyFactory.stringToKey(datastoreKeyOfCluster), touristicnessRatingFrom1To10, spotName);
	}
}
