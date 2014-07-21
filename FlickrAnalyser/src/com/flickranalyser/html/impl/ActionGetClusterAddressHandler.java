package com.flickranalyser.html.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flickranalyser.endpoints.ClusterService;
import com.flickranalyser.pojo.SeekretUser;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;

public class ActionGetClusterAddressHandler extends AbstractHtmlRequestHandler {

	 private static final Logger LOGGER = Logger.getLogger(ActionGetClusterAddressHandler.class.getName());
	 
	public String performActionAndGetNextViewConcrete(HttpServletRequest pRequest, HttpServletResponse pResponse, HttpSession pSession) {
		ClusterService clusterService = new ClusterService();

		SeekretUser currentUser = (SeekretUser) pSession.getAttribute("currentUser");
		LOGGER.log(Level.INFO, pRequest.getParameter("latitude").toString());
		LOGGER.log(Level.INFO, pRequest.getParameter("longitude").toString());
		
		double latitude = Double.parseDouble(pRequest.getParameter("latitude").toString());
		double logitude = Double.parseDouble(pRequest.getParameter("longitude").toString());

		User user = new com.google.appengine.api.users.User(currentUser.getEmail(), "");

		try {
			String address = clusterService.getAddressFromLatLng(user, latitude, logitude).getAddress();
			LOGGER.log(Level.INFO,address);
			ServletOutputStream out = pResponse.getOutputStream();
			out.write(address.getBytes("UTF-8")); 
		
		} catch (UnauthorizedException | IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}