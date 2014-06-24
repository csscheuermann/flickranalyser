package com.flickranalyser.html;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flickranalyser.businesslogic.filter.decorator.impl.EquallyWeightedScoreDecorator;
import com.flickranalyser.businesslogic.impl.SecretPlacesFacade;
import com.flickranalyser.persistence.datastore.delete.PFDeleterSpotToCrawl;
import com.flickranalyser.persistence.datastore.get.PFGetterSpotToCrawl;
import com.flickranalyser.persistence.datastore.save.PFSaverSpot;
import com.flickranalyser.pojo.Spot;

public class CrawlData extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CrawlData.class.getName());

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {


		Spot spotOnwSpotFromDataStore = PFGetterSpotToCrawl.getSpotOnwSpotFromDataStore();

		if(spotOnwSpotFromDataStore != null){
			SecretPlacesFacade secretPlacesFacade = new SecretPlacesFacade(spotOnwSpotFromDataStore);
			Spot spotAttribute = secretPlacesFacade.getSpotInformationForName("munich") ;

			PFSaverSpot.saveSpotToDatastore(spotAttribute);
			PFDeleterSpotToCrawl.deleteSpotByKey(spotOnwSpotFromDataStore.getDataStoreKey());
			return;
			
		}
		LOGGER.log(Level.INFO, "SPOT WAS NULL - I WON'T DO ANYTHING :) ");

	}


}
