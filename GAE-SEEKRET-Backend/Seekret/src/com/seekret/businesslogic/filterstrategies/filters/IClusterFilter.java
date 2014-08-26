package com.seekret.businesslogic.filterstrategies.filters;

import java.util.List;

import com.seekret.pojo.Cluster;
import com.seekret.pojo.Spot;

public interface IClusterFilter {

	List<Cluster> filterCluster(List<Cluster> clusterToFilter, Spot spot);

}
