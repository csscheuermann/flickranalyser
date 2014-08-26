package com.flickranalyser.pojo;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SpotResultList implements Serializable {
  private static final long serialVersionUID = 1L;
  private LinkedList<String> topTenSpots;

  public SpotResultList(LinkedList<String> topTenSpots){
    this.topTenSpots = topTenSpots;
  }

  public List<String> getTopSpots() {
    return Collections.unmodifiableList(this.topTenSpots);
  }
}