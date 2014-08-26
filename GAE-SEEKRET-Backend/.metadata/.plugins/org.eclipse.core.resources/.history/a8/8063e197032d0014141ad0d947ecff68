package com.flickranalyser.businesslogic.filterstrategies.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.flickranalyser.businesslogic.filterstrategies.IFilterStrategy;
import com.flickranalyser.businesslogic.filterstrategies.common.ClusterScoreComparator;
import com.flickranalyser.businesslogic.filterstrategies.common.ClusterScorePair;
import com.flickranalyser.businesslogic.filterstrategies.filters.impl.HideDismissedPOIsFilter;
import com.flickranalyser.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.Spot;

public abstract class AbstractFilterStrategy implements IFilterStrategy {

	private static final int DEFAULT_MAX_NUMBER_OF_CLUSTERS = 60;

	private boolean ignoreDismissedClusters;

	private boolean limitResultSize;

	public AbstractFilterStrategy() {
		ignoreDismissedClusters = false;
		limitResultSize = true;
	}
	
	public AbstractFilterStrategy( boolean limitResultSize){
		this.limitResultSize = limitResultSize;
	}
	
	

	@Override
	public final List<Cluster> filterCluster(List<Cluster> clusterToFilter, Spot spot) {
		List<Cluster> clusterToScore = preprocessClusterList(clusterToFilter, spot);

		List<ClusterScorePair> scoredClusters = scoreClusters(clusterToScore, spot);

		Collections.sort(scoredClusters, new ClusterScoreComparator());

		List<Cluster> clustersToReturn = new ArrayList<Cluster>();
		if (limitResultSize) {
			clustersToReturn = limitResultSize(scoredClusters);
		}else{
			clustersToReturn = getClustersOnly(scoredClusters);
		}
		
		return clustersToReturn;

	}

	private List<Cluster> getClustersOnly(List<ClusterScorePair> scoredClusters) {
		List<Cluster> result = new ArrayList<Cluster>(scoredClusters.size());
		for (ClusterScorePair clusterScorePair : scoredClusters) {
			result.add(clusterScorePair.getCluster());
		}
		return result;
	}

	private List<Cluster> preprocessClusterList(List<Cluster> clusterToFilter, Spot spot) {
		if (ignoreDismissedClusters) {
			HideDismissedPOIsFilter hideDismissedPOIsFilter = new HideDismissedPOIsFilter();
			return hideDismissedPOIsFilter.filterCluster(clusterToFilter, spot);
		} else {
			return clusterToFilter;
		}
	}

	@Override
	public void setIgnoreDismissedClustersFlag(boolean ignoreDismissedClusters) {
		this.ignoreDismissedClusters = ignoreDismissedClusters;
	}

	private List<Cluster> limitResultSize(List<ClusterScorePair> scoredClusters) {
		List<Cluster> topClusters = new LinkedList<Cluster>();
		for (ClusterScorePair clusterScorePair : scoredClusters) {
			Cluster cluster = clusterScorePair.getCluster();

			if (topClusters.size() < DEFAULT_MAX_NUMBER_OF_CLUSTERS) {
				// LOGGER.log(Level.SEVERE, "RANK" +
				// clusterScorePair.toString());
				// LOGGER.log(Level.SEVERE, "LATITUDE: " +
				// cluster.getCenterOfCluster().getLatitude());
				// LOGGER.log(Level.SEVERE, "LONGITUDE: " +
				// cluster.getCenterOfCluster().getLongitude());
				topClusters.add(cluster);
			} else {
				break;
			}

		}
		return topClusters;
	}

	private List<ClusterScorePair> scoreClusters(List<Cluster> clusterToFilter, Spot spot) {
		List<ClusterScorePair> sortedListOfCluster = new ArrayList<ClusterScorePair>(clusterToFilter.size());
		for (Cluster cluster : clusterToFilter) {

			double clusterScore = getClusterScoreDecorator(clusterToFilter, spot).scoreCluster(cluster);

			ClusterScorePair currentClusterScorePair = new ClusterScorePair(cluster, clusterScore);
			// LOGGER.log(Level.INFO, "ClusterScore: " +
			// currentClusterScorePair.toString());
			sortedListOfCluster.add(currentClusterScorePair);
		}
		return sortedListOfCluster;
	}

	protected abstract IClusterScoreDecorator getClusterScoreDecorator(List<Cluster> clustersToFilter, Spot spot);

}
