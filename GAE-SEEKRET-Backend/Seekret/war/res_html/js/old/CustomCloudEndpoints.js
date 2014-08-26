
var constants = {
    'scopes' : 'https://www.googleapis.com/auth/userinfo.email',
    'client_id' : '1099379908084-erlt14509li8acjpd7m20770t9gi5c0g.apps.googleusercontent.com',
    'api_key' : 'AIzaSyD-X7_gYK4M6Ac6usm_Y2ic0wYfJCd4X9Y',
    'api_url' : 'https://flickeranalyser.appspot.com/_ah/api'
};

function CustomCloudEndpoints() {
    'use strict';
    var apisToLoad, callback;
    apisToLoad = 2;
    callback = function () {
        apisToLoad -= 1;
        if (apisToLoad === 0) {
            CustomCloudEndpoints.prototype.signin(true);
        }
    }
   
    //Parameters are APIName,APIVersion,CallBack function,API Root
    /*ignore jslint start*/
    gapi.client.load('clusterAPI', 'v1', callback, constants.api_url);
    gapi.client.load('oauth2','v2',callback);
    /*ignore jslint end*/
};

CustomCloudEndpoints.prototype.handleAuth = function() {
    var request = gapi.client.oauth2.userinfo.get().execute(function(resp) {
        if (!resp.code) {
            console.log ( 'OAuth worked.' );
        }else{
            console.log ( 'Something went wrong with OAuth.' );
        }
    });
}
	
CustomCloudEndpoints.prototype.signin = function(mode)  {			
    console.log ( 'Trying to sign in.' );
    gapi.auth.authorize({client_id: constants.client_id ,scope: constants.scopes, immediate: mode}, CustomCloudEndpoints.prototype.handleAuth);
}
			
			
	