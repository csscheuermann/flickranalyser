package com.flickranalyser.businesslogic.filter.impl;

import java.util.Set;

import com.flickranalyser.businesslogic.filter.IFilterStrategy;
import com.flickranalyser.pojo.Cluster;

public class DoNotFilterStrategy implements IFilterStrategy {

	@Override
	public Set<Cluster> filterCluster(Set<Cluster> clusterToFilter) {
		return clusterToFilter;
	}
}
