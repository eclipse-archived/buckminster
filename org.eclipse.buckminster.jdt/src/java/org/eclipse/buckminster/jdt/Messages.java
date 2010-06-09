package org.eclipse.buckminster.jdt;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.jdt.messages"; //$NON-NLS-1$

	public static String BMClasspathContainer_description;

	public static String parsing_classpath;

	public static String publishing_jre;

	public static String unexpected_classpath_entry_kind;

	public static String list_separator;

	public static String unrecognized_properties_supplied_0;

	public static String recognized_properties_0;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
