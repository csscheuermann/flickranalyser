package com.seekret.html.cron;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seekret.businesslogic.impl.SecretPlacesFacade;
import com.seekret.persistence.datastore.delete.PFDeleterSpotToCrawl;
import com.seekret.persistence.datastore.get.PFGetterSpotToCrawl;
import com.seekret.persistence.datastore.save.PFSaverSpot;
import com.seekret.pojo.Spot;
import com.seekret.pojo.SpotToCrawl;

public class CrawlData extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CrawlData.class.getName());

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		SpotToCrawl resultSpotToCrawlFromDatastore = PFGetterSpotToCrawl.getOneSpotFromDataStore();
		
		if(resultSpotToCrawlFromDatastore != null){
			Spot spot = new Spot(resultSpotToCrawlFromDatastore);
			SecretPlacesFacade secretPlacesFacade = new SecretPlacesFacade(spot, resultSpotToCrawlFromDatastore.isOnlyExcludedPictures());
			//TODO COS DVV: We can remove the parameter I think
			Spot spotAttribute = secretPlacesFacade.getSpotInformationForName("munich") ;
			PFSaverSpot.saveSpotToDatastore(spotAttribute);
			PFDeleterSpotToCrawl.deleteSpotByKey(resultSpotToCrawlFromDatastore.getDataStoreKey());
			return;
		}
		
		LOGGER.log(Level.INFO, "NO SPOTS TO CRAWL IN QUEUE - I WON'T DO ANYTHING.");
	}


}
