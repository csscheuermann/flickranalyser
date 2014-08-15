package com.flickranalyser.businesslogic.filterstrategies.common;

import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClusterScoreComparator implements Comparator<ClusterScorePair> {
	private static final Logger LOGGER = Logger.getLogger(ClusterScoreComparator.class.getName());
	@Override
	public int compare(ClusterScorePair clusterOne, ClusterScorePair clusterTwo) {
		double value = clusterTwo.getClusterScore() * 100 - clusterOne.getClusterScore()*100;

		int returnValue;
		if (value < 0){
			returnValue = -1;
		}else if(value == 0 ){
			returnValue = 0;
		}else{
			returnValue = 1;
		}
		LOGGER.log(Level.INFO, "VALUE SORT " + value + " INT VALUE " + returnValue);
		return returnValue;
	}

}
