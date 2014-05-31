package com.flickranalyser.data.flickr;

import org.eclipse.jetty.io.ConnectedEndPoint;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.Transport;
import com.flickranalyser.pojo.Spot;

public class FlickrRequestHandler {

	
	private static final String FLICKR_REQUEST_URL = "";
	private String apiKey;
	private String sharedSecret;
	
	public void getAllImagesForSpot(Spot spot){
		new Flickr(apiKey, sharedSecret, );
	}
	
}
