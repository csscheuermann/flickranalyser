<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.seekret.html.common.HelperMethods" %>
<%@ page import="com.seekret.html.common.GoogleAuthHelper" %>
<%@ page import="com.seekret.persistence.datastore.save.PFSaverUser" %>

<!DOCTYPE html>
<html lang="en">

	<%  HelperMethods helperMethods = (HelperMethods) request.getAttribute("helperMethods"); %>
	
	<% out.println(helperMethods.getHTMLHeader()); %>
	<% out.println(helperMethods.createBodyBegin()); %>
	<% out.println(helperMethods.createNavigation(true)); %>
	<% out.println(helperMethods.createCarusel()); %>
	<% out.println(helperMethods.getMarketingForMainPage()); %>
	<% out.println(helperMethods.createWhoWeAre()); %>
	<% out.println(helperMethods.getFooter()); %>
	
	<% out.println(helperMethods.createBodyEnd());%>
	
	</html>