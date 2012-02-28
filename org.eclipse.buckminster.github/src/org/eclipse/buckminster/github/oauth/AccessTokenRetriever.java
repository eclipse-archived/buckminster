package org.eclipse.buckminster.github.oauth;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;

/**
 * This class is capable of obtaining the OAuth accesst token from GitHub
 * provided that the given user already has granted the application identified
 * with the client id and client secret the requested permissions.
 */
public class AccessTokenRetriever {
	private static final String QUERY_ENCODING = "UTF-8"; //$NON-NLS-1$

	private static final Charset ASCII = Charset.forName("ASCII"); //$NON-NLS-1$
	private static final Charset UTF_8 = Charset.forName("UTF-8"); //$NON-NLS-1$

	public static Map<String, String> parseQuery(String query) throws UnsupportedEncodingException {
		if (query == null || query.isEmpty())
			return Collections.emptyMap();

		Map<String, String> result = new HashMap<String, String>();
		int top = query.length();
		int start = 0;
		for (int idx = 0; idx < top; ++idx) {
			char c = query.charAt(idx);
			if (c == '&') {
				addPair(result, query.substring(start, idx));
				start = idx + 1;
			}
		}
		if (start < top)
			addPair(result, query.substring(start));
		return result;
	}

	private static void addPair(Map<String, String> result, String pair) throws UnsupportedEncodingException {
		int sep = pair.indexOf('=');
		if (sep < 0)
			result.put(URLDecoder.decode(pair, QUERY_ENCODING), null);
		else
			result.put(URLDecoder.decode(pair.substring(0, sep), QUERY_ENCODING), URLDecoder.decode(pair.substring(sep + 1), QUERY_ENCODING));
	}

	private final String user;
	private final String password;
	private final String clientId;
	private final String clientSecret;

	private String accessToken;

	private static final String OAUTH_URL = "https://github.com:443/login/oauth/"; //$NON-NLS-1$

	private static String getStreamContent(InputStream stream) throws IOException {
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		byte[] buf = new byte[256];
		int cnt;
		while ((cnt = stream.read(buf)) > 0)
			bas.write(buf, 0, cnt);
		return new String(bas.toByteArray());
	}

	private static SecurityException reportError(HttpURLConnection conn) throws IOException {
		return new SecurityException(getStreamContent(conn.getErrorStream()));
	}

	public AccessTokenRetriever(String user, String password, String clientId, String clientSecret) {
		this.user = user;
		this.password = password;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	public synchronized String getAccessToken(String... scopes) throws SecurityException, IOException {
		if (accessToken != null)
			return accessToken;

		StringBuilder bld = new StringBuilder();
		bld.append(OAUTH_URL);
		bld.append("authorize?client_id="); //$NON-NLS-1$
		bld.append(clientId);
		if (scopes.length > 0) {
			bld.append("&scope="); //$NON-NLS-1$
			bld.append(scopes[0]);
			for (int idx = 1; idx < scopes.length; ++idx) {
				bld.append(',');
				bld.append(scopes[idx]);
			}
		}
		URL url = new URL(bld.toString());
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setInstanceFollowRedirects(false);
		String auth = "Basic " + new String(Base64.encodeBase64((user + ':' + password).getBytes(UTF_8)), ASCII); //$NON-NLS-1$
		conn.setRequestProperty("Authorization", auth); //$NON-NLS-1$

		String code;
		conn.connect();
		try {
			if (conn.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
				URI redirect = URI.create(conn.getHeaderField("Location")); //$NON-NLS-1$
				code = parseQuery(redirect.getQuery()).get("code"); //$NON-NLS-1$
			} else
				throw reportError(conn);
		} finally {
			conn.disconnect();
		}

		url = new URL(OAUTH_URL + "access_token?client_id=" + clientId + "&client_secret=" + clientSecret + "&code=" + code); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		conn = (HttpsURLConnection) url.openConnection();
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("POST"); //$NON-NLS-1$
		conn.connect();
		try {
			// Read the single line in the response
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK)
				throw reportError(conn);

			String token = parseQuery(getStreamContent(conn.getInputStream())).get("access_token"); //$NON-NLS-1$
			if (token != null) {
				accessToken = token;
				return token;
			}

			throw new SecurityException("access_token not found in response from GitHub"); //$NON-NLS-1$
		} finally {
			conn.disconnect();
		}
	}
}
