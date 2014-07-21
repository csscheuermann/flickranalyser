package com.flickranalyser.html.webfrontend;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flickranalyser.html.IHtmlRequestHandler;
import com.flickranalyser.html.common.HelperMethods;
import com.flickranalyser.pojo.User;

public class HtmlRequestProcessor
{
  private static final String PREFIX_ACTION_HANDLER_CLASS = "Action";
  private static final String ACTION = "action";
  private static final String SHOW_VIEW = "showView";
  public static final String CURRENT_USER = "currentUser";
  private HttpServletRequest mRequest;
  private HttpServletResponse mResponse;
  private ServletContext mServletContext;
  private static final Logger LOGGER = Logger.getLogger(HtmlStarterServlet.class.getName());

  public static final User GUEST_USER = new User("guest@guest.com", "guestuser", "guest", "not link", "no picture");

  public HtmlRequestProcessor(HttpServletRequest pRequest, HttpServletResponse pResponse, ServletContext pServletContext) {
    this.mRequest = pRequest;
    this.mResponse = pResponse;
    this.mServletContext = pServletContext;
  }

  public void handleClientRequest() throws IOException {
    try {
      checkPreconditions();
      doHandleClientRequest();
    }
    catch (Exception e)
    {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      String stacktrace = sw.toString();

      String errorMsg = "An error occured: \n" + e.getMessage() + "\n\nStacktrace: \n" + stacktrace;
      LOGGER.severe(errorMsg);
      this.mResponse.getWriter().println(errorMsg.replace("\n", "<br>"));
    }
  }

  private void checkPreconditions() throws Exception
  {
    String nextView = this.mRequest.getParameter("showView");
    LOGGER.info("SHOW VIEW PARAM " + nextView);

    String action = this.mRequest.getParameter("action");
    LOGGER.info("ACTION PARAM " + action);

    if ((action != null) && (nextView != null))
      throw new RuntimeException("Action and viewparameter have been set, that is nor allowed");
  }

  private void doHandleClientRequest()
    throws Exception
  {
    checkUserLogin();

    makeHelperMethodsClassAvailableToViews();

    String nextViewName = this.mRequest.getParameter("showView");

    String action = this.mRequest.getParameter("action");

    IHtmlRequestHandler requestHandler = loadHandlerForAction(action);

    if ((nextViewName == null) && (action == null)) {
      nextViewName = "01_index";
    }

    if (requestHandler != null) {
      nextViewName = requestHandler.performActionAndGetNextView(this.mRequest, this.mResponse, this.mRequest.getSession());
    }

    IHtmlRequestHandler viewPreparationHandler = loadHandlerForPreparingView(nextViewName);

    if (viewPreparationHandler != null)
    {
      String performActionAndGetNextView = viewPreparationHandler.performActionAndGetNextView(this.mRequest, this.mResponse, this.mRequest.getSession());
      if (performActionAndGetNextView != null) {
        nextViewName = performActionAndGetNextView;
      }
    }
    if (nextViewName != null)
      showView(nextViewName);
  }

  private IHtmlRequestHandler loadHandlerForAction(String action)
    throws Exception
  {
    if (action == null) {
      return null;
    }

    IHtmlRequestHandler requestHandler = loadRequestHandlerByName("Action" + action);

    if (requestHandler != null) {
      return requestHandler;
    }

    throw new RuntimeException("No action requestHandler has been found.");
  }

  private IHtmlRequestHandler loadHandlerForPreparingView(String pNextViewName) throws Exception {
    String nameForHandlerWhichPreparesView = "Prepare" + pNextViewName;
    IHtmlRequestHandler requestHandler = loadRequestHandlerByName(nameForHandlerWhichPreparesView);
    return requestHandler;
  }

  private void showView(String pViewName) throws Exception
  {
    LOGGER.info("Showing view: " + pViewName);

    if (!doesViewExist(pViewName)) {
      throw new RuntimeException("View does not exist: " + pViewName);
    }
    LOGGER.info("View exists " + pViewName);
    RequestDispatcher rd = this.mServletContext.getRequestDispatcher("/views_html/" + pViewName + ".jsp");
    LOGGER.info("mRequest " + this.mRequest.toString());
    LOGGER.info("mResponse " + this.mResponse.toString());
    rd.forward(this.mRequest, this.mResponse);
  }

  private boolean doesViewExist(String pViewName) throws Exception {
    String pathToCodeBase = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
    File classesDirectory = new File(pathToCodeBase);
    File viewsDirectory = classesDirectory.getParentFile().getParentFile();

    LOGGER.log(Level.INFO, viewsDirectory.getAbsolutePath());

    viewsDirectory = new File(viewsDirectory.getAbsolutePath() + "/views_html/");
    for (File currentFile : viewsDirectory.listFiles()) {
      LOGGER.log(Level.INFO, "CURRENT FILE: " + currentFile.getName());
      if (!currentFile.isDirectory())
      {
        if (currentFile.getName().equals(pViewName + ".jsp")) {
          LOGGER.log(Level.INFO, "FOUND JSP");
          return true;
        }
      }
    }
    return false;
  }

  private void makeHelperMethodsClassAvailableToViews() {
    HelperMethods helperMethods = new HelperMethods(this.mRequest.getSession());
    this.mRequest.setAttribute("helperMethods", helperMethods);
  }

  private void checkUserLogin()
  {
    HttpSession session = this.mRequest.getSession();
    User currentUser = (User)session.getAttribute("currentUser");

    if (currentUser == null) {
      currentUser = GUEST_USER;
      session.setAttribute("currentUser", currentUser);
    }

    this.mRequest.setAttribute("currentUser", currentUser);
  }

  private IHtmlRequestHandler loadRequestHandlerByName(String prepareViewName) throws Exception
  {
    String expectedName = "com.flickranalyser.html.impl." + prepareViewName + "Handler";
    IHtmlRequestHandler htmlRequestHandler = (IHtmlRequestHandler)HelperMethods.instantiate(expectedName, IHtmlRequestHandler.class);
    return htmlRequestHandler;
  }
}