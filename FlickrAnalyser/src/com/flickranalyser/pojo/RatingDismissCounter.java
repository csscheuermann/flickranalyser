package com.flickranalyser.pojo;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class RatingDismissCounter {

	
	/** Compound ClusterKey + UserKey */
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String datastoreRatingKey;
	
	@Persistent
	private long created;
	
	public RatingDismissCounter(String datastoreRatingKey){
		this.datastoreRatingKey = datastoreRatingKey;
		this.created =  new Date().getTime();
	}
}
