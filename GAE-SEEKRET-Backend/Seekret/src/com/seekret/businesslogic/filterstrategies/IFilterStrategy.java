package com.seekret.businesslogic.filterstrategies;

import com.seekret.businesslogic.filterstrategies.filters.IClusterFilter;

public interface IFilterStrategy extends IClusterFilter{

	void setIgnoreDismissedClustersFlag(boolean ignoreDismissedClusters);

	void setIgnorePictureLessClusters(boolean ignorePicturelessClusters);
	
}
