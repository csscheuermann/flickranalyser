package com.seekret.html.request.impl;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seekret.businesslogic.filterstrategies.IFilterStrategy;
import com.seekret.endpoints.SpotService;
import com.seekret.html.common.HelperMethods;
import com.seekret.pojo.Cluster;
import com.seekret.pojo.Spot;

public class PrepareSpotMapHandler extends AbstractHtmlRequestHandler{
	//TODO COS DVV FACTORY
	private static final String COM_SEEKRET_BUSINESSLOGIC_FILTERSTRATEGIES_IMPL = "com.seekret.businesslogic.filterstrategies.impl.";
	private static final Logger LOGGER = Logger.getLogger(PrepareSpotMapHandler.class.getName());
	private final SpotService spotService;

	public PrepareSpotMapHandler(){
		this.spotService = new SpotService();
	}

	@Override
	public void prepareViewConcrete(HttpServletRequest pRequest,
			HttpServletResponse pResponse, HttpSession session) {


		//Get the needed Parameters
		String location = pRequest.getParameter("location");
		String filterStrategy = pRequest.getParameter("strategy");
		boolean dissmissCluster = Boolean.parseBoolean(pRequest.getParameter("dissmissCluster"));
		boolean doNotConsiderPicturelessCluster = Boolean.parseBoolean(pRequest.getParameter("doNotConsiderPicturelessCluster"));
		boolean useFluster = Boolean.parseBoolean(pRequest.getParameter("useFluster"));

		LOGGER.log(Level.INFO, "DISMISS CLUSTER: " + dissmissCluster);
		LOGGER.log(Level.INFO, "DO NOT CONSIDER PICTURELESS CLUSTER: " + doNotConsiderPicturelessCluster);
		LOGGER.log(Level.INFO, "USE FLUSTER: " + useFluster);
		LOGGER.log(Level.INFO, "LOCATION: " + location);
		LOGGER.log(Level.INFO, "FILTER STRATEGY: " + filterStrategy);

		if (location == null){
			try {
				pResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "LOCATION NULL");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (filterStrategy == null){
			try {
				pResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "FILTERSTRATEGY NULL");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		StringBuilder fullClassPath = new StringBuilder();
		fullClassPath.append(COM_SEEKRET_BUSINESSLOGIC_FILTERSTRATEGIES_IMPL);
		fullClassPath.append(filterStrategy);

		IFilterStrategy choosenFilterStrategy = HelperMethods.instantiate(fullClassPath.toString(), IFilterStrategy.class);
		choosenFilterStrategy.setIgnoreDismissedClustersFlag(dissmissCluster);
		choosenFilterStrategy.setIgnorePictureLessClusters(doNotConsiderPicturelessCluster);
		LOGGER.log(Level.INFO, "INITIALIZED FILTER STRATEGY:" + choosenFilterStrategy.getClass().getName());

		Spot spot = this.spotService.getSpotById(location);
		if (spot != null){
			List<Cluster> cluster = spot.getCluster();
			List<Cluster> filteredCluster = choosenFilterStrategy.filterCluster(cluster, spot);
			
			
			int numberOfFilteredClusters = cluster.size() - filteredCluster.size();
			LOGGER.log(Level.INFO, "NUMBER OF FILTERED CLUSTERS: " + numberOfFilteredClusters);

			pRequest.setAttribute("filteredClusters", filteredCluster);
			pRequest.setAttribute("spot", spot);
		}


	}

}