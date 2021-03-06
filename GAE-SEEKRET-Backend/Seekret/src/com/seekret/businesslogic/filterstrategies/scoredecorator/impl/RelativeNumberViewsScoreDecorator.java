package com.seekret.businesslogic.filterstrategies.scoredecorator.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.seekret.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.seekret.pojo.Cluster;

public class RelativeNumberViewsScoreDecorator implements
IClusterScoreDecorator {

	private final int MAX_NUMBER_VIEWS;
	private static final Logger LOGGER = Logger.getLogger(RelativeNumberViewsScoreDecorator.class.getName());

	public RelativeNumberViewsScoreDecorator(int maximunNumberViews) {
		this.MAX_NUMBER_VIEWS = maximunNumberViews;
	}

	@Override
	public double scoreCluster(Cluster clusterToScore) {

		int overallViews = clusterToScore.getOverallViews();
		if(overallViews<0){
			throw new RuntimeException("overall view of cluster " + clusterToScore +" is negative");
		}else if(overallViews > MAX_NUMBER_VIEWS){
			throw new RuntimeException("the cluster " + clusterToScore +" has more views ("+clusterToScore.getOverallViews()+") than the maximum ("+MAX_NUMBER_VIEWS+")");
		}
		
		double result = (double)overallViews / MAX_NUMBER_VIEWS;
		//LOGGER.log(Level.INFO, "RelativeNumberViewsScoreDecorator" + result);
		return result;
	}

}
