package org.eclipse.buckminster.subclipse;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.subclipse.messages"; //$NON-NLS-1$

	public static String branch_or_tag_0_not_found;

	public static String checking_out_0_using_revision_1;

	public static String exception_part_non_existent;

	public static String exception_part_not_found;

	public static String file_not_found;

	public static String listing_remote_folder_0;

	public static String obtaining_remote_folder_0_1;

	public static String path_not_found;

	public static String reading_remote_file_0;

	public static String remote_file_not_found;

	public static String remote_folder_does_not_exist_0;

	public static String remote_folder_does_not_exist_0_1;

	public static String remote_folder_had_no_entries_0;

	public static String svn_checkout_timed_out;

	public static String svn_session_cannot_use_both_timestamp_and_revision_number;

	public static String unable_to_find_artifacts_0;

	public static String unable_to_find_repository_location;

	public static String unable_to_load_default_svn_client;
	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}
}
