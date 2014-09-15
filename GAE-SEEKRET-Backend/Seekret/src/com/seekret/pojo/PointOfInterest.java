package com.seekret.pojo;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.javadocmd.simplelatlng.LatLng;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PointOfInterest implements Serializable, Comparable<PointOfInterest> {
	private static final long serialVersionUID = 1L;
	private int countOfViews;
	private LatLng location;
	private String pictureUrl;
	private Set<String> tags;
	private int licenseId;
	private int width;
	private int height;

	public PointOfInterest(int countOfViews, LatLng location, String pictureUrl, int width, int height, Set<String> tags, int licenseId) {
		this.pictureUrl = pictureUrl;
		this.countOfViews = countOfViews;
		this.location = location;
		this.width = width;
		this.height = height;
		this.tags = tags;
		this.licenseId = licenseId;
	}

	public String getPictureUrl() {
		return this.pictureUrl;
	}


	public int getCountOfViews() {
		return this.countOfViews;
	}


	public LatLng getLocation() {
		return this.location;
	}


	public int getLicenseId() {
		return licenseId;
	}

	public int getHeight() {
		return height;
	}
	
	
	public int getWidth() {
		return width;
	}
	

	@Override
	public int compareTo(PointOfInterest comparePOI) {
		int compareCountOfViews = comparePOI.getCountOfViews();
		return compareCountOfViews - this.countOfViews;
	}

	public Set<String> getTags() {
		return Collections.unmodifiableSet(this.tags);
	}
}