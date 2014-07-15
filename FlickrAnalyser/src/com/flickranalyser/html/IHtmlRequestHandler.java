package com.flickranalyser.html;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/** Handles Prepares the Webfrontend with data */
public interface IHtmlRequestHandler {
  
	
	public String performActionAndGetNextView( final HttpServletRequest pRequest, HttpServletResponse pResponse, final HttpSession pSession);
}
