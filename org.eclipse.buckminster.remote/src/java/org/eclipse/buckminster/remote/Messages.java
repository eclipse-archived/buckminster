/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.remote;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.remote.messages"; //$NON-NLS-1$

	public static String provider_0_already_registered;

	public static String provider_0_not_found;

	public static String null_BOM_resolution_request;

	public static String query_resolution_in_progress;

	public static String starting_query_resolution;

	public static String login_to_service_by_provider_0_failed;

	public static String remote_res_error_BOM;

	public static String remote_res_error_cancel;

	public static String remote_res_error_login_failed;

	public static String remote_res_error_login_info;

	public static String remote_res_error_logout_failed;

	public static String remote_res_error_progress_info;

	public static String remote_res_error_resolution_result;

	public static String remote_res_service_not_initialized;

	public static String remote_service_provider_not_set;

	public static String setup_fail_check_service_on_0;

	public static String unknown_encrypt_algorithm_0;

	static
	{
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
