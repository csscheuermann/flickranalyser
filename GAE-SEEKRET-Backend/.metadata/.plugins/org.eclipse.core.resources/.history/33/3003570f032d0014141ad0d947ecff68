package com.flickranalyser.memcache;

import java.util.logging.Level;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class MemcacheHelperMethods {

	
	static MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();

	public MemcacheHelperMethods(){
		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
	}
	
	public static MemcacheService getSyncCache() {
		return syncCache;
	}
	
}
