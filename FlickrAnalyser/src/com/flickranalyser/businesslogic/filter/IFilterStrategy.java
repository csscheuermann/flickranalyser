package com.flickranalyser.businesslogic.filter;

import java.util.List;

import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.Spot;

public interface IFilterStrategy {

	List<Cluster> filterCluster(List<Cluster> clusterToFilter,Spot spot);
}
