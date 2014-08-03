package com.flickranalyser.businesslogic.filterstrategies;

import com.flickranalyser.businesslogic.filterstrategies.filters.IClusterFilter;

public interface IFilterStrategy extends IClusterFilter{

	void setIgnoreDismissedClustersFlag(boolean ignoreDismissedClusters);
	
}
