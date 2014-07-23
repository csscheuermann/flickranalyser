package com.flickranalyser.pojo.results;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class KeyResult implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, String> keys;

	public KeyResult(List<String> keys){
		for (String string : keys) {
			this.keys.put(string, "");
		}
	}
	public KeyResult(){
		this.keys = new HashMap<String, String>();
	}
	

	public Map<String, String> getKeys() {
		return Collections.unmodifiableMap(this.keys);
	}
}
