package com.flickranalyser.data.flickr;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.impl.client.HttpClientBuilder;
import org.eclipse.jetty.io.ConnectedEndPoint;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.Transport;
import com.flickranalyser.pojo.Spot;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

public class FlickrRequestHandler {

	
	private static final String FLICKR_REQUEST_URL = "https://api.flickr.com/services/rest/";
	private static final String FLICKR_API_KEY = "1d39a97f7a90235ed4894bad6ad14a93";
	private static final String PHOTO_SEARCH_REQUEST = "flickr.photos.search";
	
	private String sharedSecret = "e7992cc453964557";
	
	public void getAllImagesForSpot(Spot spot){
		
		
		Flickr flickr = new Flickr(FLICKR_API_KEY, sharedSecret, new REST());
//		flickr.getPhotosInterface().search(arg0, arg1, arg2);
//		
//		StringBuilder urlForRequest = new StringBuilder(FLICKR_REQUEST_URL);
//		urlForRequest.append("?").append(PHOTO_SEARCH_REQUEST).append("&").append(FLICKR_API_KEY).append("&").append("lat=").append(spot.getLatLngPoint().getLatitude()).append("&").append("lon=").append(spot.getLatLngPoint().getLongitude()).append("&format=json");
//		
//		
//		try {
//			URL url = new URL(urlForRequest.toString());
//			URLConnection openConnection = url.openConnection();
//			InputStream inputStream = openConnection.getInputStream();
//			inputStream.
//			
////			HTTPRequest httpRequest = new HTTPRequest(url);
////			HttpURLConnection.
////			HTTPResponse response = URLFetchServiceFactory.getURLFetchService().fetch(httpRequest);
////			System.out.println(response);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
}
