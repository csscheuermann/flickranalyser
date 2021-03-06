package com.seekret.html.request.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import com.seekret.endpoints.ClusterService;
import com.seekret.html.ViewNameEnum;
import com.seekret.pojo.SeekretUser;

public class ActionEvaluateSpotHandler extends AbstractHtmlRequestHandler
{
	private static final Logger LOGGER = Logger.getLogger(ActionEvaluateSpotHandler.class.getName());


	
	public ViewNameEnum performActionAndGetNextViewConcrete(HttpServletRequest pRequest, HttpServletResponse pResponse, HttpSession pSession)
	{
		ClusterService clusterService = new ClusterService();

		String clusterKey = pRequest.getParameter("clusterKey");
		int clusterRatingValue = Integer.parseInt(pRequest.getParameter("clusterRating"));
		String spotName = pRequest.getParameter("spotName");

		SeekretUser currentUser = (SeekretUser)pSession.getAttribute("currentUser");
		User user = new com.google.appengine.api.users.User(currentUser.getEmail(), "");
		

		LOGGER.log(Level.INFO, "CLUSTERKEY: " + clusterKey);
		LOGGER.log(Level.INFO, "CLUSTERRATINGVALUE: " + clusterRatingValue);
		LOGGER.log(Level.INFO, "SPOTNAME: " + spotName);
		LOGGER.log(Level.INFO, "ADDED RATING");
		Response response = null;
		try {
			response = clusterService.evaluateCluster(user, clusterKey, clusterRatingValue, spotName);
		} catch (UnauthorizedException e1) {
			LOGGER.log(Level.SEVERE, "MESSAGE " + e1.getMessage(), e1);
			e1.printStackTrace();
		}
			
		
		
		try {
			pResponse.getWriter().print(response.getEntity().toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}