package com.flickranalyser.businesslogic.filterstrategies.scoredecorator.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.flickranalyser.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.flickranalyser.pojo.Cluster;

public class WeightedScoreDecorator implements IClusterScoreDecorator {

	private final Set<WeightedScoreInformation> decorators;

	public WeightedScoreDecorator(WeightedScoreInformation ...clusterScoreDecorators) {
		decorators = new HashSet<WeightedScoreInformation>(Arrays.asList(clusterScoreDecorators));
	}

	@Override
	public double scoreCluster(Cluster clusterToScore) {
		double overallClusterScore = 1;
		for (WeightedScoreInformation weightedDecoratorInformation : decorators) {
			IClusterScoreDecorator clusterScoreDecorator = weightedDecoratorInformation.getClusterScoreDecorator();
			double clusterScore = clusterScoreDecorator.scoreCluster(clusterToScore);
			if (clusterScore != 0 ){
				overallClusterScore += (clusterScore* weightedDecoratorInformation.getWeight());
			}
		}

		return overallClusterScore;
	}


}
