<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.flickranalyser.html.common.HelperMethods" %>


<!DOCTYPE html>
<html lang="en">
	
	
	<% out.println(HelperMethods.getHTMLHeader()); %>
	<% out.println(HelperMethods.createBodyBegin()); %>
	<% out.println(HelperMethods.createNavigation(true)); %>
	<% out.println(HelperMethods.createCarusel()); %>
	

	
	<% out.println(HelperMethods.createBodyEnd());%>
	
	</html>