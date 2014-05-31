package com.flickranalyser.pojo;

import com.javadocmd.simplelatlng.LatLng;

public class PointOfInterest {
	
	private int countOfViews;
	private LatLng location;
	
	
	
	
	
	
	public PointOfInterest(int countOfViews, LatLng location) {
		super();
		this.countOfViews = countOfViews;
		this.location = location;
	}
	
	
	public int getCountOfViews() {
		return countOfViews;
	}
	public void setCountOfViews(int countOfViews) {
		this.countOfViews = countOfViews;
	}
	public LatLng getLocation() {
		return location;
	}
	public void setLocation(LatLng location) {
		this.location = location;
	}
	
	

}
