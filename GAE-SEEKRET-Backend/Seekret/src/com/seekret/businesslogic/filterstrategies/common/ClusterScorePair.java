package com.seekret.businesslogic.filterstrategies.common;

import com.seekret.pojo.Cluster;

public class ClusterScorePair {

	
	private final Cluster cluster;
	private final double clusterScore;

	public ClusterScorePair(Cluster cluster, double clusterScore) {
		this.cluster = cluster;
		this.clusterScore = clusterScore;
	}
	
	public Cluster getCluster() {
		return cluster;
	}
	
	public double getClusterScore() {
		return clusterScore;
	}

	@Override
	public String toString() {
		return "ClusterScorePair [cluster=" + cluster.getName() + ", clusterScore="
				+ clusterScore + "]";
	}
	
	
}
