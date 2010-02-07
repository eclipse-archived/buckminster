package org.eclipse.buckminster.jdt;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.jdt.messages"; //$NON-NLS-1$

	public static String BMClasspathContainer_description;

	public static String parsing_classpath;

	public static String unexpected_classpath_entry_kind;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
