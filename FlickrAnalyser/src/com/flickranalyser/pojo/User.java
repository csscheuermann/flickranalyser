package com.flickranalyser.pojo;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User implements Serializable{
	
	

	private static final long serialVersionUID = 1L;

	/** Email address derived from oAuth */
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String email;
	
	@Persistent
	private String fullName;
	
	@Persistent
	private String givenName;
	
	@Persistent
	private String profileLink;
	
	@Persistent
	private String picture;

	public User(String email, String fullName, String givenName,
			String profileLink, String picture) {
		this.email = email;
		this.fullName = fullName;
		this.givenName = givenName;
		this.profileLink = profileLink;
		this.picture = picture;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEmail() {
		return email;
	}

	public String getFullName() {
		return fullName;
	}

	public String getGivenName() {
		return givenName;
	}

	public String getProfileLink() {
		return profileLink;
	}

	public String getPicture() {
		return picture;
	}
	
	
	
	
	

}
