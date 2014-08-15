package com.flickranalyser.businesslogic.filterstrategies.scoredecorator.impl;

import com.flickranalyser.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.flickranalyser.pojo.Cluster;

public class TouristicnessScoreDecorator implements IClusterScoreDecorator {

	@Override
	public double scoreCluster(Cluster clusterToScore) {
		double seekretness = clusterToScore.getOverallTouristicnessInPointsFrom1To10();
		return seekretness/10.0;
	}

}
