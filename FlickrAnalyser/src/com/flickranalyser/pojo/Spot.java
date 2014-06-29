package com.flickranalyser.pojo;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.javadocmd.simplelatlng.LatLng;

public class Spot implements Serializable{

	private static final long serialVersionUID = 1L;
	private LatLng latLngPoint;
	private String name;
	private String description;
	private double spotRadiusInKm = 25;
	private double clusterRadiusInKm = 0.1;
	private List<Cluster> clusters;
	private List<String> topThreePictures;
	private Key dataStoreKey;
	private int overallMaxPOINumberPerCluster;
	private int overallMaxViewNumberPerCluster;
	
	
	public Spot() {
		//DO NOTHING
	}
	
	public int getOverallMaxViewNumberPerCluster() {
		return overallMaxViewNumberPerCluster;
	}


	public int getOverallMaxPOINumberPerCluster() {
		return overallMaxPOINumberPerCluster;
	}

	public Spot(LatLng latLngPoint, String name, String description) {
		this.topThreePictures = new LinkedList<String>();
		this.latLngPoint = latLngPoint;
		this.name = name;
		this.description = description;
		this.clusters = new LinkedList<Cluster>();
	}
	
	public Spot(LatLng latLngPoint, String name, String description, double clusterRadiusInKm, double spotRadiusInKm, Key dataStoreKey, int maxPOINumberPerCluster, int maxViewNumberPerCluster) {
		this( latLngPoint,  name,  description);
		this.clusterRadiusInKm = clusterRadiusInKm;
		this.spotRadiusInKm = spotRadiusInKm;
		this.dataStoreKey = dataStoreKey;
		this.overallMaxPOINumberPerCluster = maxPOINumberPerCluster;
		this.overallMaxViewNumberPerCluster = maxViewNumberPerCluster;
		
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
	
	private void setOverallMaxValues(){
		overallMaxPOINumberPerCluster = Integer.MIN_VALUE;
		overallMaxViewNumberPerCluster = Integer.MIN_VALUE;
		for (Cluster Cluster : clusters) {
			if (Cluster.getNumberOfPOIs() > overallMaxPOINumberPerCluster){
				overallMaxPOINumberPerCluster = Cluster.getNumberOfPOIs();
			}
			if (Cluster.getOverallViews() > overallMaxViewNumberPerCluster){
				overallMaxViewNumberPerCluster = Cluster.getOverallViews();
			}
		}
	}
	

	public int getMaxNumberOfPOIsPerCluster(){
		int maxPOINumberPerCluster = Integer.MIN_VALUE;
		for (Cluster Cluster : clusters) {
			if (Cluster.getNumberOfPOIs() > maxPOINumberPerCluster){
				maxPOINumberPerCluster = Cluster.getNumberOfPOIs();
			}
		}
		return maxPOINumberPerCluster;
	}
	
	public int getMaxNumberOfViewsPerCluster(){
		int maxViewNumberPerCluster = Integer.MIN_VALUE;
		for (Cluster Cluster : clusters) {
			if (Cluster.getOverallViews() > maxViewNumberPerCluster){
				maxViewNumberPerCluster = Cluster.getOverallViews();
			}
		}
		return maxViewNumberPerCluster;
	}
	
	public  double getClusterRadiusInKm() {
		return clusterRadiusInKm;
	}
	
	public List<Cluster> getCluster() {
		return Collections.unmodifiableList(clusters);
	}

	public void addClusterTo(Cluster cluster) {
		clusters.add(cluster);
		setOverallMaxValues();
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
	
	public void setCluster(List<Cluster> newCluster) {
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
