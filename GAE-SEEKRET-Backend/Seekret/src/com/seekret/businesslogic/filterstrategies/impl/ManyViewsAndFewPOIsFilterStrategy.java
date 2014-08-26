package com.seekret.businesslogic.filterstrategies.impl;

import java.util.List;

import com.seekret.businesslogic.common.ClusterMaxValuesInformation;
import com.seekret.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.seekret.businesslogic.filterstrategies.scoredecorator.impl.EquallyWeightedScoreDecorator;
import com.seekret.businesslogic.filterstrategies.scoredecorator.impl.RelativeNumberPOIsScoreDecorator;
import com.seekret.businesslogic.filterstrategies.scoredecorator.impl.RelativeNumberViewsScoreDecorator;
import com.seekret.pojo.Cluster;
import com.seekret.pojo.Spot;

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
