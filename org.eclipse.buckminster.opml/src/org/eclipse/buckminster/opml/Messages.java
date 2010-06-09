package org.eclipse.buckminster.opml;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.opml.messages"; //$NON-NLS-1$

	public static String element_0_does_not_represent_a_valid_RFC822_formatted_date;

	public static String element_0_does_not_represent_a_valid_list_of_integers;

	public static String element_0_does_not_represent_a_valid_integer;

	public static String element_0_does_not_represent_a_valid_URI;

	public static String attribute_0_does_not_represent_a_valid_RFC822_formatted_date;

	public static String attribute_0_does_not_represent_a_valid_URI;

	public static String unable_to_find_XMLschema_for_opml;
	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}
}
