package com.flickranalyser.html.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.flickranalyser.html.IHtmlRequestHandler;
import com.flickranalyser.html.webfrontend.HtmlRequestProcessor;
import com.flickranalyser.pojo.User;

public abstract class AbstractHtmlRequestHandler implements IHtmlRequestHandler {
	private static final Logger LOGGER = Logger.getLogger(AbstractHtmlRequestHandler.class.getName());
	
	
	@Override
	public String performActionAndGetNextView(HttpServletRequest pRequest,
			HttpSession pSession) {
		User currentUser = (User) pSession.getAttribute(HtmlRequestProcessor.CURRENT_USER);
		
		LOGGER.log(Level.INFO, "USERNAME: " + currentUser.getEmail() );
		
		if (currentUser.getEmail().equals(HtmlRequestProcessor.GUEST_USER.getEmail())){
			LOGGER.log(Level.INFO, "USER NOT LOGGED IN");
			return ("Login");
		}
		
		return performActionAndGetNextViewConcrete( pRequest, pSession);
	}
	
	public abstract String performActionAndGetNextViewConcrete(HttpServletRequest pRequest,HttpSession pSession);

}
