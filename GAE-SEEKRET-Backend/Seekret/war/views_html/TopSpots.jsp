<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.seekret.html.common.HelperMethods" %>
<%@ page import="com.seekret.pojo.Spot" %>
<%@ page import="com.seekret.pojo.SpotResultList" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.lang.StringBuffer" %>
<%@ page import="com.seekret.html.common.JSPHelper" %>

<!DOCTYPE html>
<html lang="en">
	
	<%  HelperMethods helperMethods = (HelperMethods) request.getAttribute("helperMethods"); %>
	<% out.println(helperMethods.getHTMLHeader()); %>
	<% out.println(helperMethods.createBodyBegin()); %>
	<% out.println(helperMethods.createNavigation(false)); %>


   	<%  SpotResultList topSpots = (SpotResultList) request.getAttribute("topSpots"); %>
	

	
	<div class='container'>
		<div class='container'>
			<div class='row'>
				<div class='col-xs-12'> <h1>Top Spots</h1> </div>
			</div>
		</div>
		<% out.println(helperMethods.getFilterStrategyButtons(topSpots)); %>
	</div>
	
	
	
	<% out.println(helperMethods.createBodyEnd());%>
	
	
	
	
	
	</html>