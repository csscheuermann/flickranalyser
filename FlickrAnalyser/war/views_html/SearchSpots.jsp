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
		
		
		
		function addSpotResult(spotName){
			var clusterDetails = new ClusterDetails();
			
			var linkManyViewsAndViewPOIsFilter = "http://flickeranalyser.appspot.com/?showView=SpotMap&location=" + spotName + "&strategy=ManyViewsAndFewPOIsFilter"
			var linkDoNotFilterStrategy = "http://flickeranalyser.appspot.com/?showView=SpotMap&location=" + spotName + "&strategy=DoNotFilterStrategy"
			var linkRelativeRatioViewsAndPOIsFilter = "http://flickeranalyser.appspot.com/?showView=SpotMap&location=" + spotName + "&strategy=RelativeRatioViewsAndPOIsFilter"
			var linkManyViewsAndFixedAmountOfPOIsFilter = "http://flickeranalyser.appspot.com/?showView=SpotMap&location=" + spotName + "&strategy=ManyViewsAndFixedAmountOfPOIsFilter"
			
			
				var htmlToShow = '<div class="container"> ' + 
					'<div class="row"> ' +
						'<div class="col-xs-3"><h4>Full Spot Name</h4></div> ' + 
						'<div class="col-xs-9"><h4>' + spotName + '</h4></div> ' + 
					'</div> ' + 
					'<div class="row"> ' +
					
						'<div class="col-md-3"><a href="'+ linkManyViewsAndViewPOIsFilter + '">ManyViewsAndFewPOIsFilter</a> </div> '+
						'<div class="col-md-3"><a href="'+ linkDoNotFilterStrategy + '">DoNotFilterStrategy</a> </div> '+
						'<div class="col-md-3"><a href="'+ linkRelativeRatioViewsAndPOIsFilter + '">RelativeRatioViewsAndPOIsFilter</a> </div> '+
						'<div class="col-md-3"><a href="'+ linkManyViewsAndFixedAmountOfPOIsFilter + '">ManyViewsAndFixedAmountOfPOIsFilter</a> </div> '+

					'</div> ' + 
				'</div> ' ;
			
			
			clusterDetails.addElementDiv('spotResult', 'spotResultInfo',  htmlToShow);
		}
		
		function addCrawlQueueRequest(spotName,spotResultLatitude ,spotResultLongitude){
			var clusterDetails = new ClusterDetails();
			
			var clickString = "addToCrawlQueue(" + spotResultLatitude + "," + spotResultLongitude + ",'" + spotName + "')";
			
			  
			var htmlToShow = '<div class="row"> ' +
				'<div class="col-xs-9"><h4>' + spotName + '</h4></div> ' + 
				'<div class="col-xs-3"><button type="submit" class="btn btn-default" onClick="' + clickString + '">Crawl</button></div> ' + 
			'</div> ' + 
			
			'<div class="row"> ' +
				'<form role="form" name="crawlForm" action="/" method="post"> ' + 
					'<input type="hidden" name="action" value="SearchSpots" id="spotCrawlParameters" >' + 
					'<input type="hidden" name="crawlLatitude" value="default" id="spotCrawlLatitude" >' + 
					'<input type="hidden" name="crawlLongitude" value="default" id="spotCrawlLongitude" >' + 
					'<input type="hidden" name="crawlAddress" value="default" id="spotCrawlAddress" >' + 	
				'</form> ' + 
			'</div> ';
						clusterDetails.addElementDiv('spotResult', 'spotResultInfo',  htmlToShow);
		}
		
		
		function addToCrawlQueue(spotResultLatitude, spotResultLongitude, spotCrawlName){
			var inputSpotLatitude = document.getElementById("spotCrawlLatitude");
			inputSpotLatitude.setAttribute('value', spotResultLatitude);

			var inputspotLongitude = document.getElementById("spotCrawlLongitude");
			inputspotLongitude.setAttribute('value', spotResultLongitude);
		
			var spotName = document.getElementById("spotCrawlAddress");
			spotName.setAttribute('value', spotCrawlName);
			document.forms["crawlForm"].submit();
		
		
		}
		
		function getLongitudeAndLatitudeByName(searchString){
			var geocoder = new google.maps.Geocoder();
			
			geocoder.geocode( { 'address': searchString}, function(results, status) {

		  	if (status == google.maps.GeocoderStatus.OK) {
		    	var latitude = results[0].geometry.location.lat();
		    	var longitude = results[0].geometry.location.lng();
			
		       	var latlng = new google.maps.LatLng(latitude, longitude);
		        geocoder.geocode({'latLng': latlng}, function(results, status) {
		       
		   	   	if (status == google.maps.GeocoderStatus.OK) {
		            if (results[0]) {	
						var inputSpotLatitude = document.getElementById("spotLatitude");
						inputSpotLatitude.setAttribute('value', latlng.lat());
			
						var inputspotLongitude = document.getElementById("spotLongitude");
						inputspotLongitude.setAttribute('value', latlng.lng());
						
						var spotName = document.getElementById("spotAddress");
						spotName.setAttribute('value', results[0].formatted_address);
				
						document.forms["searchForm"].submit();
		            }
		        } else {
		            alert("Geocoder failed due to: " + status);
		        }
		        });
	
				
		  	} 
			}); 
		}
		
		
		function handleClick(event){
			var searchString = document.getElementById("spotName").value;
			getLongitudeAndLatitudeByName(searchString);
			
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
			double spotResultLatitude = (double) request.getAttribute(HelperMethods.LATITUDE_PARAM);
			double spotResultLongitude = (double) request.getAttribute(HelperMethods.LONGITUDE_PARAM);
			out.println("addCrawlQueueRequest('"+ 
				spotName + "' , " +
				spotResultLatitude + " , " +
				spotResultLongitude + ");");
		
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
	
	<div class='container'>
		<div class='row'>
	  	<div class='col-xs-9'><input type="text" class="form-control" id="spotName" placeholder="Munich"></div>
		<div class='col-xs-3'><button type="submit" class="btn btn-default" onClick="handleClick()">Search</button></div>
		</div>
  	</div>
		
	
	<div class='container'>
		<form role="form" name="searchForm" action="/" method="post">
			<input type="hidden" name="action" value="SearchSpots" id="spotParameters" >
			<input type="hidden" name="latitude" value="default" id="spotLatitude" >
			<input type="hidden" name="longitude" value="default" id="spotLongitude" >
			<input type="hidden" name="address" value="default" id="spotAddress" >
		</form>
		
	</div>
	
	<div class='container top-buffer' id="spotResult">
	
	</div>
	
	
	
	<% out.println(helperMethods.createBodyEnd());%>
	
	
	</html>