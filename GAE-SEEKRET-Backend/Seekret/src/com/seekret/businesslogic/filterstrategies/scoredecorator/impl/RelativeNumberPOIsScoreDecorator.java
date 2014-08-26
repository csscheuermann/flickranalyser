package com.seekret.businesslogic.filterstrategies.scoredecorator.impl;

import com.seekret.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.seekret.pojo.Cluster;

public class RelativeNumberPOIsScoreDecorator implements IClusterScoreDecorator {

	private final int MAXIMUM_NUMBER_OF_POIS;

	public RelativeNumberPOIsScoreDecorator(int maximumNumberPOIs) {
		this.MAXIMUM_NUMBER_OF_POIS = maximumNumberPOIs;
	}
	
	@Override
	public double scoreCluster(Cluster clusterToScore) {

		int numberPOIs = clusterToScore.getNumberOfPOIs();
		if(numberPOIs<0){
			throw new RuntimeException("number of pois in cluster " + clusterToScore +" is negative");
		}else if(numberPOIs > MAXIMUM_NUMBER_OF_POIS){
			throw new RuntimeException("the cluster " + clusterToScore +" has more pois ("+clusterToScore.getOverallViews()+") than the maximum ("+MAXIMUM_NUMBER_OF_POIS+")");
		}
		double result = 1-(double)numberPOIs / MAXIMUM_NUMBER_OF_POIS;
		return result;
	}
	
}
