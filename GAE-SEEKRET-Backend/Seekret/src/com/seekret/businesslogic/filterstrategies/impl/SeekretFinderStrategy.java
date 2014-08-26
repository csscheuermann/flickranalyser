package com.seekret.businesslogic.filterstrategies.impl;

import java.util.List;

import com.seekret.businesslogic.common.ClusterMaxValuesInformation;
import com.seekret.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.seekret.businesslogic.filterstrategies.scoredecorator.impl.RelativeNumberViewsScoreDecorator;
import com.seekret.businesslogic.filterstrategies.scoredecorator.impl.TouristicnessScoreDecorator;
import com.seekret.businesslogic.filterstrategies.scoredecorator.impl.WeightedScoreDecorator;
import com.seekret.businesslogic.filterstrategies.scoredecorator.impl.WeightedScoreInformation;
import com.seekret.pojo.Cluster;
import com.seekret.pojo.Spot;

public class SeekretFinderStrategy extends AbstractFilterStrategy {

	@Override
	protected IClusterScoreDecorator getClusterScoreDecorator(
			List<Cluster> clustersToFilter, Spot spot) {
		
		ClusterMaxValuesInformation maxValues = new ClusterMaxValuesInformation(clustersToFilter);
		WeightedScoreInformation touristicness = new WeightedScoreInformation(new TouristicnessScoreDecorator(), 5);
		WeightedScoreInformation relativeNumberViews = new WeightedScoreInformation( new RelativeNumberViewsScoreDecorator(maxValues.getMaximumNumberViews()), 1);
		
		return new WeightedScoreDecorator(touristicness, relativeNumberViews);
	}

}
