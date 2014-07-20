package com.flickranalyser.html.impl;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flickranalyser.html.IHtmlRequestHandler;

public abstract class AbstractHtmlRequestHandler implements IHtmlRequestHandler {


	private static final Logger LOGGER = Logger
			.getLogger(AbstractHtmlRequestHandler.class.getName());

	
	
	@Override
	public String performActionAndGetNextView(HttpServletRequest pRequest,
			HttpServletResponse pResponse, HttpSession pSession) {
		
		return performActionAndGetNextViewConcrete(pRequest, pResponse, pSession);
	}

	protected boolean isLoginRequired() {
		return true;
	}

	public abstract String performActionAndGetNextViewConcrete(
			HttpServletRequest pRequest,HttpServletResponse pResponse, HttpSession pSession);

}
