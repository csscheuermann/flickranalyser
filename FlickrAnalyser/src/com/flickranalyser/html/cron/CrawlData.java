package com.flickranalyser.html.cron;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flickranalyser.businesslogic.impl.SecretPlacesFacade;
import com.flickranalyser.persistence.datastore.delete.PFDeleterSpotToCrawl;
import com.flickranalyser.persistence.datastore.get.PFGetterSpotToCrawl;
import com.flickranalyser.persistence.datastore.save.PFSaverSpot;
import com.flickranalyser.pojo.Spot;
import com.flickranalyser.pojo.SpotToCrawl;

public class CrawlData extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CrawlData.class.getName());

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {


		SpotToCrawl spotToCrawl = PFGetterSpotToCrawl.getOneSpotFromDataStore();
		LOGGER.log(Level.INFO, "SPOT TO CRAWL LAT: " + spotToCrawl.getLatitude());
		LOGGER.log(Level.INFO, "SPOT TO CRAWL LONG: " + spotToCrawl.getLongitude());
		
		if(spotToCrawl != null){
			Spot spot = new Spot(spotToCrawl);
			SecretPlacesFacade secretPlacesFacade = new SecretPlacesFacade(spot);
			Spot spotAttribute = secretPlacesFacade.getSpotInformationForName("munich") ;
			LOGGER.log(Level.INFO, "spotAttribute LAT: " + spotAttribute.getLatitude());
			LOGGER.log(Level.INFO, "spotAttribute LONG: " + spotAttribute.getLongitude());
			PFSaverSpot.saveSpotToDatastore(spotAttribute);
			PFDeleterSpotToCrawl.deleteSpotByKey(spotToCrawl.getDataStoreKey());
			return;
			
		}
		LOGGER.log(Level.INFO, "SPOT WAS NULL - I WON'T DO ANYTHING :) ");

	}


}
