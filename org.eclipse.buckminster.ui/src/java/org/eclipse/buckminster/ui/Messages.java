package org.eclipse.buckminster.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.ui.messages"; //$NON-NLS-1$

	public static String duplicate_0_found_in_plugin_1;

	public static String SplashScreen_splash_will_close_after_X_sec_but_you_can_click_to_close;

	public static String unknown_0_derivate;
	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}
}
