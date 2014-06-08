package com.flickranalyser.businesslogic.filter.decorator.impl;

import com.flickranalyser.businesslogic.filter.decorator.IClusterScoreDecorator;
import com.flickranalyser.pojo.Cluster;

public class RelativeNumberPOIsScoreDecorator implements IClusterScoreDecorator {

	private final int maximumNumberPOIs;

	public RelativeNumberPOIsScoreDecorator(int maximumNumberPOIs) {
		this.maximumNumberPOIs = maximumNumberPOIs;
	}
	
	@Override
	public double scoreCluster(Cluster clusterToScore) {

		int numberPOIs = clusterToScore.getPointOfInterestList().size();
		if(numberPOIs<0){
			throw new RuntimeException("number of pois in cluster " + clusterToScore +" is negative");
		}else if(numberPOIs > maximumNumberPOIs){
			throw new RuntimeException("the cluster " + clusterToScore +" has more pois ("+clusterToScore.getOverallViews()+") than the maximum ("+maximumNumberPOIs+")");
		}
		return 1-(double)numberPOIs / maximumNumberPOIs;
	}
	
}
