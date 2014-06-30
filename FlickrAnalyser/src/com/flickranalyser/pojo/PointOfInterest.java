package com.flickranalyser.pojo;

import java.io.Serializable;

import com.javadocmd.simplelatlng.LatLng;

public class PointOfInterest implements Serializable, Comparable<PointOfInterest> {

	private static final long serialVersionUID = 1L;
	private int countOfViews;
	private LatLng location;
	private String pictureUrl;

	public PointOfInterest(int countOfViews, LatLng location,String pictureUrl) {
		this.pictureUrl = pictureUrl;
		this.countOfViews = countOfViews;
		this.location = location;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
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

	@Override
	public int compareTo(PointOfInterest comparePOI) {
		
		int compareCountOfViews = comparePOI.getCountOfViews();
		
		
		// TODO Auto-generated method stub
		return compareCountOfViews - this.countOfViews;
	}



}
