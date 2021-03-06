package com.seekret.html.common;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.eclipsesource.json.JsonObject;
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
import com.seekret.endpoints.UserService;
import com.seekret.pojo.SeekretUser;

public class GoogleAuthHelper {
	private static final String NOT_AVAILABLE = "NOT AVAILABLE";
	private static final Logger LOGGER = Logger.getLogger(GoogleAuthHelper.class.getName());
	public static final String CLIENT_ID = "449444009918-2or65i59u6m5s97arnnsg8econu0rvot.apps.googleusercontent.com";
	public static final String CLIENT_SECRET = "373LBORcbZnl6e94TbzWTgtj";
	private static final String CALLBACK_URI = "https://seekret-dev.appspot.com/oauth2callback";
	private static final Iterable<String> SCOPE = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile;https://www.googleapis.com/auth/userinfo.email".split(";"));
	private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo";
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private String stateToken;
	private final GoogleAuthorizationCodeFlow flow;
	private UserService userService;

	public GoogleAuthHelper() {
		this.flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, (Collection) SCOPE).build();
		userService = new UserService();
		generateStateToken();
	}

	public String buildLoginUrl() {
		GoogleAuthorizationCodeRequestUrl url = this.flow.newAuthorizationUrl();

		return url.setRedirectUri(CALLBACK_URI).setState(this.stateToken).build();
	}

	private void generateStateToken() {
		SecureRandom sr1 = new SecureRandom();

		this.stateToken = ("google;" + sr1.nextInt());
	}

	public String getStateToken() {
		return this.stateToken;
	}

	public SeekretUser getGoogleUserInfo(String authCode) throws IOException {
		GoogleTokenResponse response = this.flow.newTokenRequest(authCode).setRedirectUri(CALLBACK_URI).execute();
		Credential credential = this.flow.createAndStoreCredential(response, null);
		HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);

		GenericUrl url = new GenericUrl(USER_INFO_URL);
		HttpRequest request = requestFactory.buildGetRequest(url);
		request.getHeaders().setContentType("application/json");
		String jsonIdentity = request.execute().parseAsString();

		JsonObject userObject = JsonObject.readFrom(jsonIdentity);

		String email = "NOT AVAILABLE";
		String fullName = "NOT AVAILABLE";
		String givenName = "NOT AVAILABLE";
		String profileLink = "NOT AVAILABLE";
		String picture = "NOT AVAILABLE";

		if (userObject.get("email") != null) {
			email = userObject.get("email").asString();
		}

		if (userObject.get("name") != null) {
			fullName = userObject.get("name").asString();
		}

		if (userObject.get("given_name") != null) {
			givenName = userObject.get("given_name").asString();
		}

		if (userObject.get("link") != null) {
			profileLink = userObject.get("link").asString();
		}

		if (userObject.get("picture") != null) {
			picture = userObject.get("picture").asString();
		}

		LOGGER.log(Level.INFO, "Email: " + email);
		LOGGER.log(Level.INFO, "Full Name: " + fullName);
		LOGGER.log(Level.INFO, "Given Name: " + givenName);
		LOGGER.log(Level.INFO, "Profile Link: " + profileLink);
		LOGGER.log(Level.INFO, "Picture: " + picture);

		SeekretUser userFromDb = userService.getUserByEmail(email);
		if (userFromDb != null) {
			return userFromDb;
		}
		userService.addUserToDatastore(email, fullName, givenName, profileLink, picture);
		return new SeekretUser(email, fullName, givenName, profileLink, picture);
	}
}