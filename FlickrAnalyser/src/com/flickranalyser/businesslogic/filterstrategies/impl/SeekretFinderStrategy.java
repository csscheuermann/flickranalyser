package com.flickranalyser.businesslogic.filterstrategies.impl;

import java.util.List;

import com.flickranalyser.businesslogic.common.ClusterMaxValuesInformation;
import com.flickranalyser.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.flickranalyser.businesslogic.filterstrategies.scoredecorator.impl.RelativeNumberViewsScoreDecorator;
import com.flickranalyser.businesslogic.filterstrategies.scoredecorator.impl.TouristicnessScoreDecorator;
import com.flickranalyser.businesslogic.filterstrategies.scoredecorator.impl.WeightedScoreDecorator;
import com.flickranalyser.businesslogic.filterstrategies.scoredecorator.impl.WeightedScoreInformation;
import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.Spot;

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
