package com.seekret.businesslogic.filterstrategies.impl;

import java.util.List;

import com.seekret.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.seekret.pojo.Cluster;
import com.seekret.pojo.Spot;

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
