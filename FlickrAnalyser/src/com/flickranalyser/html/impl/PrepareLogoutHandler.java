package com.flickranalyser.html.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flickranalyser.html.webfrontend.HtmlRequestProcessor;
import com.flickranalyser.pojo.SeekretUser;

public class PrepareLogoutHandler extends AbstractHtmlRequestHandler {
  public String performActionAndGetNextViewConcrete(HttpServletRequest pRequest, HttpServletResponse pResponse, HttpSession pSession) {
    SeekretUser user = HtmlRequestProcessor.GUEST_USER;
    pSession.setAttribute("currentUser", user);

    return null;
  }
}