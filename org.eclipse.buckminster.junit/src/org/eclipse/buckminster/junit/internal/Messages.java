/*******************************************************************************
 * Copyright (c) 2009, eXXcellent solutions gmbh
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * Contributors:
 *     Achim Demelt - initial API and implementation
 *******************************************************************************/
package org.eclipse.buckminster.junit.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.junit.internal.messages"; //$NON-NLS-1$

	public static String JUnitCommand_Cannot_open_launch_config;

	public static String JUnitCommand_No_launch_config;

	public static String TestListener_Elapsed_time;

	public static String TestListener_Errors;

	public static String TestListener_Failed_tests;

	public static String TestListener_Ignored_tests;

	public static String TestListener_Overall_status;

	public static String TestListener_Running_test;

	public static String TestListener_Starting_test_session;

	public static String TestListener_Successful_tests;

	public static String TestListener_Tests_finished;

	public static String TestListener_Total_number_of_tests;

	public static String TestListener_Actual;

	public static String TestListener_Expected;

	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}
}
