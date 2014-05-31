package com.flickranalyser.pojo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

public class Cluster {

	
	private LatLng centerOfCluster;
	private String name;
	private String description;
	private List<PointOfInterest> pointsOfInterest;
	
	
	public Cluster(LatLng centerOfCluster, String name, String description) {
		this.centerOfCluster = centerOfCluster;
		this.name = name;
		this.description = description;
		this.pointsOfInterest = new LinkedList<PointOfInterest>();
	}


	
	public List<PointOfInterest> getPointOfInterestList() {
		return Collections.unmodifiableList(pointsOfInterest);
	}

	public void addPointOfInterestToList(PointOfInterest pointOfInterest) {
		pointsOfInterest.add(pointOfInterest);
	}
	public LatLng getCenterOfCluster() {
		return centerOfCluster;
	}


	public void setCenterOfCluster(LatLng centerOfCluster) {
		this.centerOfCluster = centerOfCluster;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
}
