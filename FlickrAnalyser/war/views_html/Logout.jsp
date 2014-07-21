<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.flickranalyser.html.common.HelperMethods" %>
<%@ page import="java.util.List" %>






<!DOCTYPE html>
<html lang="en">
	<%  HelperMethods helperMethods = (HelperMethods) request.getAttribute("helperMethods"); %>
	
	
	
	<% out.println(helperMethods.getHTMLHeader()); %>
	<% out.println(helperMethods.createBodyBegin()); %>
	<% out.println(helperMethods.createNavigation(false)); %>
	
	<div class='container'>
		<div class='row'>
		    <div class="col-xs-12">
				<h2>You are now logged out.</h2>
			</div>
		</div>
	</div>	
	
	
	<% out.println(helperMethods.createBodyEnd());%>
	
	
	
	
	
	</html>