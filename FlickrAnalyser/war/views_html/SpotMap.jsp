<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.flickranalyser.pojo.Spot" %>
<%@ page import="com.flickranalyser.pojo.Cluster" %>
<%@ page import="com.javadocmd.simplelatlng.LatLng" %>
<%@ page import="java.lang.StringBuilder" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.flickranalyser.businesslogic.common.ParameterConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.flickranalyser.html.common.HelperMethods" %>

<!DOCTYPE html>
<html lang="en">

	<%  HelperMethods helperMethods = (HelperMethods) request.getAttribute("helperMethods"); %>
	<% out.println(helperMethods.getHTMLHeaderUnclosed()); %>
	
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
	<script src="/res_html/js/Chart.js"></script>
	<script src="/res_html/js/ClusterDetails.js"></script>
   	<script type="text/javascript">
		var map;
		var lastClickedMarker = null;
		  var geo = new google.maps.Geocoder;

   //Construct the circle for each value in citymap.
   // Note: We scale the area of the circle based on the population.
   function addCircle(lgt, lat, opacity,radius ) {
     var populationOptions = {
       strokeColor: '#FF0000',
       strokeOpacity: 1,
       strokeWeight: 2,
       fillColor: '#FF0000',
       fillOpacity: opacity,
       map: map,
       center: new google.maps.LatLng(lgt, lat),
       radius: radius
     };
     // Add the circle for this city to the map.
     var cityCircle = new google.maps.Circle(populationOptions);
   }
  

   function addMarker(spotName, lat,lgt , numberOfViews, viewCountRelativeInPercent,pOICountRealativeInPercent, touristicnessInPercent,pOICountOverallInPercent, viewCountOverallInPercent,  pictureUrl1, pictureUrl2, pictureUrl3) {
   	// To add the marker to the map, use the 'map' property
   	var marker = new google.maps.Marker({
   	    position: new google.maps.LatLng(lat, lgt),
   	    map: map,
   	    title: 'CLUSTER NAME STILL TO DO',
		icon: '/res_html/img/eye_not_watched_yet.png'
   	});
	
   google.maps.event.addListener(marker, 'click', function() {
   
   	if (lastClickedMarker != null){
   		lastClickedMarker.setIcon('/res_html/img/eye_already_watched.png');
		lastClickedMarker = marker;
	}else{
		lastClickedMarker = marker;
	}
	
	marker.setIcon('/res_html/img/eye_currently_watching.png');
	
	
		var clusterDetails = new ClusterDetails();
		clusterDetails.addElementImage('spot-image1', 'ClusterDetailPicture1', pictureUrl1);
		clusterDetails.addElementImage('spot-image2', 'ClusterDetailPicture2', pictureUrl2);
		clusterDetails.addElementImage('spot-image3', 'ClusterDetailPicture3', pictureUrl3);
		
		addDoughnutChart(viewCountRelativeInPercent, 'viewCountRelative', "#F7464A" , "#E2EAE9");
		addDoughnutChart(pOICountRealativeInPercent, 'poiCountRelative', "#F7464A", "#E2EAE9");
		addDoughnutChart(touristicnessInPercent, 'touristicness', "#FFBB33" , "#99CC00");
		
		addDoughnutChart(pOICountOverallInPercent, 'poiCountOverall', "#F7464A", "#E2EAE9");
		addDoughnutChart(viewCountOverallInPercent, 'viewCountOverall', "#F7464A", "#E2EAE9");
		
	    clusterDetails.addElementDiv('spotInfo', 'spotName',  '<h4>' + spotName + '</h4>');
		
		getLatLong(spotName);
		codeLatLng(lat,lgt);
		
		
		
		
   });
   
   }
   
   function codeLatLng(lat, lng) {
     var latlng = new google.maps.LatLng(lat, lng);
     geo.geocode({'latLng': latlng}, function(results, status) {
       	var clusterDetails = new ClusterDetails();
		 var locationString = '<p>Latitude ' + lat + '<br /> Longitude ' + lng + '</p>';
		clusterDetails.addElementDiv('spotInfo', 'clusterGeoCoordinates',  locationString);
	   if (status == google.maps.GeocoderStatus.OK) {
         if (results[1]) {
		 clusterDetails.addElementDiv('spotInfo', 'clusterAdress1',  results[0].formatted_address);
         }
       } else {
         alert("Geocoder failed due to: " + status);
       }
     });
   }
   
   function getLatLong(address){
         geo.geocode({'address':address},function(results, status){
                 if (status == google.maps.GeocoderStatus.OK) {
				 
				 var clusterDetails = new ClusterDetails();
				 var latlngString = results[0].geometry.location;
				 var locationString = '<p>Latitude ' + latlngString.lat() + '<br /> Longitude ' + latlngString.lng() + '</p>';
				 clusterDetails.addElementDiv('spotInfo', 'spot_location', locationString);
				   
                 } else {
                   //alert("Geocode was not successful for the following reason: " + status);
                 }

          });

     }
	 
	 
   
   
   function addDoughnutChart(percentage, elementId, colorPercent, colorRemainer) {

   	var remainder = 100 - percentage;
   	var data = [
   		{
   			value: percentage,
   			color: colorPercent
   		},
   		{
   			value : remainder,
   			color : colorRemainer
   		}
   	]
	
   	//Get the context of the canvas element we want to select
   	var ctx = document.getElementById(elementId).getContext("2d");
	
   	var options = {
   	segmentShowStroke : true
   	};
	
   	new Chart(ctx).Doughnut(data,options);
	
	
	}



	
   <%  Spot spot = (Spot) request.getAttribute("spot"); %>

   	function initialize() {
     		var mapOptions = {
       	zoom: 14,
      	 <% 
			 if (spot != null){
			 	out.println("center: new google.maps.LatLng("+ spot.getLatitude()+","+  spot.getLongitude()+")");
		 	}else{
		 		out.println("center: new google.maps.LatLng('43','11')");
		 	}
			%>
    	};
     	map = new google.maps.Map(document.getElementById('map-canvas'),mapOptions);

	    <%

			if (spot != null){
			double spotRadiusInMeter = spot.getSpotRadiusInKm()*1000;
			out.println("addCircle(" + spot.getLatitude() + "," + spot.getLongitude() + ",0.1," + spotRadiusInMeter + ");");
			String spotName = spot.getName();
	    	int maxValue = spot.getMaxClusterViews();

			List<Cluster> cluster = spot.getCluster();
	  		
			double clusterRadiusInMeter = spot.getClusterRadiusInKm()*1000;
			int overallMaxNumberOfPOIs = spot.getOverallMaxPOINumberPerCluster();
		
			int overallMaxNumberOfViews = spot.getOverallMaxViewNumberPerCluster();
		
			int maxNumberOfPOIs = spot.getMaxNumberOfPOIsPerCluster();	
			int maxNumberOfViews = spot.getMaxNumberOfViewsPerCluster();	
		
		
	     	String FLICKR_REQUEST_URL = "https://api.flickr.com/services/rest/";
	     	String FLICKR_API_KEY = "1d39a97f7a90235ed4894bad6ad14a93";
	     	String PHOTO_SEARCH_REQUEST = "flickr.photos.geo.photosForLocation";
 
	   	for (Cluster currentCluster : cluster){
		
			int numberOfPOIsForCurrentCluster = currentCluster.getNumberOfPOIs();
 
	       	double currentLat = currentCluster.getLatitude();
	       	double currentLng = currentCluster.getLongitude();
	   		int clusterOverallViews = currentCluster.getOverallViews();
			
			List<String> urlOfMostViewedPicture = currentCluster.getUrlOfMostViewedPicture();
		
			String[] urls = new String[]{"","",""};	
			if (urlOfMostViewedPicture != null){
				for(int i = 0; i < urlOfMostViewedPicture.size(); i++){
					urls[i] = urlOfMostViewedPicture.get(i);
				}
			}
		
			double viewCountRealativeInPercent = ((double) (100.00/maxNumberOfViews)*clusterOverallViews);
			double pOICountRealativeInPercent = ((double) (100.00/maxNumberOfPOIs)*numberOfPOIsForCurrentCluster);
			double touristicnessInPercent = currentCluster.getOverallTouristicnessInPointsFrom1To10()*100;
		
			double pOICountOverallInPercent = ((double) (100.00/overallMaxNumberOfPOIs)*numberOfPOIsForCurrentCluster);
			double viewCountOverallInPercent = ((double) (100.00/overallMaxNumberOfViews)*clusterOverallViews);
		
			if (clusterOverallViews > 200){
	     			out.println("addMarker('"+ 
						spotName 		+ "'," +
						currentLat 		+ "," +
						currentLng 		+ "," +
						clusterOverallViews 	+ "," +
						viewCountRealativeInPercent 	+ "," +
						pOICountRealativeInPercent 	+ "," +	
						touristicnessInPercent 	+ "," +	
						pOICountOverallInPercent 	+ "," +	
						viewCountOverallInPercent 	+ ",'" +		
						 urls[0] + "' , '" +		
						 urls[1] + "' , '" +
						 urls[2] + "');");
				
				
	       	}
			double opacityViewCountRelative = viewCountRealativeInPercent/100.00;
	       	out.println("addCircle(" + currentLat + "," + currentLng + "," + opacityViewCountRelative + "," + clusterRadiusInMeter + ");");
		}	
	   	}
	     	%>
   }
     	map = new google.maps.event.addDomListener(window, 'load', initialize);
   </script>


   </head>
	
	
	
	
	
	
	<% out.println(helperMethods.createBodyBegin()); %>
	<% out.println(helperMethods.createNavigation(false)); %>
	<% out.println(helperMethods.createMap()); %>
	
	<div class='container'>
		<div class='row'>
		    <div class="col-xs-12">
				<h2>Spot Info</h2>
				 <% 
				if (spot != null){	
					int overallMaxNumberOfPOIs = spot.getOverallMaxPOINumberPerCluster();
					int overallMaxNumberOfViews = spot.getOverallMaxViewNumberPerCluster();
			   	 	out.println("Overall POI Count: " + overallMaxNumberOfPOIs); 
			     	out.println("Overall View Count: " + overallMaxNumberOfViews); 
				}	
				%>
			</div>
		</div>
	</div>	
	

