package com.flickranalyser.businesslogic.filter.impl;

import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.PointOfInterest;
import com.google.gwt.dev.util.collect.HashSet;
import com.javadocmd.simplelatlng.LatLng;

public class ManyViewsAndFewPOISFilterTest {

	private ManyViewsAndFewPOIsFilter filterUnderTest;
	private Cluster cluster1;
	private Cluster cluster2;
	private Cluster cluster3;
	private Cluster cluster4;
	private Cluster cluster5;

	@Before
	public void setup() {
		filterUnderTest = new ManyViewsAndFewPOIsFilter();

		cluster1 = new Cluster(new LatLng(1, 1),
				"cluster1", "cluster 1 description");
		cluster1.setOverallViews(10000);
		addPointsOfInterests(cluster1, 500);
		cluster2 = new Cluster(new LatLng(2, 2), "cluster2",
				"cluster 2 description");
		cluster2.setOverallViews(100);
		addPointsOfInterests(cluster2, 3);
		cluster3 = new Cluster(new LatLng(3, 3), "cluster3",
				"cluster 3 description");
		cluster3.setOverallViews(10000);
		addPointsOfInterests(cluster3, 300);
		
		cluster4 = new Cluster(new LatLng(4, 4), "cluster4",
				"cluster 4 description");
		cluster4.setOverallViews(100);
		addPointsOfInterests(cluster4, 500);
		cluster5 = new Cluster(new LatLng(5, 5), "cluster5",
				"cluster 5 description");
		cluster5.setOverallViews(10000);
		addPointsOfInterests(cluster5, 2);
	}

	private void addPointsOfInterests(Cluster cluster,
			int numberPointsOfInterests) {
		for (int i = 0; i < numberPointsOfInterests; i++) {
			PointOfInterest pointOfInterest = new PointOfInterest(0, cluster.getCenterOfCluster(),"test");
			cluster.addPointOfInterestToList(pointOfInterest);
		}

	}

	@Test
	public void test() {
		Set<Cluster> clusterToFilter = new HashSet<Cluster>();
		clusterToFilter.add(cluster1);
		clusterToFilter.add(cluster2);
		clusterToFilter.add(cluster3);
		clusterToFilter.add(cluster4);
		clusterToFilter.add(cluster5);
		Set<Cluster> filteredSetOfClusters = filterUnderTest.filterCluster(clusterToFilter);
		
		
		Assert.assertEquals(ManyViewsAndFewPOIsFilter.getMaxNumberOfClusters(), filteredSetOfClusters.size());
		
		Assert.assertTrue(filteredSetOfClusters.contains(cluster5));
		Assert.assertTrue(filteredSetOfClusters.contains(cluster2));
		Assert.assertTrue(filteredSetOfClusters.contains(cluster3));
		
	}
}
