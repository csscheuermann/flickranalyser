package com.flickranalyser.persistence.datastore.common.properties;


public enum PropertiesSpot {

	NAME("Name"),
	LATITUDE("Latitude"),
	LONGITUDE("Longitude"),
	DESCRIPTION("Description"),
	SPOT_RADIUS_IN_KM("SpotRadiusKM"),
	OVERALL_TOURISTICNESS_IN_PERCENT("OverallTouristicnessInPercent"),
	OVERALL_MAXIMUM_POI_COUNT("OverallMaxiumumPOICount"),
	OVERALL_MAXIMUM_VIEW_COUNT("OverallMaxiumumViewCount"),
	CLUSTER_RADIUS_IN_KM("ClusterRadiusInKM");
	
	private final String propertieName;
	
	PropertiesSpot(String propertieName){
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
