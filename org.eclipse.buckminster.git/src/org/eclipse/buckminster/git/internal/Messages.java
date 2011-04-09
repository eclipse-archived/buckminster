package org.eclipse.buckminster.git.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.git.internal.messages"; //$NON-NLS-1$

	public static String git_reader_can_not_materialize;

	public static String git_reader_type_is_missing_required_property_0;

	public static String git_reader_0_cannot_switch_to_1_without_causing_conflict_beneath_2;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
