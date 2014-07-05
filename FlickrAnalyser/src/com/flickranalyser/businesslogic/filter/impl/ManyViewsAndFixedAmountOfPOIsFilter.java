package com.flickranalyser.businesslogic.filter.impl;

import java.util.LinkedList;
import java.util.List;

import com.flickranalyser.businesslogic.filter.IFilterStrategy;
import com.flickranalyser.pojo.Cluster;

public class ManyViewsAndFixedAmountOfPOIsFilter implements IFilterStrategy{

	@Override
	public List<Cluster> filterCluster(List<Cluster> clusterToFilter) {
		
		
		List<Cluster> topClusters = new LinkedList<Cluster>();
		
		
		for(Cluster currentCluster : clusterToFilter){
			if((currentCluster.getOverallViews() > 2500) && currentCluster.getNumberOfPOIs() < 100 && currentCluster.getNumberOfPOIs() > 10){
				topClusters.add(currentCluster);
			}
		}
		return topClusters;
	}

}
