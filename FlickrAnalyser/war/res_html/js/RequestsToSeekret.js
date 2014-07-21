var google = google || {};
google.appengine = google.appengine || {};
google.appengine.seekret = google.appengine.seekret || {};


// Set a variable that is set to the div containing the overlay (created on page load)
google.appengine.seekret.page_overlay = jQuery('<div id="overlay"><div id="loadingImage"><img  src="/res_html/img/loading.gif"></div> </div>');

    // Function to Add the overlay to the page
google.appengine.seekret.showOverlay = function(){
         google.appengine.seekret.page_overlay.appendTo(document.body);
    }
    
    // Function to Remove the overlay from the page
google.appengine.seekret.hideOverlay = function(){
         google.appengine.seekret.page_overlay.remove();
    }
    

google.appengine.seekret.getClusterAddressByLatLong = function(lat, lng) {

 $.ajax({
         type: "post",
         url: "?action=GetClusterAddress", //this is my servlet
         data: "latitude=" + lat +"&longitude=" + lng,
	     beforeSend: function() { 
 		    $('html, body').animate({ scrollTop: 0 }, 0);
		    google.appengine.seekret.showOverlay();
 		},  
	    success: function(data){ 
	        google.appengine.seekret.hideOverlay()
			$('#clusterAddress').html(data);
		}
	});
}




google.appengine.seekret.dismissCluster = function(clusterDatastoreKey) {

$.ajax({
         type: "post",
         url: "?action=DismissCluster", //this is my servlet
         data: "clusterDatastoreKey=" + clusterDatastoreKey,
	     beforeSend: function() { 
 		    $('html, body').animate({ scrollTop: 0 }, 0);
		    google.appengine.seekret.showOverlay();
 		},  
	    success: function(data){ 
	        google.appengine.seekret.hideOverlay()
			$('#voteResultField').show();
		    $('#voteResultMessage').html(data);
		    $('#voteResultMessage').show();
		}
	});
	
}

google.appengine.seekret.addImageTagToDivId = function(divId, url) {
	if (url === ""){
		$(divId).html('');
	}else{
		$(divId).html("<img src='" + url +"' height='160px' />");
	}
}




google.appengine.seekret.vote = function(buttonId, clusterRatingValue) {
   


	
     $.ajax({
         type: "post",
         url: "?action=EvaluateSpot", //this is my servlet
         data: "clusterKey=" +$(buttonId).val()+"&clusterRating=" + clusterRatingValue +"&spotName="+$('#spotaddress').html(),
	     beforeSend: function() { 
 		    $('html, body').animate({ scrollTop: 0 }, 0);
		    google.appengine.seekret.showOverlay();
 		},  
	    success: function(data){ 
	        google.appengine.seekret.hideOverlay()
			$(buttonId).attr("disabled", true);      
            $('#voteResultField').show();
		    $('#voteResultMessage').html(data);
		    $('#voteResultMessage').show();
		}
	});
}