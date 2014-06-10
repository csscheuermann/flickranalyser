package com.flickranalyser.businesslogic.filter.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.flickranalyser.businesslogic.common.ClusterSetInformation;
import com.flickranalyser.businesslogic.filter.IFilterStrategy;
import com.flickranalyser.businesslogic.filter.common.ClusterScoreComparator;
import com.flickranalyser.businesslogic.filter.common.ClusterScorePair;
import com.flickranalyser.businesslogic.filter.decorator.IClusterScoreDecorator;
import com.flickranalyser.businesslogic.filter.decorator.impl.EquallyWeightedScoreDecorator;
import com.flickranalyser.businesslogic.filter.decorator.impl.RelativeNumberPOIsScoreDecorator;
import com.flickranalyser.businesslogic.filter.decorator.impl.RelativeNumberViewsScoreDecorator;
import com.flickranalyser.pojo.Cluster;

public class ManyViewsAndFewPOIsFilter implements IFilterStrategy {

	private final int maxNumberOfClusters;

	public ManyViewsAndFewPOIsFilter(int maxNumberOfClusters) {
		this.maxNumberOfClusters = maxNumberOfClusters;
	}
	
	@Override
	public Set<Cluster> filterCluster(Set<Cluster> clusterToFilter) {
		ClusterSetInformation clusterSetInformation = new ClusterSetInformation(clusterToFilter);
		
		int maximunNumberViews = clusterSetInformation.getMaximumNumberViews();
		IClusterScoreDecorator relativeNumberViewsScoreDecorator = new RelativeNumberViewsScoreDecorator(maximunNumberViews);
		
		int maximunNumberPOIs = clusterSetInformation.getMaximumNumberPOIs();
		IClusterScoreDecorator relativeNumberPOIsScoreDecorator = new RelativeNumberPOIsScoreDecorator(maximunNumberPOIs);
		
		
		IClusterScoreDecorator equallyWeightedScoreDecorator = new EquallyWeightedScoreDecorator(relativeNumberPOIsScoreDecorator, relativeNumberViewsScoreDecorator);
		
		List<ClusterScorePair> sortedListOfCluster = new ArrayList<ClusterScorePair>(clusterToFilter.size());
		for (Cluster cluster : clusterToFilter) {
			double clusterScore = equallyWeightedScoreDecorator.scoreCluster(cluster);
			
			sortedListOfCluster.add(new ClusterScorePair(cluster, clusterScore));
		}
		
		Collections.sort(sortedListOfCluster, new ClusterScoreComparator());
		
		Set<Cluster> topClusters = new HashSet<Cluster>();
		for (ClusterScorePair clusterScorePair : sortedListOfCluster) {
			Cluster cluster = clusterScorePair.getCluster();
			if(topClusters.size() < maxNumberOfClusters){
				topClusters.add(cluster);
			}else{
				break;
			}
			
		}
		return topClusters;
	}

	
}
