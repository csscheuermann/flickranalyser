package com.flickranalyser.businesslogic.filter.decorator.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.businesslogic.filter.decorator.IClusterScoreDecorator;
import com.flickranalyser.pojo.Cluster;

public class RelativeNumberPOIsScoreDecorator implements IClusterScoreDecorator {

	private final int maximumNumberPOIs;
	private static final Logger log = Logger.getLogger(RelativeNumberViewsScoreDecorator.class.getName());

	public RelativeNumberPOIsScoreDecorator(int maximumNumberPOIs) {
		this.maximumNumberPOIs = maximumNumberPOIs;
	}
	
	@Override
	public double scoreCluster(Cluster clusterToScore) {

		int numberPOIs = clusterToScore.getNumberOfPOIs();
		if(numberPOIs<0){
			throw new RuntimeException("number of pois in cluster " + clusterToScore +" is negative");
		}else if(numberPOIs > maximumNumberPOIs){
			throw new RuntimeException("the cluster " + clusterToScore +" has more pois ("+clusterToScore.getOverallViews()+") than the maximum ("+maximumNumberPOIs+")");
		}
		
		log.log(Level.INFO, "OVERALL VIEWS: " +numberPOIs );
		log.log(Level.INFO, "MAX_NUMBER_VIEWS: " +maximumNumberPOIs );	
		
		double result = 1-(double)numberPOIs / maximumNumberPOIs;
		
		log.log(Level.INFO, "RELATIVE NUMBER OF VIEWS: "+ result);
		
		
		return result;
	}
	
}
