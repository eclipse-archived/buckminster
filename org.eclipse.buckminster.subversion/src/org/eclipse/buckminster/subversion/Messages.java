package org.eclipse.buckminster.subversion;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.subversion.messages"; //$NON-NLS-1$

	public static String branch_or_tag_0_not_found;

	public static String listing_remote_folder_0;

	public static String remote_folder_does_not_exist_0;

	public static String remote_folder_had_no_entries_0;

	public static String svn_session_cannot_use_both_timestamp_and_revision_number;

	public static String unable_to_find_artifacts_at_0;

	public static String URI_0_has_no_scheme;

	public static String URI_can_not_be_null_at_this_point;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
