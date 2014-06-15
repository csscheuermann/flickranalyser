package com.flickranalyser.pojo;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.appengine.api.datastore.Key;
import com.javadocmd.simplelatlng.LatLng;

public class Spot implements Serializable{

	private static final long serialVersionUID = 1L;
	private LatLng latLngPoint;
	private String name;
	private String description;
	private double spotRadiusInKm = 25;
	private double clusterRadiusInKm = 0.25;
	private Set<Cluster> clusters;
	
	private List<String> topThreePictures;
	private Key dataStoreKey;
	
	
	public Spot() {
		//DO NOTHING
	}
	public Spot(LatLng latLngPoint, String name, String description) {
		this.topThreePictures = new LinkedList<String>();
		this.latLngPoint = latLngPoint;
		this.name = name;
		this.description = description;
		this.clusters = new HashSet<Cluster>();
	}
	
	public Spot(LatLng latLngPoint, String name, String description, double clusterRadiusInKm, double spotRadiusInKm, Key dataStoreKey) {
		this( latLngPoint,  name,  description);
		this.clusterRadiusInKm = clusterRadiusInKm;
		this.spotRadiusInKm = spotRadiusInKm;
		this.dataStoreKey = dataStoreKey;
	}

	public int getMaxClusterViews(){
		int max = Integer.MIN_VALUE;
		for (Cluster Cluster : clusters) {
			if (Cluster.getOverallViews() > max){
				max = Cluster.getOverallViews();
			}
		}
		return max;
	}
	
	public  double getClusterRadiusInKm() {
		return clusterRadiusInKm;
	}
	
	public Set<Cluster> getCluster() {
		return Collections.unmodifiableSet(clusters);
	}

	public void addClusterTo(Cluster cluster) {
		clusters.add(cluster);
	}


	public List<String> getTopThreePictures() {
		return Collections.unmodifiableList(topThreePictures);
	}
	public void setTopThreePictures(List<String> topThreePictures) {
		this.topThreePictures = topThreePictures;
	}
	public void addTopThreePictures(String url){
		this.topThreePictures.add(url);
	}
	
	public void setCluster(Set<Cluster> newCluster) {
		this.clusters = newCluster;
	}


	public double getSpotRadiusInKm() {
		return spotRadiusInKm;
	}


	public LatLng getLatLngPoint() {
		return latLngPoint;
	}


	public void setLatLngPoint(LatLng latLngPoint) {
		this.latLngPoint = latLngPoint;
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
	
}
