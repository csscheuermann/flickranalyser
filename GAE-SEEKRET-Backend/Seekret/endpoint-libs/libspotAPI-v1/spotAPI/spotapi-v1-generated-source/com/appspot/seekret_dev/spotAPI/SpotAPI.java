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
 * on 2014-09-24 at 15:05:38 UTC 
 * Modify at your own risk.
 */

package com.appspot.seekret_dev.spotAPI;

/**
 * Service definition for SpotAPI (v1).
 *
 * <p>
 * API for Spots.
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link SpotAPIRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class SpotAPI extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.18.0-rc of the spotAPI library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://seekret-dev.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "spotAPI/v1/";

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
  public SpotAPI(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  SpotAPI(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "getNearestSpotByAddress".
   *
   * This request holds the parameters needed by the spotAPI server.  After setting any optional
   * parameters, call the {@link GetNearestSpotByAddress#execute()} method to invoke the remote
   * operation.
   *
   * @param spotName
   * @return the request
   */
  public GetNearestSpotByAddress getNearestSpotByAddress(java.lang.String spotName) throws java.io.IOException {
    GetNearestSpotByAddress result = new GetNearestSpotByAddress(spotName);
    initialize(result);
    return result;
  }

  public class GetNearestSpotByAddress extends SpotAPIRequest<com.appspot.seekret_dev.spotAPI.model.Spot> {

    private static final String REST_PATH = "spot/{spotName}";

    /**
     * Create a request for the method "getNearestSpotByAddress".
     *
     * This request holds the parameters needed by the the spotAPI server.  After setting any optional
     * parameters, call the {@link GetNearestSpotByAddress#execute()} method to invoke the remote
     * operation. <p> {@link GetNearestSpotByAddress#initialize(com.google.api.client.googleapis.servi
     * ces.AbstractGoogleClientRequest)} must be called to initialize this instance immediately after
     * invoking the constructor. </p>
     *
     * @param spotName
     * @since 1.13
     */
    protected GetNearestSpotByAddress(java.lang.String spotName) {
      super(SpotAPI.this, "GET", REST_PATH, null, com.appspot.seekret_dev.spotAPI.model.Spot.class);
      this.spotName = com.google.api.client.util.Preconditions.checkNotNull(spotName, "Required parameter spotName must be specified.");
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
    public GetNearestSpotByAddress setAlt(java.lang.String alt) {
      return (GetNearestSpotByAddress) super.setAlt(alt);
    }

    @Override
    public GetNearestSpotByAddress setFields(java.lang.String fields) {
      return (GetNearestSpotByAddress) super.setFields(fields);
    }

    @Override
    public GetNearestSpotByAddress setKey(java.lang.String key) {
      return (GetNearestSpotByAddress) super.setKey(key);
    }

    @Override
    public GetNearestSpotByAddress setOauthToken(java.lang.String oauthToken) {
      return (GetNearestSpotByAddress) super.setOauthToken(oauthToken);
    }

    @Override
    public GetNearestSpotByAddress setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetNearestSpotByAddress) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetNearestSpotByAddress setQuotaUser(java.lang.String quotaUser) {
      return (GetNearestSpotByAddress) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetNearestSpotByAddress setUserIp(java.lang.String userIp) {
      return (GetNearestSpotByAddress) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String spotName;

    /**

     */
    public java.lang.String getSpotName() {
      return spotName;
    }

    public GetNearestSpotByAddress setSpotName(java.lang.String spotName) {
      this.spotName = spotName;
      return this;
    }

    @Override
    public GetNearestSpotByAddress set(String parameterName, Object value) {
      return (GetNearestSpotByAddress) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "getSeekretSpotsBySpotName".
   *
   * This request holds the parameters needed by the spotAPI server.  After setting any optional
   * parameters, call the {@link GetSeekretSpotsBySpotName#execute()} method to invoke the remote
   * operation.
   *
   * @param spotName
   * @return the request
   */
  public GetSeekretSpotsBySpotName getSeekretSpotsBySpotName(java.lang.String spotName) throws java.io.IOException {
    GetSeekretSpotsBySpotName result = new GetSeekretSpotsBySpotName(spotName);
    initialize(result);
    return result;
  }

  public class GetSeekretSpotsBySpotName extends SpotAPIRequest<com.appspot.seekret_dev.spotAPI.model.ClusterCollection> {

    private static final String REST_PATH = "clustercollection/{spotName}";

    /**
     * Create a request for the method "getSeekretSpotsBySpotName".
     *
     * This request holds the parameters needed by the the spotAPI server.  After setting any optional
     * parameters, call the {@link GetSeekretSpotsBySpotName#execute()} method to invoke the remote
     * operation. <p> {@link GetSeekretSpotsBySpotName#initialize(com.google.api.client.googleapis.ser
     * vices.AbstractGoogleClientRequest)} must be called to initialize this instance immediately
     * after invoking the constructor. </p>
     *
     * @param spotName
     * @since 1.13
     */
    protected GetSeekretSpotsBySpotName(java.lang.String spotName) {
      super(SpotAPI.this, "GET", REST_PATH, null, com.appspot.seekret_dev.spotAPI.model.ClusterCollection.class);
      this.spotName = com.google.api.client.util.Preconditions.checkNotNull(spotName, "Required parameter spotName must be specified.");
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
    public GetSeekretSpotsBySpotName setAlt(java.lang.String alt) {
      return (GetSeekretSpotsBySpotName) super.setAlt(alt);
    }

    @Override
    public GetSeekretSpotsBySpotName setFields(java.lang.String fields) {
      return (GetSeekretSpotsBySpotName) super.setFields(fields);
    }

    @Override
    public GetSeekretSpotsBySpotName setKey(java.lang.String key) {
      return (GetSeekretSpotsBySpotName) super.setKey(key);
    }

    @Override
    public GetSeekretSpotsBySpotName setOauthToken(java.lang.String oauthToken) {
      return (GetSeekretSpotsBySpotName) super.setOauthToken(oauthToken);
    }

    @Override
    public GetSeekretSpotsBySpotName setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetSeekretSpotsBySpotName) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetSeekretSpotsBySpotName setQuotaUser(java.lang.String quotaUser) {
      return (GetSeekretSpotsBySpotName) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetSeekretSpotsBySpotName setUserIp(java.lang.String userIp) {
      return (GetSeekretSpotsBySpotName) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String spotName;

    /**

     */
    public java.lang.String getSpotName() {
      return spotName;
    }

    public GetSeekretSpotsBySpotName setSpotName(java.lang.String spotName) {
      this.spotName = spotName;
      return this;
    }

    @Override
    public GetSeekretSpotsBySpotName set(String parameterName, Object value) {
      return (GetSeekretSpotsBySpotName) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "getSpotById".
   *
   * This request holds the parameters needed by the spotAPI server.  After setting any optional
   * parameters, call the {@link GetSpotById#execute()} method to invoke the remote operation.
   *
   * @param spotId
   * @return the request
   */
  public GetSpotById getSpotById(java.lang.String spotId) throws java.io.IOException {
    GetSpotById result = new GetSpotById(spotId);
    initialize(result);
    return result;
  }

  public class GetSpotById extends SpotAPIRequest<com.appspot.seekret_dev.spotAPI.model.Spot> {

    private static final String REST_PATH = "getSpotById";

    /**
     * Create a request for the method "getSpotById".
     *
     * This request holds the parameters needed by the the spotAPI server.  After setting any optional
     * parameters, call the {@link GetSpotById#execute()} method to invoke the remote operation. <p>
     * {@link
     * GetSpotById#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param spotId
     * @since 1.13
     */
    protected GetSpotById(java.lang.String spotId) {
      super(SpotAPI.this, "GET", REST_PATH, null, com.appspot.seekret_dev.spotAPI.model.Spot.class);
      this.spotId = com.google.api.client.util.Preconditions.checkNotNull(spotId, "Required parameter spotId must be specified.");
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
    public GetSpotById setAlt(java.lang.String alt) {
      return (GetSpotById) super.setAlt(alt);
    }

    @Override
    public GetSpotById setFields(java.lang.String fields) {
      return (GetSpotById) super.setFields(fields);
    }

    @Override
    public GetSpotById setKey(java.lang.String key) {
      return (GetSpotById) super.setKey(key);
    }

    @Override
    public GetSpotById setOauthToken(java.lang.String oauthToken) {
      return (GetSpotById) super.setOauthToken(oauthToken);
    }

    @Override
    public GetSpotById setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetSpotById) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetSpotById setQuotaUser(java.lang.String quotaUser) {
      return (GetSpotById) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetSpotById setUserIp(java.lang.String userIp) {
      return (GetSpotById) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String spotId;

    /**

     */
    public java.lang.String getSpotId() {
      return spotId;
    }

    public GetSpotById setSpotId(java.lang.String spotId) {
      this.spotId = spotId;
      return this;
    }

    @Override
    public GetSpotById set(String parameterName, Object value) {
      return (GetSpotById) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "getSpotByNamePutToCrawlQueue".
   *
   * This request holds the parameters needed by the spotAPI server.  After setting any optional
   * parameters, call the {@link GetSpotByNamePutToCrawlQueue#execute()} method to invoke the remote
   * operation.
   *
   * @param spotName
   * @param onlyExcludedPictures
   * @return the request
   */
  public GetSpotByNamePutToCrawlQueue getSpotByNamePutToCrawlQueue(java.lang.String spotName, java.lang.Boolean onlyExcludedPictures) throws java.io.IOException {
    GetSpotByNamePutToCrawlQueue result = new GetSpotByNamePutToCrawlQueue(spotName, onlyExcludedPictures);
    initialize(result);
    return result;
  }

  public class GetSpotByNamePutToCrawlQueue extends SpotAPIRequest<com.appspot.seekret_dev.spotAPI.model.Response> {

    private static final String REST_PATH = "response/{spotName}/{onlyExcludedPictures}";

    /**
     * Create a request for the method "getSpotByNamePutToCrawlQueue".
     *
     * This request holds the parameters needed by the the spotAPI server.  After setting any optional
     * parameters, call the {@link GetSpotByNamePutToCrawlQueue#execute()} method to invoke the remote
     * operation. <p> {@link GetSpotByNamePutToCrawlQueue#initialize(com.google.api.client.googleapis.
     * services.AbstractGoogleClientRequest)} must be called to initialize this instance immediately
     * after invoking the constructor. </p>
     *
     * @param spotName
     * @param onlyExcludedPictures
     * @since 1.13
     */
    protected GetSpotByNamePutToCrawlQueue(java.lang.String spotName, java.lang.Boolean onlyExcludedPictures) {
      super(SpotAPI.this, "GET", REST_PATH, null, com.appspot.seekret_dev.spotAPI.model.Response.class);
      this.spotName = com.google.api.client.util.Preconditions.checkNotNull(spotName, "Required parameter spotName must be specified.");
      this.onlyExcludedPictures = com.google.api.client.util.Preconditions.checkNotNull(onlyExcludedPictures, "Required parameter onlyExcludedPictures must be specified.");
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
    public GetSpotByNamePutToCrawlQueue setAlt(java.lang.String alt) {
      return (GetSpotByNamePutToCrawlQueue) super.setAlt(alt);
    }

    @Override
    public GetSpotByNamePutToCrawlQueue setFields(java.lang.String fields) {
      return (GetSpotByNamePutToCrawlQueue) super.setFields(fields);
    }

    @Override
    public GetSpotByNamePutToCrawlQueue setKey(java.lang.String key) {
      return (GetSpotByNamePutToCrawlQueue) super.setKey(key);
    }

    @Override
    public GetSpotByNamePutToCrawlQueue setOauthToken(java.lang.String oauthToken) {
      return (GetSpotByNamePutToCrawlQueue) super.setOauthToken(oauthToken);
    }

    @Override
    public GetSpotByNamePutToCrawlQueue setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetSpotByNamePutToCrawlQueue) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetSpotByNamePutToCrawlQueue setQuotaUser(java.lang.String quotaUser) {
      return (GetSpotByNamePutToCrawlQueue) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetSpotByNamePutToCrawlQueue setUserIp(java.lang.String userIp) {
      return (GetSpotByNamePutToCrawlQueue) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String spotName;

    /**

     */
    public java.lang.String getSpotName() {
      return spotName;
    }

    public GetSpotByNamePutToCrawlQueue setSpotName(java.lang.String spotName) {
      this.spotName = spotName;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.Boolean onlyExcludedPictures;

    /**

     */
    public java.lang.Boolean getOnlyExcludedPictures() {
      return onlyExcludedPictures;
    }

    public GetSpotByNamePutToCrawlQueue setOnlyExcludedPictures(java.lang.Boolean onlyExcludedPictures) {
      this.onlyExcludedPictures = onlyExcludedPictures;
      return this;
    }

    @Override
    public GetSpotByNamePutToCrawlQueue set(String parameterName, Object value) {
      return (GetSpotByNamePutToCrawlQueue) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "getTopSpots".
   *
   * This request holds the parameters needed by the spotAPI server.  After setting any optional
   * parameters, call the {@link GetTopSpots#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public GetTopSpots getTopSpots() throws java.io.IOException {
    GetTopSpots result = new GetTopSpots();
    initialize(result);
    return result;
  }

  public class GetTopSpots extends SpotAPIRequest<com.appspot.seekret_dev.spotAPI.model.SpotResultList> {

    private static final String REST_PATH = "spotresultlist";

    /**
     * Create a request for the method "getTopSpots".
     *
     * This request holds the parameters needed by the the spotAPI server.  After setting any optional
     * parameters, call the {@link GetTopSpots#execute()} method to invoke the remote operation. <p>
     * {@link
     * GetTopSpots#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected GetTopSpots() {
      super(SpotAPI.this, "GET", REST_PATH, null, com.appspot.seekret_dev.spotAPI.model.SpotResultList.class);
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
    public GetTopSpots setAlt(java.lang.String alt) {
      return (GetTopSpots) super.setAlt(alt);
    }

    @Override
    public GetTopSpots setFields(java.lang.String fields) {
      return (GetTopSpots) super.setFields(fields);
    }

    @Override
    public GetTopSpots setKey(java.lang.String key) {
      return (GetTopSpots) super.setKey(key);
    }

    @Override
    public GetTopSpots setOauthToken(java.lang.String oauthToken) {
      return (GetTopSpots) super.setOauthToken(oauthToken);
    }

    @Override
    public GetTopSpots setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetTopSpots) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetTopSpots setQuotaUser(java.lang.String quotaUser) {
      return (GetTopSpots) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetTopSpots setUserIp(java.lang.String userIp) {
      return (GetTopSpots) super.setUserIp(userIp);
    }

    @Override
    public GetTopSpots set(String parameterName, Object value) {
      return (GetTopSpots) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link SpotAPI}.
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

    /** Builds a new instance of {@link SpotAPI}. */
    @Override
    public SpotAPI build() {
      return new SpotAPI(this);
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
     * Set the {@link SpotAPIRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setSpotAPIRequestInitializer(
        SpotAPIRequestInitializer spotapiRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(spotapiRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
