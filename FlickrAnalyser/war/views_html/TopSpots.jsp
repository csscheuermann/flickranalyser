<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.flickranalyser.html.common.HelperMethods" %>
<%@ page import="com.flickranalyser.pojo.Spot" %>
<%@ page import="com.flickranalyser.pojo.SpotResultList" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.lang.StringBuffer" %>
<%@ page import="com.flickranalyser.html.common.JSPHelper" %>

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
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("<div class='row'> ");
			stringBuffer.append("<div class='col-xs-3'> ");
			stringBuffer.append(spotName);
			stringBuffer.append("</div>");
			stringBuffer.append("<div class='col-xs-9'> ");
			stringBuffer.append("<a href='https://flickeranalyser.appspot.com/?showView=SpotMap&location="+spotName+"&strategy=DoNotFilterStrategy'>DoNotFilterStrategy</a> ");
			if(JSPHelper.isUserAdmin(request)){
				stringBuffer.append("|<a href='https://flickeranalyser.appspot.com/?showView=SpotMap&location="+spotName+"&strategy=ManyViewsAndFewPOIsFilter'>ManyViewsAndFewPOIsFilter</a> |");
				stringBuffer.append("<a href='https://flickeranalyser.appspot.com/?showView=SpotMap&location="+spotName+"&strategy=RelativeRatioViewsAndPOIsFilter'>RelativeRatioViewsAndPOIsFilter</a> |");
				stringBuffer.append("<a href='https://flickeranalyser.appspot.com/?showView=SpotMap&location="+spotName+"&strategy=ManyViewsAndFixedAmountOfPOIsFilter'>ManyViewsAndFixedAmountOfPOIsFilter</a> |");
			}
			stringBuffer.append("</div></div>");
			
			out.println(stringBuffer.toString());
		} 
		%>
		
	</div>
	
	
	
	<% out.println(helperMethods.createBodyEnd());%>
	
	
	
	
	
	</html>