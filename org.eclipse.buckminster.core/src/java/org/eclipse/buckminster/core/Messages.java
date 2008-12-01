package org.eclipse.buckminster.core;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.core.messages"; //$NON-NLS-1$

	public static String AbstractActor__0_not_valid_value_of_boolean_property;

	public static String AbstractActor_No_active_IActionContext;

	public static String AbstractActor_Prerequisite_alias;

	public static String AbstractActor_Prerequisite_rebase;

	public static String AbstractActor_Product_alias;

	public static String AbstractActor_Product_base;

	public static String AbstractBuckminsterBuilder_Method_not_overridden;

	public static String AbstractBuckminsterBuilder_Unknown_kind;

	public static String AbstractPreferencesCommand_Illegal_include_0_Must_be_in_the_form;

	public static String AbstractPreferencesCommand_Invalid_scope_Valid_scopes_are_0_and_1;

	public static String AbstractPreferencesCommand_Only_one_file_can_be_given;

	public static String AbstractPreferencesCommand_Only_one_scope_can_be_given;

	public static String Build_Too_many_arguments;

	public static String Clean_Too_many_arguments;

	public static String CorePlugin_Missing_qualifier_generator_for_id_0;

	public static String CorePlugin_Unable_to_activate_bundle_0;

	public static String ExportPreferences_Unable_to_open_file_0;

	public static String ExternalCommandBuilder_Command_line_0;

	public static String ExternalCommandBuilder_Could_not_resolve_to_a_command_line;

	public static String ExternalCommandBuilder_External_command_0_exited_with_1;

	public static String ExternalCommandBuilder_Launcher_definitions_file_name_not_relative_to_project_root_0;

	public static String ExternalCommandBuilder_Missing_value_for_definition_to_use;

	public static String ExternalCommandBuilder_Unexpected_non_match;

	public static String GetConfiguration_Query_complete;

	public static String GetConfiguration_SSL_handshake_exception;

	public static String GetConfiguration_Too_many_arguments;

	public static String GetConfiguration_Using_workspace_at_0;

	public static String GetConfiguration_Warning;

	public static String GetPreference_Too_many_arguments;

	public static String GetPreference_You_must_provide_a_preference_name;

	public static String Import_Import_complete;

	public static String Import_Missing_BOM_URL;

	public static String Import_Too_many_arguments;

	public static String ImportPreferences_Unable_to_open_file_0;

	public static String ListPreferences_Description;

	public static String ListPreferences_Found;

	public static String ListPreferences_is_the_pattern_0_correct;

	public static String ListPreferences_Key;

	public static String ListPreferences_No_preferences_found;

	public static String ListPreferences_no_value_set;

	public static String ListPreferences_preferences;

	public static String ListPreferences_Too_many_arguments;

	public static String ListPreferences_Value;

	public static String NoSuchActorException_Action_0_refers_to_actor_with_id_1_but_no_such_actor_registered_with_extension_point_2;

	public static String Perform_and;

	public static String Perform_Attribute_names_must_be_in_the_form_component_name_attribute_name;

	public static String Perform_Build_failed;

	public static String Perform_errors;

	public static String Perform_Found;

	public static String Perform_Invalid_URL_or_Path_0;

	public static String Perform_No_attributes_specified;

	public static String Perform_Not_a_key_value_string_0;

	public static String Perform_Too_many_warnings_Exiting_with_error_status;

	public static String Perform_warnings;

	public static String PreferenceMappingManager_and;

	public static String PreferenceMappingManager_No_preference_matches_0;

	public static String PreferenceMappingManager_Preference_0_is_ambiguous;

	public static String PropertiesEmitter_Deleting;

	public static String PropertiesEmitter_Emitting_properties;

	public static String PropertiesEmitter_Generated_by_Buckminster_Do_not_edit;

	public static String Resolve_An_SSL_handshake_exception_occurred_keystore;

	public static String Resolve_Query_complete;

	public static String Resolve_Too_many_arguments;

	public static String RxAssembly_URI_pattern_group_count_was_0_expected_1;

	public static String RxPattern_Unbalanced_parenthesis_in_pattern_0;

	public static String SetPreference__0_is_not_an_assignment;

	public static String SetPreference__0_set_to_the_value_1;

	public static String SetPreference_You_must_provide_at_least_one_preference_assignment;

	public static String TargetPlatform_No_targetPlatformProvider_registered_with_targetPlatformProviders_extension_point;

	public static String UnsetPreference_Too_many_arguments;

	public static String UnsetPreference_Unset_the_value_for_0;

	public static String UnsetPreference_You_must_provide_a_preference_name;
	public static String WorkspaceCommand_Error_while_refreshing_workspace_0;

	public static String WorkspaceCommand_Error_while_saving_workspace_0;

	public static String WorkspaceInitCommand_Only_folders_zip_and_jar_files_can_be_uses_as_workspace_template;

	public static String WorkspaceInitCommand_Only_zip_and_jar_files_allowed_for_remote_workspace_templates;

	public static String WorkspaceInitCommand_Workspace_at_0_is_not_empty;
	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}
}
