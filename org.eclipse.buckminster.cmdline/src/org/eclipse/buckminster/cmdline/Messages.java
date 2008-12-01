package org.eclipse.buckminster.cmdline;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.cmdline.messages"; //$NON-NLS-1$

	public static String AbstractCommand_Help_missing_for_0;

	public static String AbstractCommand_Help_text_for_0;

	public static String AmbiguousOptionException_The_option_0_is_ambiguous;

	public static String BasicPreferenceHandler_No_slash_in_preference_path;

	public static String CommandInfo__0_is_not_a_valid_command_status;

	public static String CommandInfo_Command_0_is_deprecated;

	public static String CommandInfo_Command_0_not_found;

	public static String CommandInfo_implemented_by_class_0;

	public static String CommandInfo_Multiple_matches_for_0_for;

	public static String CommandInfo_Use_command_0_instead;

	public static String CommandLineParser_ENV_Variables_not_supported_unless_Java_1_5_or_higher;

	public static String Headless_buckminster__help;

	public static String Headless_buckminster_command__help;

	public static String Headless_buckminster_listcommands;

	public static String Headless_Command_canceled;

	public static String Headless_Command_was_interrupted;

	public static String Headless_Help_is_not_available;

	public static String Headless_Help_text_for_buckminster;

	public static String Headless_No_command_provided_Try_one_of;

	public static String Headless_The_scriptfile_option_cannot_be_combined_with_a_command;

	public static String Headless_Unexpected_option;

	public static String IllegalCommandAliasException_The_command_alias_0_is_illegal;

	public static String InvalidOptionException_The_option_0_is_invalid;

	public static String InvalidOptionValueException_The_value_for_option_0_is_invalid;

	public static String ListCommands_Available_commands_by_namespace;

	public static String ListCommands_Available_commands_including_aliases;

	public static String NoOptionNameException_Missing_option_name;

	public static String OptionRequiresValueException_The_option_0_requires_a_value;

	public static String ParseResult_Unknown_OptionValueType;
	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}
}
