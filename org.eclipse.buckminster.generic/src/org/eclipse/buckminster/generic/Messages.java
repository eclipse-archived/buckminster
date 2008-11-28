package org.eclipse.buckminster.generic;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.generic.messages"; //$NON-NLS-1$

	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}

	public static String error_closing_instance_0;
	public static String failed_to_load_extension_point_element_0_in_1;
	public static String missing_attribute_0;
	public static String missing_name_attribute;
	public static String missing_sequence_0;
	public static String pending_;
	public static String requiredElement_0_not_correct_expected_1;
	public static String unexpected_exception;
}
