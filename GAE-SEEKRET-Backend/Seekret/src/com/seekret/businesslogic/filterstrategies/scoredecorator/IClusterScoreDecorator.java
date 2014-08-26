package com.seekret.businesslogic.filterstrategies.scoredecorator;

import com.seekret.pojo.Cluster;

public interface IClusterScoreDecorator {

	
	double scoreCluster(Cluster clusterToScore);
}
