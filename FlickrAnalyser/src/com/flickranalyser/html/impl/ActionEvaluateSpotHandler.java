package com.flickranalyser.html.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import com.flickranalyser.endpoints.ClusterService;
import com.flickranalyser.endpoints.RatingService;
import com.flickranalyser.pojo.SeekretUser;

public class ActionEvaluateSpotHandler extends AbstractHtmlRequestHandler
{
	private static final Logger LOGGER = Logger.getLogger(ActionEvaluateSpotHandler.class.getName());

	public String performActionAndGetNextViewConcrete(HttpServletRequest pRequest, HttpServletResponse pResponse, HttpSession pSession)
	{
		ClusterService clusterService = new ClusterService();
		RatingService ratingService = new RatingService();

		String clusterKey = pRequest.getParameter("clusterKey");
		int clusterRatingValue = Integer.parseInt(pRequest.getParameter("clusterRating"));
		String spotName = pRequest.getParameter("spotName");

		SeekretUser currentUser = (SeekretUser)pSession.getAttribute("currentUser");

		String email = currentUser.getEmail();
		Response hasUserAlreadyVoted = ratingService.hasUserAlreadyVoted(email, clusterKey);

		if (hasUserAlreadyVoted.getStatus() == 200) {
			boolean hasUserAlreadyVotedResult = ((Boolean)hasUserAlreadyVoted.getEntity()).booleanValue();
			if (hasUserAlreadyVotedResult) {
				try {
					pResponse.getWriter().print("ALREADY VOTED");
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				LOGGER.log(Level.INFO, "ALREADY VOTED.");
				return null;
			}

		}

		LOGGER.log(Level.INFO, "CLUSTERKEY: " + clusterKey);
		LOGGER.log(Level.INFO, "CLUSTERRATINGVALUE: " + clusterRatingValue);
		LOGGER.log(Level.INFO, "SPOTNAME: " + spotName);

		ratingService.addNewRating(email, clusterKey);
		LOGGER.log(Level.INFO, "ADDED RATING");
		clusterService.evaluateCluster(clusterKey, clusterRatingValue, spotName);
		LOGGER.log(Level.INFO, "EVALUATED");
		try {
			pResponse.getWriter().print("ADDED RATING THANKS.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}