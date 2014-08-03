package com.flickranalyser.html.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flickranalyser.endpoints.SpotService;
import com.flickranalyser.pojo.SpotResultList;
import com.google.api.server.spi.response.UnauthorizedException;

public class PrepareTopSpotsHandler extends AbstractHtmlRequestHandler{
	private SpotResultList topSpots;

	public String performActionAndGetNextViewConcrete(HttpServletRequest pRequest, HttpServletResponse mResponse, HttpSession pSession){
		
		return null;
	}
	
	@Override
	public void prepareViewConcrete(HttpServletRequest pRequest,
			HttpServletResponse pResponse,
			HttpSession pSession) {
		SpotService spotService = new SpotService();
		com.flickranalyser.pojo.SeekretUser currentUser = (com.flickranalyser.pojo.SeekretUser)pSession.getAttribute("currentUser");
		try{
			this.topSpots = spotService.getTopSpots(new com.google.appengine.api.users.User(currentUser.getEmail(), ""));
		}
		catch (UnauthorizedException e) {
			e.printStackTrace();
		}
		pRequest.setAttribute("topSpots", this.topSpots);
	}
}