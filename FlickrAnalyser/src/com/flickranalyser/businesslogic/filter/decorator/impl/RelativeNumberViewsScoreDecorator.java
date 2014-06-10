package com.flickranalyser.businesslogic.filter.decorator.impl;

import com.flickranalyser.businesslogic.filter.decorator.IClusterScoreDecorator;
import com.flickranalyser.pojo.Cluster;

public class RelativeNumberViewsScoreDecorator implements
		IClusterScoreDecorator {

	private final int maximunNumberViews;

	public RelativeNumberViewsScoreDecorator(int maximunNumberViews) {
		this.maximunNumberViews = maximunNumberViews;
	}

	@Override
	public double scoreCluster(Cluster clusterToScore) {
		
		int overallViews = clusterToScore.getOverallViews();
		if(overallViews<0){
			throw new RuntimeException("overall view of cluster " + clusterToScore +" is negative");
		}else if(overallViews > maximunNumberViews){
			throw new RuntimeException("the cluster " + clusterToScore +" has more views ("+clusterToScore.getOverallViews()+") than the maximum ("+maximunNumberViews+")");
		}
		return (double)overallViews / maximunNumberViews;
	}

}
