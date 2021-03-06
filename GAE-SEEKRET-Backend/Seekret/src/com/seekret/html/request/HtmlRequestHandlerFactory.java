package com.seekret.html.request;

import com.seekret.html.ActionNameEnum;
import com.seekret.html.ViewNameEnum;
import com.seekret.html.request.impl.ActionDismissClusterHandler;
import com.seekret.html.request.impl.ActionEvaluateSpotHandler;
import com.seekret.html.request.impl.ActionGetClusterAddressHandler;
import com.seekret.html.request.impl.ActionSearchSpotsHandler;
import com.seekret.html.request.impl.Prepare01_indexHandler;
import com.seekret.html.request.impl.PrepareLogoutHandler;
import com.seekret.html.request.impl.PrepareNothingHandler;
import com.seekret.html.request.impl.PrepareSpotMapHandler;
import com.seekret.html.request.impl.PrepareTopSpotsHandler;

public class HtmlRequestHandlerFactory {

	public IHtmlRequestHandler createActionHandler(ActionNameEnum actionName) {
		switch(actionName) {
		case DISMISS_CLUSTER:
			return new ActionDismissClusterHandler();
		case EVALULATE_SPOT:
			return new ActionEvaluateSpotHandler();
		case GET_CLUSTER_ADDRESS:
			return new ActionGetClusterAddressHandler();
		case SEARCH_SPOTS:
			return new ActionSearchSpotsHandler();
		default:
			return null;
		}
		
	}
	
	public IHtmlRequestHandler createViewPrepareHandler(ViewNameEnum viewName) {
		switch (viewName) {
		case INDEX:
			return new Prepare01_indexHandler();
		case LOGOUT:
			return new PrepareLogoutHandler();
		case SPOTMAP:
			return new PrepareSpotMapHandler();
		case TOPSPOTS:
			return new PrepareTopSpotsHandler();
		default:
			return new PrepareNothingHandler();
		}
	}
}
