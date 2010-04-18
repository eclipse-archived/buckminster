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
	public static String mapping_entries_problems_0;
	public static String mapping_entry_0_invalid_1;
	public static String mapping_entry_not_exactly_three_fields;
	public static String problems_during_0;
	public static String processing_of_0;
	public static String processing_project_0;
	public static String tag_not_supplied;
	public static String tagging_project_0;
	public static String workspace_resolution_tagging_of_0;
	public static String unrecognized_properties_supplied_0;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
