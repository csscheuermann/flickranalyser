package com.flickranalyser.html.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flickranalyser.memcache.MemcacheSpot;
import com.flickranalyser.pojo.SpotResultList;

public class PrepareTopSpotsHandler extends AbstractHtmlRequestHandler{

	private SpotResultList topSpots;
	@Override
	public String performActionAndGetNextViewConcrete(
			HttpServletRequest pRequest, HttpServletResponse mResponse, HttpSession pSession) {
		topSpots = MemcacheSpot.getTopSpots();
		//TODO COS DVV: Extract this strings to a common class used also for the JSPs
		pRequest.setAttribute("topSpots", topSpots );
		return null;
		
		
	}
}
