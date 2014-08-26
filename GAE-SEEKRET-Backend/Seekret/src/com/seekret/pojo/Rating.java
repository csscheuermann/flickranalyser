package com.seekret.pojo;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Rating {

	
	/** Compound ClusterKey + UserKey */
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String datastoreRatingKey;
	
	@Persistent
	private long created;
	
	@Persistent
	private String userEmail;
	
	public Rating(String datastoreRatingKey, String userEmail) {
		this.userEmail = userEmail;
		this.datastoreRatingKey = datastoreRatingKey;
		this.created =  new Date().getTime();
	}
}
