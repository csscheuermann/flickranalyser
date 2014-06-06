package com.flickranalyser.businesslogic.filter.decorator;

import com.flickranalyser.pojo.Cluster;

public interface IClusterScoreDecorator {

	
	double scoreCluster(Cluster clusterToScore);
}
