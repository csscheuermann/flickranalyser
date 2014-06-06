package com.flickranalyser.businesslogic.filter.common;

import java.util.Comparator;

public class ClusterScoreComparator implements Comparator<ClusterScorePair> {

	@Override
	public int compare(ClusterScorePair clusterOne, ClusterScorePair clusterTwo) {
		return new Double( clusterOne.getClusterScore()*100 - clusterTwo.getClusterScore()*100).intValue();
	}

}
