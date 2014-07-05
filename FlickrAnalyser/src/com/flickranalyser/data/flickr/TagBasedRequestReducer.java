package com.flickranalyser.data.flickr;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.flickranalyser.pojo.PointOfInterest;

public class TagBasedRequestReducer {

	private Set<String> unwantedTags;

	public TagBasedRequestReducer() {
		unwantedTags = new HashSet<String>();
		unwantedTags.add("WOMEN");
		unwantedTags.add("WOMAN");
		unwantedTags.add("MAN");
		unwantedTags.add("MEN");
		unwantedTags.add("SEXY");
		unwantedTags.add("GAY");
		unwantedTags.add("KISS");
		unwantedTags.add("LESBIAN");
		unwantedTags.add("FLOWER");
		unwantedTags.add("FACE");
		unwantedTags.add("ROOM");
		unwantedTags.add("FURNITURE");
		unwantedTags.add("CAR");
		unwantedTags.add("MUJER");
		unwantedTags.add("CATS");
		unwantedTags.add("CAT");
		
	}

	public Set<PointOfInterest> reduceResult(Set<PointOfInterest> pointsToReduce) {

		HashSet<PointOfInterest> result = new HashSet<PointOfInterest>();
		for (PointOfInterest poi : pointsToReduce) {
			if (isFreeOfUnwantedTags(poi)) {
				result.add(poi);
			}
		}

		return result;
	}

	private boolean isFreeOfUnwantedTags(PointOfInterest poi) {
		return Collections.disjoint(unwantedTags, poi.getTags());
	}
}
