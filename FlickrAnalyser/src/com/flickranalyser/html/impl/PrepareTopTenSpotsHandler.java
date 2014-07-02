package com.flickranalyser.html.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.flickranalyser.html.IHtmlRequestHandler;
import com.flickranalyser.memcache.MemcacheSpot;
import com.flickranalyser.pojo.TopTenSpots;

public class PrepareTopTenSpotsHandler implements IHtmlRequestHandler{

	private static final Logger LOGGER = Logger.getLogger(PrepareTopTenSpotsHandler.class.getName());
	private TopTenSpots topTenSpots;
	@Override
	public String performActionAndGetNextView(HttpServletRequest pRequest, HttpSession pSession) {
		
		topTenSpots = MemcacheSpot.getTopTenSpots();
		//LOGGER.log(Level.INFO, "GOT THE TOP TEN SPOTS. SIZE: " + topTenSpots.getTopTenSpots().size());
		pRequest.setAttribute("topTenSpots", topTenSpots );
		return null;
	}
}
