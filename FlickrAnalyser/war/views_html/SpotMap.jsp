<%@ page import="java.util.List"%>
<%@ page import="com.flickranalyser.pojo.Spot"%>
<%@ page import="com.flickranalyser.pojo.Cluster"%>
<%@ page import="com.javadocmd.simplelatlng.LatLng"%>
<%@ page import="java.lang.StringBuilder"%>
<%@ page import="java.util.Iterator"%>
<%@ page
	import="com.flickranalyser.businesslogic.common.ParameterConstants"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.flickranalyser.html.common.HelperMethods"%>
<%@ page import="com.google.appengine.api.datastore.Key"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>


<!DOCTYPE html>
<html lang="en">

<%  HelperMethods helperMethods = (HelperMethods) request.getAttribute("helperMethods"); %>
<% out.println(helperMethods.getHTMLHeaderUnclosed()); %>

<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
<script src="/res_html/js/Chart.js"></script>
<script src="/res_html/js/spinner.js"></script>
<script src="/res_html/js/Fluster2.packed.js"></script>
<script src="/res_html/js/RequestsToSeekret.js"></script>
<script type="text/javascript">
	
	
	<%  
	boolean useFluster = helperMethods.isFlusterFlag(request);
	Spot spot = (Spot) request.getAttribute("spot");
	List<Cluster> cluster = (List<Cluster>) request.getAttribute("filteredClusters");
	final String DIV_MAP_ID_FILTERED = "map-canvas";
	%>


	//KOMMT IN DIE INIT NACHHER	 	
    $(document).ready(function() { 	 	 
        $('#btnTouristic').click(function (){	 
	        google.appengine.seekret.vote('#btnTouristic', 10);
        });
        
        $('#btnSeekret').click(function (){	 
            google.appengine.seekret.vote('#btnSeekret', 0);
        });
	
        $('#btnDismiss').click(function (){	 
            google.appengine.seekret.dismissCluster($('#btnDismiss').val());
        });
		
	});
		 
	
	
     
     </script>



