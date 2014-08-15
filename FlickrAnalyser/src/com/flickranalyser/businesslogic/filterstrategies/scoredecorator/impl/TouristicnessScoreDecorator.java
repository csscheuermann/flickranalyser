package com.flickranalyser.businesslogic.filterstrategies.scoredecorator.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.flickranalyser.pojo.Cluster;

public class TouristicnessScoreDecorator implements IClusterScoreDecorator {
	private static final Logger LOGGER = Logger.getLogger(TouristicnessScoreDecorator.class.getName());
	@Override
	public double scoreCluster(Cluster clusterToScore) {
		double seekretness = clusterToScore.getOverallTouristicnessInPointsFrom1To10()/10.0;
		LOGGER.log(Level.INFO, "TOURISTICNESS: " + seekretness);		
		double threshold = 0.3;
		if (seekretness < threshold){
			LOGGER.log(Level.INFO, "TOURISTICNESS < " + threshold + " " + seekretness);
			return 0.0;
		}
		return seekretness;
	}

}
