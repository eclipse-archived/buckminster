package org.eclipse.buckminster.github;

public class GitHubException extends Exception {

	private static final long serialVersionUID = -1176030904144200038L;

	public GitHubException() {
	}

	public GitHubException(String message) {
		super(message);
	}

	public GitHubException(String message, Throwable cause) {
		super(message, cause);
	}

	public GitHubException(Throwable cause) {
		super(cause);
	}
}
