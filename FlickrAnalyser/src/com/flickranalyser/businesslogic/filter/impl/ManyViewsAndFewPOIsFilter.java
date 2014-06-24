package com.flickranalyser.businesslogic.filter.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.businesslogic.common.ClusterMaxValuesInformation;
import com.flickranalyser.businesslogic.filter.IFilterStrategy;
import com.flickranalyser.businesslogic.filter.common.ClusterScoreComparator;
import com.flickranalyser.businesslogic.filter.common.ClusterScorePair;
import com.flickranalyser.businesslogic.filter.decorator.IClusterScoreDecorator;
import com.flickranalyser.businesslogic.filter.decorator.impl.EquallyWeightedScoreDecorator;
import com.flickranalyser.businesslogic.filter.decorator.impl.RelativeNumberPOIsScoreDecorator;
import com.flickranalyser.businesslogic.filter.decorator.impl.RelativeNumberViewsScoreDecorator;
import com.flickranalyser.pojo.Cluster;

public class ManyViewsAndFewPOIsFilter implements IFilterStrategy {

	private static final int MAX_NUMBER_OF_CLUSTERS = 30;
	private static final Logger LOGGER = Logger.getLogger(ManyViewsAndFewPOIsFilter.class.getName());

	public ManyViewsAndFewPOIsFilter() {
	}
	
	public static int getMaxNumberOfClusters() {
		return MAX_NUMBER_OF_CLUSTERS;
	}
	
	@Override
	public Set<Cluster> filterCluster(Set<Cluster> clusterToFilter) {
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
			LOGGER.log(Level.INFO, "ClusterScore: " + currentClusterScorePair.toString());
			sortedListOfCluster.add(currentClusterScorePair);
		}
		
		Collections.sort(sortedListOfCluster, new ClusterScoreComparator());
		
		Set<Cluster> topClusters = new HashSet<Cluster>();
		for (ClusterScorePair clusterScorePair : sortedListOfCluster) {
			Cluster cluster = clusterScorePair.getCluster();
			if(topClusters.size() < MAX_NUMBER_OF_CLUSTERS){
				topClusters.add(cluster);
			}else{
				break;
			}
			
		}
		
		
		
		return topClusters;
	}

	
}
