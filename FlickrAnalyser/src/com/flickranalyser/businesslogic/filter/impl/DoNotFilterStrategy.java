package com.flickranalyser.businesslogic.filter.impl;

import java.util.List;

import com.flickranalyser.businesslogic.filter.IFilterStrategy;
import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.Spot;

public class DoNotFilterStrategy implements IFilterStrategy {

	@Override
	public List<Cluster> filterCluster(List<Cluster> clusterToFilter, Spot spot) {
		return clusterToFilter;
	}
}
