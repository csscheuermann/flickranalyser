package com.flickranalyser.pojo.responses;


public class Address {

	//private static final Logger LOGGER = Logger.getLogger(Address.class.getName());
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
