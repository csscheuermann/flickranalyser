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
 * on 2014-08-05 at 18:29:55 UTC 
 * Modify at your own risk.
 */

package com.appspot.flickeranalyser.userAPI.model;

/**
 * Model definition for SeekretUser.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the userAPI. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class SeekretUser extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String email;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String fullName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String givenName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String picture;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String profileLink;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String userGroup;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getEmail() {
    return email;
  }

  /**
   * @param email email or {@code null} for none
   */
  public SeekretUser setEmail(java.lang.String email) {
    this.email = email;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getFullName() {
    return fullName;
  }

  /**
   * @param fullName fullName or {@code null} for none
   */
  public SeekretUser setFullName(java.lang.String fullName) {
    this.fullName = fullName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getGivenName() {
    return givenName;
  }

  /**
   * @param givenName givenName or {@code null} for none
   */
  public SeekretUser setGivenName(java.lang.String givenName) {
    this.givenName = givenName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getPicture() {
    return picture;
  }

  /**
   * @param picture picture or {@code null} for none
   */
  public SeekretUser setPicture(java.lang.String picture) {
    this.picture = picture;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getProfileLink() {
    return profileLink;
  }

  /**
   * @param profileLink profileLink or {@code null} for none
   */
  public SeekretUser setProfileLink(java.lang.String profileLink) {
    this.profileLink = profileLink;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getUserGroup() {
    return userGroup;
  }

  /**
   * @param userGroup userGroup or {@code null} for none
   */
  public SeekretUser setUserGroup(java.lang.String userGroup) {
    this.userGroup = userGroup;
    return this;
  }

  @Override
  public SeekretUser set(String fieldName, Object value) {
    return (SeekretUser) super.set(fieldName, value);
  }

  @Override
  public SeekretUser clone() {
    return (SeekretUser) super.clone();
  }

}