package com.flickranalyser.businesslogic.common;

import java.util.Set;

import com.flickranalyser.pojo.Cluster;

public class ClusterMaxValuesInformation {

	private int maximumNumberViews = 0;
	private int maximumNumberPOIs = 0;

	public ClusterMaxValuesInformation(Set<Cluster> cluster) {
		determineMaxNumberViewsAndPOis(cluster);
	}

	private void determineMaxNumberViewsAndPOis(Set<Cluster> clusters) {
		for (Cluster cluster : clusters) {
			if (cluster.getOverallViews() > maximumNumberViews) {
				maximumNumberViews = cluster.getOverallViews();
			}
			if (cluster.getNumberOfPOIs() > maximumNumberPOIs) {
				maximumNumberPOIs = cluster.getNumberOfPOIs();
			}
		}
	}

	public int getMaximumNumberPOIs() {
		return maximumNumberPOIs;
	}

	public int getMaximumNumberViews() {
		return maximumNumberViews;
	}
}
