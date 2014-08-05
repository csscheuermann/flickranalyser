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
 * on 2014-08-05 at 18:30:35 UTC 
 * Modify at your own risk.
 */

package com.appspot.flickeranalyser.ratingAPI;

/**
 * Service definition for RatingAPI (v1).
 *
 * <p>
 * This API serves everything needed for Ratings
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link RatingAPIRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class RatingAPI extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.16.0-rc of the ratingAPI library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://flickeranalyser.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "ratingAPI/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public RatingAPI(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  RatingAPI(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "addNewRating".
   *
   * This request holds the parameters needed by the the ratingAPI server.  After setting any optional
   * parameters, call the {@link AddNewRating#execute()} method to invoke the remote operation.
   *
   * @param userPrimaryKey
   * @param clusterPrimaryKey
   * @return the request
   */
  public AddNewRating addNewRating(java.lang.String userPrimaryKey, java.lang.String clusterPrimaryKey) throws java.io.IOException {
    AddNewRating result = new AddNewRating(userPrimaryKey, clusterPrimaryKey);
    initialize(result);
    return result;
  }

  public class AddNewRating extends RatingAPIRequest<com.appspot.flickeranalyser.ratingAPI.model.Response> {

    private static final String REST_PATH = "addNewRating/{userPrimaryKey}/{clusterPrimaryKey}";

    /**
     * Create a request for the method "addNewRating".
     *
     * This request holds the parameters needed by the the ratingAPI server.  After setting any
     * optional parameters, call the {@link AddNewRating#execute()} method to invoke the remote
     * operation. <p> {@link
     * AddNewRating#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param userPrimaryKey
     * @param clusterPrimaryKey
     * @since 1.13
     */
    protected AddNewRating(java.lang.String userPrimaryKey, java.lang.String clusterPrimaryKey) {
      super(RatingAPI.this, "POST", REST_PATH, null, com.appspot.flickeranalyser.ratingAPI.model.Response.class);
      this.userPrimaryKey = com.google.api.client.util.Preconditions.checkNotNull(userPrimaryKey, "Required parameter userPrimaryKey must be specified.");
      this.clusterPrimaryKey = com.google.api.client.util.Preconditions.checkNotNull(clusterPrimaryKey, "Required parameter clusterPrimaryKey must be specified.");
    }

    @Override
    public AddNewRating setAlt(java.lang.String alt) {
      return (AddNewRating) super.setAlt(alt);
    }

    @Override
    public AddNewRating setFields(java.lang.String fields) {
      return (AddNewRating) super.setFields(fields);
    }

    @Override
    public AddNewRating setKey(java.lang.String key) {
      return (AddNewRating) super.setKey(key);
    }

    @Override
    public AddNewRating setOauthToken(java.lang.String oauthToken) {
      return (AddNewRating) super.setOauthToken(oauthToken);
    }

    @Override
    public AddNewRating setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (AddNewRating) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public AddNewRating setQuotaUser(java.lang.String quotaUser) {
      return (AddNewRating) super.setQuotaUser(quotaUser);
    }

    @Override
    public AddNewRating setUserIp(java.lang.String userIp) {
      return (AddNewRating) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String userPrimaryKey;

    /**

     */
    public java.lang.String getUserPrimaryKey() {
      return userPrimaryKey;
    }

    public AddNewRating setUserPrimaryKey(java.lang.String userPrimaryKey) {
      this.userPrimaryKey = userPrimaryKey;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String clusterPrimaryKey;

    /**

     */
    public java.lang.String getClusterPrimaryKey() {
      return clusterPrimaryKey;
    }

    public AddNewRating setClusterPrimaryKey(java.lang.String clusterPrimaryKey) {
      this.clusterPrimaryKey = clusterPrimaryKey;
      return this;
    }

    @Override
    public AddNewRating set(String parameterName, Object value) {
      return (AddNewRating) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "getAllDismissKeysOfSpecifiedUser".
   *
   * This request holds the parameters needed by the the ratingAPI server.  After setting any optional
   * parameters, call the {@link GetAllDismissKeysOfSpecifiedUser#execute()} method to invoke the
   * remote operation.
   *
   * @param userPrimaryKey
   * @return the request
   */
  public GetAllDismissKeysOfSpecifiedUser getAllDismissKeysOfSpecifiedUser(java.lang.String userPrimaryKey) throws java.io.IOException {
    GetAllDismissKeysOfSpecifiedUser result = new GetAllDismissKeysOfSpecifiedUser(userPrimaryKey);
    initialize(result);
    return result;
  }

  public class GetAllDismissKeysOfSpecifiedUser extends RatingAPIRequest<com.appspot.flickeranalyser.ratingAPI.model.KeyResult> {

    private static final String REST_PATH = "getAllDismissKeysOfSpecifiedUser";

    /**
     * Create a request for the method "getAllDismissKeysOfSpecifiedUser".
     *
     * This request holds the parameters needed by the the ratingAPI server.  After setting any
     * optional parameters, call the {@link GetAllDismissKeysOfSpecifiedUser#execute()} method to
     * invoke the remote operation. <p> {@link GetAllDismissKeysOfSpecifiedUser#initialize(com.google.
     * api.client.googleapis.services.AbstractGoogleClientRequest)} must be called to initialize this
     * instance immediately after invoking the constructor. </p>
     *
     * @param userPrimaryKey
     * @since 1.13
     */
    protected GetAllDismissKeysOfSpecifiedUser(java.lang.String userPrimaryKey) {
      super(RatingAPI.this, "GET", REST_PATH, null, com.appspot.flickeranalyser.ratingAPI.model.KeyResult.class);
      this.userPrimaryKey = com.google.api.client.util.Preconditions.checkNotNull(userPrimaryKey, "Required parameter userPrimaryKey must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetAllDismissKeysOfSpecifiedUser setAlt(java.lang.String alt) {
      return (GetAllDismissKeysOfSpecifiedUser) super.setAlt(alt);
    }

    @Override
    public GetAllDismissKeysOfSpecifiedUser setFields(java.lang.String fields) {
      return (GetAllDismissKeysOfSpecifiedUser) super.setFields(fields);
    }

    @Override
    public GetAllDismissKeysOfSpecifiedUser setKey(java.lang.String key) {
      return (GetAllDismissKeysOfSpecifiedUser) super.setKey(key);
    }

    @Override
    public GetAllDismissKeysOfSpecifiedUser setOauthToken(java.lang.String oauthToken) {
      return (GetAllDismissKeysOfSpecifiedUser) super.setOauthToken(oauthToken);
    }

    @Override
    public GetAllDismissKeysOfSpecifiedUser setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetAllDismissKeysOfSpecifiedUser) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetAllDismissKeysOfSpecifiedUser setQuotaUser(java.lang.String quotaUser) {
      return (GetAllDismissKeysOfSpecifiedUser) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetAllDismissKeysOfSpecifiedUser setUserIp(java.lang.String userIp) {
      return (GetAllDismissKeysOfSpecifiedUser) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String userPrimaryKey;

    /**

     */
    public java.lang.String getUserPrimaryKey() {
      return userPrimaryKey;
    }

    public GetAllDismissKeysOfSpecifiedUser setUserPrimaryKey(java.lang.String userPrimaryKey) {
      this.userPrimaryKey = userPrimaryKey;
      return this;
    }

    @Override
    public GetAllDismissKeysOfSpecifiedUser set(String parameterName, Object value) {
      return (GetAllDismissKeysOfSpecifiedUser) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "getAllRatingKeysOfSpecifiedUser".
   *
   * This request holds the parameters needed by the the ratingAPI server.  After setting any optional
   * parameters, call the {@link GetAllRatingKeysOfSpecifiedUser#execute()} method to invoke the
   * remote operation.
   *
   * @param userPrimaryKey
   * @return the request
   */
  public GetAllRatingKeysOfSpecifiedUser getAllRatingKeysOfSpecifiedUser(java.lang.String userPrimaryKey) throws java.io.IOException {
    GetAllRatingKeysOfSpecifiedUser result = new GetAllRatingKeysOfSpecifiedUser(userPrimaryKey);
    initialize(result);
    return result;
  }

  public class GetAllRatingKeysOfSpecifiedUser extends RatingAPIRequest<com.appspot.flickeranalyser.ratingAPI.model.KeyResult> {

    private static final String REST_PATH = "getAllRatingKeysOfSpecifiedUser";

    /**
     * Create a request for the method "getAllRatingKeysOfSpecifiedUser".
     *
     * This request holds the parameters needed by the the ratingAPI server.  After setting any
     * optional parameters, call the {@link GetAllRatingKeysOfSpecifiedUser#execute()} method to
     * invoke the remote operation. <p> {@link GetAllRatingKeysOfSpecifiedUser#initialize(com.google.a
     * pi.client.googleapis.services.AbstractGoogleClientRequest)} must be called to initialize this
     * instance immediately after invoking the constructor. </p>
     *
     * @param userPrimaryKey
     * @since 1.13
     */
    protected GetAllRatingKeysOfSpecifiedUser(java.lang.String userPrimaryKey) {
      super(RatingAPI.this, "GET", REST_PATH, null, com.appspot.flickeranalyser.ratingAPI.model.KeyResult.class);
      this.userPrimaryKey = com.google.api.client.util.Preconditions.checkNotNull(userPrimaryKey, "Required parameter userPrimaryKey must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetAllRatingKeysOfSpecifiedUser setAlt(java.lang.String alt) {
      return (GetAllRatingKeysOfSpecifiedUser) super.setAlt(alt);
    }

    @Override
    public GetAllRatingKeysOfSpecifiedUser setFields(java.lang.String fields) {
      return (GetAllRatingKeysOfSpecifiedUser) super.setFields(fields);
    }

    @Override
    public GetAllRatingKeysOfSpecifiedUser setKey(java.lang.String key) {
      return (GetAllRatingKeysOfSpecifiedUser) super.setKey(key);
    }

    @Override
    public GetAllRatingKeysOfSpecifiedUser setOauthToken(java.lang.String oauthToken) {
      return (GetAllRatingKeysOfSpecifiedUser) super.setOauthToken(oauthToken);
    }

    @Override
    public GetAllRatingKeysOfSpecifiedUser setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetAllRatingKeysOfSpecifiedUser) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetAllRatingKeysOfSpecifiedUser setQuotaUser(java.lang.String quotaUser) {
      return (GetAllRatingKeysOfSpecifiedUser) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetAllRatingKeysOfSpecifiedUser setUserIp(java.lang.String userIp) {
      return (GetAllRatingKeysOfSpecifiedUser) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String userPrimaryKey;

    /**

     */
    public java.lang.String getUserPrimaryKey() {
      return userPrimaryKey;
    }

    public GetAllRatingKeysOfSpecifiedUser setUserPrimaryKey(java.lang.String userPrimaryKey) {
      this.userPrimaryKey = userPrimaryKey;
      return this;
    }

    @Override
    public GetAllRatingKeysOfSpecifiedUser set(String parameterName, Object value) {
      return (GetAllRatingKeysOfSpecifiedUser) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "hasUserAlreadyVoted".
   *
   * This request holds the parameters needed by the the ratingAPI server.  After setting any optional
   * parameters, call the {@link HasUserAlreadyVoted#execute()} method to invoke the remote operation.
   *
   * @param userPrimaryKey
   * @param clusterPrimaryKey
   * @return the request
   */
  public HasUserAlreadyVoted hasUserAlreadyVoted(java.lang.String userPrimaryKey, java.lang.String clusterPrimaryKey) throws java.io.IOException {
    HasUserAlreadyVoted result = new HasUserAlreadyVoted(userPrimaryKey, clusterPrimaryKey);
    initialize(result);
    return result;
  }

  public class HasUserAlreadyVoted extends RatingAPIRequest<com.appspot.flickeranalyser.ratingAPI.model.Response> {

    private static final String REST_PATH = "hasUserAlreadyVoted/{userPrimaryKey}/{clusterPrimaryKey}";

    /**
     * Create a request for the method "hasUserAlreadyVoted".
     *
     * This request holds the parameters needed by the the ratingAPI server.  After setting any
     * optional parameters, call the {@link HasUserAlreadyVoted#execute()} method to invoke the remote
     * operation. <p> {@link HasUserAlreadyVoted#initialize(com.google.api.client.googleapis.services.
     * AbstractGoogleClientRequest)} must be called to initialize this instance immediately after
     * invoking the constructor. </p>
     *
     * @param userPrimaryKey
     * @param clusterPrimaryKey
     * @since 1.13
     */
    protected HasUserAlreadyVoted(java.lang.String userPrimaryKey, java.lang.String clusterPrimaryKey) {
      super(RatingAPI.this, "POST", REST_PATH, null, com.appspot.flickeranalyser.ratingAPI.model.Response.class);
      this.userPrimaryKey = com.google.api.client.util.Preconditions.checkNotNull(userPrimaryKey, "Required parameter userPrimaryKey must be specified.");
      this.clusterPrimaryKey = com.google.api.client.util.Preconditions.checkNotNull(clusterPrimaryKey, "Required parameter clusterPrimaryKey must be specified.");
    }

    @Override
    public HasUserAlreadyVoted setAlt(java.lang.String alt) {
      return (HasUserAlreadyVoted) super.setAlt(alt);
    }

    @Override
    public HasUserAlreadyVoted setFields(java.lang.String fields) {
      return (HasUserAlreadyVoted) super.setFields(fields);
    }

    @Override
    public HasUserAlreadyVoted setKey(java.lang.String key) {
      return (HasUserAlreadyVoted) super.setKey(key);
    }

    @Override
    public HasUserAlreadyVoted setOauthToken(java.lang.String oauthToken) {
      return (HasUserAlreadyVoted) super.setOauthToken(oauthToken);
    }

    @Override
    public HasUserAlreadyVoted setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (HasUserAlreadyVoted) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public HasUserAlreadyVoted setQuotaUser(java.lang.String quotaUser) {
      return (HasUserAlreadyVoted) super.setQuotaUser(quotaUser);
    }

    @Override
    public HasUserAlreadyVoted setUserIp(java.lang.String userIp) {
      return (HasUserAlreadyVoted) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String userPrimaryKey;

    /**

     */
    public java.lang.String getUserPrimaryKey() {
      return userPrimaryKey;
    }

    public HasUserAlreadyVoted setUserPrimaryKey(java.lang.String userPrimaryKey) {
      this.userPrimaryKey = userPrimaryKey;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String clusterPrimaryKey;

    /**

     */
    public java.lang.String getClusterPrimaryKey() {
      return clusterPrimaryKey;
    }

    public HasUserAlreadyVoted setClusterPrimaryKey(java.lang.String clusterPrimaryKey) {
      this.clusterPrimaryKey = clusterPrimaryKey;
      return this;
    }

    @Override
    public HasUserAlreadyVoted set(String parameterName, Object value) {
      return (HasUserAlreadyVoted) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link RatingAPI}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link RatingAPI}. */
    @Override
    public RatingAPI build() {
      return new RatingAPI(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link RatingAPIRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setRatingAPIRequestInitializer(
        RatingAPIRequestInitializer ratingapiRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(ratingapiRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
