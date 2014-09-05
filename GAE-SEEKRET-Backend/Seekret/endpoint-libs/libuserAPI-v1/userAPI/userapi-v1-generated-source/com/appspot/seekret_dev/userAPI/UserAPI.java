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
 * on 2014-09-05 at 15:48:22 UTC 
 * Modify at your own risk.
 */

package com.appspot.seekret_dev.userAPI;

/**
 * Service definition for UserAPI (v1).
 *
 * <p>
 * API for User.
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link UserAPIRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class UserAPI extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.16.0-rc of the userAPI library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
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
  public static final String DEFAULT_SERVICE_PATH = "userAPI/v1/";

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
  public UserAPI(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  UserAPI(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "addUserToDatastore".
   *
   * This request holds the parameters needed by the the userAPI server.  After setting any optional
   * parameters, call the {@link AddUserToDatastore#execute()} method to invoke the remote operation.
   *
   * @param email
   * @param fullName
   * @param givenName
   * @param profileLink
   * @param picture
   * @return the request
   */
  public AddUserToDatastore addUserToDatastore(java.lang.String email, java.lang.String fullName, java.lang.String givenName, java.lang.String profileLink, java.lang.String picture) throws java.io.IOException {
    AddUserToDatastore result = new AddUserToDatastore(email, fullName, givenName, profileLink, picture);
    initialize(result);
    return result;
  }

  public class AddUserToDatastore extends UserAPIRequest<com.appspot.seekret_dev.userAPI.model.Response> {

    private static final String REST_PATH = "addUserToDatastore/{email}/{fullName}/{givenName}/{profileLink}/{picture}";

    /**
     * Create a request for the method "addUserToDatastore".
     *
     * This request holds the parameters needed by the the userAPI server.  After setting any optional
     * parameters, call the {@link AddUserToDatastore#execute()} method to invoke the remote
     * operation. <p> {@link AddUserToDatastore#initialize(com.google.api.client.googleapis.services.A
     * bstractGoogleClientRequest)} must be called to initialize this instance immediately after
     * invoking the constructor. </p>
     *
     * @param email
     * @param fullName
     * @param givenName
     * @param profileLink
     * @param picture
     * @since 1.13
     */
    protected AddUserToDatastore(java.lang.String email, java.lang.String fullName, java.lang.String givenName, java.lang.String profileLink, java.lang.String picture) {
      super(UserAPI.this, "POST", REST_PATH, null, com.appspot.seekret_dev.userAPI.model.Response.class);
      this.email = com.google.api.client.util.Preconditions.checkNotNull(email, "Required parameter email must be specified.");
      this.fullName = com.google.api.client.util.Preconditions.checkNotNull(fullName, "Required parameter fullName must be specified.");
      this.givenName = com.google.api.client.util.Preconditions.checkNotNull(givenName, "Required parameter givenName must be specified.");
      this.profileLink = com.google.api.client.util.Preconditions.checkNotNull(profileLink, "Required parameter profileLink must be specified.");
      this.picture = com.google.api.client.util.Preconditions.checkNotNull(picture, "Required parameter picture must be specified.");
    }

    @Override
    public AddUserToDatastore setAlt(java.lang.String alt) {
      return (AddUserToDatastore) super.setAlt(alt);
    }

    @Override
    public AddUserToDatastore setFields(java.lang.String fields) {
      return (AddUserToDatastore) super.setFields(fields);
    }

    @Override
    public AddUserToDatastore setKey(java.lang.String key) {
      return (AddUserToDatastore) super.setKey(key);
    }

    @Override
    public AddUserToDatastore setOauthToken(java.lang.String oauthToken) {
      return (AddUserToDatastore) super.setOauthToken(oauthToken);
    }

    @Override
    public AddUserToDatastore setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (AddUserToDatastore) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public AddUserToDatastore setQuotaUser(java.lang.String quotaUser) {
      return (AddUserToDatastore) super.setQuotaUser(quotaUser);
    }

    @Override
    public AddUserToDatastore setUserIp(java.lang.String userIp) {
      return (AddUserToDatastore) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String email;

    /**

     */
    public java.lang.String getEmail() {
      return email;
    }

    public AddUserToDatastore setEmail(java.lang.String email) {
      this.email = email;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String fullName;

    /**

     */
    public java.lang.String getFullName() {
      return fullName;
    }

    public AddUserToDatastore setFullName(java.lang.String fullName) {
      this.fullName = fullName;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String givenName;

    /**

     */
    public java.lang.String getGivenName() {
      return givenName;
    }

    public AddUserToDatastore setGivenName(java.lang.String givenName) {
      this.givenName = givenName;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String profileLink;

    /**

     */
    public java.lang.String getProfileLink() {
      return profileLink;
    }

    public AddUserToDatastore setProfileLink(java.lang.String profileLink) {
      this.profileLink = profileLink;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String picture;

    /**

     */
    public java.lang.String getPicture() {
      return picture;
    }

    public AddUserToDatastore setPicture(java.lang.String picture) {
      this.picture = picture;
      return this;
    }

    @Override
    public AddUserToDatastore set(String parameterName, Object value) {
      return (AddUserToDatastore) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "doesUserExist".
   *
   * This request holds the parameters needed by the the userAPI server.  After setting any optional
   * parameters, call the {@link DoesUserExist#execute()} method to invoke the remote operation.
   *
   * @param email
   * @return the request
   */
  public DoesUserExist doesUserExist(java.lang.String email) throws java.io.IOException {
    DoesUserExist result = new DoesUserExist(email);
    initialize(result);
    return result;
  }

  public class DoesUserExist extends UserAPIRequest<com.appspot.seekret_dev.userAPI.model.SeekretUser> {

    private static final String REST_PATH = "seekretuser/{email}";

    /**
     * Create a request for the method "doesUserExist".
     *
     * This request holds the parameters needed by the the userAPI server.  After setting any optional
     * parameters, call the {@link DoesUserExist#execute()} method to invoke the remote operation. <p>
     * {@link DoesUserExist#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientR
     * equest)} must be called to initialize this instance immediately after invoking the constructor.
     * </p>
     *
     * @param email
     * @since 1.13
     */
    protected DoesUserExist(java.lang.String email) {
      super(UserAPI.this, "GET", REST_PATH, null, com.appspot.seekret_dev.userAPI.model.SeekretUser.class);
      this.email = com.google.api.client.util.Preconditions.checkNotNull(email, "Required parameter email must be specified.");
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
    public DoesUserExist setAlt(java.lang.String alt) {
      return (DoesUserExist) super.setAlt(alt);
    }

    @Override
    public DoesUserExist setFields(java.lang.String fields) {
      return (DoesUserExist) super.setFields(fields);
    }

    @Override
    public DoesUserExist setKey(java.lang.String key) {
      return (DoesUserExist) super.setKey(key);
    }

    @Override
    public DoesUserExist setOauthToken(java.lang.String oauthToken) {
      return (DoesUserExist) super.setOauthToken(oauthToken);
    }

    @Override
    public DoesUserExist setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (DoesUserExist) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public DoesUserExist setQuotaUser(java.lang.String quotaUser) {
      return (DoesUserExist) super.setQuotaUser(quotaUser);
    }

    @Override
    public DoesUserExist setUserIp(java.lang.String userIp) {
      return (DoesUserExist) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String email;

    /**

     */
    public java.lang.String getEmail() {
      return email;
    }

    public DoesUserExist setEmail(java.lang.String email) {
      this.email = email;
      return this;
    }

    @Override
    public DoesUserExist set(String parameterName, Object value) {
      return (DoesUserExist) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link UserAPI}.
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

    /** Builds a new instance of {@link UserAPI}. */
    @Override
    public UserAPI build() {
      return new UserAPI(this);
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
     * Set the {@link UserAPIRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setUserAPIRequestInitializer(
        UserAPIRequestInitializer userapiRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(userapiRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
