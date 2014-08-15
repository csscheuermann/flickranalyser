package com.flickranalyser.businesslogic.filterstrategies.scoredecorator.impl;

import com.flickranalyser.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;

public class WeightedScoreInformation{
	
	private IClusterScoreDecorator clusterScoreDecorator;
	
	private double weight;

	public WeightedScoreInformation(
			IClusterScoreDecorator clusterScoreDecorator, double weight) {
		this.clusterScoreDecorator = clusterScoreDecorator;
		this.weight = weight;
	}

	public IClusterScoreDecorator getClusterScoreDecorator() {
		return clusterScoreDecorator;
	}

	public double getWeight() {
		return weight;
	}
	
	
	
	
}