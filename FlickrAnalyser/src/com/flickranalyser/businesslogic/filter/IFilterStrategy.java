package com.flickranalyser.businesslogic.filter;

import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.PointOfInterest;

import java.util.Set;

public interface IFilterStrategy {

	Set<Cluster> filterCluster(Set<Cluster> clusterToFilter);
}
