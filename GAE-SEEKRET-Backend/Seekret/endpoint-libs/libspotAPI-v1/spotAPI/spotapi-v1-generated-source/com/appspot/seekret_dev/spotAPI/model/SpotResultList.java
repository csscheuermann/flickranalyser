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
 * on 2014-09-05 at 15:48:34 UTC 
 * Modify at your own risk.
 */

package com.appspot.seekret_dev.spotAPI.model;

/**
 * Model definition for SpotResultList.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the spotAPI. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class SpotResultList extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> topSpots;

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getTopSpots() {
    return topSpots;
  }

  /**
   * @param topSpots topSpots or {@code null} for none
   */
  public SpotResultList setTopSpots(java.util.List<java.lang.String> topSpots) {
    this.topSpots = topSpots;
    return this;
  }

  @Override
  public SpotResultList set(String fieldName, Object value) {
    return (SpotResultList) super.set(fieldName, value);
  }

  @Override
  public SpotResultList clone() {
    return (SpotResultList) super.clone();
  }

}
