package com.seekret.endpoints;

import java.util.List;

import javax.ws.rs.core.Response;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import com.seekret.businesslogic.filterstrategies.IFilterStrategy;
import com.seekret.businesslogic.spotfinder.ISpotFinder;
import com.seekret.businesslogic.spotfinder.impl.NearestSpotFinder;
import com.seekret.html.common.HelperMethods;
import com.seekret.memcache.MemcacheSpot;
import com.seekret.pojo.Cluster;
import com.seekret.pojo.Spot;
import com.seekret.pojo.SpotResultList;

@Api(name="spotAPI",
version="v1", 
description="API for Spots.",
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
audiences = {Constants.ANDROID_AUDIENCE})
public class SpotService
{
  private final ISpotFinder spotFinder;

  public SpotService(){
    this.spotFinder = new NearestSpotFinder();
  }

  @ApiMethod(name="getNearestSpotByAddress")
  public Spot getNearestSpotByAddress(@Named("spotName") String spotName) {
    return this.spotFinder.findSpotByName(spotName);
  }
  
  
  @ApiMethod(name="getSeekretSpotsBySpotName")
  public List<Cluster> getSeekretSpotsBySpotName(User user, @Named("spotName") String spotName) throws UnauthorizedException, RuntimeException {
	  if (user == null) {
	      throw new UnauthorizedException("User is Not Valid");
	    }
	  StringBuilder fullClassPath = new StringBuilder();
	  fullClassPath.append("com.seekret.businesslogic.filterstrategies.impl.SeekretFinderStrategy");
	  IFilterStrategy choosenFilterStrategy = HelperMethods.instantiate(fullClassPath.toString(), IFilterStrategy.class);
	  choosenFilterStrategy.setIgnoreDismissedClustersFlag(true);
	  choosenFilterStrategy.setIgnorePictureLessClusters(true);
	  Spot spot = this.getSpotById(spotName);
		if (spot != null){
			List<Cluster> cluster = spot.getCluster();
			List<Cluster> filteredCluster = choosenFilterStrategy.filterCluster(cluster, spot);
			return filteredCluster;
		}else{
			throw new RuntimeException("SPOT WAS NULL IN SERVICE setSeekretSpotsBySpotName.");
		}
  }
  
  
  
  @ApiMethod(name="getSpotById", path="getSpotById")
  public Spot getSpotById(@Named("spotId") String spotId) {
    return MemcacheSpot.getSpotForSpotName(spotId);
  }

  @ApiMethod(name="getSpotByNamePutToCrawlQueue")
  public Response getSpotByNamePutToCrawlQueue(@Named("spotName") String spotName, @Named("onlyExcludedPictures") boolean onlyExcludedPictures) {
    return this.spotFinder.getSpotByNamePutToCrawlQueue(spotName, onlyExcludedPictures);
  }

  @ApiMethod(name="getTopSpots")
  public SpotResultList getTopSpots(User user)
    throws UnauthorizedException{
    if (user == null) {
      throw new UnauthorizedException("User is Not Valid");
    }
    return MemcacheSpot.getTopSpots();
  }
}