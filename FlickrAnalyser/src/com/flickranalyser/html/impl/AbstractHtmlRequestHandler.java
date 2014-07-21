package com.flickranalyser.html.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flickranalyser.html.IHtmlRequestHandler;
import com.flickranalyser.html.webfrontend.HtmlRequestProcessor;
import com.flickranalyser.pojo.User;

public abstract class AbstractHtmlRequestHandler
  implements IHtmlRequestHandler
{
  private static final String MESSAGE_USER_NOT_LOGGED_IN = "USER NOT LOGGED IN";
  private static final String MESSAGE_USERNAME = "USERNAME: ";
  private static final String VIEW_LOGIN = "Login";
  private static final Logger LOGGER = Logger.getLogger(AbstractHtmlRequestHandler.class.getName());

  public String performActionAndGetNextView(HttpServletRequest pRequest, HttpServletResponse pResponse, HttpSession pSession)
  {
    if (isLoginRequired()) {
      User currentUser = (User)pSession.getAttribute("currentUser");
      LOGGER.log(Level.INFO, "USERNAME: " + currentUser.getEmail());

      if (currentUser.getEmail().equals(
        HtmlRequestProcessor.GUEST_USER.getEmail())) {
        LOGGER.log(Level.INFO, "USER NOT LOGGED IN");
        return "Login";
      }
    }

    return performActionAndGetNextViewConcrete(pRequest, pResponse, pSession);
  }

  protected boolean isLoginRequired() {
    return true;
  }

  public abstract String performActionAndGetNextViewConcrete(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, HttpSession paramHttpSession);
}