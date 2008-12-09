package org.eclipse.buckminster.subclipse;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.subclipse.messages"; //$NON-NLS-1$

	public static String checking_out_0_using_revision_1;

	public static String obtaining_remote_folder_0_1;

	public static String remote_folder_does_not_exist_0_1;

	public static String svn_checkout_timed_out;

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
