package org.eclipse.buckminster.subversive;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.subversive.messages"; //$NON-NLS-1$

	public static String could_not_create_repository_resource;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
