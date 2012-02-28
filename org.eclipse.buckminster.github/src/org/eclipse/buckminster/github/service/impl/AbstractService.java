package org.eclipse.buckminster.github.service.impl;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.eclipse.buckminster.github.GitHubException;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class AbstractService {
	static class TypeToken<T> implements ParameterizedType {
		private final ParameterizedType type;

		public TypeToken() {
			Type superclass = getClass().getGenericSuperclass();
			if (!(superclass instanceof ParameterizedType))
				throw new IllegalArgumentException("Missing type parameter"); //$NON-NLS-1$
			Type paramType = ((ParameterizedType) superclass).getActualTypeArguments()[0];
			if (!(paramType instanceof ParameterizedType))
				throw new IllegalArgumentException("Use a plain class for non parameterized types"); //$NON-NLS-1$
			type = (ParameterizedType) paramType;
		}

		@Override
		public Type[] getActualTypeArguments() {
			return type.getActualTypeArguments();
		}

		@Override
		public Type getOwnerType() {
			return type.getOwnerType();
		}

		@Override
		public Type getRawType() {
			return type.getRawType();
		}
	}

	// A somewhat orthodox format. Why the 'Z' at then end? A bug at GitHub?
	static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); //$NON-NLS-1$
	static final DateFormat dateFormatWithMS = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'"); //$NON-NLS-1$
	static final JsonParser parser = new JsonParser();

	private static final String UTF_8_KEY = "UTF-8"; //$NON-NLS-1$
	private static final Charset UTF_8 = Charset.forName(UTF_8_KEY);

	static {
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); //$NON-NLS-1$
	}

	private final String accessToken;

	private final GsonBuilder gsonBuilder;

	private final HttpClient httpClient;

	protected AbstractService(String accessToken) {
		this.accessToken = accessToken;
		GsonBuilder builder = new GsonBuilder();
		builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

			@Override
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				try {
					return dateFormat.parse(json.getAsString());
				} catch (ParseException e) {
					try {
						return dateFormatWithMS.parse(json.getAsString());
					} catch (ParseException e2) {
					}
					throw new JsonParseException(e);
				}
			}
		});
		gsonBuilder = builder;
		httpClient = new DefaultHttpClient();
	}

	protected InputStream callApiGet(String apiUrl) throws GitHubException {
		return callApiMethod("GET", apiUrl, null, null, 200); //$NON-NLS-1$
	}

	protected InputStream callApiMethod(String httpMethod, String apiUrl, String jsonStr, String contentType, int expectedCode)
			throws GitHubException {
		try {
			HttpUriRequest method;
			URI uri = new URI(apiUrl);
			uri = authorize(uri);

			if (HttpGet.METHOD_NAME.equals(httpMethod)) {
				method = new HttpGet(uri);
			} else if (HttpPost.METHOD_NAME.equals(httpMethod)) {
				HttpPost post = new HttpPost(uri);
				if (jsonStr != null) {
					post.setEntity(new StringEntity(jsonStr, contentType, UTF_8_KEY));
				}
				method = post;
			} else if (HttpDelete.METHOD_NAME.equals(httpMethod)) {
				method = new HttpDelete(uri);
			} else {
				throw new GitHubException("Unsupported method: " + httpMethod); //$NON-NLS-1$
			}

			HttpResponse response;
			response = httpClient.execute(method);

			StatusLine status = response.getStatusLine();
			HttpEntity entity = response.getEntity();
			if (status.getStatusCode() == expectedCode)
				return entity == null ? null : entity.getContent();

			String msg = null;
			if (entity == null)
				msg = status.getReasonPhrase();
			else {

				try {
					JsonObject json = unmarshall(entity.getContent());
					JsonElement jsonMsg = json.get("message"); //$NON-NLS-1$
					if (jsonMsg != null)
						msg = jsonMsg.getAsString();
				} catch (Exception e) {
				}
			}
			if (msg == null)
				msg = status.getReasonPhrase();
			throw new GitHubException(String.format("URL: %s, response code %d: %s", apiUrl, Integer.valueOf(status.getStatusCode()), msg)); //$NON-NLS-1$
		} catch (URISyntaxException e) {
			throw new GitHubException(e);
		} catch (IOException e) {
			throw new GitHubException(e);
		}
	}

	protected void closeStream(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
			}
		}
	}

	protected GsonBuilder getGsonBuilder() {
		return gsonBuilder;
	}

	protected JsonObject unmarshall(InputStream jsonContent) throws GitHubException {
		try {
			JsonElement json = parser.parse(new InputStreamReader(jsonContent, UTF_8));
			if (json.isJsonObject())
				return (JsonObject) json;
			throw new GitHubException("Unknown content found in response: " + json); //$NON-NLS-1$
		} catch (JsonParseException e) {
			throw new GitHubException(e);
		} finally {
			closeStream(jsonContent);
		}
	}

	protected <T> T unmarshall(Type cls, InputStream jsonContent) throws GitHubException {
		try {
			return gsonBuilder.create().fromJson(new InputStreamReader(jsonContent, UTF_8), cls);
		} catch (JsonParseException e) {
			throw new GitHubException(e);
		} finally {
			closeStream(jsonContent);
		}
	}

	protected <T> T unmarshall(Type cls, JsonElement response) throws GitHubException {
		try {
			Gson gson = getGsonBuilder().create();
			return gson.fromJson(response, cls);
		} catch (JsonParseException e) {
			throw new GitHubException(e);
		}
	}

	private URI authorize(URI uri) throws URISyntaxException {
		if (accessToken == null)
			return uri;

		String query = uri.getQuery();
		StringBuilder bld = new StringBuilder();
		if (query != null) {
			bld.append(query);
			bld.append('&');
		}
		bld.append("access_token="); //$NON-NLS-1$
		bld.append(accessToken);
		return new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), uri.getPath(), bld.toString(), uri.getFragment());
	}
}
