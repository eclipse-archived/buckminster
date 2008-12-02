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

	public static String AbstractComponentType_Component_type_0_defines_desiredNamePattern_but_no_substitution;

	public static String AbstractPreferencesCommand_Illegal_include_0_Must_be_in_the_form;

	public static String AbstractPreferencesCommand_Invalid_scope_Valid_scopes_are_0_and_1;

	public static String AbstractPreferencesCommand_Only_one_file_can_be_given;

	public static String AbstractPreferencesCommand_Only_one_scope_can_be_given;

	public static String AccessibleByteArrayOutputStream_Max_size_0_exceeded;

	public static String AttributeAlreadyDefinedException_Attribute_0_is_defined_more_then_once_in_component_1;

	public static String BMProperties_put_immutable;

	public static String BMProperties_setMutable;

	public static String Build_Too_many_arguments;

	public static String CircularDependencyException_Circular_component_dependency_detected_Chain_is_0;

	public static String CircularReferenceException_Component_0_has_an_internal_circular_attribute_reference_Attribute_chain_is_1;

	public static String Clean_Too_many_arguments;

	public static String ComponentRequestConflictException_Component_request_0_is_in_conflict_with_request_1;

	public static String ComponentTypeMismatchException_Component_type_mismatch_exception_for_component_0_Expected_1_but_actual_2;

	public static String CorePlugin_Missing_qualifier_generator_for_id_0;

	public static String CorePlugin_Unable_to_activate_bundle_0;

	public static String CryptoUtils_Internal_error_0_is_not_supported_encoding;

	public static String CryptoUtils_Unknown_encrypt_algorithm_0;

	public static String CSpec_Referenced_from_Resolution;

	public static String DateAndTimeUtils_Bogus;

	public static String DateAndTimeUtils_Unable_to_parse_0_as_timestamp;

	public static String DependencyAlreadyDefinedException_Dependency_0_is_defined_more_then_once_in_component_1;

	public static String ExportPreferences_Unable_to_open_file_0;

	public static String ExternalCommandBuilder_Command_line_0;

	public static String ExternalCommandBuilder_Could_not_resolve_to_a_command_line;

	public static String ExternalCommandBuilder_External_command_0_exited_with_1;

	public static String ExternalCommandBuilder_Launcher_definitions_file_name_not_relative_to_project_root_0;

	public static String ExternalCommandBuilder_Missing_value_for_definition_to_use;

	public static String ExternalCommandBuilder_Unexpected_non_match;

	public static String FileUtils_Cannot_copy_0_to_1_since_destination_equal_or_contained_in_source;

	public static String FileUtils_Unable_to_create_directory_0;

	public static String FileUtils_Unable_to_create_directory_0_Not_a_directory;

	public static String FileUtils_Unable_to_delete_0;

	public static String FileUtils_Unable_to_use_0_destination_for_copy_not_empty;

	public static String GeneratorAlreadyDefinedException_A_generator_that_generates_0_is_defined_more_then_once_in_component_1;

	public static String GetConfiguration_Query_complete;

	public static String GetConfiguration_SSL_handshake_exception;

	public static String GetConfiguration_Too_many_arguments;

	public static String GetConfiguration_Using_workspace_at_0;

	public static String GetConfiguration_Warning;

	public static String GetPreference_Too_many_arguments;

	public static String GetPreference_You_must_provide_a_preference_name;

	public static String IllegalParameterException_Parameter_0_is_illegal_for_id_1_extension_point_2;

	public static String Import_Import_complete;

	public static String Import_Missing_BOM_URL;

	public static String Import_Too_many_arguments;

	public static String ImportPreferences_Unable_to_open_file_0;

	public static String JobBlocker_blocked_0_1;

	public static String JobBlocker_JOB_AboutToRun_0;

	public static String ListPreferences_Description;

	public static String ListPreferences_Found;

	public static String ListPreferences_is_the_pattern_0_correct;

	public static String ListPreferences_Key;

	public static String ListPreferences_No_preferences_found;

	public static String ListPreferences_no_value_set;

	public static String ListPreferences_preferences;

	public static String ListPreferences_Too_many_arguments;

	public static String ListPreferences_Value;

	public static String MissingAttributeException_CSpec_0_has_no_1_action_group_or_local_artifact_named_2;

	public static String MissingBuilderException_No_Component_Specification_cspec_builder_with_id_0_has_been_registered_with_extension_point_1;

	public static String MissingComponentTypeException_No_component_type_with_id_0_has_been_registered_with_extension_point_1;

	public static String MissingCSpecSourceException_Provider_0_1_Missing_CSpec_source_required_by_component_type_2;

	public static String MissingDependencyException_Component_0_has_no_declared_dependency_to_component_1;

	public static String MissingPathException_CSpec_0_attribute_1_does_not_define_path_2;

	public static String MissingPrerequisiteException_CSpec_0_attribute_1_does_not_define_prerequisite_2;

	public static String MissingPropertyException_CSpec_0_attribute_1_has_no_2_named_3;

	public static String NoSuchActorException_Action_0_refers_to_actor_with_id_1_but_no_such_actor_registered_with_extension_point_2;

	public static String PathAlreadyDefinedException_CSpec_0_attribute_1_already_defines_the_path_2;

	public static String PathGroup_base_cannot_be_null;

	public static String PathGroup_destination_must_be_absolute;

	public static String PathGroup_source_must_be_absolute;

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

	public static String PrerequisiteAlreadyDefinedException_CSpec_0_attribute_1_already_has_a_prerequisite_named_2;

	public static String ProductsHandler__0_is_not_a_valid_UpToDatePolicy;

	public static String PropertiesEmitter_Deleting;

	public static String PropertiesEmitter_Emitting_properties;

	public static String PropertiesEmitter_Generated_by_Buckminster_Do_not_edit;

	public static String PropertyAlreadyDefinedException_CSpec_0_attribute_1_already_has_a_2_named_3;

	public static String PropertyRef_The_property_0_has_not_been_set_and_will_default_to_null;

	public static String ReplaceHandler_pattern_but_no_replacement;

	public static String ReplaceHandler_replacement_but_no_pattern;

	public static String Resolve_An_SSL_handshake_exception_occurred_keystore;

	public static String Resolve_Query_complete;

	public static String Resolve_Too_many_arguments;

	public static String RxAssembly_URI_pattern_group_count_was_0_expected_1;

	public static String RxPattern_Unbalanced_parenthesis_in_pattern_0;

	public static String SetPreference__0_is_not_an_assignment;

	public static String SetPreference__0_set_to_the_value_1;

	public static String SetPreference_You_must_provide_at_least_one_preference_assignment;

	public static String ShortDurationFileCache_File_is_closed;

	public static String SmartArrayList_Bad_value_list_0;

	public static String SmartArrayList_Unbalanced_double_quotes_0;

	public static String TargetPlatform_No_targetPlatformProvider_registered_with_targetPlatformProviders_extension_point;

	public static String TopLevelAttribute_Unable_to_determine_a_unique_product_path_for_0;

	public static String UnsetPreference_Too_many_arguments;

	public static String UnsetPreference_Unset_the_value_for_0;

	public static String UnsetPreference_You_must_provide_a_preference_name;

	public static String UUIDUtil_MD5_not_supported;

	public static String WellknownActions_Unexpected_name_0;

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
