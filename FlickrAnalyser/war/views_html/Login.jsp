<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.flickranalyser.html.common.HelperMethods" %>
<%@ page import="java.util.List" %>
<%@ page import="com.flickranalyser.html.common.GoogleAuthHelper" %>
<%@ page import="com.flickranalyser.pojo.User" %>
<%@ page import="com.flickranalyser.persistence.datastore.save.PFSaverUser" %>




<!DOCTYPE html>
<html lang="en">
	<%  HelperMethods helperMethods = (HelperMethods) request.getAttribute("helperMethods"); %>
	
	
	
	<% out.println(helperMethods.getHTMLHeader()); %>
	<% out.println(helperMethods.createBodyBegin()); %>
	<% out.println(helperMethods.createNavigation(false)); %>
	

	<%
	/*
	 * The GoogleAuthHelper handles all the heavy lifting, and contains all "secrets"
	 * required for constructing a google login url.
	 */
	final GoogleAuthHelper helper = new GoogleAuthHelper();
	if (request.getParameter("code") == null
		|| request.getParameter("state") == null) {
		/*
		 * initial visit to the page
		 */
			out.println("<div class='container'> " +
					"<div class='row'>" +
					    "<div class='col-xs-6'>" +
							"<h2>You need to login first</h2>" +
						"</div>" +
							
					 		   "<div class='col-xs-6'>" +
									"<h2><a href='" + helper.buildLoginUrl() + "'>log in with google</a></h2>" +
								"</div>" +
										
					"</div>" +
				"</div>");


		/*
		 * set the secure state token in session to be able to track what we sent to google
		 */
		session.setAttribute("state", helper.getStateToken());


	} 
	%>

	
	<% out.println(helperMethods.createBodyEnd());%>
	
	
	
	
	
	</html>