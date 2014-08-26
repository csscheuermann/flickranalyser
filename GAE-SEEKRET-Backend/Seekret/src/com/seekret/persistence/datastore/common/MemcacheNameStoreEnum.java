package com.seekret.persistence.datastore.common;

/**
 * Represents Naming for our Entities.
 */
public enum MemcacheNameStoreEnum {
	
	SPOT("Spot"),
	CLUSTER ("Cluster"),
	SPOT_TO_CRAWL("SpotToCrawl"),
	POI("POI");
	
	/** EntityName. */
	private final String entityName;

	/**EnumEntityNameStore Constructor.
	 * @param pEntityName entityName.
	 */
	MemcacheNameStoreEnum(String pEntityName){
		this.entityName = pEntityName;
	}

	/**
	 * To String Method, needed for Datastore.
	 * @return String representation.
	 */
	public String toString(){
		return this.entityName;
	}
	
	
}
