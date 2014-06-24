package com.flickranalyser.businesslogic.filter.decorator.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.businesslogic.filter.decorator.IClusterScoreDecorator;
import com.flickranalyser.pojo.Cluster;

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

		LOGGER.log(Level.INFO, "OVERALL VIEWS: " +overallViews );
		LOGGER.log(Level.INFO, "MAX_NUMBER_VIEWS: " +MAX_NUMBER_VIEWS );	
		LOGGER.log(Level.INFO, "RELATIVE NUMBER OF VIEWS: "+ (double)overallViews / MAX_NUMBER_VIEWS);
		
		return (double)overallViews / MAX_NUMBER_VIEWS;
	}

}
