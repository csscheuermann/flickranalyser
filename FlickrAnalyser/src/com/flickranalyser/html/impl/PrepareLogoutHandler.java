package com.flickranalyser.html.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flickranalyser.html.webfrontend.HtmlRequestProcessor;
import com.flickranalyser.pojo.User;

public class PrepareLogoutHandler extends AbstractHtmlRequestHandler{

	@Override
	public String performActionAndGetNextViewConcrete(
			HttpServletRequest pRequest,  HttpServletResponse pResponse,HttpSession pSession) {
		
		User user = HtmlRequestProcessor.GUEST_USER;
		pSession.setAttribute(HtmlRequestProcessor.CURRENT_USER, user);

		return null;
	}
}
