package com.flickranalyser.pojo;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class SpotResultList implements Serializable {

	private static final long serialVersionUID = 1L;

	private LinkedList<Spot> topTenSpots;

	public SpotResultList(LinkedList<Spot> topTenSpots){
		this.topTenSpots = topTenSpots;
	}

	public List<Spot> getTopTenSpots() {
		return Collections.unmodifiableList(topTenSpots);
	}
	
	
}
