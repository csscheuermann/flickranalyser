package com.flickranalyser.pojo.results;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
public class KeyResult implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<String> keys;

	public KeyResult(List<String> keys){
		this.keys = keys;
	}
	public KeyResult(){
		keys = new LinkedList<String>();
	}
	

	public List<String> getKeys() {
		return Collections.unmodifiableList(this.keys);
	}
}
