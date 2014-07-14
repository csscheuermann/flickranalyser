package com.flickranalyser.pojo;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.annotations.FetchGroup;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@FetchGroup(name="eagerClusterLoading", members = { @Persistent(name="clusters") })
@PersistenceCapable
public class Spot implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** Name of the cluster */
	@Persistent
	private String name;
	
	//TODO COS: Implement automatic description
	/** Description of the cluster */
	@Persistent
	private String description;
	
	@Persistent
	private double latitude;
	
	@Persistent
	private double longitude;
	
	/** Default radius of spot in kilomenters */
	@Persistent
	private double spotRadiusInKm = 25;
	
	/** Default cluster radius in kilometers */
	@Persistent
	private double clusterRadiusInKm = 0.1;
	
	/** List of all clusters */
	@Persistent
	private List<Cluster> clusters;
	
	@Persistent
	private List<String> topThreePictures;
	
	/** Datastore Key */
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key dataStoreKey;
	
	/** Represents the overall maximum number of POIs, it is set via constructor or
	 * by adding a new cluster. It can not be set from outside. Due to filtering
	 * mechanisms the clusters attached to this spot can vary. Thats why, this
	 * value is initially set and not settable.
	 */
	@Persistent
	private int overallMaxPOINumberPerCluster;
	
	/** Represents the overall maximum number of Views, it is set via constructor or
	 * by adding a new cluster. It can not be set from outside. Due to filtering
	 * mechanisms the clusters attached to this spot can vary. Thats why, this
	 * value is initially set and not settable.
	 */
	@Persistent
	private int overallMaxViewNumberPerCluster;
	
	
	public Spot() {
		//DO NOTHING
	}
	
	public int getOverallMaxViewNumberPerCluster() {
		return overallMaxViewNumberPerCluster;
	}


	public int getOverallMaxPOINumberPerCluster() {
		return overallMaxPOINumberPerCluster;
	}

	public Spot(double latitude, double longitude,  String name, String description) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.topThreePictures = new LinkedList<String>();
		this.name = name;
		this.description = description;
		this.clusters = new LinkedList<Cluster>();
	}
	
	public Spot(double latitude, double longitude,  String name, String description, double clusterRadiusInKm, double spotRadiusInKm, Key dataStoreKey, int maxPOINumberPerCluster, int maxViewNumberPerCluster) {
		this( latitude, longitude,  name,  description);
		this.clusterRadiusInKm = clusterRadiusInKm;
		this.spotRadiusInKm = spotRadiusInKm;
		this.dataStoreKey = dataStoreKey;
		this.overallMaxPOINumberPerCluster = maxPOINumberPerCluster;
		this.overallMaxViewNumberPerCluster = maxViewNumberPerCluster;
		
	}

	public Spot(SpotToCrawl spotToCrawl) {
		this( spotToCrawl.getLatitude(), spotToCrawl.getLongitude(),  spotToCrawl.getName(),  spotToCrawl.getDescription());
		this.clusterRadiusInKm = spotToCrawl.getClusterRadiusInKm();
		this.spotRadiusInKm = spotToCrawl.getSpotRadiusInKm();
	}

	public int getMaxClusterViews(){
		int max = Integer.MIN_VALUE;
		for (Cluster Cluster : clusters) {
			if (Cluster.getOverallViews() > max){
				max = Cluster.getOverallViews();
			}
		}
		return max;
	}
	
	private void setOverallMaxValues(){
		overallMaxPOINumberPerCluster = Integer.MIN_VALUE;
		overallMaxViewNumberPerCluster = Integer.MIN_VALUE;
		for (Cluster Cluster : clusters) {
			if (Cluster.getNumberOfPOIs() > overallMaxPOINumberPerCluster){
				overallMaxPOINumberPerCluster = Cluster.getNumberOfPOIs();
			}
			if (Cluster.getOverallViews() > overallMaxViewNumberPerCluster){
				overallMaxViewNumberPerCluster = Cluster.getOverallViews();
			}
		}
	}
	

	public int getMaxNumberOfPOIsPerCluster(){
		int maxPOINumberPerCluster = Integer.MIN_VALUE;
		for (Cluster Cluster : clusters) {
			if (Cluster.getNumberOfPOIs() > maxPOINumberPerCluster){
				maxPOINumberPerCluster = Cluster.getNumberOfPOIs();
			}
		}
		return maxPOINumberPerCluster;
	}
	
	public int getMaxNumberOfViewsPerCluster(){
		int maxViewNumberPerCluster = Integer.MIN_VALUE;
		for (Cluster Cluster : clusters) {
			if (Cluster.getOverallViews() > maxViewNumberPerCluster){
				maxViewNumberPerCluster = Cluster.getOverallViews();
			}
		}
		return maxViewNumberPerCluster;
	}
	
	public  double getClusterRadiusInKm() {
		return clusterRadiusInKm;
	}
	
	public  double getClusterRadiusInMeter() {
		return getClusterRadiusInKm()*1000;
	}
	
	
	
	public List<Cluster> getCluster() {
		return Collections.unmodifiableList(clusters);
	}

	public void addClusterTo(Cluster cluster) {
		clusters.add(cluster);
		setOverallMaxValues();
	}


	public List<String> getTopThreePictures() {
		return Collections.unmodifiableList(topThreePictures);
	}
	public void setTopThreePictures(List<String> topThreePictures) {
		this.topThreePictures = topThreePictures;
	}
	
	public void addTopThreePictures(String url){
		this.topThreePictures.add(url);
	}
	
	public void setCluster(List<Cluster> newCluster) {
		this.clusters = newCluster;
	}


	public double getSpotRadiusInKm() {
		return spotRadiusInKm;
	}
	
	public double getSpotRadiusInMeter() {
		return getSpotRadiusInKm()*1000;
	}
	
	


	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String getName() {
		return name;
	}


	public String getDescription() {
		return description;
	}

	public Key getDataStoreKey() {
		return dataStoreKey;
	}

	public void setDataStoreKey(Key dataStoreKey) {
		this.dataStoreKey = dataStoreKey;
	}
	
}
