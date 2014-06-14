package com.flickranalyser.persistence.datastore.common.properties;



public enum PropertiesCluster {

	
	NAME("Name"),
	LATITUDE("Latitude"),
	LONGITUDE("Longitude"),
	DESCRIPTION("Description"),
	OVERALL_VIEWS("OverallViews");
	
	private final String propertieName;
	
	PropertiesCluster(String propertieName){
		this.propertieName = propertieName;
	}
	
	public String toString(){
		return propertieName;
	}

	/**
	 * @return the propertieName
	 */
	public String getPropertieName() {
		return propertieName;
	}
	
}
