package org.eclipse.buckminster.git.internal;

public interface IPropertyKeys {
	/**
	 * Denotes the URL of the originating repository.
	 */
	String PROP_REMOTE_URI = "git.remote.uri"; //$NON-NLS-1$

	/**
	 * Denotes the name of the remote configuration (defaults to 'origin').
	 */
	String PROP_REMOTE_NAME = "git.remote.name"; //$NON-NLS-1$

	/**
	 * Set to <code>true</code> if an automatic fetch should be made from the
	 * remote repository
	 */
	String PROP_AUTO_FETCH = "git.auto.fetch"; //$NON-NLS-1$
}
