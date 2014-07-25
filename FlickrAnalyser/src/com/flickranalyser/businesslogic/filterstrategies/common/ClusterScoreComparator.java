package com.flickranalyser.businesslogic.filterstrategies.common;

import java.util.Comparator;

public class ClusterScoreComparator implements Comparator<ClusterScorePair> {

	@Override
	public int compare(ClusterScorePair clusterOne, ClusterScorePair clusterTwo) {
		return new Double( clusterTwo.getClusterScore()*100 - clusterOne.getClusterScore()*100).intValue();
	}

}
