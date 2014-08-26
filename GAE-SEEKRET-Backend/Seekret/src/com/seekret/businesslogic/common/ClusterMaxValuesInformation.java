package com.seekret.businesslogic.common;

import java.util.List;

import com.seekret.pojo.Cluster;

public class ClusterMaxValuesInformation {

	private int maximumNumberViews = 0;
	private int maximumNumberPOIs = 0;

	
	public ClusterMaxValuesInformation(List<Cluster> cluster) {
		determineMaxNumberViewsAndPOis(cluster);
	}

	private void determineMaxNumberViewsAndPOis(List<Cluster> clusters) {
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
