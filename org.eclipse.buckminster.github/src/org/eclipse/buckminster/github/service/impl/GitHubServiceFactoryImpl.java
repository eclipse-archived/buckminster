package org.eclipse.buckminster.github.service.impl;

import java.io.IOException;

import org.eclipse.buckminster.github.GitHubException;
import org.eclipse.buckminster.github.GitHubServiceFactory;
import org.eclipse.buckminster.github.oauth.AccessTokenRetriever;
import org.eclipse.buckminster.github.service.RepoService;

public class GitHubServiceFactoryImpl implements GitHubServiceFactory {
	@Override
	public RepoService createRepoService(String authToken) {
		return new RepoServiceImpl(authToken);
	}

	@Override
	public String getAccessToken(String login, String password, String clientId, String clientSecret, String... scopes) throws GitHubException {
		AccessTokenRetriever tokenRetriever = new AccessTokenRetriever(login, password, clientId, clientSecret);
		try {
			return tokenRetriever.getAccessToken(scopes);
		} catch (IOException e) {
			throw new GitHubException(e);
		}
	}
}
