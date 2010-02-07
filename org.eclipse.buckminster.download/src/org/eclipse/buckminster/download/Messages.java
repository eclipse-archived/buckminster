package org.eclipse.buckminster.download;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.download.messages"; //$NON-NLS-1$

	public static String connection_retry_count;

	public static String connection_retry_delay;

	public static String connection_to_0_failed_on_1_retry_attempt_2;

	public static String fetching_0_1_at_2;

	public static String fetching_0_1_of_2_at_3;

	public static String digest_mismatch_reading_0;

	public static String digest_not_fully_read_expected_0_got_1;

	public static String error_0_cause_1;

	public static String not_a_directory;

	public static String system_property_0_not_set;

	public static String unable_to_access_cache_0;

	public static String unable_to_access_directory_0;

	public static String unable_to_create_directory_0;

	public static String unable_to_read_0_hex_chars_from_1;

	public static String unable_to_rename_0;

	public static String unable_to_rename_temp_0;

	public static String unable_to_unzip_into_directory_0;

	public static String URL_reader;

	public static String value_0_illegal_for_1;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
