package com.flickranalyser.businesslogic.filter.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.businesslogic.filter.IFilterStrategy;
import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.Spot;

public class RelativeRatioViewsAndPOIsFilter implements IFilterStrategy{

	private static final Logger LOGGER = Logger.getLogger(RelativeRatioViewsAndPOIsFilter.class.getName());
	
	@Override
	public List<Cluster> filterCluster(List<Cluster> clusterToFilter,Spot spot) {


		List<Cluster> topClusters = new LinkedList<Cluster>();


		for(Cluster currentCluster : clusterToFilter){
			
			int overallMaxNumberOfPOIs = spot.getOverallMaxPOINumberPerCluster();
			int overallMaxNumberOfViews = spot.getOverallMaxViewNumberPerCluster();
			
			int numberOfPOIsForCurrentCluster = currentCluster.getNumberOfPOIs();
	   		int clusterOverallViews = currentCluster.getOverallViews();
			
			double pOICountOverallInPercent = ((double) (100.00/overallMaxNumberOfPOIs)*numberOfPOIsForCurrentCluster);
			double viewCountOverallInPercent = ((double) (100.00/overallMaxNumberOfViews)*clusterOverallViews);

			if (ratioFits(viewCountOverallInPercent,pOICountOverallInPercent)){
				topClusters.add(currentCluster);
			}

		}
		return topClusters;
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
	
	
	
}
