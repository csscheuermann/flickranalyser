package com.flickranalyser.businesslogic.filter;

import java.util.List;

import com.flickranalyser.pojo.Cluster;

public interface IFilterStrategy {

	List<Cluster> filterCluster(List<Cluster> clusterToFilter);
}
