package com.flickranalyser.html.impl;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Prepare01_indexHandler extends AbstractHtmlRequestHandler{

	private static final Logger LOGGER = Logger.getLogger(Prepare01_indexHandler.class.getName());
	
	@Override
	protected boolean isLoginRequired() {
		return false;
	}
	
	@Override
	public String performActionAndGetNextViewConcrete(
			HttpServletRequest pRequest, HttpServletResponse pResponse, HttpSession pSession) {
		
	
		return "01_index";
	}
}
