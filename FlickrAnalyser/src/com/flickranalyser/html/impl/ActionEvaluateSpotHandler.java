package com.flickranalyser.html.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.flickranalyser.endpoints.ClusterService;
import com.flickranalyser.html.common.HelperMethods;

public class ActionEvaluateSpotHandler extends AbstractHtmlRequestHandler{

	private static final Logger LOGGER = Logger.getLogger(ActionEvaluateSpotHandler.class.getName());


	@Override
	public String performActionAndGetNextViewConcrete(HttpServletRequest pRequest, HttpSession pSession) {
		
		
		ClusterService clusterService = new ClusterService();

		String clusterKey = pRequest.getParameter(HelperMethods.CLUSTER_KEY);
		int clusterRatingValue = Integer.parseInt(pRequest.getParameter(HelperMethods.CLUSTER_RATING_VALUE));
		String spotName = pRequest.getParameter(HelperMethods.SPOT_NAME);
		
		
		LOGGER.log(Level.INFO, "CLUSTERKEY: " + clusterKey );
		LOGGER.log(Level.INFO, "CLUSTERRATINGVALUE: " + clusterRatingValue );
		LOGGER.log(Level.INFO, "SPOTNAME: " + spotName );
		
		clusterService.evaluateCluster(clusterKey, clusterRatingValue, spotName);
		
		return null;

	}


}
