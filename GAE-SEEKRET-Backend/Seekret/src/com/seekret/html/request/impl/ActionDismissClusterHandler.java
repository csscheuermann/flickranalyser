package com.seekret.html.request.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import com.seekret.endpoints.ClusterService;
import com.seekret.html.ViewNameEnum;
import com.seekret.pojo.SeekretUser;

public class ActionDismissClusterHandler extends AbstractHtmlRequestHandler {

	 private static final Logger LOGGER = Logger.getLogger(ActionDismissClusterHandler.class.getName());
	 
	public ViewNameEnum performActionAndGetNextViewConcrete(HttpServletRequest pRequest, HttpServletResponse pResponse, HttpSession pSession) {
		ClusterService clusterService = new ClusterService();

		SeekretUser currentUser = (SeekretUser) pSession.getAttribute("currentUser");
	
		String datastoreKeyOfCluster = pRequest.getParameter("clusterDatastoreKey").toString();
		LOGGER.log(Level.INFO, datastoreKeyOfCluster);
		

		User user = new com.google.appengine.api.users.User(currentUser.getEmail(), "");
		
		try {
			Response incrementDismissCount = clusterService.incrementDismissCount(user, datastoreKeyOfCluster);
			ServletOutputStream out = pResponse.getOutputStream();
			try {
				out.write(incrementDismissCount.getEntity().toString().getBytes("UTF-8"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} catch (UnauthorizedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}