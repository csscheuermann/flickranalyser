package com.flickranalyser.businesslogic.filter.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.flickranalyser.businesslogic.common.ClusterMaxValuesInformation;
import com.flickranalyser.businesslogic.filter.IFilterStrategy;
import com.flickranalyser.businesslogic.filter.common.ClusterScoreComparator;
import com.flickranalyser.businesslogic.filter.common.ClusterScorePair;
import com.flickranalyser.businesslogic.filter.decorator.IClusterScoreDecorator;
import com.flickranalyser.businesslogic.filter.decorator.impl.EquallyWeightedScoreDecorator;
import com.flickranalyser.businesslogic.filter.decorator.impl.RelativeNumberPOIsScoreDecorator;
import com.flickranalyser.businesslogic.filter.decorator.impl.RelativeNumberViewsScoreDecorator;
import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.Spot;

public class ManyViewsAndFewPOIsFilter implements IFilterStrategy {

	private static final int MAX_NUMBER_OF_CLUSTERS = 30;

	public ManyViewsAndFewPOIsFilter() {
	}
	
	public static int getMaxNumberOfClusters() {
		return MAX_NUMBER_OF_CLUSTERS;
	}
	
	@Override
	public List<Cluster> filterCluster(List<Cluster> clusterToFilter, Spot spot) {
		ClusterMaxValuesInformation clusterSetInformation = new ClusterMaxValuesInformation(clusterToFilter);
		
		int maximunNumberViews = clusterSetInformation.getMaximumNumberViews();
		IClusterScoreDecorator relativeNumberViewsScoreDecorator = new RelativeNumberViewsScoreDecorator(maximunNumberViews);
		
		int maximunNumberPOIs = clusterSetInformation.getMaximumNumberPOIs();
		IClusterScoreDecorator relativeNumberPOIsScoreDecorator = new RelativeNumberPOIsScoreDecorator(maximunNumberPOIs);
		
		
		
		IClusterScoreDecorator equallyWeightedScoreDecorator = new EquallyWeightedScoreDecorator(relativeNumberPOIsScoreDecorator, relativeNumberViewsScoreDecorator);
		
		List<ClusterScorePair> sortedListOfCluster = new ArrayList<ClusterScorePair>(clusterToFilter.size());
		for (Cluster cluster : clusterToFilter) {
			
			double clusterScore = equallyWeightedScoreDecorator.scoreCluster(cluster);
			
			ClusterScorePair currentClusterScorePair = new ClusterScorePair(cluster, clusterScore);
			//LOGGER.log(Level.INFO, "ClusterScore: " + currentClusterScorePair.toString());
			sortedListOfCluster.add(currentClusterScorePair);
		}
		
		Collections.sort(sortedListOfCluster, new ClusterScoreComparator());
		
		List<Cluster> topClusters = new LinkedList<Cluster>();
		for (ClusterScorePair clusterScorePair : sortedListOfCluster) {
			Cluster cluster = clusterScorePair.getCluster();
			
			if(topClusters.size() < MAX_NUMBER_OF_CLUSTERS){
//				LOGGER.log(Level.SEVERE, "RANK" + clusterScorePair.toString());
//				LOGGER.log(Level.SEVERE, "LATITUDE: " + cluster.getCenterOfCluster().getLatitude());
//				LOGGER.log(Level.SEVERE, "LONGITUDE: " + cluster.getCenterOfCluster().getLongitude());
				topClusters.add(cluster);
			}else{
				break;
			}
			
		}
		
		
		
		return topClusters;
	}

	
}
