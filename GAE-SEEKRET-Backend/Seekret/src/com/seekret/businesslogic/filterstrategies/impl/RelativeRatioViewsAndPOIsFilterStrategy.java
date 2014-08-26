package com.seekret.businesslogic.filterstrategies.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.seekret.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.seekret.pojo.Cluster;
import com.seekret.pojo.Spot;

public class RelativeRatioViewsAndPOIsFilterStrategy extends AbstractFilterStrategy{

	private static final Logger LOGGER = Logger.getLogger(RelativeRatioViewsAndPOIsFilterStrategy.class.getName());
	
	
	@Override
	protected IClusterScoreDecorator getClusterScoreDecorator(List<Cluster> clustersToFilter, final Spot spot) {
		return new IClusterScoreDecorator() {
			
			@Override
			public double scoreCluster(Cluster clusterToScore) {
				int overallMaxNumberOfPOIs = spot.getOverallMaxPOINumberPerCluster();
				int overallMaxNumberOfViews = spot.getOverallMaxViewNumberPerCluster();
				
				int numberOfPOIsForCurrentCluster = clusterToScore.getNumberOfPOIs();
		   		int clusterOverallViews = clusterToScore.getOverallViews();
				
				double pOICountOverallInPercent = (100.00/overallMaxNumberOfPOIs*numberOfPOIsForCurrentCluster);
				double viewCountOverallInPercent = (100.00/overallMaxNumberOfViews*clusterOverallViews);

				if (ratioFits(viewCountOverallInPercent,pOICountOverallInPercent)){
					return 1;
				}
				return 0;
			}
			
			private boolean ratioFits(double viewCountRealativeInPercent,
					double pOICountRealativeInPercent) {
				double d = viewCountRealativeInPercent/pOICountRealativeInPercent;
				
				
				if(viewCountRealativeInPercent > 99.00){
					return true;
				}
				
			
				if ((viewCountRealativeInPercent < 3.0) || (viewCountRealativeInPercent > 66.66)){
					return false;
				}
				
				if ((pOICountRealativeInPercent) < 3.0 || (pOICountRealativeInPercent > 66.66)){
					return false;
				}
				
				
				double e = 0.7;
				double e2 = 0.5;
				
				if ((d > e2) && (d < e)){
					LOGGER.log(Level.INFO, "RATIO Between "+e+"  and " + e2 +": " + d);
					return true;
				} else if ((d > 1.9) && (d < 2.1)){
					LOGGER.log(Level.INFO, "RATIO Between 1.9 and 2.1: " + d);
					return true;
				}
				return false;

			}
		};
	}
	
}
