package com.flickranalyser.data.flickr;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.client.fluent.Request;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.flickranalyser.pojo.PointOfInterest;
import com.flickranalyser.pojo.Spot;
import com.javadocmd.simplelatlng.LatLng;

public class FlickrRequestHandler {

	private static final Logger log = Logger.getLogger(FlickrRequestHandler.class.getName());

	private static final String FLICKR_REQUEST_URL = "https://api.flickr.com/services/rest/";
	private static final String FLICKR_API_KEY = "1d39a97f7a90235ed4894bad6ad14a93";
	private static final String PHOTO_SEARCH_REQUEST = "flickr.photos.search";

	private String sharedSecret = "e7992cc453964557";

	public Set<PointOfInterest> getPOIsForSpot(Spot spot) {

		Set<PointOfInterest> result = new HashSet<PointOfInterest>();


		int requestedPage = 1;
		int numberPages = 1;
		int maxViewCount = 0;

		do {
			StringBuilder urlForRequest = new StringBuilder(FLICKR_REQUEST_URL);
			urlForRequest.append("?method=").append(PHOTO_SEARCH_REQUEST)
			.append("&api_key=").append(FLICKR_API_KEY).append("&")
			.append("lat=").append(spot.getLatLngPoint().getLatitude())
			.append("&").append("lon=")
			.append(spot.getLatLngPoint().getLongitude())
			.append("&radius=").append(spot.getSpotRadiusInKm())
			.append("&sort=interestingness-desc")
			.append("&extras=views%2Cgeo%2Curl_s").append("&per_page=1000")
			.append("&page=").append(requestedPage)
			.append("&format=json&nojsoncallback=1");


			JsonObject photosObject;

			try {
				//log.log(Level.INFO,"Retrieving all images for spot "+spot+" (page="+requestedPage+",numberPages="+numberPages+") -> "+ urlForRequest);
				String jsonResponse = Request.Get(urlForRequest.toString())
						.execute().returnContent().asString();
				requestedPage++;
				photosObject = JsonObject.readFrom(jsonResponse);
			} catch (Exception e) {
				//log.log(Level.WARNING, "Could not execute http request", e);
				return result;
			}

			if(photosObject.get("photos") != null){
				
				JsonObject photosArrayObject = photosObject.get("photos").asObject();
				//			int totalNumber = Integer.valueOf(
				//					photosArrayObject.get("total").asString()).intValue();
				//			int photosPerPage = photosArrayObject.get("perpage").asInt();
				numberPages = photosArrayObject.get("pages").asInt();

				JsonArray photoArray = photosArrayObject.get("photo").asArray();
				for (int j = 0; j < photoArray.size(); j++) {
					JsonObject photo = photoArray.get(j).asObject();
					int numberViews = Integer
							.valueOf(photo.get("views").asString()).intValue();
					if(numberViews > maxViewCount){
						maxViewCount = numberViews;
					}
					double latitude = photo.get("latitude").asDouble();
					double longitude = photo.get("longitude").asDouble();
					String url = photo.get("url_s").asString();
					LatLng location = new LatLng(latitude, longitude);
					result.add(new PointOfInterest(numberViews, location,url));
				}
			}else{
				log.log(Level.INFO,"COULD NOT FIND PHOTOS.");
			}

			//We need to make this algorithm more efficient
		} while (requestedPage < 200);

		//log.log(Level.INFO,"maximum number of views: "+ maxViewCount);

		return result;

	}
}
