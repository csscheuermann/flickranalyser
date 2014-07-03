package com.flickranalyser.html.common;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.eclipsesource.json.JsonObject;
import com.flickranalyser.html.webfrontend.HtmlRequestProcessor;
import com.flickranalyser.pojo.User;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;

public class GoogleAuthHelper {

	private static final Logger LOGGER = Logger.getLogger(GoogleAuthHelper.class.getName());
	/**
	 * Please provide a value for the CLIENT_ID constant before proceeding, set this up at https://code.google.com/apis/console/
	 */
	public static final String CLIENT_ID = "1099379908084-erlt14509li8acjpd7m20770t9gi5c0g.apps.googleusercontent.com";

	/**
	 * Please provide a value for the CLIENT_SECRET constant before proceeding, set this up at https://code.google.com/apis/console/
	 */
	public static final String CLIENT_SECRET = "zoQ1ahfrdOIglvRNv210_Yy0";


	/**
	 * Callback URI that google will redirect to after successful authentication
	 */
	private static final String CALLBACK_URI = "https://flickeranalyser.appspot.com/oauth2callback";

	// start google authentication constants
	private static final Iterable<String> SCOPE = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile;https://www.googleapis.com/auth/userinfo.email".split(";"));
	private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo";
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	// end google authentication constants

	private String stateToken;

	private User user = HtmlRequestProcessor.GUEST_USER;
	private final GoogleAuthorizationCodeFlow flow;


	/**
	 * Constructor initializes the Google Authorization Code Flow with CLIENT ID, SECRET, and SCOPE 
	 */
	public GoogleAuthHelper() {
		flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT,
				JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, (Collection<String>) SCOPE).build();

		generateStateToken();
	}

	/**
	 * Builds a login URL based on client ID, secret, callback URI, and scope 
	 */
	public String buildLoginUrl() {

		final GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();

		return url.setRedirectUri(CALLBACK_URI).setState(stateToken).build();
	}

	/**
	 * Generates a secure state token 
	 */
	private void generateStateToken(){

		SecureRandom sr1 = new SecureRandom();

		stateToken = "google;"+sr1.nextInt();

	}

	/**
	 * Accessor for state token
	 */
	public String getStateToken(){
		return stateToken;
	}

	/**
	 * Expects an Authentication Code, and makes an authenticated request for the user's profile information
	 * @return JSON formatted user profile information
	 * @param authCode authentication code provided by google
	 */
	public User getUserInfoJson(final String authCode) throws IOException {

		final GoogleTokenResponse response = flow.newTokenRequest(authCode).setRedirectUri(CALLBACK_URI).execute();
		final Credential credential = flow.createAndStoreCredential(response, null);
		final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);
		// Make an authenticated request
		final GenericUrl url = new GenericUrl(USER_INFO_URL);
		final HttpRequest request = requestFactory.buildGetRequest(url);
		request.getHeaders().setContentType("application/json");
		final String jsonIdentity = request.execute().parseAsString();

		JsonObject userObject = JsonObject.readFrom(jsonIdentity); 
		
		String email = 	userObject.get("email").asString();
		String fullName = 	userObject.get("name").asString();
		String givenName = 	userObject.get("given_name").asString();
		String profileLink = 	userObject.get("link").asString();
		String picture = 	userObject.get("picture").asString();
		
		LOGGER.log(Level.INFO, "Email: " + email );
		LOGGER.log(Level.INFO, "Full Name: " + fullName );
		LOGGER.log(Level.INFO, "Given Name: " + givenName );
		LOGGER.log(Level.INFO, "Profile Link: " + profileLink );
		LOGGER.log(Level.INFO, "Picture: " + picture );
	
		
		return new User(email, fullName, givenName, profileLink, picture);

	}
}
