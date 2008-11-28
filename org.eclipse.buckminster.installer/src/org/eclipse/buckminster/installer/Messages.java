package org.eclipse.buckminster.installer;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.installer.messages"; //$NON-NLS-1$

	public static String feature_listing_heading;

	public static String installing_0_;

	public static String local_site;

	public static String multiple_versions_found_0;

	public static String no_feature_id_provided;

	public static String no_install_in_development_mode;

	public static String no_site_provided;

	public static String no_suitable_feature_version_found;

	public static String no_uninstall_in_development_mode;

	public static String only_reference_found;

	public static String searching_0_;

	public static String searching_for_0_;

	public static String searching_for_0_in_1_;

	public static String site_to_install_to_not_found;

	public static String site_to_uninstall_from_not_found;

	public static String too_many_arguments;

	public static String uninstalling_0_;

	public static String URL_0_malformed;

	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}
}
