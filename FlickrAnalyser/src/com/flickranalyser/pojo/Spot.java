package com.flickranalyser.pojo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

public class Spot {

	
	private LatLng latLngPoint;
	private String name;
	private String description;
	private static final int RADIUS_IN_KM = 35;
	private List<Cluster> clusterList;
	
	
	public Spot(LatLng latLngPoint, String name, String description) {

		this.latLngPoint = latLngPoint;
		this.name = name;
		this.description = description;
		this.clusterList = new LinkedList<Cluster>();
	}


	public List<Cluster> getClusterList() {
		return Collections.unmodifiableList(clusterList);
	}

	public void addClusterToList(Cluster cluster) {
		clusterList.add(cluster);
	}


	public void setClusterList(List<Cluster> clusterList) {
		this.clusterList = clusterList;
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
