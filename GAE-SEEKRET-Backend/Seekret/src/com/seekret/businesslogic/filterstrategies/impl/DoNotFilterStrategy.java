package com.seekret.businesslogic.filterstrategies.impl;

import java.util.List;

import com.seekret.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.seekret.pojo.Cluster;
import com.seekret.pojo.Spot;

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
