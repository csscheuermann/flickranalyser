package com.flickranalyser.pojo;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class SpotToCrawl implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** Name of the cluster */
	@Persistent
	private String name;
	
	//TODO COS: Implement automatic description
	/** Description of the cluster */
	@Persistent
	private String description;
	
	/** Default radius of spot in kilomenters */
	@Persistent
	private double spotRadiusInKm = 25;
	
	/** Default cluster radius in kilometers */
	@Persistent
	private double clusterRadiusInKm = 0.1;
	
	/** Datastore Key */
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key dataStoreKey;
	
	@Persistent
	private double latitude;
	
	@Persistent
	private double longitude;
	
	
	
	public SpotToCrawl(double latitude, double longitude, String name, String description, double clusterRadiusInKm, double spotRadiusInKm, Key dataStoreKey, int maxPOINumberPerCluster, int maxViewNumberPerCluster) {
		this.description = description;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.clusterRadiusInKm = clusterRadiusInKm;
		this.spotRadiusInKm = spotRadiusInKm;
		this.dataStoreKey = dataStoreKey;
		
	}

	public  double getClusterRadiusInKm() {
		return clusterRadiusInKm;
	}
	

	public double getSpotRadiusInKm() {
		return spotRadiusInKm;
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
	
	public void setSpotRadiusInKm(int spotRadiusInKm) {
		this.spotRadiusInKm = spotRadiusInKm;
	}

	public  void setClusterRadiusInKm(double clusterRadiusInKm) {
		this.clusterRadiusInKm = clusterRadiusInKm;
	}
	
	public Key getDataStoreKey() {
		return dataStoreKey;
	}

	public void setDataStoreKey(Key dataStoreKey) {
		this.dataStoreKey = dataStoreKey;
	}
	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}	

}
