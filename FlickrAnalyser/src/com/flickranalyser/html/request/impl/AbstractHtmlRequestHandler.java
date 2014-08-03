package com.flickranalyser.html.request.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flickranalyser.html.ViewNameEnum;
import com.flickranalyser.html.request.IHtmlRequestHandler;
import com.flickranalyser.html.webfrontend.HtmlRequestProcessor;
import com.flickranalyser.pojo.SeekretUser;

public abstract class AbstractHtmlRequestHandler implements IHtmlRequestHandler {
	private static final String MESSAGE_USER_NOT_LOGGED_IN = "USER NOT LOGGED IN";
	private static final String MESSAGE_USERNAME = "USERNAME: ";
	private static final String VIEW_LOGIN = "Login";
	private static final Logger LOGGER = Logger
			.getLogger(AbstractHtmlRequestHandler.class.getName());

	public final ViewNameEnum performActionAndGetNextView(
			HttpServletRequest pRequest, HttpServletResponse pResponse,
			HttpSession pSession) {

		SeekretUser currentUser = (SeekretUser) pSession
				.getAttribute("currentUser");
		if (isLoginRequired() && !isUserLoggedIn(currentUser)) {
			try {
				pResponse.sendRedirect("?showView="+ViewNameEnum.LOGIN.name());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		return performActionAndGetNextViewConcrete(pRequest, pResponse,
				pSession);
	}

	public final void prepareView(HttpServletRequest mRequest,
			HttpServletResponse mResponse, HttpSession session) {
		SeekretUser currentUser = (SeekretUser) session
				.getAttribute("currentUser");
		if (isLoginRequired() && !isUserLoggedIn(currentUser)) {
			try {
				mResponse.sendRedirect("?showView="+ViewNameEnum.LOGIN.name());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		prepareViewConcrete(mRequest, mResponse, session);

	}

	protected boolean isLoginRequired() {
		return true;
	}

	public ViewNameEnum performActionAndGetNextViewConcrete(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse,
			HttpSession paramHttpSession) {
		return null;
	}

	public void prepareViewConcrete(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse,
			HttpSession paramHttpSession) {

	}

	private boolean isUserLoggedIn(SeekretUser currentUser) {
		return !currentUser.getEmail().equals(
				HtmlRequestProcessor.GUEST_USER.getEmail());
	}

}