package com.flickranalyser.pojo.responses;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

public class Address {

	private static final Logger LOGGER = Logger.getLogger(Address.class.getName());
	String address;
	
	public Address(String address){
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
}