<div class='container'>
	<div class='row'>
	    <div class="col-xs-12">
			<h2>Cluster Information</h2>
		
		</div>
	</div>
</div>	
				
				
	<div class='container'>
		
		
			
		<div class='row'>
		    <div class="col-md-3">
				<div id="spotInfo"> </div>
			</div>
			
		    <div class="col-md-3">
				<div class="centeralized-div" id="spot-image1">	
				</div>
			</div>
			
			
			<div class="col-md-3">
				<div class="centeralized-div" id="spot-image2">	
				</div>
			</div>
			
			<div class="col-md-3">
				<div class="centeralized-div" id="spot-image3">	
				</div>
			</div>
			
		</div>	
		<div class='row'>
		</div>	
		
		<div class='row'>
		    <div class="col-md-4">
				<h4 class="centeralized-div" >SEEKRET-METER (USER DEFINED)</h4>
			</div>
			
			
			<div class="col-md-4">
				<h4 class="centeralized-div" >VIEW COUNT (RELATIVE)</h4>
			</div>
			
			<div class="col-md-4">
				<h4 class="centeralized-div" >POI COUNT (RELATIVE)</h4>
			</div>
			
		</div>	
		
		<div class='row'>
		    <div class="col-md-4">
					<div class="centeralized-div">
						<canvas id="touristicness" width="200" height="200"></canvas>
					</div>	
			</div>
			
			
			<div class="col-md-4">
				<div class="centeralized-div">
					<canvas id="viewCountRelative" width="200" height="200"></canvas>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="centeralized-div">
					<canvas id="poiCountRelative" width="200" height="200"></canvas>
				</div>
			</div>
			
		</div>	
	</div>


		<div class='container'>
			<div class='row'>
			    <div class="col-md-4">
					<h4 class="centeralized-div" ></h4>
				</div>
			
			
				<div class="col-md-4">
					<h4 class="centeralized-div" >VIEW COUNT (ABSOLUTE)</h4>
				</div>
			
				<div class="col-md-4">
					<h4 class="centeralized-div" >POI COUNT (ABSOLUTE)</h4>
				</div>
			
			</div>	
			
			<div class='row'>
			    <div class="col-md-4">
					<div class="centeralized-div">
						
					</div>
				</div>
				<div class="col-md-4">
					<div class="centeralized-div">
						<canvas id="viewCountOverall" width="200" height="200"></canvas>
					</div>
				</div>
			
				<div class="col-md-4">
					<div class="centeralized-div">
						<canvas id="poiCountOverall" width="200" height="200"></canvas>
					</div>
				</div>
			</div>
			</div>
			
			
	
	
	<% out.println(helperMethods.createBodyEnd());%>
	
	</html>
	
