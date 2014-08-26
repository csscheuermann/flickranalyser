package com.flickranalyser.html.request.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flickranalyser.html.webfrontend.HtmlRequestProcessor;
import com.flickranalyser.pojo.SeekretUser;

public class PrepareLogoutHandler extends AbstractHtmlRequestHandler {
  
  @Override
	public void prepareViewConcrete(HttpServletRequest mRequest,
			HttpServletResponse mResponse, HttpSession session) {
	  SeekretUser user = HtmlRequestProcessor.GUEST_USER;
	  session.setAttribute("currentUser", user);

	}
}