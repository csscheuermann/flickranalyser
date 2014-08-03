package com.flickranalyser.html;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/** Handles Prepares the Webfrontend with data */
public interface IHtmlViewPrepareHandler {
  
	
	/**
	 * Performs the action and returns the next view the ui has to show. 
	 * @param pRequest
	 * @param pResponse
	 * @param pSession
	 * @return the name of the new view that has to be shown or null if the current view should stay
	 */
	public String prepareView( final HttpServletRequest pRequest, HttpServletResponse pResponse, final HttpSession pSession);
}
