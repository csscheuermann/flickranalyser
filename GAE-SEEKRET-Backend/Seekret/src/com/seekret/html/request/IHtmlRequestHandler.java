package com.seekret.html.request;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seekret.html.ViewNameEnum;


/** Handles Prepares the Webfrontend with data */
public interface IHtmlRequestHandler {
  
	
	/**
	 * Performs the action and returns the next view the ui has to show. 
	 * @param pRequest
	 * @param pResponse
	 * @param pSession
	 * @return the name of the new view that has to be shown or null if the current view should stay
	 */
	public ViewNameEnum performActionAndGetNextView( final HttpServletRequest pRequest, HttpServletResponse pResponse, final HttpSession pSession);

	public void prepareView(HttpServletRequest mRequest,
			HttpServletResponse mResponse, HttpSession session);
}
