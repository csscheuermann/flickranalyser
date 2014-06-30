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

import com.flickranalyser.html.IHtmlRequestHandler;
import com.flickranalyser.html.common.HelperMethods;


public class HtmlRequestProcessor{
	private HttpServletRequest mRequest;
	private HttpServletResponse mResponse;
	private ServletContext mServletContext;
	private static final Logger LOGGER = Logger.getLogger(HtmlStarterServlet.class.getName());

	public HtmlRequestProcessor(final HttpServletRequest pRequest, final HttpServletResponse pResponse, final ServletContext pServletContext) {
		mRequest = pRequest;
		mResponse = pResponse;
		mServletContext = pServletContext;
	}

	public void handleClientRequest() throws IOException{
		try{
			checkPreconditions();
			doHandleClientRequest();
		}
		catch( Exception e ){
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String stacktrace = sw.toString();

			final String errorMsg = "An error occured: \n" + e.getMessage() + "\n\nStacktrace: \n" + stacktrace;
			LOGGER.severe(errorMsg);
			mResponse.getWriter().println(errorMsg.replace("\n", "<br>"));
		}
	}

	private void checkPreconditions() throws Exception{
		// this is the view which is to be shown
		String nextView = mRequest.getParameter("showView");
		LOGGER.info("showView: " + nextView);
	}

	private void doHandleClientRequest() throws Exception{
		// the view, which is to be shown
		String nextViewName = mRequest.getParameter("showView");

		// if there is neither action nor showView, then show the main view
		if((nextViewName == null) ){
			nextViewName = "01_index";
		}


		IHtmlRequestHandler viewPreparationHandler = loadHandlerForPreparingView(nextViewName);
		if( viewPreparationHandler != null ){
			viewPreparationHandler.performActionAndGetNextView(mRequest, mRequest.getSession());
		}

		showView(nextViewName);
	}


	private IHtmlRequestHandler loadHandlerForPreparingView( final String pNextViewName ) throws Exception {
		final String nameForHandlerWhichPreparesView = "Prepare" + pNextViewName;
		IHtmlRequestHandler requestHandler = loadRequestHandlerByName(nameForHandlerWhichPreparesView);
		return requestHandler;
	}


	private void showView( final String pViewName ) throws Exception{
		LOGGER.info("Showing view: " + pViewName);
		// view does not exist => show error
		if( !doesViewExist(pViewName) ){
			throw new RuntimeException("View does not exist: " + pViewName);
		}
		LOGGER.info("View exists " + pViewName);
		RequestDispatcher rd = mServletContext.getRequestDispatcher("/views_html/" + pViewName + ".jsp");
		LOGGER.info("mRequest " + mRequest.toString());
		LOGGER.info("mResponse " + mResponse.toString());
		rd.forward(mRequest, mResponse);
	}

	private boolean doesViewExist( final String pViewName ) throws Exception{
		final String pathToCodeBase = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		final File classesDirectory = new File(pathToCodeBase);
		File viewsDirectory = classesDirectory.getParentFile().getParentFile();

		LOGGER.log(Level.INFO, viewsDirectory.getAbsolutePath());

		viewsDirectory = new File(viewsDirectory.getAbsolutePath() + "/views_html/");
		for( File currentFile : viewsDirectory.listFiles() ){
			LOGGER.log(Level.INFO,"CURRENT FILE: " +currentFile.getName());
			if( currentFile.isDirectory() ){
				continue;
			}
			if( currentFile.getName().equals(pViewName + ".jsp") ){
				LOGGER.log(Level.INFO,"FOUND JSP");
				return true;
			}
		}
		return false;
	}


	private IHtmlRequestHandler loadRequestHandlerByName( final String prepareViewName ) throws Exception{
		String expectedName = "com.flickranalyser.html.impl." + prepareViewName + "Handler";
		IHtmlRequestHandler htmlRequestHandler =  HelperMethods.instantiate(expectedName, IHtmlRequestHandler.class);
		return htmlRequestHandler;
	}
}