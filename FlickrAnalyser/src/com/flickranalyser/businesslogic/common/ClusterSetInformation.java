package com.flickranalyser.businesslogic.common;

import java.util.Set;

import com.flickranalyser.pojo.Cluster;

public class ClusterSetInformation {

	private int maximumNumberViews = 0;
	private int maximumNumberPOIs = 0;

	public ClusterSetInformation(Set<Cluster> cluster) {
		determineMaxNumberViewsAndPOis(cluster);
	}

	private int determineMaxNumberViewsAndPOis(Set<Cluster> clusters) {
		for (Cluster cluster : clusters) {
			if (cluster.getOverallViews() > maximumNumberViews) {
				maximumNumberViews = cluster.getOverallViews();
			}
			if (cluster.getPointOfInterestList().size() > maximumNumberPOIs) {
				maximumNumberPOIs = cluster.getPointOfInterestList().size();
			}
		}
		return maximumNumberViews;
	}

	public int getMaximumNumberPOIs() {
		return maximumNumberPOIs;
	}

	public int getMaximumNumberViews() {
		return maximumNumberViews;
	}
}
