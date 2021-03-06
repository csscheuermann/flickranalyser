package com.seekret.html.webfrontend;

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

import com.seekret.html.ActionNameEnum;
import com.seekret.html.ViewNameEnum;
import com.seekret.html.common.HelperMethods;
import com.seekret.html.request.HtmlRequestHandlerFactory;
import com.seekret.html.request.IHtmlRequestHandler;
import com.seekret.pojo.SeekretUser;

public class HtmlRequestProcessor {
	private static final String PREFIX_ACTION_HANDLER_CLASS = "Action";
	private static final String ACTION = "action";
	private static final String SHOW_VIEW = "showView";
	public static final String CURRENT_USER = "currentUser";
	private HttpServletRequest mRequest;
	private HttpServletResponse mResponse;
	private ServletContext mServletContext;
	private static final Logger LOGGER = Logger
			.getLogger(HtmlStarterServlet.class.getName());

	public static final SeekretUser GUEST_USER = new SeekretUser(
			"guest@guest.com", "guestuser", "guest", "not link", "no picture");

	public HtmlRequestProcessor(HttpServletRequest pRequest,
			HttpServletResponse pResponse, ServletContext pServletContext) {
		this.mRequest = pRequest;
		this.mResponse = pResponse;
		this.mServletContext = pServletContext;
	}

	public void handleClientRequest() throws IOException {
		try {
			checkPreconditions();
			doHandleClientRequest();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String stacktrace = sw.toString();

			String errorMsg = "An error occured: \n" + e.getMessage()
					+ "\n\nStacktrace: \n" + stacktrace;
			LOGGER.severe(errorMsg);
			this.mResponse.getWriter().println(errorMsg.replace("\n", "<br>"));
		}
	}

	private void checkPreconditions() throws Exception {
		String nextView = this.mRequest.getParameter(SHOW_VIEW);
		LOGGER.info("SHOW VIEW PARAM " + nextView);

		String action = this.mRequest.getParameter(ACTION);
		LOGGER.info("ACTION PARAM " + action);

		if ((action != null) && (nextView != null))
			throw new RuntimeException(
					"Action and viewparameter have been set, that is nor allowed");
	}

	private void doHandleClientRequest() throws Exception {
		checkUserLogin();

		makeHelperMethodsClassAvailableToViews();

		String nextViewName = this.mRequest.getParameter(SHOW_VIEW);
		ViewNameEnum viewStringToEnum = null;
		
		if (nextViewName != null){
			viewStringToEnum = ViewNameEnum.viewStringToEnum(nextViewName);
		}
		String action = this.mRequest.getParameter(ACTION);

		if ((nextViewName == null) && (action == null)) {
			viewStringToEnum = ViewNameEnum.INDEX;
		}


		if (action != null) {
			
			IHtmlRequestHandler requestHandler = loadHandlerForAction(ActionNameEnum.viewStringToEnum(action));
			viewStringToEnum = requestHandler.performActionAndGetNextView(
					this.mRequest, this.mResponse, this.mRequest.getSession());
		}

		if (viewStringToEnum == null) {
			return;
		}

		IHtmlRequestHandler viewPreparationHandler = loadHandlerForPreparingView(viewStringToEnum);
		viewPreparationHandler.prepareView(this.mRequest, this.mResponse,
				this.mRequest.getSession());
		showView(viewStringToEnum);
	}

	private IHtmlRequestHandler loadHandlerForAction(ActionNameEnum action)
			throws Exception {
		
		HtmlRequestHandlerFactory requestHandlerFactory = new HtmlRequestHandlerFactory();
		return requestHandlerFactory.createActionHandler(action);
	}


	private IHtmlRequestHandler loadHandlerForPreparingView(ViewNameEnum viewName)
			throws Exception {
		HtmlRequestHandlerFactory requestHandlerFactory = new HtmlRequestHandlerFactory();
		return requestHandlerFactory.createViewPrepareHandler(viewName);
	}

	private void showView(ViewNameEnum viewName) throws Exception {
		String  view = viewName.toString();
		LOGGER.info("Showing view: " + view);

		if (!doesViewExist(view)) {
			throw new RuntimeException("View does not exist: " + view);
		}
		LOGGER.info("View exists " + view);
		RequestDispatcher rd = this.mServletContext
				.getRequestDispatcher("/views_html/" + view + ".jsp");
		LOGGER.info("mRequest " + this.mRequest.toString());
		LOGGER.info("mResponse " + this.mResponse.toString());
		rd.forward(this.mRequest, this.mResponse);
	}

	private boolean doesViewExist(String pViewName) throws Exception {
		String pathToCodeBase = getClass().getProtectionDomain()
				.getCodeSource().getLocation().getPath();
		File classesDirectory = new File(pathToCodeBase);
		File viewsDirectory = classesDirectory.getParentFile().getParentFile();

		LOGGER.log(Level.INFO, viewsDirectory.getAbsolutePath());

		viewsDirectory = new File(viewsDirectory.getAbsolutePath()
				+ "/views_html/");
		for (File currentFile : viewsDirectory.listFiles()) {
			LOGGER.log(Level.INFO, "CURRENT FILE: " + currentFile.getName());
			if (!currentFile.isDirectory()) {
				if (currentFile.getName().equals(pViewName + ".jsp")) {
					LOGGER.log(Level.INFO, "FOUND JSP");
					return true;
				}
			}
		}
		return false;
	}

	private void makeHelperMethodsClassAvailableToViews() {
		HelperMethods helperMethods = new HelperMethods(
				this.mRequest.getSession());
		this.mRequest.setAttribute("helperMethods", helperMethods);
	}

	private void checkUserLogin() {

		HttpSession session = this.mRequest.getSession();
		SeekretUser currentUser = (SeekretUser) session
				.getAttribute("currentUser");

		if (currentUser == null) {
			currentUser = GUEST_USER;
			session.setAttribute("currentUser", currentUser);
		}


		this.mRequest.setAttribute("currentUser", currentUser);
	}
}