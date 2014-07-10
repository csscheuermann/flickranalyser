package com.flickranalyser.html.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.flickranalyser.html.IHtmlRequestHandler;
import com.flickranalyser.html.webfrontend.HtmlRequestProcessor;
import com.flickranalyser.pojo.User;

public abstract class AbstractHtmlRequestHandler implements IHtmlRequestHandler {
	
	/* Message Strings */
	private static final String MESSAGE_USER_NOT_LOGGED_IN = "USER NOT LOGGED IN";
	private static final String MESSAGE_USERNAME = "USERNAME: ";

	private static final String VIEW_LOGIN = "Login";
	
	private static final Logger LOGGER = Logger.getLogger(AbstractHtmlRequestHandler.class.getName());
	
	@Override
	public String performActionAndGetNextView(HttpServletRequest pRequest,HttpSession pSession) {

		User currentUser = (User) pSession.getAttribute(HtmlRequestProcessor.CURRENT_USER);
		LOGGER.log(Level.INFO, MESSAGE_USERNAME + currentUser.getEmail() );

		if (currentUser.getEmail().equals(HtmlRequestProcessor.GUEST_USER.getEmail())){
			LOGGER.log(Level.INFO, MESSAGE_USER_NOT_LOGGED_IN);
			return VIEW_LOGIN;
		}
		return performActionAndGetNextViewConcrete( pRequest, pSession);
	}
	
	public abstract String performActionAndGetNextViewConcrete(HttpServletRequest pRequest,HttpSession pSession);

}
