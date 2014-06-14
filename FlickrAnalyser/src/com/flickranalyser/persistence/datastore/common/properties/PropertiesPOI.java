package com.flickranalyser.persistence.datastore.common.properties;


public enum PropertiesPOI {

	NAME("Name"),
	LATITUDE("Latitude"),
	LONGITUDE("Longitude"),
	DESCRIPTION("Description"),
	SPOT_RADIUS_IN_KM("SpotRadiusKM"),
	CLUSTER_RADIUS_IN_KM("ClusterRadiusInKM");
	
	private final String propertieName;
	
	PropertiesPOI(String propertieName){
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
