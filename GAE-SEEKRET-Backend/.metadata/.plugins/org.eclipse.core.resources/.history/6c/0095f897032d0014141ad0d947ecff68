package com.flickranalyser.businesslogic.filterstrategies.impl;

import java.util.List;

import com.flickranalyser.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.Spot;

public class ManyViewsAndFixedAmountOfPOIsFilterStrategy extends AbstractFilterStrategy{


	@Override
	protected IClusterScoreDecorator getClusterScoreDecorator(List<Cluster> clustersToFilter, Spot spot) {
		return new IClusterScoreDecorator() {
			
			@Override
			public double scoreCluster(Cluster clusterToScore) {
				if((clusterToScore.getOverallViews() > 500) && (clusterToScore.getOverallViews() < 100000) && clusterToScore.getNumberOfPOIs() < 500 && clusterToScore.getNumberOfPOIs() > 10){
					return 1;
				}
				return 0;
			}
		};
	}

}
