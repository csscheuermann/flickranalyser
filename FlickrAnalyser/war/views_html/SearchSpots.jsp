<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.flickranalyser.html.common.HelperMethods" %>
<%@ page import="com.flickranalyser.pojo.Spot" %>
<%@ page import="com.flickranalyser.pojo.SpotResultList" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html lang="en">
	
	<%  HelperMethods helperMethods = (HelperMethods) request.getAttribute("helperMethods"); %>
	
	<% out.println(helperMethods.getHTMLHeaderUnclosed()); %>
	<script src="/res_html/js/ClusterDetails.js"></script>
	<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
	<script type="text/javascript">
		
		
	
		
		function addCrawlQueueRequest(spotName){
			var clusterDetails = new ClusterDetails();
			
			var clickString = "addToCrawlQueue('" + spotName + "')";
			
			  
			var htmlToShow = '<div class="row"> ' +
				'<div class="col-xs-9"><h4>' + spotName + '</h4></div> ' + 
				'<div class="col-xs-3"><button type="submit" class="btn btn-default" onClick="' + clickString + '">Crawl</button></div> ' + 
			'</div> ' + 
			
			'<div class="row"> ' +
				'<form role="form" name="crawlForm" action="/" method="post"> ' + 
					'<input type="hidden" name="onlyExcluded" id="onlyExcludedPicturesField">' + 
					'<input type="hidden" name="action" value="SearchSpots" id="spotCrawlParameters" >' + 
					'<input type="hidden" name="crawlLatitude" value="default" id="spotCrawlLatitude" >' + 
					'<input type="hidden" name="crawlLongitude" value="default" id="spotCrawlLongitude" >' + 
					'<input type="hidden" name="crawlAddress" value="default" id="spotCrawlAddress" >' + 	
				'</form> ' + 
			'</div> ';
						clusterDetails.addElementDiv('spotResult', 'spotResultInfo',  htmlToShow);
		}
		
		
		function addToCrawlQueue(spotCrawlName){
			var spotName = document.getElementById("spotCrawlAddress");
			spotName.setAttribute('value', spotCrawlName);
			
			$('#onlyExcludedPicturesField').val($('#onlyExcludedPictures').is(':checked'));
			document.forms["crawlForm"].submit();
		
		}
		
	
		
		
		function handleClick(event){
			var searchString = document.getElementById("spotName").value;
			
			var spotName = document.getElementById("spotAddress");
			spotName.setAttribute('value', searchString);
	
			document.forms["searchForm"].submit();
			
			
			
		}
		
		function resultChecker(){
		<% 
			
		String error = (String) request.getAttribute(HelperMethods.MESSAGE_ERROR);
	  	String successfull = (String) request.getAttribute(HelperMethods.MESSAGE_SUCCESSFUL); 
		String successfullCron = (String) request.getAttribute(HelperMethods.MESSAGE_SUCCESSFUL_CRON);
		String errorCron = (String) request.getAttribute(HelperMethods.MESSAGE_ERROR_CRON);
		
		Spot spot = (Spot) request.getAttribute(HelperMethods.SPOT); 
	
		
		if (error != null){
			String spotName = (String) request.getAttribute(HelperMethods.ADDRESS_PARAM);
			
		
			out.println("addCrawlQueueRequest('"+ 
				spotName + "');");
		
		}else if (successfull != null){

			String spotName = spot.getName();
			out.println("addSpotResult('"+ 
				spotName + "');");
			
		
		}
	
	

		%>
			}
		window.onload = resultChecker;
	
	</script>
	
	
	</head>
	
	
	<% out.println(helperMethods.createBodyBegin()); %>
	<% out.println(helperMethods.createNavigation(false)); %>


  	<div class='container'>
  		<div class='row'>
  	  		<div class='col-xs-12'><h1>Search for a  Spot</div>
  		</div>
    </div>

   	<%  
	  	out.println("<div class='container'>");
	  	if (error != null){
			 out.println("<div class='row'> " +
				"<div class='col-xs-12'><p class='alert alert-danger'>" +  error + "</p></div></div>"); 
	  	}else if (successfull != null){
		 	out.println("<div class='row'> " +
				"<div class='col-xs-12'><p class='alert alert-success'>" +  successfull + "</p></div></div>");			
	  	}else if (successfullCron != null){
		 	out.println("<div class='row'> " +
				"<div class='col-xs-12'><p class='alert alert-success'>" +  successfullCron + "</p></div></div>");			
	  	}else if (errorCron != null){
		 	out.println("<div class='row'> " +
				"<div class='col-xs-12'><p class='alert alert-danger'>" +  errorCron + "</p></div></div>");			
	  	}
	  
		out.println("</div>");
	  %>
	
  	
		
	  <%
	  	if (error != null){
	  		out.println(helperMethods.createCrawlSettings());
	  	}
	%>
	<div class='container'>
		<div class='row'>
	  	<div class='col-xs-9'><input type="text" class="form-control" id="spotName" placeholder="Munich"></div>
		<div class='col-xs-3'><button type="submit" class="btn btn-default" onClick="handleClick()">Search</button></div>
		</div>
  	</div>
		
	
	<div class='container'>
		<form role="form" name="searchForm" action="/" method="post">
			<input type="hidden" name="action" value="SearchSpots" id="spotParameters" >
			<input type="hidden" name="address" value="default" id="spotAddress" >
		</form>
		
	</div>
	
	<div class='container top-buffer' id="spotResult">
	
	</div>
	
	<%	
	

	Spot spotForSpotName = (Spot) request.getAttribute(HelperMethods.SPOT); 
	if (spotForSpotName != null){
		 out.println(helperMethods.getFilterStrategyButtons(spotForSpotName.getName()));
	}
	%>
		
	
	<% out.println(helperMethods.createBodyEnd());%>
	
	
	</html>