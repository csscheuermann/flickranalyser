package com.seekret.businesslogic.filterstrategies.scoredecorator.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.seekret.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.seekret.pojo.Cluster;

public class TouristicnessScoreDecorator implements IClusterScoreDecorator {
	private static final Logger LOGGER = Logger.getLogger(TouristicnessScoreDecorator.class.getName());
	@Override
	public double scoreCluster(Cluster clusterToScore) {
		double touristicness = clusterToScore.getOverallTouristicnessInPointsFrom1To10()/10.0;
		LOGGER.log(Level.INFO, "TOURISTICNESS: " + touristicness);		
		double threshold = 0.5;
		if (touristicness > threshold){
			LOGGER.log(Level.INFO, "TOURISTICNESS > " + threshold + " " + touristicness);
			return -0.1;
		}else{
			return 1.0;
		}
	}

}
