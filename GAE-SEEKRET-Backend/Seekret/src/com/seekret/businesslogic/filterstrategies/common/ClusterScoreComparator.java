package com.seekret.businesslogic.filterstrategies.common;

import java.util.Comparator;
import java.util.logging.Logger;

public class ClusterScoreComparator implements Comparator<ClusterScorePair> {
	private static final Logger LOGGER = Logger.getLogger(ClusterScoreComparator.class.getName());
	@Override
	public int compare(ClusterScorePair clusterOne, ClusterScorePair clusterTwo) {

		//TODO COS DVV: Wie muss hier sortiert werden, so funktioniert es jetzt
		int returnValue;
		if ( clusterOne.getClusterScore()*100 < clusterTwo.getClusterScore() * 100){
			returnValue = 1;
		}else if(clusterOne.getClusterScore()*100 == clusterTwo.getClusterScore() * 100 ){
			returnValue = 0;
		}else{
			returnValue = -1;
		}
		//LOGGER.log(Level.INFO, "VALUE SORT " + value + " INT VALUE " + returnValue);
		return returnValue;
	}

}
