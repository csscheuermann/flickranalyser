package com.flickranalyser.html.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flickranalyser.memcache.MemcacheSpot;

public class PrepareTopSpotsHandler extends AbstractHtmlRequestHandler{

	private ArrayList<String> topSpots;
	@Override
	public String performActionAndGetNextViewConcrete(
			HttpServletRequest pRequest, HttpServletResponse mResponse, HttpSession pSession) {
		topSpots = (ArrayList<String>) MemcacheSpot.getTopSpots();
		//TODO COS DVV: Extract this strings to a common class used also for the JSPs
		pRequest.setAttribute("topSpots", topSpots );
		return null;
		
		
	}
}
