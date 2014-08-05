/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-07-22 21:53:01 UTC)
 * on 2014-08-05 at 18:30:19 UTC 
 * Modify at your own risk.
 */

package com.appspot.flickeranalyser.spotAPI.model;

/**
 * Model definition for LatLng.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the spotAPI. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class LatLng extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double latitude;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long latitudeInternal;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double longitude;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long longitudeInternal;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean polar;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getLatitude() {
    return latitude;
  }

  /**
   * @param latitude latitude or {@code null} for none
   */
  public LatLng setLatitude(java.lang.Double latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getLatitudeInternal() {
    return latitudeInternal;
  }

  /**
   * @param latitudeInternal latitudeInternal or {@code null} for none
   */
  public LatLng setLatitudeInternal(java.lang.Long latitudeInternal) {
    this.latitudeInternal = latitudeInternal;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getLongitude() {
    return longitude;
  }

  /**
   * @param longitude longitude or {@code null} for none
   */
  public LatLng setLongitude(java.lang.Double longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getLongitudeInternal() {
    return longitudeInternal;
  }

  /**
   * @param longitudeInternal longitudeInternal or {@code null} for none
   */
  public LatLng setLongitudeInternal(java.lang.Long longitudeInternal) {
    this.longitudeInternal = longitudeInternal;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getPolar() {
    return polar;
  }

  /**
   * @param polar polar or {@code null} for none
   */
  public LatLng setPolar(java.lang.Boolean polar) {
    this.polar = polar;
    return this;
  }

  @Override
  public LatLng set(String fieldName, Object value) {
    return (LatLng) super.set(fieldName, value);
  }

  @Override
  public LatLng clone() {
    return (LatLng) super.clone();
  }

}
