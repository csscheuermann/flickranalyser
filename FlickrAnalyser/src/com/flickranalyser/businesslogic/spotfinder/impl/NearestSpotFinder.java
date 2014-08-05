package com.flickranalyser.businesslogic.spotfinder.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.flickranalyser.businesslogic.spotfinder.ISpotFinder;
import com.flickranalyser.memcache.MemcacheSpot;
import com.flickranalyser.persistence.datastore.save.PFSaverSpotToCrawl;
import com.flickranalyser.pojo.Spot;
import com.flickranalyser.pojo.SpotToCrawl;

public class NearestSpotFinder implements ISpotFinder {

	private static final Logger LOGGER = Logger.getLogger(NearestSpotFinder.class.getName());

	// URL prefix to the geocoder
	private static final String GEOCODER_REQUEST_PREFIX_FOR_XML = "http://maps.google.com/maps/api/geocode/xml";

	@Override
	public Spot findSpotByName(String name) {
		String addressBySearchString = findAddressBySearchString(name);
		if(addressBySearchString != null){
			return  MemcacheSpot.getSpotForSpotName(addressBySearchString);			
		}
		return null;
	}


	@Override
	public Response getSpotByNamePutToCrawlQueue(String name, boolean onlyExcludedPictures) {

		// prepare a URL to the geocoder
		try {
			URL url = new URL(GEOCODER_REQUEST_PREFIX_FOR_XML + "?address=" + URLEncoder.encode(name, "UTF-8") + "&sensor=false");


			// prepare an HTTP connection to the geocoder
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			Document geocoderResultDocument = null;
			try {
				// open the connection and get results as InputSource.
				conn.connect();
				InputSource geocoderResultInputSource = new InputSource(conn.getInputStream());

				// read result and parse into XML Document
				geocoderResultDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(geocoderResultInputSource);
			} finally {
				conn.disconnect();
			}

			// prepare XPath
			XPath xpath = XPathFactory.newInstance().newXPath();


			// a) obtain the formatted_address field for every result
			NodeList resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result/formatted_address", geocoderResultDocument, XPathConstants.NODESET);
			String address = null;
			//Only get the first result
			for(int i=0; i<resultNodeList.getLength();) {
				address = resultNodeList.item(i).getTextContent();
				LOGGER.log(Level.INFO, "FOUND ADDRESS FROM GOOGLE FOR "+ name + " : " + address);
				break;
			}

			if (onlyExcludedPictures){
				address += ", Excluded Pictures Only";
			}
			
			if(MemcacheSpot.getSpotForSpotName(address) == null){

				// c) extract the coordinates of the first result
				resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/geometry/location/*", geocoderResultDocument, XPathConstants.NODESET);
				double lat = Float.NaN;
				double lng = Float.NaN;
				for(int i=0; i<resultNodeList.getLength(); ++i) {
					Node node = resultNodeList.item(i);
					if("lat".equals(node.getNodeName())) lat = Double.parseDouble(node.getTextContent());
					if("lng".equals(node.getNodeName())) lng = Double.parseDouble(node.getTextContent());
				}

				LOGGER.log(Level.INFO, "lat/lng=" + lat + "," + lng);
				return PFSaverSpotToCrawl.saveSpotToDatastore(new SpotToCrawl(new Spot(lat,lng,address,"NOT SET"), onlyExcludedPictures));
			}else{
				return Response.status(400).entity("SPOT ALREADY IN DATASTORE").build();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.log(Level.SEVERE, "error while retrieving location information", e);
		} 

		return Response.status(400).entity("SOMETHING WENT WRONG").build();
	}

	@Override
	public String findAddressBySearchString(String searchAdress){
		// prepare a URL to the geocoder
		try {
			URL url = new URL(GEOCODER_REQUEST_PREFIX_FOR_XML + "?address=" + URLEncoder.encode(searchAdress, "UTF-8") + "&sensor=false");


			// prepare an HTTP connection to the geocoder
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			Document geocoderResultDocument = null;
			try {
				// open the connection and get results as InputSource.
				conn.connect();
				InputSource geocoderResultInputSource = new InputSource(conn.getInputStream());

				// read result and parse into XML Document
				geocoderResultDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(geocoderResultInputSource);
			} finally {
				conn.disconnect();
			}

			// prepare XPath
			XPath xpath = XPathFactory.newInstance().newXPath();


			// a) obtain the formatted_address field for every result
			NodeList resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result/formatted_address", geocoderResultDocument, XPathConstants.NODESET);
			for(int i=0; i < resultNodeList.getLength(); i++) {
				LOGGER.log(Level.INFO, resultNodeList.item(i).getTextContent());
				return resultNodeList.item(i).getTextContent();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.log(Level.SEVERE, "error while retrieving location information", e);
		} 

		return null;
	}





	@Override
	public Spot findSpotByLocation(long latitude, long longitude) {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public String findAddressByLatLng(double lat, double lng) {

		LOGGER.log(Level.INFO, "LATITUDE: " + lat + " LONGITUDE :" + lng);
		// prepare a URL to the geocoder
				try {
					URL url = new URL(GEOCODER_REQUEST_PREFIX_FOR_XML + "?latlng="
							+ lat + "," + lng + "&sensor=true");
					// prepare an HTTP connection to the geocoder
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();

					Document geocoderResultDocument = null;
					try {
						// open the connection and get results as InputSource.
						conn.connect();
						InputSource geocoderResultInputSource = new InputSource(conn.getInputStream());

						// read result and parse into XML Document
						geocoderResultDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(geocoderResultInputSource);
					} finally {
						conn.disconnect();
					}

					// prepare XPath
					XPath xpath = XPathFactory.newInstance().newXPath();


					// a) obtain the formatted_address field for every result
					NodeList resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result/formatted_address", geocoderResultDocument, XPathConstants.NODESET);
					for(int i=0; i < resultNodeList.getLength(); i++) {
					//	LOGGER.log(Level.INFO, resultNodeList.item(i).getTextContent());
						return resultNodeList.item(i).getTextContent();
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					LOGGER.log(Level.SEVERE, "error while retrieving location information", e);
				} 

				return null;
	}

}
