package com.flickranalyser.html.webfrontend;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HtmlStarterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest pRequest, HttpServletResponse pResponse)
			throws IOException
			{
		startRequestHandling(pRequest, pResponse);
			}

	protected void doPost(HttpServletRequest pRequest, HttpServletResponse pResponse) throws IOException
	{
		startRequestHandling(pRequest, pResponse);
	}

	private void startRequestHandling(HttpServletRequest pRequest, HttpServletResponse pResponse)
			throws IOException
			{
		HtmlRequestProcessor applicationLogic = new HtmlRequestProcessor(pRequest, pResponse, getServletContext());
		applicationLogic.handleClientRequest();
			}
}