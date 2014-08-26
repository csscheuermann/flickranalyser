package com.seekret.data.flickr;

import com.seekret.pojo.PointOfInterest;

public interface IFotoExcluder {

	boolean isFotoToExclude(PointOfInterest poi);
}