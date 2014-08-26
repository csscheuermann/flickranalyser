package com.seekret.html.request.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.api.server.spi.response.UnauthorizedException;
import com.seekret.endpoints.SpotService;
import com.seekret.pojo.SpotResultList;

public class PrepareTopSpotsHandler extends AbstractHtmlRequestHandler{
	private SpotResultList topSpots;

	
	@Override
	public void prepareViewConcrete(HttpServletRequest pRequest,
			HttpServletResponse pResponse,
			HttpSession pSession) {
		SpotService spotService = new SpotService();
		com.seekret.pojo.SeekretUser currentUser = (com.seekret.pojo.SeekretUser)pSession.getAttribute("currentUser");
		try{
			this.topSpots = spotService.getTopSpots(new com.google.appengine.api.users.User(currentUser.getEmail(), ""));
		}
		catch (UnauthorizedException e) {
			e.printStackTrace();
		}
		pRequest.setAttribute("topSpots", this.topSpots);
	}
}