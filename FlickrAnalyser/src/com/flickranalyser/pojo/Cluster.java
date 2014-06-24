package com.flickranalyser.pojo;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

public class Cluster implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private LatLng centerOfCluster;
	private String name;
	private String description;
	private final List<PointOfInterest> pointsOfInterest;
	private int overallViews;
	private String urlOfMostViewedPicture;
	//Corresponds with the List size of POIs but we will not store this POIs
	private int numberOfPOIs;

	
	
	public Cluster(LatLng centerOfCluster, String name, String description) {
		this.centerOfCluster = centerOfCluster;
		this.name = name;
		this.description = description;
		this.pointsOfInterest = new LinkedList<PointOfInterest>();
	}
	
	
	
	
	public Cluster(LatLng centerOfCluster, String name, String description,int overallViews, String urlOfMostViewedPicture, int numberOfPOIs) {
		this(centerOfCluster,name,description);
		this.overallViews = overallViews;
		this.urlOfMostViewedPicture = urlOfMostViewedPicture;
		this.numberOfPOIs = numberOfPOIs;
		
	}

	
	public int getNumberOfPOIs() {
		return numberOfPOIs;
	}




	public void setNumberOfPOIs(int numberOfPOIs) {
		this.numberOfPOIs = numberOfPOIs;
	}




	public String getUrlOfMostViewedPicture() {
		return urlOfMostViewedPicture;
	}




	public void setUrlOfMostViewedPicture(String urlOfMostViewedPicture) {
		this.urlOfMostViewedPicture = urlOfMostViewedPicture;
	}




	public void addViewCount(int viewCount){
		overallViews = overallViews + viewCount;
	}

	private void incrementCountPOI(){
		numberOfPOIs++;
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
		incrementCountPOI();
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
