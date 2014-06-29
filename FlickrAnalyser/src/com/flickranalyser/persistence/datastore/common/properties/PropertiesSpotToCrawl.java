package com.flickranalyser.persistence.datastore.common.properties;


public enum PropertiesSpotToCrawl {

	NAME("Name"),
	LATITUDE("Latitude"),
	LONGITUDE("Longitude"),
	DESCRIPTION("Description"),
	SPOT_RADIUS_IN_KM("SpotRadiusKM"),
	CLUSTER_RADIUS_IN_KM("ClusterRadiusInKM"),
	OVERALL_MAXIMUM_VIEW_COUNT("OverallMaxiumumViewCount"),
	OVERALL_MAXIMUM_POI_COUNT("OverallMaxiumumPOICount");
	
	private final String propertieName;
	
	PropertiesSpotToCrawl(String propertieName){
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
