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
 * (build: 2014-06-09 16:41:44 UTC)
 * on 2014-06-30 at 10:16:16 UTC 
 * Modify at your own risk.
 */

package com.appspot.flickeranalyser.clusterAPI;

/**
 * Service definition for ClusterAPI (v1).
 *
 * <p>
 * This API serves everything needed to update a cluster.
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link ClusterAPIRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class ClusterAPI extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.16.0-rc of the clusterAPI library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
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
  public static final String DEFAULT_SERVICE_PATH = "clusterAPI/v1/evaluateCluster/";

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
  public ClusterAPI(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  ClusterAPI(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "evaluateCluster".
   *
   * This request holds the parameters needed by the the clusterAPI server.  After setting any
   * optional parameters, call the {@link EvaluateCluster#execute()} method to invoke the remote
   * operation.
   *
   * @param datastoreKeyOfCluster
   * @param touristicnessRatingFrom1To10
   * @param spotName
   * @return the request
   */
  public EvaluateCluster evaluateCluster(java.lang.String datastoreKeyOfCluster, java.lang.Integer touristicnessRatingFrom1To10, java.lang.String spotName) throws java.io.IOException {
    EvaluateCluster result = new EvaluateCluster(datastoreKeyOfCluster, touristicnessRatingFrom1To10, spotName);
    initialize(result);
    return result;
  }

  public class EvaluateCluster extends ClusterAPIRequest<com.appspot.flickeranalyser.clusterAPI.model.Response> {

    private static final String REST_PATH = "{datastoreKeyOfCluster}/{touristicnessRatingFrom1To10}/{spotName}";

    /**
     * Create a request for the method "evaluateCluster".
     *
     * This request holds the parameters needed by the the clusterAPI server.  After setting any
     * optional parameters, call the {@link EvaluateCluster#execute()} method to invoke the remote
     * operation. <p> {@link EvaluateCluster#initialize(com.google.api.client.googleapis.services.Abst
     * ractGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param datastoreKeyOfCluster
     * @param touristicnessRatingFrom1To10
     * @param spotName
     * @since 1.13
     */
    protected EvaluateCluster(java.lang.String datastoreKeyOfCluster, java.lang.Integer touristicnessRatingFrom1To10, java.lang.String spotName) {
      super(ClusterAPI.this, "POST", REST_PATH, null, com.appspot.flickeranalyser.clusterAPI.model.Response.class);
      this.datastoreKeyOfCluster = com.google.api.client.util.Preconditions.checkNotNull(datastoreKeyOfCluster, "Required parameter datastoreKeyOfCluster must be specified.");
      this.touristicnessRatingFrom1To10 = com.google.api.client.util.Preconditions.checkNotNull(touristicnessRatingFrom1To10, "Required parameter touristicnessRatingFrom1To10 must be specified.");
      this.spotName = com.google.api.client.util.Preconditions.checkNotNull(spotName, "Required parameter spotName must be specified.");
    }

    @Override
    public EvaluateCluster setAlt(java.lang.String alt) {
      return (EvaluateCluster) super.setAlt(alt);
    }

    @Override
    public EvaluateCluster setFields(java.lang.String fields) {
      return (EvaluateCluster) super.setFields(fields);
    }

    @Override
    public EvaluateCluster setKey(java.lang.String key) {
      return (EvaluateCluster) super.setKey(key);
    }

    @Override
    public EvaluateCluster setOauthToken(java.lang.String oauthToken) {
      return (EvaluateCluster) super.setOauthToken(oauthToken);
    }

    @Override
    public EvaluateCluster setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (EvaluateCluster) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public EvaluateCluster setQuotaUser(java.lang.String quotaUser) {
      return (EvaluateCluster) super.setQuotaUser(quotaUser);
    }

    @Override
    public EvaluateCluster setUserIp(java.lang.String userIp) {
      return (EvaluateCluster) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String datastoreKeyOfCluster;

    /**

     */
    public java.lang.String getDatastoreKeyOfCluster() {
      return datastoreKeyOfCluster;
    }

    public EvaluateCluster setDatastoreKeyOfCluster(java.lang.String datastoreKeyOfCluster) {
      this.datastoreKeyOfCluster = datastoreKeyOfCluster;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.Integer touristicnessRatingFrom1To10;

    /**

     */
    public java.lang.Integer getTouristicnessRatingFrom1To10() {
      return touristicnessRatingFrom1To10;
    }

    public EvaluateCluster setTouristicnessRatingFrom1To10(java.lang.Integer touristicnessRatingFrom1To10) {
      this.touristicnessRatingFrom1To10 = touristicnessRatingFrom1To10;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String spotName;

    /**

     */
    public java.lang.String getSpotName() {
      return spotName;
    }

    public EvaluateCluster setSpotName(java.lang.String spotName) {
      this.spotName = spotName;
      return this;
    }

    @Override
    public EvaluateCluster set(String parameterName, Object value) {
      return (EvaluateCluster) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link ClusterAPI}.
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

    /** Builds a new instance of {@link ClusterAPI}. */
    @Override
    public ClusterAPI build() {
      return new ClusterAPI(this);
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
     * Set the {@link ClusterAPIRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setClusterAPIRequestInitializer(
        ClusterAPIRequestInitializer clusterapiRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(clusterapiRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
