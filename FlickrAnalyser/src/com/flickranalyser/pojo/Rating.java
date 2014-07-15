package com.flickranalyser.pojo;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Rating {

	
	/** Compound ClusterKey + UserKey */
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String datastoreRatingKey;
	
	@Persistent
	private long created;
	
	public Rating(String datastoreRatingKey){
		this.datastoreRatingKey = datastoreRatingKey;
		this.created =  new Date().getTime();
	}
}
