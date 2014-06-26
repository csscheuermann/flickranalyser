<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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



<html>
  <head>
    <title>Servlet</title>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>

<script type="text/javascript">
var map;


//Construct the circle for each value in citymap.
// Note: We scale the area of the circle based on the population.
function addCircle(lgt, lat, opacity) {
  var populationOptions = {
    strokeColor: '#FF0000',
    strokeOpacity: 1,
    strokeWeight: 2,
    fillColor: '#FF0000',
    fillOpacity: opacity,
    map: map,
    center: new google.maps.LatLng(lgt, lat),
    radius: 250
  };
  // Add the circle for this city to the map.
  var cityCircle = new google.maps.Circle(populationOptions);
}




function addMarker(lgt, lat, numberOfViews, url, numberOfPOI , overallTouristicnessInPoints, datastoreKey, overallVotes) {
	// To add the marker to the map, use the 'map' property
	var marker = new google.maps.Marker({
	    position: new google.maps.LatLng(lgt, lat),
	    url:  url,
	    map: map,
	    title: numberOfViews
	});
	
	var contentString = '<div id="content">' +
		'<h1> ClusterName </h1>' +
		'<p>Number of POI: ' + numberOfPOI + '<br />' +
		'Number of Views: ' + numberOfViews + '<br />'+
		'Touristicness in Percent: ' + overallTouristicnessInPoints + '<br />'+
		'Vote Count: ' + overallVotes + '<br />'+
		'DataStore Key: ' + datastoreKey + '</p>'+
		
		
		'<img src="' + url + '"/>';
		 
	
		var infowindow = new google.maps.InfoWindow({
		      content: contentString,
			  maxWidth: 300
		  });

		
		  google.maps.event.addListener(marker, 'click', function() {
		    infowindow.open(map,marker);
		  });
	
	
	
		  }

<%  Spot spot = (Spot) request.getAttribute("spot"); %>

	function initialize() {
  		var mapOptions = {
    	zoom: 14,
   	 <% out.println("center: new google.maps.LatLng("+ spot.getLatLngPoint().getLatitude()+","+  spot.getLatLngPoint().getLongitude()+")");%>
 	};
  	map = new google.maps.Map(document.getElementById('map-canvas'),mapOptions);
 <%
 	int maxValue = spot.getMaxClusterViews();
  	List<Cluster> cluster = spot.getCluster();
  	String FLICKR_REQUEST_URL = "https://api.flickr.com/services/rest/";
  	String FLICKR_API_KEY = "1d39a97f7a90235ed4894bad6ad14a93";
  	String PHOTO_SEARCH_REQUEST = "flickr.photos.geo.photosForLocation";
  	
	for (Cluster currentCluster : cluster){
		double opacity = ((double) currentCluster.getOverallViews()/maxValue);
    	double currentLat = currentCluster.getCenterOfCluster().getLatitude();
    	double currentLng = currentCluster.getCenterOfCluster().getLongitude();
		
		
		int overallViews = currentCluster.getOverallViews();
		int pOICount = currentCluster.getNumberOfPOIs();
		
		double overallTouristicnessInPointsFrom1To10 = currentCluster.getOverallTouristicnessInPointsFrom1To10();	
		int overallTouristicnessVotes = currentCluster.getOverallTouristicnessVotes();	
		
    	if (currentCluster.getOverallViews() > 200){
  			out.println("addMarker("+ currentLat +"," + currentLng + ", '" + overallViews + "', '" + currentCluster.getUrlOfMostViewedPicture() + "', '" + pOICount +  "', '" + overallTouristicnessInPointsFrom1To10 + "', '" +	currentCluster.getDatastoreClusterKey() + "', '" +	overallTouristicnessVotes + "');");
    	}
    	out.println("addCircle(" + currentLat + "," + currentLng + "," + opacity + ");");
	}
  	%>
}
  	map = new google.maps.event.addDomListener(window, 'load', initialize);
</script>


  </head>
  <body>
  <h1> Here everything will happen</h1>

<div id="map-canvas" style="height:800px; width:600px"></div>

</body>
</html> 