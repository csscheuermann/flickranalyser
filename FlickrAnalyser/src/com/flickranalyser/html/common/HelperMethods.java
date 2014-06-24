package com.flickranalyser.html.common;

public class HelperMethods {

	
	
	public static <T> T instantiate(final String className, final Class<T> type){
	    try{
	        return type.cast(Class.forName(className).newInstance());
	    } catch(final InstantiationException e){
	        throw new IllegalStateException(e);
	    } catch(final IllegalAccessException e){
	        throw new IllegalStateException(e);
	    } catch(final ClassNotFoundException e){
	        throw new IllegalStateException(e);
	    }
	}
	
	
}
