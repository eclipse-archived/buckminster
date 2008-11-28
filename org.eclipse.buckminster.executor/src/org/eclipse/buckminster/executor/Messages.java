package org.eclipse.buckminster.executor;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.executor.messages"; //$NON-NLS-1$

	public static String actorProperty_0_invalid_valid_are_1;

	public static String actorProperty_at_least_one_0_1;

	public static String actorProperty_at_most_one_0_1;

	public static String in_directory_0;

	public static String now_executing_0;

	public static String odd_number_of_quoting_chars_in_0;

	public static String program_0_exit_code_1;

	public static String setting_environment_variables_0;

	public static String shell_interpreter_for_0_not_supported;

	public static String using_system_environment_0;

	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}
}
