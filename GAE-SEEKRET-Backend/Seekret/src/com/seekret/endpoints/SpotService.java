package com.seekret.endpoints;

import javax.ws.rs.core.Response;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import com.seekret.businesslogic.spotfinder.ISpotFinder;
import com.seekret.businesslogic.spotfinder.impl.NearestSpotFinder;
import com.seekret.memcache.MemcacheSpot;
import com.seekret.persistence.datastore.get.PFGetterSpot;
import com.seekret.pojo.Spot;
import com.seekret.pojo.SpotResultList;

@Api(name="spotAPI", version="v1", description="API for Spots.")
public class SpotService
{
  private final ISpotFinder spotFinder;

  public SpotService()
  {
    this.spotFinder = new NearestSpotFinder();
  }

  @ApiMethod(name="getNearestSpotByAddress")
  public Spot getNearestSpotByAddress(@Named("spotName") String spotName) {
    return this.spotFinder.findSpotByName(spotName);
  }
  
  @ApiMethod(name="getSpotById", path="getSpotById")
  public Spot getSpotById(@Named("spotId") String spotId) {
    return MemcacheSpot.getSpotForSpotName(spotId);
  }

  @ApiMethod(name="getSpotByNamePutToCrawlQueue")
  public Response getSpotByNamePutToCrawlQueue(@Named("spotName") String spotName, @Named("onlyExcludedPictures") boolean onlyExcludedPictures) {
    return this.spotFinder.getSpotByNamePutToCrawlQueue(spotName, onlyExcludedPictures);
  }

  @ApiMethod(name="getTopSpots", scopes={"https://www.googleapis.com/auth/userinfo.email"}, clientIds={"449444009918-25mnitq6rqvgnuj0kvrg9qgm1ms2v4gu.apps.googleusercontent.com", "449444009918-2or65i59u6m5s97arnnsg8econu0rvot.apps.googleusercontent.com"})
  public SpotResultList getTopSpots(User user)
    throws UnauthorizedException
  {
    if (user == null) {
      throw new UnauthorizedException("User is Not Valid");
    }
    return MemcacheSpot.getTopSpots();
  }
}