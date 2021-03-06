package com.seekret.html.request.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import com.seekret.endpoints.SpotService;
import com.seekret.html.ViewNameEnum;
import com.seekret.pojo.Spot;

public class ActionSearchSpotsHandler extends AbstractHtmlRequestHandler
{
  private static final String MESSAGE_CRAWLQUEUE_SPOT_NAME = "CRAWL QUEUE, FULL SPOT NAME: ";
  private static final Logger LOGGER = Logger.getLogger(ActionSearchSpotsHandler.class.getName());

  public ViewNameEnum performActionAndGetNextViewConcrete(HttpServletRequest pRequest, HttpServletResponse pResponse, HttpSession pSession)
  {
    SpotService spotService = new SpotService();

    String crawlSpotName = pRequest.getParameter("crawlAddress");
    LOGGER.log(Level.INFO, MESSAGE_CRAWLQUEUE_SPOT_NAME + crawlSpotName);

    if (crawlSpotName != null) {
      LOGGER.log(Level.INFO, MESSAGE_CRAWLQUEUE_SPOT_NAME+ " SPOT TO CRAWL.");
      boolean onlyExcludedPictures = Boolean.parseBoolean(pRequest.getParameter("onlyExcluded"));
      LOGGER.log(Level.INFO, "onlyExcluded: " + onlyExcludedPictures);
      
      Response findSpotByNamePutToCrawlQueue = spotService.getSpotByNamePutToCrawlQueue(crawlSpotName, onlyExcludedPictures);

      
      if (findSpotByNamePutToCrawlQueue.getStatus() == 400)
        pRequest.setAttribute("errorCron", findSpotByNamePutToCrawlQueue.getEntity().toString());
      else {
        pRequest.setAttribute("successfulCron", findSpotByNamePutToCrawlQueue.getEntity().toString());
      }

      return ViewNameEnum.SEARCH_SPOT;
    }

    return handleSpotBehavior(pRequest, spotService);
  }

  private ViewNameEnum handleSpotBehavior(HttpServletRequest pRequest, SpotService spotService)
  {
    String spotName = pRequest.getParameter("address");
    Spot spotByName = spotService.getNearestSpotByAddress(spotName);

    if (spotByName == null) {
      pRequest.setAttribute("error", "Spot was not found in Database");
      pRequest.setAttribute("address", spotName);
    } else {
      pRequest.setAttribute("successful", "Spot was found in Database");
      pRequest.setAttribute("spot", spotByName);
    }

    return ViewNameEnum.SEARCH_SPOT;
  }
}