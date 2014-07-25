package com.flickranalyser.businesslogic.filterstrategies.filters;

import java.util.List;

import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.Spot;

public interface IClusterFilter {

	List<Cluster> filterCluster(List<Cluster> clusterToFilter, Spot spot);

}
