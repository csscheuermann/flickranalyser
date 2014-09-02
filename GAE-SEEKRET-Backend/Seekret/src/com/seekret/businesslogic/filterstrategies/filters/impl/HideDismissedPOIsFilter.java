package com.seekret.businesslogic.filterstrategies.filters.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.seekret.businesslogic.filterstrategies.filters.IClusterFilter;
import com.seekret.pojo.Cluster;
import com.seekret.pojo.Spot;

public class HideDismissedPOIsFilter implements IClusterFilter {

	private static final Logger LOGGER = Logger.getLogger(HideDismissedPOIsFilter.class.getName());
	@Override
	public List<Cluster> filterCluster(List<Cluster> clusterToFilter, Spot spot) {
		List<Cluster> result = new ArrayList<Cluster>(clusterToFilter.size());
		for (Cluster cluster : clusterToFilter) {
			if(cluster.getDismissCounter() == 0){
				result.add(cluster);
			}else{
				LOGGER.log(Level.INFO, "DID NOT CONCIDER THIS CLUSTER");
			}
		}
		LOGGER.log(Level.INFO, "Number of clusters that have been dismissed and are not going to be shown: " + (clusterToFilter.size() - result.size()) );
		return result;
	}

}
