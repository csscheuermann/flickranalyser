package com.flickranalyser.data.flickr;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;
import com.flickranalyser.pojo.PointOfInterest;
import com.flickranalyser.pojo.Spot;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONArray;

public class FlickrRequestHandler {

	private static final String FLICKR_REQUEST_URL = "https://api.flickr.com/services/rest/";
	private static final String FLICKR_API_KEY = "1d39a97f7a90235ed4894bad6ad14a93";
	private static final String PHOTO_SEARCH_REQUEST = "flickr.photos.search";

	private String sharedSecret = "e7992cc453964557";

	public void getAllImagesForSpot(Spot spot) {

		// REST rest = new REST();
		// Flickr flickr = new Flickr( FLICKR_API_KEY,sharedSecret, rest);
		// System.out.println("apikey:"+flickr.getApiKey());
		// System.out.println("shared secret:"+flickr.getSharedSecret());
		//
		// System.out.println(flickr.getAuthInterface().getRequestToken());
		// // try {
		// // flickr.getPhotosInterface().getPhoto("");
		// // } catch (FlickrException e1) {
		// // // TODO Auto-generated catch block
		// // e1.printStackTrace();
		// // }
		// SearchParameters searchParameters = new SearchParameters();
		// //
		// searchParameters.setLatitude(String.valueOf(spot.getLatLngPoint().getLatitude()));
		// //
		// searchParameters.setLongitude(String.valueOf(spot.getLatLngPoint().getLongitude()));
		// try {
		// int photosPerPage = 100;
		// int page = 1;
		// PhotoList<Photo> result =
		// flickr.getPhotosInterface().search(searchParameters, photosPerPage,
		// page);
		// System.out.println(result);
		// } catch (FlickrException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		StringBuilder urlForRequest = new StringBuilder(FLICKR_REQUEST_URL);
		urlForRequest.append("?method=").append(PHOTO_SEARCH_REQUEST)
				.append("&api_key=").append(FLICKR_API_KEY).append("&")
				.append("lat=").append(spot.getLatLngPoint().getLatitude())
				.append("&").append("lon=")
				.append(spot.getLatLngPoint().getLongitude()).append("&extras=views%2Cgeo")
				.append("&format=json&nojsoncallback=1");

		try {
			String jsonResponse = Request.Get(urlForRequest.toString()).execute()
					.returnContent().asString();
			
			JsonObject photosObject = JsonObject.readFrom(jsonResponse);
			JsonObject photosArrayObject = photosObject.get("photos").asObject();
			int totalNumber = Integer.valueOf(photosArrayObject.get("total").asString()).intValue();
			int photosPerPage = photosArrayObject.get("perpage").asInt();
			int numberPages = photosArrayObject.get("pages").asInt();
			List<PointOfInterest> result= new ArrayList<PointOfInterest>(totalNumber);

			JsonArray photoArray = photosArrayObject.get("photo").asArray();
			for (int i = 0; i < photosPerPage; i++) {
				System.out.println(photoArray.get(i).asObject().get("views").asString());	
			}
			
			
			
			
			System.out.println(jsonResponse);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
