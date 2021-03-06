package com.seekret.html;

public enum ActionNameEnum {
	DISMISS_CLUSTER("DismissCluster"), EVALULATE_SPOT("EvaluateSpot"), GET_CLUSTER_ADDRESS("GetClusterAddress"), SEARCH_SPOTS("SearchSpots");



	private String actionName;

	ActionNameEnum(String actionName){
		this.actionName = actionName;
	}

	/**
	 * To String Method, needed for Datastore.
	 * @return String representation.
	 */
	public String toString(){
		return this.actionName;
	}

	public static ActionNameEnum viewStringToEnum(String actionName){
		if(actionName.equalsIgnoreCase(ActionNameEnum.DISMISS_CLUSTER.actionName)){
			return DISMISS_CLUSTER;
		} else if(actionName.equalsIgnoreCase(ActionNameEnum.EVALULATE_SPOT.actionName)){
			return EVALULATE_SPOT;
		} else if(actionName.equalsIgnoreCase(ActionNameEnum.GET_CLUSTER_ADDRESS.actionName)){
			return GET_CLUSTER_ADDRESS;
		} else if(actionName.equalsIgnoreCase(ActionNameEnum.SEARCH_SPOTS.actionName)){
			return SEARCH_SPOTS;
		}
		return null;

	}
}
