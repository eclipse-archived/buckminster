package org.eclipse.buckminster.subversive;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.subversive.messages"; //$NON-NLS-1$

	public static String could_not_create_repository_resource;

	public static String exception_part_file_not_found;

	public static String exception_part_path_not_found;

	public static String exception_part_unable_to_find_repository_location;

	public static String reading_remote_file_0;

	public static String remote_file_not_found_0;

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
