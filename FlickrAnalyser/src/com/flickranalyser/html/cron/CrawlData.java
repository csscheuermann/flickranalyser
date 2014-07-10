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
		SpotToCrawl resultSpotToCrawlFromDatastore = PFGetterSpotToCrawl.getOneSpotFromDataStore();
		
		if(resultSpotToCrawlFromDatastore != null){
			Spot spot = new Spot(resultSpotToCrawlFromDatastore);
			SecretPlacesFacade secretPlacesFacade = new SecretPlacesFacade(spot);
			//TODO COS DVV: We can remove the parameter I think
			Spot spotAttribute = secretPlacesFacade.getSpotInformationForName("munich") ;
			PFSaverSpot.saveSpotToDatastore(spotAttribute);
			PFDeleterSpotToCrawl.deleteSpotByKey(resultSpotToCrawlFromDatastore.getDataStoreKey());
			return;
		}
		
		LOGGER.log(Level.INFO, "NO SPOTS TO CRAWL IN QUEUE - I WON'T DO ANYTHING.");
	}


}
