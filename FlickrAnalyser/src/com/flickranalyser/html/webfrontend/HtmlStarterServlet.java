package com.flickranalyser.html.webfrontend;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class HtmlStarterServlet extends HttpServlet
{
	@Override
	public void doGet( final HttpServletRequest pRequest, final HttpServletResponse pResponse ) throws IOException {
		startRequestHandling(pRequest, pResponse);
	}

	@Override
	protected void doPost( final HttpServletRequest pRequest, final HttpServletResponse pResponse ) throws IOException{
		startRequestHandling(pRequest, pResponse);
	}

	private void startRequestHandling( final HttpServletRequest pRequest, final HttpServletResponse pResponse ) throws IOException
	{
		HtmlRequestProcessor applicationLogic = new HtmlRequestProcessor(pRequest, pResponse, getServletContext());
		applicationLogic.handleClientRequest();
	}
}