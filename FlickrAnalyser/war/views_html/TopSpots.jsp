<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.flickranalyser.html.common.HelperMethods" %>
<%@ page import="com.flickranalyser.pojo.Spot" %>
<%@ page import="com.flickranalyser.pojo.SpotResultList" %>
<%@ page import="java.util.LinkedList" %>

<!DOCTYPE html>
<html lang="en">
	
	<%  HelperMethods helperMethods = (HelperMethods) request.getAttribute("helperMethods"); %>
	<% out.println(helperMethods.getHTMLHeader()); %>
	<% out.println(helperMethods.createBodyBegin()); %>
	<% out.println(helperMethods.createNavigation(false)); %>


   	<%  SpotResultList topSpots = (SpotResultList) request.getAttribute("topSpots"); %>
	

	
	<div class='container'>
		
		<% out.println("<div class='row'> " +
			"<div class='col-xs-3'> <h4>Name</h4> </div>" +
			"<div class='col-xs-9'><h4>Cluster Algos</h4></div></div>");
		
		for (String spotName : topSpots.getTopSpots())	{
			out.println("<div class='row'> " +
				"<div class='col-xs-3'> " + spotName + "</div>" +
				"<div class='col-xs-9'> " +
					"<a href='https://flickeranalyser.appspot.com/?showView=SpotMap&location=" + spotName + "&strategy=ManyViewsAndFewPOIsFilter'>1</a> | " +
						"<a href='https://flickeranalyser.appspot.com/?showView=SpotMap&location=" + spotName + "&strategy=DoNotFilterStrategy'>2</a> | " +
						"<a href='https://flickeranalyser.appspot.com/?showView=SpotMap&location=" + spotName + "&strategy=RelativeRatioViewsAndPOIsFilter'>3</a> | " +
			 		"<a href='https://flickeranalyser.appspot.com/?showView=SpotMap&location=" + spotName + "&strategy=ManyViewsAndFixedAmountOfPOIsFilter'>4</a>" +
				"</div></div>");
		} 
		%>
		
	</div>
	
	
	
	<% out.println(helperMethods.createBodyEnd());%>
	
	
	
	
	
	</html>