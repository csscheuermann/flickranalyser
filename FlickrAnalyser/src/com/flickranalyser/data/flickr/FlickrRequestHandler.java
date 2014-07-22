package com.flickranalyser.data.flickr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.client.fluent.Request;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.flickranalyser.pojo.PointOfInterest;
import com.flickranalyser.pojo.Spot;
import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class FlickrRequestHandler {

	// Maximal 16 pages a 250 pictures results in 4000 pictures (max result from
	// flickr).
	private static final int MAX_NUMBER_PAGES_TO_CRAWL = 17;

	private static final Logger log = Logger
			.getLogger(FlickrRequestHandler.class.getName());

	private static final String FLICKR_REQUEST_URL = "https://api.flickr.com/services/rest/";
	private static final String FLICKR_API_KEY = "1d39a97f7a90235ed4894bad6ad14a93";
	private static final String PHOTO_SEARCH_REQUEST = "flickr.photos.search";

	private final IFotoExcluder fotoExcluder;

	public FlickrRequestHandler(IFotoExcluder fotoExcluder) {
		this.fotoExcluder = fotoExcluder;
	}

	// private String sharedSecret = "e7992cc453964557";

	public Set<PointOfInterest> getPOIsForSpot(Spot spot) {

		Set<PointOfInterest> result = new HashSet<PointOfInterest>();

		HashMap<LatLng, Double> calculateNewPoint = calculateNewPoint(spot);

		List<String> picture_ids = new ArrayList<String>();

		for (Entry<LatLng, Double> entry : calculateNewPoint.entrySet()) {
			int requestedPage = 1;
			int numberPages = 1;
			int maxViewCount = 0;

			LatLng key = entry.getKey();
			double radiusInKm = entry.getValue().doubleValue();

			do {
				StringBuilder urlForRequest = new StringBuilder(
						FLICKR_REQUEST_URL);
				urlForRequest.append("?method=").append(PHOTO_SEARCH_REQUEST)
						.append("&api_key=").append(FLICKR_API_KEY).append("&")
						.append("lat=").append(key.getLatitude()).append("&")
						.append("lon=").append(key.getLongitude())
						.append("&radius=").append(radiusInKm)
						.append("&sort=interestingness-desc")
						.append("&extras=views%2Cgeo%2Curl_s%2Ctags")
						.append("&per_page=250").append("&page=")
						.append(requestedPage)
						.append("&format=json&nojsoncallback=1");

				JsonObject photosObject;

				try {
					log.log(Level.INFO, "Retrieving all images for spot "
							+ spot + " (page=" + requestedPage
							+ ",numberPages=" + numberPages + ") -> "
							+ urlForRequest);
					String jsonResponse = Request.Get(urlForRequest.toString())
							.execute().returnContent().asString();
					requestedPage++;
					photosObject = JsonObject.readFrom(jsonResponse);
				} catch (Exception e) {
					// log.log(Level.WARNING, "Could not execute http request",
					// e);
					return result;
				}

				if (photosObject.get("photos") != null) {

					JsonObject photosArrayObject = photosObject.get("photos")
							.asObject();
					// int totalNumber = Integer.valueOf(
					// photosArrayObject.get("total").asString()).intValue();
					// int photosPerPage =
					// photosArrayObject.get("perpage").asInt();
					numberPages = photosArrayObject.get("pages").asInt();

					JsonArray photoArray = photosArrayObject.get("photo")
							.asArray();
					for (int j = 0; j < photoArray.size(); j++) {
						JsonObject photo = photoArray.get(j).asObject();
						int numberViews = Integer.valueOf(
								photo.get("views").asString()).intValue();
						if (numberViews > maxViewCount) {
							maxViewCount = numberViews;
						}
						double latitude;
						double longitude;
						try {
							String[] tagsArray = photo.get("tags").asString()
									.split(" ");
							Set<String> tags = new HashSet<String>();
							int size = Arrays.asList(tagsArray).size();

							for (int i = 0; i < tagsArray.length; i++) {
								if (i < size) {
									tags.add(Arrays.asList(tagsArray).get(i)
											.toUpperCase());
								}

							}
							latitude = photo.get("latitude").asDouble();
							longitude = photo.get("longitude").asDouble();
							String url = photo.get("url_s").asString();
							String picture_id = photo.get("id").asString();
							LatLng location = new LatLng(latitude, longitude);

							if (!picture_ids.contains(picture_id)) {
								picture_ids.add(picture_id);
								PointOfInterest pointOfInterest = new PointOfInterest(
										numberViews, location, url, tags);
								if (!fotoExcluder
										.isFotoToExclude(pointOfInterest)) {
									result.add(pointOfInterest);
								}
							}
						} catch (Exception e) {
							log.log(Level.SEVERE, "COULD NOT PARSE THIS HERE.");
							e.printStackTrace();
						}
					}
				} else {
					log.log(Level.INFO, "COULD NOT FIND PHOTOS.");
				}

				// We need to make this algorithm more efficient
			} while ((requestedPage < numberPages)
					&& (requestedPage < MAX_NUMBER_PAGES_TO_CRAWL)
					&& result.size() < 10000);
			log.log(Level.INFO, "NUMBER OF PICTURES " + picture_ids.size());
		}
		return result;
	}

	public HashMap<LatLng, Double> calculateNewPoint(Spot spot) {
		HashMap<LatLng, Double> allPoints = new HashMap<LatLng, Double>();
		LatLng latLng = new LatLng(spot.getLatitude(), spot.getLongitude());
		Double radius = Double.valueOf(spot.getSpotRadiusInKm());
		allPoints.put(latLng, radius);
		return allPoints;
	}

	/**
	 * This method generates satelites around a given spot. Attention, using
	 * this method for requests to flickr, we can get problems with write/read
	 * operations and problems with the spot size ( > 1MB) concerning memcache.
	 * 
	 * @param spot
	 *            given Spot.
	 * @param allPoints
	 *            Map of Points with spotradius.
	 */
	@SuppressWarnings("unused")
	private void createSatelitesArroundGivenSpot(Spot spot,
			HashMap<LatLng, Double> allPoints) {
		for (int degree = 0; degree < 316; degree += 45) {
			double d = spot.getSpotRadiusInKm()
					+ (spot.getSpotRadiusInKm() / 2.0);
			System.out.println("Distance = " + d);
			double dist = d / 6371.0;
			double brng = Math.toRadians(degree);
			double lat1 = Math.toRadians(spot.getLatitude());
			double lon1 = Math.toRadians(spot.getLongitude());

			double lat2 = Math.asin(Math.sin(lat1) * Math.cos(dist)
					+ Math.cos(lat1) * Math.sin(dist) * Math.cos(brng));
			double a = Math.atan2(
					Math.sin(brng) * Math.sin(dist) * Math.cos(lat1),
					Math.cos(dist) - Math.sin(lat1) * Math.sin(lat2));
			System.out.println("a = " + a);
			double lon2 = lon1 + a;

			lon2 = (lon2 + 3 * Math.PI) % (2 * Math.PI) - Math.PI;

			double latitude2 = Math.toDegrees(lat2);
			double longitude2 = Math.toDegrees(lon2);
			System.out.println("Latitude = " + latitude2 + "\nLongitude = "
					+ longitude2);

			LatLng origin = new LatLng(spot.getLatitude(), spot.getLongitude());
			LatLng newPoint = new LatLng(latitude2, longitude2);

			log.log(Level.INFO,
					"DISTANCE TO CENTER "
							+ (LatLngTool.distance(origin, newPoint,
									LengthUnit.KILOMETER)));
			allPoints.put(newPoint,
					Double.valueOf(spot.getSpotRadiusInKm() / 2.0));
		}
	}

}
