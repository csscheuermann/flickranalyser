package com.flickranalyser.businesslogic.spotfinder.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.flickranalyser.businesslogic.spotfinder.ISpotFinder;
import com.flickranalyser.memcache.MemcacheSpot;
import com.flickranalyser.pojo.Spot;

public class NearestSpotFinder implements ISpotFinder {

	private static final Logger LOGGER = Logger.getLogger(NearestSpotFinder.class.getName());
	
	  // URL prefix to the geocoder
	  private static final String GEOCODER_REQUEST_PREFIX_FOR_XML = "http://maps.google.com/maps/api/geocode/xml";
	
	@Override
	public Spot findSpotByName(String name) {
		
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
		    for(int i=0; i<resultNodeList.getLength(); ++i) {
		    	LOGGER.log(Level.INFO, resultNodeList.item(i).getTextContent());
		    }
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.log(Level.SEVERE, "error while retrieving location information", e);
		} 
		
		return  MemcacheSpot.getSpotForSpotName(name);
	}

	@Override
	public Spot findSpotByLocation(long latitude, long longitude) {
		// TODO Auto-generated method stub
		return null;
	}

}
