package com.flickranalyser.persistence.datastore.common.properties;

public enum PropertiesCluster {
	
	NAME("Name"),
	LATITUDE("Latitude"),
	LONGITUDE("Longitude"),
	DESCRIPTION("Description"),
	URL_OF_MOST_VIEWED_PICTURE("PictureURL"),
	NUMBER_OF_POIS("POICount"),
	AVARAGE_TOURISTICNESS_IN_POINTS_FROM_1_TO_10("Avarage_Touristicness"),
	COUNT_OF_TOURISTICNESS_EVALUATION("TouristicnessEvaluationCount"),
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
