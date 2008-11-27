package org.eclipse.buckminster.subversive;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.subversive.messages"; //$NON-NLS-1$

	public static String branch_or_tag_0_not_found;

	public static String could_not_create_repository_resource;

	public static String exception_part_file_not_found;

	public static String exception_part_non_existent;

	public static String exception_part_not_found;

	public static String exception_part_path_not_found;

	public static String exception_part_unable_to_find_repository_location;

	public static String listing_remote_folder_0;

	public static String reading_remote_file_0;

	public static String remote_file_not_found_0;

	public static String remote_folder_does_not_exist_0;

	public static String remote_folder_had_no_entries_0;

	public static String svn_session_cannot_use_both_timestamp_and_revision_number;

	public static String unable_to_find_artifacts_at_0;

	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}
}
