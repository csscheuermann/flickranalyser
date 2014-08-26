package com.flickranalyser.businesslogic.filterstrategies.impl;

import java.util.List;

import com.flickranalyser.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.Spot;

public class DoNotFilterStrategy extends AbstractFilterStrategy {

	public DoNotFilterStrategy() {
		super(false);
	}
	
	@Override
	protected IClusterScoreDecorator getClusterScoreDecorator(List<Cluster> clustersToFilter, Spot spot) {
		return new IClusterScoreDecorator() {
			
			@Override
			public double scoreCluster(Cluster clusterToScore) {
				return 1;
			}
		};
	}
	

}
