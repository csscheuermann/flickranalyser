package com.flickranalyser.html.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.flickranalyser.memcache.MemcacheSpot;
import com.flickranalyser.pojo.TopTenSpots;

public class PrepareTopTenSpotsHandler extends AbstractHtmlRequestHandler{

	private TopTenSpots topTenSpots;
	@Override
	public String performActionAndGetNextViewConcrete(
			HttpServletRequest pRequest, HttpSession pSession) {
		topTenSpots = MemcacheSpot.getTopTenSpots();
		pRequest.setAttribute("topTenSpots", topTenSpots );
		return null;
	}
}
