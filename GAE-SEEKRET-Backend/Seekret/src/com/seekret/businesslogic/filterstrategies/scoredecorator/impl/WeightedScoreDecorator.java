package com.seekret.businesslogic.filterstrategies.scoredecorator.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.seekret.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.seekret.pojo.Cluster;

public class WeightedScoreDecorator implements IClusterScoreDecorator {

	private final Set<WeightedScoreInformation> decorators;
	private static final Logger LOGGER = Logger.getLogger(WeightedScoreDecorator.class.getName());
	public WeightedScoreDecorator(WeightedScoreInformation ...clusterScoreDecorators) {
		decorators = new HashSet<WeightedScoreInformation>(Arrays.asList(clusterScoreDecorators));
	}

	@Override
	public double scoreCluster(Cluster clusterToScore) {
		double overallClusterScore = 0;
		
		int sumOfWeights = 0;
		for (WeightedScoreInformation weightedDecoratorInformation : decorators) {
			IClusterScoreDecorator clusterScoreDecorator = weightedDecoratorInformation.getClusterScoreDecorator();
			double clusterScore = clusterScoreDecorator.scoreCluster(clusterToScore);
			if (clusterScore != 0 ){
				sumOfWeights += weightedDecoratorInformation.getWeight();
				overallClusterScore += (clusterScore * weightedDecoratorInformation.getWeight());
				//LOGGER.log(Level.INFO, "OVERALLCLUSTERSCORE STEP" + overallClusterScore + " CLUSTERSCORE " + clusterScore+ " WEIGHT " + weightedDecoratorInformation.getWeight());
			}
		}
		double normalizedClusterWeights = overallClusterScore/sumOfWeights;
		//LOGGER.log(Level.INFO, "OVERALLCLUSTERSCORE " + normalizedClusterWeights);
		return normalizedClusterWeights;
	}


}
