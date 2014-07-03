<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.flickranalyser.html.common.HelperMethods" %>
<%@ page import="com.flickranalyser.html.common.GoogleAuthHelper" %>
<%@ page import="com.flickranalyser.pojo.User" %>
<%@ page import="com.flickranalyser.persistence.datastore.save.PFSaverUser" %>

<!DOCTYPE html>
<html lang="en">
	
	<%
		
	/*
	* In case we got redirected from Login Page
	*/
	if (request.getParameter("code") != null && request.getParameter("state") != null && request.getParameter("state").equals(session.getAttribute("state"))) {
			final GoogleAuthHelper helper = new GoogleAuthHelper();
			session.removeAttribute("state");
			User user = helper.getUserInfoJson(request.getParameter("code"));
			PFSaverUser.saveUserToDatastore(user);
			session.setAttribute("currentUser", user);
		}
	%>

	<%  HelperMethods helperMethods = (HelperMethods) request.getAttribute("helperMethods"); %>
	
	<% out.println(helperMethods.getHTMLHeader()); %>
	<% out.println(helperMethods.createBodyBegin()); %>
	<% out.println(helperMethods.createNavigation(true)); %>
	<% out.println(helperMethods.createCarusel()); %>
	

	
	<% out.println(helperMethods.createBodyEnd());%>
	
	</html>