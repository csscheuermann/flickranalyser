package com.flickranalyser.html.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.flickranalyser.businesslogic.common.ParameterConstants;
import com.flickranalyser.businesslogic.filter.IFilterStrategy;
import com.flickranalyser.html.IHtmlRequestHandler;
import com.flickranalyser.html.common.HelperMethods;
import com.flickranalyser.memcache.MemcacheSpot;
import com.flickranalyser.pojo.Spot;

public class PrepareSpotMapHandler implements IHtmlRequestHandler{


	@Override
	public String performActionAndGetNextView(HttpServletRequest pRequest, HttpSession pSession) {
		//GET THE PARAMS
		String location = pRequest.getParameter(ParameterConstants.REQUEST_PARAM_LOCATION);
		String filterStrategy = pRequest.getParameter(ParameterConstants.FILTER_STRATEGY);

		StringBuilder fullClassPath = new StringBuilder();
		fullClassPath.append("com.flickranalyser.businesslogic.filter.impl.");
		fullClassPath.append(filterStrategy);

		IFilterStrategy choosenFilterStrategy = HelperMethods.instantiate(fullClassPath.toString(), IFilterStrategy.class);
		Spot spot = MemcacheSpot.getSpotForSpotName(location);
		spot.setCluster(choosenFilterStrategy.filterCluster(spot.getCluster()));

		pRequest.setAttribute("spot", spot );

		return null;
	}
}
