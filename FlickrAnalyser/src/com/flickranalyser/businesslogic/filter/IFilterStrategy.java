package com.flickranalyser.businesslogic.filter;

import java.util.Set;

import com.flickranalyser.pojo.Cluster;

public interface IFilterStrategy {

	Set<Cluster> filterCluster(Set<Cluster> clusterToFilter);
}
