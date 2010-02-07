package org.eclipse.buckminster.generic.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.generic.ui.messages"; //$NON-NLS-1$

	public static String active_part_not_set;

	public static String browser_external;

	public static String browser_internal;

	public static String can_not_open_browser;

	public static String no_valid_URL_for_selected_object;

	public static String nothing_selected;

	public static String nothing_was_selected;

	public static String only_TreeView_accepted;

	public static String show_in_browser;

	public static String view_0_in_1_;

	public static String view_in_0_;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
