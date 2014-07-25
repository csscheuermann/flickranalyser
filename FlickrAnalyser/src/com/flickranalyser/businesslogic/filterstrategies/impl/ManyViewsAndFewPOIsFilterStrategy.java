package com.flickranalyser.businesslogic.filterstrategies.impl;

import java.util.List;

import com.flickranalyser.businesslogic.common.ClusterMaxValuesInformation;
import com.flickranalyser.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.flickranalyser.businesslogic.filterstrategies.scoredecorator.impl.EquallyWeightedScoreDecorator;
import com.flickranalyser.businesslogic.filterstrategies.scoredecorator.impl.RelativeNumberPOIsScoreDecorator;
import com.flickranalyser.businesslogic.filterstrategies.scoredecorator.impl.RelativeNumberViewsScoreDecorator;
import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.Spot;

public class ManyViewsAndFewPOIsFilterStrategy extends AbstractFilterStrategy {

	
	@Override
	protected IClusterScoreDecorator getClusterScoreDecorator(List<Cluster> clusterToFilter, Spot spot) {
		ClusterMaxValuesInformation clusterSetInformation = new ClusterMaxValuesInformation(clusterToFilter);
		int maximunNumberViews = clusterSetInformation.getMaximumNumberViews();
		IClusterScoreDecorator relativeNumberViewsScoreDecorator = new RelativeNumberViewsScoreDecorator(maximunNumberViews);
		
		int maximunNumberPOIs = clusterSetInformation.getMaximumNumberPOIs();
		IClusterScoreDecorator relativeNumberPOIsScoreDecorator = new RelativeNumberPOIsScoreDecorator(maximunNumberPOIs);
		
		return new EquallyWeightedScoreDecorator(relativeNumberPOIsScoreDecorator, relativeNumberViewsScoreDecorator);
	}

	
}