<script type="text/javascript">
		
		var map;
		var fluster;
		var lastClickedMarker = null;
		var lastClickedMarkerIcon = null;
		var geo = new google.maps.Geocoder;

		
   //Construct the circle for each value in citymap.
   // Note: We scale the area of the circle based on the population.
   function addCircle(lat,
		   lgt,
		   opacity,
		   radius,
		   datastoreClusterKey,
		   spotName,
		   clusterAlreadyVoted,
		   clusterAlreadyDismissed,
		   overallMaxNumberOfPOIs,
		   overallMaxNumberOfViews,
		   numberOfViews,
		   numberOfPOIsForCurrentCluster,
		   maxNumberOfViews,
		   maxNumberOfPOIs,
			viewCountRelativeInPercent,
			pOICountRealativeInPercent, 
			touristicnessInPercent,
			pOICountOverallInPercent,
			viewCountOverallInPercent,
		   pictureUrl1,
		   pictureUrl2,
		   pictureUrl3
		   ) {
     var populationOptions = {
       strokeColor: '#CC0000',
       strokeOpacity: 0.5,
       strokeWeight: 2,
       fillColor: '#FF4444',
       fillOpacity: opacity,
       map: map,
       center: new google.maps.LatLng(lat, lgt),
       radius: radius
     };
     // Add the circle for this city to the map.
     var cityCircle = new google.maps.Circle(populationOptions);
     google.maps.event.addListener(cityCircle, 'click', function() {
    	
    	 showVoting(datastoreClusterKey,
    			   spotName,
    			   clusterAlreadyVoted,
    			   clusterAlreadyDismissed,
    			   overallMaxNumberOfPOIs,
    			   overallMaxNumberOfViews,
    			   lat,
    			   lgt,
    			   numberOfViews,
    			   numberOfPOIsForCurrentCluster,
    			   maxNumberOfViews,
    			   maxNumberOfPOIs,
    			   viewCountRelativeInPercent,
    			   pOICountRealativeInPercent, 
    			   touristicnessInPercent,
    			   pOICountOverallInPercent,
    			   viewCountOverallInPercent,
    			   pictureUrl1,
    			   pictureUrl2,
    			   pictureUrl3);	 
     });
   }
   
   
   function showVoting(
		   datastoreClusterKey,
		   spotName,
		   clusterAlreadyVoted,
		   clusterAlreadyDismissed,
		   overallMaxNumberOfPOIs,
		   overallMaxNumberOfViews,
		   lat,
		   lgt,
		   numberOfViews,
		   numberOfPOIsForCurrentCluster,
		   maxNumberOfViews,
		   maxNumberOfPOIs,
			viewCountRelativeInPercent,
			pOICountRealativeInPercent, 
			touristicnessInPercent,
			pOICountOverallInPercent,
			viewCountOverallInPercent,
		   pictureUrl1,
		   pictureUrl2,
		   pictureUrl3){
		var btnTouristicness = document.getElementById("btnTouristic");
 		btnTouristicness.setAttribute('value', datastoreClusterKey);
 		
 		var btnDismiss = document.getElementById("btnDismiss");
 		btnDismiss.setAttribute('value', datastoreClusterKey);
 		
 		var btnSeekret = document.getElementById("btnSeekret");
 		btnSeekret.setAttribute('value', datastoreClusterKey);
 	
 		$('#btnTouristic').attr("disabled", clusterAlreadyVoted);   
 		$('#btnDismiss').attr("disabled", clusterAlreadyDismissed);   
 		$('#btnSeekret').attr("disabled", clusterAlreadyVoted);   
 	
 	
 		//Now set up the Spot Info Container
 		$('#spotInfoContainer').show();
 		$('#spotaddress').html(spotName);
 		$('#poiCount').html(overallMaxNumberOfPOIs);
 		$('#spotOverallview').html(overallMaxNumberOfViews);
 		
 		//Now set up the Cluster Info Container
 		$('#seekretSpotInfoContainer').show();
 	    
 		getAddress(lat, lgt);
 		
 		
 		$('#clusterViews').html(numberOfViews);
 		$('#clusterPOIs').html(numberOfPOIsForCurrentCluster);
 		$('#maxClusterViews').html(maxNumberOfViews);
 		$('#maxClusterPOIs').html(maxNumberOfPOIs);
 		 
 		//Now set up the Image Container
 		$('#topPicturesContainer').show();

 		google.appengine.seekret.addImageTagToDivId('#picture1', pictureUrl1);
 		google.appengine.seekret.addImageTagToDivId('#picture2', pictureUrl2);
 		google.appengine.seekret.addImageTagToDivId('#picture3', pictureUrl3);
 		
 		$('#ratingInformationContainer').show();
 		
 		addDoughnutChart(viewCountRelativeInPercent, 'viewCountRelative', "#F7464A" , "#E2EAE9");
 		addDoughnutChart(pOICountRealativeInPercent, 'poiCountRelative', "#F7464A", "#E2EAE9");
 		addDoughnutChart(touristicnessInPercent, 'touristicness', "#FFBB33" , "#99CC00");
 		addDoughnutChart(pOICountOverallInPercent, 'poiCountOverall', "#F7464A", "#E2EAE9");
 		addDoughnutChart(viewCountOverallInPercent, 'viewCountOverall', "#F7464A", "#E2EAE9");
 	
 		if (clusterAlreadyVoted || clusterAlreadyDismissed  || checkIfAlreadyVotedByMarkerPicture(lastClickedMarkerIcon) ){
			//Now set up the buttons
			$('#voteButtonContainer').hide();
			$('#voteResultField').show();
			$('#voteResultMessage').html("Already rated.");
		} else{
			$('#voteButtonContainer').show();	
			$('#voteResultField').hide();
		} 
	   
	   
   }

   function addMarker(datastoreClusterKey, spotName, clusterAlreadyVoted,clusterAlreadyDismissed, overallMaxNumberOfPOIs, overallMaxNumberOfViews, maxNumberOfPOIs, maxNumberOfViews, lat, lgt, numberOfViews,numberOfPOIsForCurrentCluster, viewCountRelativeInPercent,pOICountRealativeInPercent, touristicnessInPercent,pOICountOverallInPercent, viewCountOverallInPercent,  pictureUrl1, pictureUrl2, pictureUrl3) {
   
   var iconForMarker = '/res_html/img/eye_not_watched_yet.png';
   if(clusterAlreadyVoted){
       iconForMarker = '/res_html/img/eye_already_voted.png';
   }else if(clusterAlreadyDismissed){
       iconForMarker = '/res_html/img/eye_already_dismissed.png';
   }
   
   	// To add the marker to the map, use the 'map' property
   	var marker = new google.maps.Marker({
   	    position: new google.maps.LatLng(lat, lgt),
	   	 <% 
	     if(!useFluster){
	    	 out.println("map:map,");
	     }
	   	%>
   	    title: 'CLUSTER NAME STILL TO DO',
		icon: iconForMarker 
   	});
	
   <% 
   if(useFluster){
	out.println("fluster.addMarker(marker);");   
   }
   %>
   
   google.maps.event.addListener(marker, 'click', function() {
   
   	
   
   	if (lastClickedMarker != null){
	    if (lastClickedMarkerIcon === '/res_html/img/eye_not_watched_yet.png'){
	    	lastClickedMarker.setIcon('/res_html/img/eye_already_watched.png');
		}else{
			lastClickedMarker.setIcon(lastClickedMarkerIcon);
		}
   		
	}
	
	lastClickedMarker = marker;
	lastClickedMarkerIcon = marker.getIcon();
	
	marker.setIcon('/res_html/img/eye_currently_watching.png');
			
	showVoting(datastoreClusterKey,
			   spotName,
			   clusterAlreadyVoted,
			   clusterAlreadyDismissed,
			   overallMaxNumberOfPOIs,
			   overallMaxNumberOfViews,
			   lat,
			   lgt,
			   numberOfViews,
			   numberOfPOIsForCurrentCluster,
			   maxNumberOfViews,
			   maxNumberOfPOIs,
				viewCountRelativeInPercent,
				pOICountRealativeInPercent, 
				touristicnessInPercent,
				pOICountOverallInPercent,
				viewCountOverallInPercent,
			   pictureUrl1,
			   pictureUrl2,
			   pictureUrl3, 
			   lastClickedMarkerIcon);
	
 
	
		
		
   });
   
   }
   
   function checkIfAlreadyVotedByMarkerPicture(lastClickedMarkerIcon){  
       if ((lastClickedMarkerIcon === '/res_html/img/eye_already_dismissed.png') || (lastClickedMarkerIcon ==='/res_html/img/eye_already_voted.png')){
	       return true;
       }
	   return false;
   }
   
   function getAddress(lat, lgt){
	//Get the Custer Address
	google.appengine.seekret.getClusterAddressByLatLong(lat, lgt);
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
	var elementById = document.getElementById(elementId)
	if (elementById !== null){
   	    var ctx = elementById.getContext("2d");
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
	
	}


	
	


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
     	map = new google.maps.Map(document.getElementById(<% out.println("'"+DIV_MAP_ID_FILTERED+"'"); %>),mapOptions);
     	fluster = new Fluster2(map);
     	
     // Add a listener to the zoom change event so we can change the grid size
     // of the cluster script. Should be dynamic!
     google.maps.event.addListener(map, "zoom_changed", function() {

       var zoomLevel = map.getZoom();

       switch(true){
         case zoomLevel > 13:
           fluster.gridSize = 0;
           break;
         case zoomLevel > 12:
           fluster.gridSize = 15;
           break;
         case zoomLevel > 7:
           fluster.gridSize = 20;
           break;
         case zoomLevel > 5:
           fluster.gridSize = 40;
           break;
         default:
           fluster.gridSize = 60;
           break;
       }

     });
	    <%

			if (spot != null){
			double spotRadiusInMeter = spot.getSpotRadiusInMeter();
			//out.println("addCircle(" + spot.getLatitude() + "," + spot.getLongitude() + ",0.1," + spotRadiusInMeter + ");");
			String spotName = spot.getName();
	    	int maxValue = spot.getMaxClusterViews();

			
	  		
			double clusterRadiusInMeter = spot.getClusterRadiusInMeter();
			int overallMaxNumberOfPOIs = spot.getOverallMaxPOINumberPerCluster();
			int overallMaxNumberOfViews = spot.getOverallMaxViewNumberPerCluster();
		
			int maxNumberOfPOIs = spot.getMaxNumberOfPOIsPerCluster();	
			int maxNumberOfViews = spot.getMaxNumberOfViewsPerCluster();	
		
	
			
 
	   	for (Cluster currentCluster : spot.getCluster()){
	   		
	   		boolean clusterWasFiltered = !cluster.contains(currentCluster);
	   		
		
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
			
			boolean clusterAlreadyVoted = helperMethods.checkIfClusterWasAlreadyRated(datastoreClusterKey);
	   		boolean clusterAlreadyDismissed = helperMethods.checkIfClusterWasAlreadyDismissed(datastoreClusterKey);
			
			if (!clusterWasFiltered){
	     			out.println("addMarker('"+
						datastoreClusterKey  + "','" +		
						spotName 		+ "'," +
						clusterAlreadyVoted + "," +
						clusterAlreadyDismissed + "," +
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
				
				
	       	}else{
	       	   double opacityViewCountRelative = viewCountRealativeInPercent/100.00;
	       		out.println("addCircle(" +
			    currentLat + "," +
	       		currentLng + "," +
			    opacityViewCountRelative + "," +
	       		clusterRadiusInMeter + ",'"+
	       		datastoreClusterKey  + "','" +		
				spotName 		+ "'," +
				clusterAlreadyVoted + "," +
				clusterAlreadyDismissed + "," +
				overallMaxNumberOfPOIs+ "," +
				overallMaxNumberOfViews	+ "," +
				maxNumberOfPOIs + "," +
				maxNumberOfViews + "," +
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
	       }	
	   	out.println("fluster.initialize();");
	   	}
	     	%>
   }
     	map = new google.maps.event.addDomListener(window, 'load', initialize);
   </script>


</head>






<% out.println(helperMethods.createBodyBegin()); 
	 out.println(helperMethods.createNavigation(false)); 
	 out.println(helperMethods.createMap(DIV_MAP_ID_FILTERED)); 
	
	 out.println(helperMethods.createVoteResultField());
	 out.println(helperMethods.createTopPicturesContainer()); 
	 out.println(helperMethods.createVoteButtons()); 
	
	 out.println(helperMethods.createSpotInfo()); 
	 out.println(helperMethods.createSeekretSpotInformation());
	 out.println(helperMethods.createRatingInformationContainer()); 
	
	 out.println(helperMethods.createBodyEnd()); %>

</html>

