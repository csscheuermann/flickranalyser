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
 * on 2014-08-28 at 11:40:13 UTC 
 * Modify at your own risk.
 */

package com.appspot.seekret_dev.spotAPI;

/**
 * SpotAPI request initializer for setting properties like key and userIp.
 *
 * <p>
 * The simplest usage is to use it to set the key parameter:
 * </p>
 *
 * <pre>
  public static final GoogleClientRequestInitializer KEY_INITIALIZER =
      new SpotAPIRequestInitializer(KEY);
 * </pre>
 *
 * <p>
 * There is also a constructor to set both the key and userIp parameters:
 * </p>
 *
 * <pre>
  public static final GoogleClientRequestInitializer INITIALIZER =
      new SpotAPIRequestInitializer(KEY, USER_IP);
 * </pre>
 *
 * <p>
 * If you want to implement custom logic, extend it like this:
 * </p>
 *
 * <pre>
  public static class MyRequestInitializer extends SpotAPIRequestInitializer {

    {@literal @}Override
    public void initializeSpotAPIRequest(SpotAPIRequest{@literal <}?{@literal >} request)
        throws IOException {
      // custom logic
    }
  }
 * </pre>
 *
 * <p>
 * Finally, to set the key and userIp parameters and insert custom logic, extend it like this:
 * </p>
 *
 * <pre>
  public static class MyRequestInitializer2 extends SpotAPIRequestInitializer {

    public MyKeyRequestInitializer() {
      super(KEY, USER_IP);
    }

    {@literal @}Override
    public void initializeSpotAPIRequest(SpotAPIRequest{@literal <}?{@literal >} request)
        throws IOException {
      // custom logic
    }
  }
 * </pre>
 *
 * <p>
 * Subclasses should be thread-safe.
 * </p>
 *
 * @since 1.12
 */
public class SpotAPIRequestInitializer extends com.google.api.client.googleapis.services.json.CommonGoogleJsonClientRequestInitializer {

  public SpotAPIRequestInitializer() {
    super();
  }

  /**
   * @param key API key or {@code null} to leave it unchanged
   */
  public SpotAPIRequestInitializer(String key) {
    super(key);
  }

  /**
   * @param key API key or {@code null} to leave it unchanged
   * @param userIp user IP or {@code null} to leave it unchanged
   */
  public SpotAPIRequestInitializer(String key, String userIp) {
    super(key, userIp);
  }

  @Override
  public final void initializeJsonRequest(com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest<?> request) throws java.io.IOException {
    super.initializeJsonRequest(request);
    initializeSpotAPIRequest((SpotAPIRequest<?>) request);
  }

  /**
   * Initializes SpotAPI request.
   *
   * <p>
   * Default implementation does nothing. Called from
   * {@link #initializeJsonRequest(com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest)}.
   * </p>
   *
   * @throws java.io.IOException I/O exception
   */
  protected void initializeSpotAPIRequest(SpotAPIRequest<?> request) throws java.io.IOException {
  }
}
