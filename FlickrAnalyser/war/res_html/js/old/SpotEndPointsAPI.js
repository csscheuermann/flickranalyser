/**
 * @fileoverview
 * Provides methods for the Spot API.
 */


var constants = {
    'scopes' : 'https://www.googleapis.com/auth/userinfo.email',
    'client_id' : '1099379908084-erlt14509li8acjpd7m20770t9gi5c0g.apps.googleusercontent.com',
    'api_key' : 'AIzaSyD-X7_gYK4M6Ac6usm_Y2ic0wYfJCd4X9Y',
    'api_url' : 'https://flickeranalyser.appspot.com/_ah/api'
};

var google = google || {};
google.appengine = google.appengine || {};
google.appengine.seekret = google.appengine.seekret || {};

google.appengine.seekret.signedIn = false;

google.appengine.seekret.userAuthed = function() {
    var request = gapi.client.oauth2.userinfo.get().execute(function(resp) {
        if (!resp.code) {
            google.appengine.seekret.signedIn = true;
	        console.log ('logged in');
	         $('#seekretNavigation').html(google.appengine.seekret.addNavigation());
        }
   
    });
};

google.appengine.seekret.signin = function(mode, callback) {
    gapi.auth.authorize({client_id: constants.client_id, scope: constants.scopes, immediate: mode}, callback);
};


google.appengine.seekret.auth = function() {
    if (!google.appengine.seekret.signedIn) {
        google.appengine.seekret.signin(false, google.appengine.seekret.userAuthed);
    } else {
        google.appengine.seekret.signedIn = false;
	    alert('Not signed in');
    }
};

//  var signinButton = document.querySelector('#signinButton');
// signinButton.addEventListener('click', google.appengine.samples.hello.auth);

google.appengine.seekret.init = function() {
  // Loads the OAuth and helloworld APIs asynchronously, and triggers login
  // when they have completed.
  var apisToLoad;
  var callback = function() {
    if (--apisToLoad == 0) {
      google.appengine.seekret.signin(true, google.appengine.seekret.userAuthed);
    }
  }

  apisToLoad = 3; // must match number of calls to gapi.client.load()
  gapi.client.load('spotAPI', 'v1', callback, constants.api_url);
  gapi.client.load('clusterAPI', 'v1', callback, constants.api_url);
  gapi.client.load('oauth2', 'v2', callback);
};




google.appengine.seekret.addNavigation = function()  {			
		var navigation = "";

		navigation += "<li class='active'><a href='/'>Home</a></li>";

		if(!google.appengine.seekret.signedIn){
			navigation += "<li><a href='?showView=Login'>Login</a></li>";
		}else{
			navigation += "<li><a href='?showView=TopSpots'>Top Spots</a></li>";
			navigation += "<li><a href='?showView=SearchSpots'>Search Spot</a></li>";
		}
		navigation += "</li>";
	
		return 	navigation;
	};