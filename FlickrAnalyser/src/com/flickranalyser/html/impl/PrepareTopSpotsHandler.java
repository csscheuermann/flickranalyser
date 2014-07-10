package com.flickranalyser.html.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.flickranalyser.memcache.MemcacheSpot;
import com.flickranalyser.pojo.SpotResultList;

public class PrepareTopSpotsHandler extends AbstractHtmlRequestHandler{

	private SpotResultList topSpots;
	@Override
	public String performActionAndGetNextViewConcrete(
			HttpServletRequest pRequest, HttpSession pSession) {
		topSpots = MemcacheSpot.getTopSpots();
		pRequest.setAttribute("topSpots", topSpots );
		return null;
		
		
	}
}
