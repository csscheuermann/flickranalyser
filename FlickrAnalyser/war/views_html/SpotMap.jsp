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
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ page import="com.flickranalyser.businesslogic.spotfinder.impl.NearestSpotFinder" %>

<!DOCTYPE html>
<html lang="en">

	<%  HelperMethods helperMethods = (HelperMethods) request.getAttribute("helperMethods"); %>
	<% out.println(helperMethods.getHTMLHeaderUnclosed()); %>
	
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
	<script src="/res_html/js/Chart.js"></script>
	<script src="/res_html/js/ClusterDetails.js"></script>
	

     <script type="text/javascript">
	
	$(document).ready(function() { 	 	 
         $('#btnTouristic').click(function ()
         {	 
            vote('#btnTouristic', 10);
         });
        
         $('#btnSeekret').click(function (){	 
            vote('#btnSeekret', 0);
         });
	   });
		 
	function vote(buttonId, clusterRatingValue){
        
		
    	// Set a variable that is set to the div containing the overlay (created on page load)
           var page_overlay = jQuery('<div id="overlay"><div id="loadingImage"><img  src="/res_html/img/loading.gif"></div> </div>');

           // Function to Add the overlay to the page
           function showOverlay(){
               page_overlay.appendTo(document.body);
           }
           // Function to Remove the overlay from the page
           function hideOverlay(){
               page_overlay.remove();
           }
		
			
		 $.ajax({
             type: "post",
             url: "?action=EvaluateSpot", //this is my servlet
             data: "clusterKey=" +$(buttonId).val()+"&clusterRating=" + clusterRatingValue +"&spotName="+$('#spotaddress').html(),
			 beforeSend: function() { 
 					$('html, body').animate({ scrollTop: 0 }, 0);
					showOverlay();
 					
			 },  
			 
			 success: function(data){ 
			
			 		hideOverlay()
					$(buttonId).attr("disabled", true);      
              		$('#voteResultField').show();
					$('#voteResultMessage').html(data);
					$('#voteResultMessage').show();
					}
					
			 });
	}
	
     
     </script>
	
	
	
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
  
   function handleTouristicVote(key){
   		alert(key);
   }
   
   function handleSeekretVote(key){
   		alert ('seekret' + key);
   }

   function addMarker(datastoreClusterKey,clusterAdressFromGoogle, spotName,overallMaxNumberOfPOIs, overallMaxNumberOfViews, maxNumberOfPOIs, maxNumberOfViews, lat,lgt , numberOfViews,numberOfPOIsForCurrentCluster, viewCountRelativeInPercent,pOICountRealativeInPercent, touristicnessInPercent,pOICountOverallInPercent, viewCountOverallInPercent,  pictureUrl1, pictureUrl2, pictureUrl3) {
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
		
		var btnTouristicness = document.getElementById("btnTouristic");
		btnTouristicness.setAttribute('value', datastoreClusterKey);
		
		var btnSeekret = document.getElementById("btnSeekret");
		btnSeekret.setAttribute('value', datastoreClusterKey);
	
		//Now set up the Spot Info Container
		$('#spotInfoContainer').show();
		$('#spotaddress').html(spotName);
		$('#poiCount').html(overallMaxNumberOfPOIs);
		$('#spotOverallview').html(overallMaxNumberOfViews);
		
		//Now set up the Cluster Info Container
		$('#seekretSpotInfoContainer').show();
		$('#clusterAddress').html(clusterAdressFromGoogle);
		$('#clusterViews').html(numberOfViews);
		$('#clusterPOIs').html(numberOfPOIsForCurrentCluster);
		$('#maxClusterViews').html(maxNumberOfViews);
		$('#maxClusterPOIs').html(maxNumberOfPOIs);
		 
		//Now set up the Image Container
		$('#topPicturesContainer').show();
		$('#picture1').html('<img src=' + pictureUrl1 +' />');
		$('#picture2').html('<img src=' + pictureUrl2 +' />');
		$('#picture3').html('<img src=' + pictureUrl3 +' />');

		//Now set up the Charts
		$('#ratingInformationContainer').show();
		
		
		addDoughnutChart(viewCountRelativeInPercent, 'viewCountRelative', "#F7464A" , "#E2EAE9");
		addDoughnutChart(pOICountRealativeInPercent, 'poiCountRelative', "#F7464A", "#E2EAE9");
		addDoughnutChart(touristicnessInPercent, 'touristicness', "#FFBB33" , "#99CC00");
		addDoughnutChart(pOICountOverallInPercent, 'poiCountOverall', "#F7464A", "#E2EAE9");
		addDoughnutChart(viewCountOverallInPercent, 'viewCountOverall', "#F7464A", "#E2EAE9");
	
		//Now set up the buttons
		$('#voteButtonContainer').show();
		$('#voteButtonContainer').attr("disabled", true);
		  
		//Always Hide voteresult after click
		$('#voteResultField').hide();
		
		
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
	ctx.clearRect(0, 0, 200, 200);
	ctx.width = 200;
	ctx.height = 200;
	
	ctx.canvas.width = 200;
	ctx.canvas.height = 200;
	
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
			double spotRadiusInMeter = spot.getSpotRadiusInMeter();
			out.println("addCircle(" + spot.getLatitude() + "," + spot.getLongitude() + ",0.1," + spotRadiusInMeter + ");");
			String spotName = spot.getName();
	    	int maxValue = spot.getMaxClusterViews();

			List<Cluster> cluster = spot.getCluster();
	  		
			double clusterRadiusInMeter = spot.getClusterRadiusInMeter();
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
			double touristicnessInPercent = currentCluster.getOverallTouristicnessInPointsFrom1To10()*10;
		
			double pOICountOverallInPercent = ((double) (100.00/overallMaxNumberOfPOIs)*numberOfPOIsForCurrentCluster);
			double viewCountOverallInPercent = ((double) (100.00/overallMaxNumberOfViews)*clusterOverallViews);
		
		 	String datastoreClusterKey = currentCluster.getDatastoreClusterKey();
			
	   		NearestSpotFinder  nearestSpotFinder = new NearestSpotFinder();
			String clusterAdressFromGoogle = nearestSpotFinder.findAddressByLatLng(currentLat,currentLng);
			
			
			if (clusterOverallViews > 200){
	     			out.println("addMarker('"+
						datastoreClusterKey  + "','" +		
						clusterAdressFromGoogle  + "','" +
						spotName 		+ "'," +
						overallMaxNumberOfPOIs+ "," +
						overallMaxNumberOfViews	+ "," +
						maxNumberOfPOIs + "," +
						maxNumberOfViews + "," +
						currentLat 		+ "," +
						currentLng 		+ "," +
						clusterOverallViews 	+ "," +
						numberOfPOIsForCurrentCluster 	+ "," +	
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
	<% out.println(helperMethods.createVoteResultField()); %>
	
	<% out.println(helperMethods.createSpotInfo()); %>
	<% out.println(helperMethods.createSeekretSpotInformation()); %>
	
	<% out.println(helperMethods.createTopPicturesContainer()); %>		
	<% out.println(helperMethods.createRatingInformationContainer()); %>
	<% out.println(helperMethods.createVoteButtons()); %>




			
				
			
	
	
	<% out.println(helperMethods.createBodyEnd());%>
	
	</html>
	
