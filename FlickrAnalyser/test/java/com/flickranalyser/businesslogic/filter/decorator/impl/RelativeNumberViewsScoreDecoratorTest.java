package com.flickranalyser.businesslogic.filter.decorator.impl;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.flickranalyser.businesslogic.filter.decorator.IClusterScoreDecorator;
import com.flickranalyser.pojo.Cluster;
import com.javadocmd.simplelatlng.LatLng;

public class RelativeNumberViewsScoreDecoratorTest {

	private Cluster clusterToScore;

	@Before
	public void setUp(){
		LatLng centerOfCluster = new LatLng(2, 5);
		clusterToScore = new Cluster(centerOfCluster, "test cluster", "description");
		clusterToScore.setOverallViews(75);
	}
	
	@Test
	public void scoreCluster_75percentOfViewsAtTheCluster_75percentAsRelativeView(){
		int maximunNumberViews = 100;
		IClusterScoreDecorator decorator = new RelativeNumberViewsScoreDecorator(maximunNumberViews);
		double clusterScore = decorator.scoreCluster(clusterToScore);
		
		Assert.assertEquals(0.75, clusterScore,0.01);
	}
}
