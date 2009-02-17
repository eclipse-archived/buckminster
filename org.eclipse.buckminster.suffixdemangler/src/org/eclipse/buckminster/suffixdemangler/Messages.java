package org.eclipse.buckminster.suffixdemangler;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.suffixdemangler.messages"; //$NON-NLS-1$

	public static String _0_cannotBeEmpty;

	public static String _0_cannotContainWhitespace;

	public static String file_0_hasCorruptTimestampAtLine_1;

	public static String missingArguments;

	public static String storageFolder_0_doesNotExist;

	public static String tooManyArguments;

	public static String widthCannotBeGreaterThen10;

	public static String widthCannotBeNegative;
	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}
}
