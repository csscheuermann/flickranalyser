package com.flickranalyser.businesslogic.filter.decorator.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.flickranalyser.businesslogic.filter.decorator.IClusterScoreDecorator;
import com.flickranalyser.pojo.Cluster;

public class EquallyWeightedScoreDecorator implements IClusterScoreDecorator {

	private final Set<IClusterScoreDecorator> decorators;
	
	public EquallyWeightedScoreDecorator(IClusterScoreDecorator ...clusterScoreDecorators) {
		decorators = new HashSet<IClusterScoreDecorator>(Arrays.asList(clusterScoreDecorators));
	}
	
	@Override
	public double scoreCluster(Cluster clusterToScore) {
		double overallClusterScore = 1;
		for (IClusterScoreDecorator decorator : decorators) {
			double clusterScore = decorator.scoreCluster(clusterToScore);
			overallClusterScore *= clusterScore;
		}
		
		return overallClusterScore;
	}

	
}
