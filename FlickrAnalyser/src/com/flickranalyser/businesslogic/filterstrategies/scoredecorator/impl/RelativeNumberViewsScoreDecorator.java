package com.flickranalyser.businesslogic.filterstrategies.scoredecorator.impl;

import com.flickranalyser.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.flickranalyser.pojo.Cluster;

public class RelativeNumberViewsScoreDecorator implements
IClusterScoreDecorator {

	private final int MAX_NUMBER_VIEWS;


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
		
		return (double)overallViews / MAX_NUMBER_VIEWS;
	}

}
