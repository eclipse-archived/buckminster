package org.eclipse.buckminster.github.test;

import static org.junit.Assert.fail;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.eclipse.buckminster.github.GitHubServiceFactory;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.osgi.framework.Bundle;

public class ServiceTest {
	private static String owner;
	private static String repository;
	private static GitHubClient githubClient;

	private static void close(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) { // ignore
			}
		}
	}

	@BeforeClass
	public static void initialize() {
		try {
			Properties defaultProps = new Properties();
			File propsFile = getTestData("test.properties");
			InputStream in = new BufferedInputStream(new FileInputStream(
					propsFile));
			try {
				defaultProps.load(in);
			} finally {
				close(in);
			}
			Properties localProps = new Properties(defaultProps);
			propsFile = getTestData("local.test.properties");
			try {
				in = new BufferedInputStream(new FileInputStream(propsFile));
				try {
					defaultProps.load(in);
				} finally {
					close(in);
				}
			} catch (FileNotFoundException e) {
				// Ignore
			}

			String clientID = getRequiredProperty(localProps, "clientID");
			String clientSecret = getRequiredProperty(localProps, "clientSecret");
			String login = getRequiredProperty(localProps, "login");
			String password = getRequiredProperty(localProps, "password");
			owner = getRequiredProperty(localProps, "owner");
			repository = getRequiredProperty(localProps, "repository");

			githubClient = new GitHubClient();
			githubClient.setOAuth2Token(GitHubServiceFactory.INSTANCE
					.getAccessToken(login, password, clientID, clientSecret,
							"repo"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	private static String getRequiredProperty(Properties props, String key) {
		String val = props.getProperty(key);
		if (val != null) {
			if (val.length() > 1 && val.charAt(0) == '<'
					&& val.charAt(val.length() - 1) == '>')
				throw new RuntimeException(
						String.format(
								"Key '%s' needs to have its template value '%s' replaced",
								key, val));
			return val;
		}
		throw new RuntimeException(String.format("Missing property '%s'", key));
	}

	public static File getTestData(String fileName) throws IOException {
		Bundle self = Activator.context.getBundle();
		URL base = self.getEntry("testData");
		if (base == null)
			throw new RuntimeException("Unable to find \"testData\" folder");
		return new File(toFile(base), fileName);
	}

	private static File toFile(URL url) throws IOException {
		return new File(
				new Path(FileLocator.toFileURL(url).getPath()).toOSString());
	}

	public static File getTestOutputFolder(String name) throws IOException {
		return Activator.context.getDataFile(name);
	}

	public static GitHubClient getGitHubClient() {
		return githubClient;
	}

	public static IRepositoryIdProvider getRepository() {
		return new RepositoryId(owner, repository);
	}
}
