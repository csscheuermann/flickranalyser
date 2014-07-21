package com.flickranalyser.html.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flickranalyser.businesslogic.filter.IFilterStrategy;
import com.flickranalyser.endpoints.SpotService;
import com.flickranalyser.html.common.HelperMethods;
import com.flickranalyser.pojo.Spot;

public class PrepareSpotMapHandler extends AbstractHtmlRequestHandler
{
  private static final Logger LOGGER = Logger.getLogger(PrepareSpotMapHandler.class.getName());
  private final SpotService spotService;

  public PrepareSpotMapHandler()
  {
    this.spotService = new SpotService();
  }

  public String performActionAndGetNextViewConcrete(HttpServletRequest pRequest, HttpServletResponse pResponse, HttpSession pSession)
  {
    String location = pRequest
      .getParameter("location");
    String filterStrategy = pRequest
      .getParameter("strategy");

    LOGGER.log(Level.INFO, "LOCATION: " + location);
    LOGGER.log(Level.INFO, "FILTER STRATEGY: " + filterStrategy);

    StringBuilder fullClassPath = new StringBuilder();
    fullClassPath.append("com.flickranalyser.businesslogic.filter.impl.");
    fullClassPath.append(filterStrategy);

    IFilterStrategy choosenFilterStrategy = (IFilterStrategy)HelperMethods.instantiate(
      fullClassPath.toString(), IFilterStrategy.class);
    LOGGER.log(Level.INFO, "INITIALIZED FILTER STRATEGY:" + 
      choosenFilterStrategy.getClass().getName());

    Spot spot = this.spotService.getSpotByName(location);
    if (spot != null)
    {
      List cluster = spot.getCluster();

      List filteredCluster = choosenFilterStrategy
        .filterCluster(cluster, spot);
      spot.setCluster(filteredCluster);

      int numberOfFilteredClusters = cluster.size() - 
        filteredCluster.size();
      LOGGER.log(Level.INFO, "NUMBER OF FILTERED CLUSTERS: " + 
        numberOfFilteredClusters);

      pRequest.setAttribute("spot", spot);
    }

    return null;
  }
}