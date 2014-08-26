package com.seekret.html;

public enum ViewNameEnum {
	LOGIN("Login"), INDEX("01_index"), LOGOUT("Logout"), SPOTMAP("SpotMap"), TOPSPOTS("TopSpots"), SEARCH_SPOT("SearchSpots");

	
	
	private String pageName;

	ViewNameEnum(String pageName){
		this.pageName = pageName;
	}

	/**
	 * To String Method, needed for Datastore.
	 * @return String representation.
	 */
	public String toString(){
		return this.pageName;
	}
	
	public static ViewNameEnum viewStringToEnum(String viewName){
		if(viewName.equalsIgnoreCase("logout")){
			return LOGOUT;
		} else if(viewName.equalsIgnoreCase("login")){
			return LOGIN;
		}
		else if(viewName.equalsIgnoreCase("spotmap")){
			return SPOTMAP;
		} else if(viewName.equalsIgnoreCase("topspots")){
			return TOPSPOTS;
		} else if(viewName.equalsIgnoreCase("searchspots")){
			return SEARCH_SPOT;
		}else
			return INDEX;
	}

}
