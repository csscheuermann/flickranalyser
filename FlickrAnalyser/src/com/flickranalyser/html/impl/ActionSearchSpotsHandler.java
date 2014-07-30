package com.flickranalyser.html.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import com.flickranalyser.endpoints.SpotService;
import com.flickranalyser.pojo.Spot;

public class ActionSearchSpotsHandler extends AbstractHtmlRequestHandler
{
  private static final String VIEW_SEARCH_SPOTS = "SearchSpots";
  private static final String MESSAGE_CRAWLQUEUE_SPOT_NAME = "CRAWL QUEUE, FULL SPOT NAME: ";
  private static final Logger LOGGER = Logger.getLogger(ActionSearchSpotsHandler.class.getName());

  public String performActionAndGetNextViewConcrete(HttpServletRequest pRequest, HttpServletResponse pResponse, HttpSession pSession)
  {
    SpotService spotService = new SpotService();

    String crawlSpotName = pRequest.getParameter("crawlAddress");
    LOGGER.log(Level.INFO, MESSAGE_CRAWLQUEUE_SPOT_NAME + crawlSpotName);

    if (crawlSpotName != null) {
      LOGGER.log(Level.INFO, MESSAGE_CRAWLQUEUE_SPOT_NAME+ " SPOT TO CRAWL.");
      Response findSpotByNamePutToCrawlQueue = spotService.getSpotByNamePutToCrawlQueue(crawlSpotName);

      if (findSpotByNamePutToCrawlQueue.getStatus() == 400)
        pRequest.setAttribute("errorCron", findSpotByNamePutToCrawlQueue.getEntity().toString());
      else {
        pRequest.setAttribute("successfulCron", findSpotByNamePutToCrawlQueue.getEntity().toString());
      }

      return VIEW_SEARCH_SPOTS;
    }

    return handleSpotBehavior(pRequest, spotService);
  }

  private String handleSpotBehavior(HttpServletRequest pRequest, SpotService spotService)
  {
    String spotName = pRequest.getParameter("address");
    Spot spotByName = spotService.getSpotByName(spotName);

    if (spotByName == null) {
      pRequest.setAttribute("error", "Spot was not found in Database");
      pRequest.setAttribute("address", spotName);
    } else {
      pRequest.setAttribute("successful", "Spot was found in Database");
      pRequest.setAttribute("spot", spotByName);
    }

    return VIEW_SEARCH_SPOTS;
  }
}