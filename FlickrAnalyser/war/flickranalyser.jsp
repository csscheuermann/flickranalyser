<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.flickranalyser.pojo.Spot" %>
<%@ page import="com.flickranalyser.pojo.Cluster" %>
<%@ page import="com.javadocmd.simplelatlng.LatLng" %>


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


function initialize() {
  	var mapOptions = {
    zoom: 8,
    center: new google.maps.LatLng(48.1333, 11.5667)
 	};
  	map = new google.maps.Map(document.getElementById('map-canvas'),mapOptions);
 
 	<%  Spot spot = (Spot) request.getAttribute("spot"); 
 	int maxValue = spot.getMaxClusterViews();
  	List<Cluster> clusterListe = spot.getClusterList();
  	for ( Cluster currentCluster : clusterListe){
  		double opacity = ((double) currentCluster.getOverallViews()/maxValue);
  		out.println("addCircle("+currentCluster.getCenterOfCluster().getLatitude()+","+ currentCluster.getCenterOfCluster().getLongitude()+","+opacity+");");
  	}
  	%>


}

  	map = new google.maps.event.addDomListener(window, 'load', initialize);


</script>


  </head>
  <body>
  <h1> Here everything will happen</h1>
  <p><% out.println("Max Overall Views: "+ maxValue); %> </p>












<div id="map-canvas" style="height:1024px; width:1800px"></div>

</body>
</html> 