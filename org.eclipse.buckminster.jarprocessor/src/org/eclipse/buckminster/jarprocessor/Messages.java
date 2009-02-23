package org.eclipse.buckminster.jarprocessor;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.jarprocessor.messages"; //$NON-NLS-1$

	public static String action_0_does_not_recognize_command_1;
	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}
}
