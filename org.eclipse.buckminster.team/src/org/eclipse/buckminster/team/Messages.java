/*******************************************************************************
 * Copyright (c) 2010, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.team;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.team.messages"; //$NON-NLS-1$
	public static String list_separator;
	public static String exclude_label;
	public static String include_label;
	public static String _0_entry_1_invalid_2;
	public static String property_settings_problems_0;
	public static String problems_during_0;
	public static String processing_of_0;
	public static String processing_project_0;
	public static String required_properties_not_supplied_0;
	public static String tagging_project_0;
	public static String workspace_resolution_tagging_of_0;
	public static String unrecognized_properties_supplied_0;
	public static String recognized_properties_0;
	public static String specified_pattern_is_invalid_0;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
