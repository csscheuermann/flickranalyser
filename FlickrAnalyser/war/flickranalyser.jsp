<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.flickranalyser.pojo.Spot" %>

<html>
  <head>
    <title>Servlet</title>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>

<script>
var map;
function initialize() {
  var mapOptions = {
    zoom: 8,
    center: new google.maps.LatLng(-34.397, 150.644)
  };
  map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);
}

google.maps.event.addDomListener(window, 'load', initialize);
</script>


  </head>
  <body>
  <h1> Here everything will happen</h1>


<% Spot spot = ((Spot) request.getAttribute("spot")); %>
<% out.println( "Cluster Found: " + spot.getClusterList().size());%>








<div id="map-canvas" style="height:300px; width:500px"></div>

</body>
</html> 