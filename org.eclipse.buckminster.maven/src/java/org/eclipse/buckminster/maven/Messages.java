package org.eclipse.buckminster.maven;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.maven.messages"; //$NON-NLS-1$

	public static String _0_has_no_version;

	public static String _0_is_a_better_match;

	public static String digest_verification_failed;

	public static String trying_again_with_dots;

	public static String digest_for_0_still_doesnt_match_after_1_download_attempts_corrupt_repo;

	public static String digest_mismatch_after_download_for_0;

	public static String generating_cspec_from_maven_artifact;

	public static String only_0_is_a_triplet;

	public static String the_result_of_applying_a_match_rule_had_no_separator_slash_0;

	public static String timestamp_0_is_more_strict;

	public static String unable_to_create_directory_0;

	public static String unable_to_find_digest_for_0;

	public static String unable_to_read_the_0_character_hexadecimal_form_of_the_digest_for_1;

	public static String unable_to_resolve_component_name_0_skipping_dependency;

	public static String verifying_digest_with_dots;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
