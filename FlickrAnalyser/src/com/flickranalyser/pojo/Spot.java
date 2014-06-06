package com.flickranalyser.pojo;

import java.util.Collections;
import java.util.Set;

import com.google.gwt.dev.util.collect.HashSet;
import com.javadocmd.simplelatlng.LatLng;

public class Spot {

	private LatLng latLngPoint;
	private String name;
	private String description;
	private static final int RADIUS_IN_KM = 35;
	private static final double CLUSTER_RADIUS_IN_KM = 0.25;
	private Set<Cluster> clusters;
	
	public Spot(LatLng latLngPoint, String name, String description) {
		this.latLngPoint = latLngPoint;
		this.name = name;
		this.description = description;
		this.clusters = new HashSet<Cluster>();
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
	
	public static double getClusterRadiusInKm() {
		return CLUSTER_RADIUS_IN_KM;
	}
	
	public Set<Cluster> getCluster() {
		return Collections.unmodifiableSet(clusters);
	}

	public void addClusterTo(Cluster cluster) {
		clusters.add(cluster);
	}


	public void setCluster(Set<Cluster> newCluster) {
		this.clusters = newCluster;
	}


	public static int getRadiusInKm() {
		return RADIUS_IN_KM;
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
	
	
	
	

	
}
