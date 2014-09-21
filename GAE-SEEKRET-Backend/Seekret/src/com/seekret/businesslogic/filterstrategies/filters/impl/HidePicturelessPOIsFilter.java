package com.seekret.businesslogic.filterstrategies.filters.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.seekret.businesslogic.filterstrategies.filters.IClusterFilter;
import com.seekret.pojo.Cluster;
import com.seekret.pojo.Spot;

public class HidePicturelessPOIsFilter implements IClusterFilter {

	private static final Logger LOGGER = Logger.getLogger(HideDismissedPOIsFilter.class.getName());
	
	@Override
	public List<Cluster> filterCluster(List<Cluster> clusterToFilter, Spot spot) {
		List<Cluster> result = new ArrayList<Cluster>(clusterToFilter.size());
		for (Cluster cluster : clusterToFilter) {
			if(!cluster.getUrlOfMostViewedPicture().isEmpty()){
				result.add(cluster);
			}
		}
		int numberFilteredClusters = clusterToFilter.size() - result.size();
		LOGGER.log(Level.INFO, "Number of clusters that have no pictures and are not going to be shown: " + numberFilteredClusters+ "("+(100.0*numberFilteredClusters)/clusterToFilter.size()+"%)" );
		return result;
	}

}
