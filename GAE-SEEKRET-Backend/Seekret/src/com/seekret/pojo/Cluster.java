package com.seekret.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


@PersistenceCapable
public class Cluster implements Serializable {


	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key datastoreClusterKey;

	@Persistent
	private double latitude;

	/** Counts how many people have dismissed this Cluster already */
	@Persistent
	private int dismissCounter;

	@Persistent
	private double longitude;

	/** Name of the Spot */
	@Persistent
	private String name;

	/** Description not implemented yet */
	@Persistent
	private String description;

	/** POIs, they are not stored in Datastore */
	@NotPersistent
	private List<PointOfInterest> pointsOfInterest;

	/** Overall views, its the sum of every POIs views */
	@Persistent
	private int overallViews;

	/** URL of the most viewed picture */
	@Persistent
	private List<String> urlOfMostViewedPicture;

	/** Corresponds with the list size of POIs, but we will not store this POIs. They are too much */
	@Persistent
	private int numberOfPOIs;

	/** The avarage touristicness evaluation factor from 1 to 10 */
	@Persistent
	private double overallTouristicnessInPointsFrom1To10;

	/** Number of touristicness votes */
	@Persistent
	private int overallTouristicnessVotes;


	public Cluster(double latitude, double longitude, String name, String description) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.description = description;
		this.pointsOfInterest = new LinkedList<PointOfInterest>();
		overallTouristicnessInPointsFrom1To10 =0.0;
		overallTouristicnessVotes = 0;
		urlOfMostViewedPicture = new ArrayList<String>();
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


	public int getNumberOfPOIs() {
		return numberOfPOIs;
	}




	public void setNumberOfPOIs(int numberOfPOIs) {
		this.numberOfPOIs = numberOfPOIs;
	}




	public List<String> getUrlOfMostViewedPicture() {
		return urlOfMostViewedPicture;
	}


	public String getDatastoreClusterKey() {
		return KeyFactory.keyToString(datastoreClusterKey);
	}


	public void setDatastoreClusterKey(Key datastoreClusterKey) {
		this.datastoreClusterKey = datastoreClusterKey;
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
		if ((this.pointsOfInterest == null) || (this.pointsOfInterest.isEmpty())) {
			return Collections.unmodifiableList(new LinkedList<PointOfInterest>());
		}
		Collections.sort(pointsOfInterest);
		return Collections.unmodifiableList(pointsOfInterest);
	}

	public void addPointOfInterestToList(PointOfInterest pointOfInterest) {
		pointsOfInterest.add(pointOfInterest);
		incrementCountPOI();
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
		return "Cluster [centerOfCluster=" + latitude + ","+longitude+ ", name=" + name
				+ ", description=" + description + ", overallViews="
				+ overallViews + "]";
	}




	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public int getDismissCounter() {
		return dismissCounter;
	}

	public void incrementDismissCounter(){
		this.dismissCounter++;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (o instanceof Cluster) {
			return this.getDatastoreClusterKey().equals(((Cluster) o).getDatastoreClusterKey());
		} else {
			return false;
		}
	}


	public void addPictureUrl(String pictureUrl) {
		urlOfMostViewedPicture.add(pictureUrl);
		
	}

}
