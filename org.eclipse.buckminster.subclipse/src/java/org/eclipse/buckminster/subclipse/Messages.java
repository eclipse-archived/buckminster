package org.eclipse.buckminster.subclipse;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.subclipse.messages"; //$NON-NLS-1$

	public static String checking_out_0_using_revision_1;

	public static String file_not_found;

	public static String path_not_found;

	public static String reading_remote_file_0;

	public static String remote_file_not_found;

	public static String svn_checkout_timed_out;

	public static String unable_to_find_artifacts_0;

	public static String unable_to_find_repository_location;
	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}
}
