package com.flickranalyser.businesslogic.filter.impl;

import java.util.List;

import com.flickranalyser.businesslogic.filter.IFilterStrategy;
import com.flickranalyser.pojo.Cluster;

public class DoNotFilterStrategy implements IFilterStrategy {

	@Override
	public List<Cluster> filterCluster(List<Cluster> clusterToFilter) {
		return clusterToFilter;
	}
}
