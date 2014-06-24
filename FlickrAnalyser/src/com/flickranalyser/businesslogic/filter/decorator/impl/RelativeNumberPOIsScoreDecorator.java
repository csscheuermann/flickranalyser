package com.flickranalyser.businesslogic.filter.decorator.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.businesslogic.filter.decorator.IClusterScoreDecorator;
import com.flickranalyser.pojo.Cluster;

public class RelativeNumberPOIsScoreDecorator implements IClusterScoreDecorator {

	private final int MAXIMUM_NUMBER_OF_POIS;
	private static final Logger LOGGER = Logger.getLogger(RelativeNumberViewsScoreDecorator.class.getName());

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
		
		LOGGER.log(Level.INFO, "OVERALL POIs: " +numberPOIs );
		LOGGER.log(Level.INFO, "MAX_NUMBER_POIs: " +MAXIMUM_NUMBER_OF_POIS );	
		
		double result = 1-(double)numberPOIs / MAXIMUM_NUMBER_OF_POIS;
		
		LOGGER.log(Level.INFO, "RELATIVE NUMBER OF VIEWS: "+ result);
		
		
		return result;
	}
	
}
