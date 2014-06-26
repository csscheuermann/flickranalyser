package com.flickranalyser.pojo;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.javadocmd.simplelatlng.LatLng;

public class Cluster implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	private Key datastoreClusterKey;
	
	/** Center of the Cluster */
	private LatLng centerOfCluster;
	
	/** Name of the Spot */
	private String name;
	
	/** Description not implemented yet */
	private String description;
	
	/** POIs, they are not stores in Datastore */
	private final List<PointOfInterest> pointsOfInterest;
	
	/** Overall views, its the sum of every POIs views */
	private int overallViews;
	
	/** URL of the most viewed picture */
	private String urlOfMostViewedPicture;
	
	/** Corresponds with the List size of POIs, but we will not store this POIs. They are too much */
	private int numberOfPOIs;
	
	/** The avarage touristicness evaluation factor from 1 to 10 */
	private double overallTouristicnessInPointsFrom1To10;
	
	/** Number of touristicness votes */
	private int overallTouristicnessVotes;
	
	
	public Cluster(LatLng centerOfCluster, String name, String description) {
		this.centerOfCluster = centerOfCluster;
		this.name = name;
		this.description = description;
		this.pointsOfInterest = new LinkedList<PointOfInterest>();
		overallTouristicnessInPointsFrom1To10 =0.0;
		overallTouristicnessVotes = 0;
	}
	
	
	
	
	public double getOverallTouristicnessInPointsFrom1To10() {
		return overallTouristicnessInPointsFrom1To10;
	}




	public void setOverallTouristicnessInPointsFrom1To10(
			double overallTouristicnessInPointsFrom1To10) {
		this.overallTouristicnessInPointsFrom1To10 = overallTouristicnessInPointsFrom1To10;
	}




	public int getOverallTouristicnessVotes() {
		return overallTouristicnessVotes;
	}


	public void setOverallTouristicnessVotes(int overallTouristicnessVotes) {
		this.overallTouristicnessVotes = overallTouristicnessVotes;
	}


	public Cluster(Key datastoreClusterKey, LatLng centerOfCluster, String name, String description,int overallViews, String urlOfMostViewedPicture, int numberOfPOIs, double overallTouristicnessInPointsFrom1To10,int overallTouristicnessVotes) {
		this(centerOfCluster,name,description);
		this.overallViews = overallViews;
		this.urlOfMostViewedPicture = urlOfMostViewedPicture;
		this.numberOfPOIs = numberOfPOIs;
		this.overallTouristicnessInPointsFrom1To10 = overallTouristicnessInPointsFrom1To10;
		this.overallTouristicnessVotes = overallTouristicnessVotes;
		this.datastoreClusterKey = datastoreClusterKey;
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




	public String getDatastoreClusterKey() {
		return KeyFactory.keyToString(datastoreClusterKey);
	}




	public void setDatastoreClusterKey(Key datastoreClusterKey) {
		this.datastoreClusterKey = datastoreClusterKey;
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
