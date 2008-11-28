package org.eclipse.buckminster.cvspkg;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.cvspkg.messages"; //$NON-NLS-1$

	public static String cvs_module;

	public static String cvs_root;

	public static String cvs_URL_host_cannot_be_empty;

	public static String cvs_URL_module_must_be_absolute;

	public static String cvs_URL_module_must_have_segment;

	public static String cvs_URL_module_must_not_have_trailing_separator;

	public static String cvs_URL_module_path;

	public static String cvs_URL_must_end_with_0;

	public static String cvs_URL_path_cannot_be_empty;

	public static String cvs_URL_path_must_be_absolute;

	public static String cvs_URL_path_must_have_segment;

	public static String cvs_URL_path_must_not_have_trailing_separator;

	public static String file_0_appears_to_be_folder;

	public static String found_no_metadata_for_0;

	public static String illegal_fetch_factory_locator;

	public static String obtaining_meta_data_for_repository_0;

	public static String repository_URI_not_in_0_format;

	public static String retrieving_0;

	public static String unable_to_create_directory_0;

	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}
}
