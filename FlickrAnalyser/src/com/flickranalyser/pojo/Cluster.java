package com.flickranalyser.pojo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

public class Cluster {

	
	private LatLng centerOfCluster;
	private String name;
	private String description;
	private final List<PointOfInterest> pointsOfInterest;
	private int overallViews;

	
	
	public Cluster(LatLng centerOfCluster, String name, String description) {
		this.centerOfCluster = centerOfCluster;
		this.name = name;
		this.description = description;
		this.pointsOfInterest = new LinkedList<PointOfInterest>();
	}

	public void addViewCount(int viewCount){
		overallViews = overallViews + viewCount;
	}

	public int getOverallViews() {
		return overallViews;
	}


	public void setOverallViews(int overallViews) {
		this.overallViews = overallViews;
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

	@Override
	public String toString() {
		return "Cluster [centerOfCluster=" + centerOfCluster + ", name=" + name
				+ ", description=" + description + ", overallViews="
				+ overallViews + "]";
	}
	
	
	
	
	
}
