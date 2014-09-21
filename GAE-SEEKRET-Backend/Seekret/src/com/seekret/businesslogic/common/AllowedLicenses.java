package com.seekret.businesslogic.common;

import java.util.HashSet;
import java.util.Set;

public class AllowedLicenses {
	public static Set<Integer> licenses;
	
	static{
		licenses = new HashSet<>();
		/*
		 * Photo licences we are allowed to use in a commercial way.
		 * 
		 * See
		 * https://www.flickr.com/services/api/flickr.photos.licenses.getInfo.html 
		 * for more information
		 */
		licenses.add(4);
		licenses.add(5);
		licenses.add(6);
		licenses.add(7);
	}
}
