package org.eclipse.buckminster.fetcher;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.fetcher.messages"; //$NON-NLS-1$

	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}

	public static String actorProperty_0_invalid_valid_are_1;
	public static String actorProperty_option_0_invalid_valid_are_1;
	public static String fetching_0;
	public static String login_and_password_must_be_not_null;
	public static String preparing_destination_folder_0;
}
