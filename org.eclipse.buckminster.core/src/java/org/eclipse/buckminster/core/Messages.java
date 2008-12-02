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

	public static String AbstractCatalogReader_Only_folders_zip_and_jar_archives_allowed;

	public static String AbstractCatalogReader_Only_zip_and_jar_archives_allowed_for_remote_overlays;

	public static String AbstractCatalogReader_Provider_0_1_getContents_will_use_overlay_2_for_file_3;

	public static String AbstractCatalogReader_Provider_0_1_materializing_to_2;

	public static String AbstractComponentType_Component_type_0_defines_desiredNamePattern_but_no_substitution;

	public static String AbstractMaterializer_Unable_to_determine_users_home_directory;

	public static String AbstractParser_the_namespace_and_schemaLocation_arrays_must_be_equal_in_length;

	public static String AbstractParser_Unable_to_create_extension_handler_0_1;

	public static String AbstractParser_Unable_to_find_XMLSchema_for_namespace_0;

	public static String AbstractParser_Unknown_namespace_prefix_0;

	public static String AbstractPreferencesCommand_Illegal_include_0_Must_be_in_the_form;

	public static String AbstractPreferencesCommand_Invalid_scope_Valid_scopes_are_0_and_1;

	public static String AbstractPreferencesCommand_Only_one_file_can_be_given;

	public static String AbstractPreferencesCommand_Only_one_scope_can_be_given;

	public static String AbstractReaderType_ReaderType_0_cannot_handle_fetchFactory_data;

	public static String AbstractSCCSVersionFinder__0_is_a_better_match;

	public static String AbstractSCCSVersionFinder_no_component_was_found;

	public static String AbstractSCCSVersionFinder_not_designated_by_0;

	public static String AbstractSCCSVersionFinder_not_in_path_0;

	public static String AbstractSCCSVersionFinder_too_high;

	public static String AbstractSCCSVersionFinder_too_young;

	public static String AbstractSCCSVersionFinder_versionSelector_cannot_make_sense_of_it;

	public static String AbstractSiteMaterializer_End_mirroring;

	public static String AbstractSiteMaterializer_Start_mirroring;

	public static String AbstractSiteMaterializer_Unable_to_install_plugins_from_update_site_PDE_missing;

	public static String AbstractTripletVersion_Not_a_Triplet;

	public static String AccessibleByteArrayOutputStream_Max_size_0_exceeded;

	public static String AdvisorNodeHandler_Incorrect_number_of_resolution_priorites;

	public static String AmbigousComponentException_More_then_one_version_of_component_0_known_to_Buckminster;

	public static String AttributeAlreadyDefinedException_Attribute_0_is_defined_more_then_once_in_component_1;

	public static String BillOfMaterials_Component_query_cannot_be_null;

	public static String BillOfMaterials_Top_node_cannot_be_null;

	public static String BillOfMaterialsHandler_id_0_appoints_non_existing_wrapper;

	public static String BillOfMaterialsHandler_wrapper_0_does_not_wrap_query;

	public static String BMProperties_put_immutable;

	public static String BMProperties_setMutable;

	public static String Build_Too_many_arguments;

	public static String CircularDependencyException_Circular_component_dependency_detected_Chain_is_0;

	public static String CircularReferenceException_Component_0_has_an_internal_circular_attribute_reference_Attribute_chain_is_1;

	public static String Clean_Too_many_arguments;

	public static String ComponentQuery_Query_for_0;

	public static String ComponentQuery_Unable_to_read_property_file_0_1;

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

	public static String DestinationChangeException_attempt_to_change_fixed_materialization_location_0_to_1;

	public static String EclipseBuildActor_Invalid_kind_0;

	public static String EclipsePreferencesReader_Loading_preferences;

	public static String ElementNotFoundException_No_element_with_id_0_was_found_in_storage_1;

	public static String ExportPreferences_Unable_to_open_file_0;

	public static String ExtensionAwareHandler_Use_of_deprecated_attribute_0_1_Use_attribute_2_instead_3_line_4;

	public static String ExtensionAwareHandler_Use_of_deprecated_attribute_0_1_was_ignored_Use_attribute_2_instead_3_line_4;

	public static String ExtensionAwareHandler_Use_of_deprecated_element_0_Use_element_1_instead_2_line_3;

	public static String ExtensionAwareHandler_Use_of_deprecated_element_0_was_ignored_1_line_2;

	public static String ExtensionAwareHandler_Use_of_deprecated_value_for_attribute_0_1_Was_2_should_be_3_4_line_5;

	public static String ExternalCommandBuilder_Command_line_0;

	public static String ExternalCommandBuilder_Could_not_resolve_to_a_command_line;

	public static String ExternalCommandBuilder_External_command_0_exited_with_1;

	public static String ExternalCommandBuilder_Launcher_definitions_file_name_not_relative_to_project_root_0;

	public static String ExternalCommandBuilder_Missing_value_for_definition_to_use;

	public static String ExternalCommandBuilder_Unexpected_non_match;

	public static String FileFolderMismatchException_Unable_to_reuse_location_0_for_component_1;

	public static String FileStorage_No_such_foreign_key_0;

	public static String FileStorage_Unable_to_read_0;

	public static String FileSystemMaterializer_Preparing_type_0;

	public static String FileSystemMaterializer_Skipping_materialization_of_0_Instead_reusing_1;

	public static String FileUtils_Cannot_copy_0_to_1_since_destination_equal_or_contained_in_source;

	public static String FileUtils_Unable_to_create_directory_0;

	public static String FileUtils_Unable_to_create_directory_0_Not_a_directory;

	public static String FileUtils_Unable_to_delete_0;

	public static String FileUtils_Unable_to_use_0_destination_for_copy_not_empty;

	public static String GeneratorAlreadyDefinedException_A_generator_that_generates_0_is_defined_more_then_once_in_component_1;

	public static String GeneratorNodeHandler_wrapper_0_does_not_wrap_cspec;

	public static String GetConfiguration_Query_complete;

	public static String GetConfiguration_SSL_handshake_exception;

	public static String GetConfiguration_Too_many_arguments;

	public static String GetConfiguration_Using_workspace_at_0;

	public static String GetConfiguration_Warning;

	public static String GetPreference_Too_many_arguments;

	public static String GetPreference_You_must_provide_a_preference_name;

	public static String GlobalContext_Only_absolute_paths_can_be_scheduled_for_removal;

	public static String IllegalParameterException_Parameter_0_is_illegal_for_id_1_extension_point_2;

	public static String Import_Import_complete;

	public static String Import_Missing_BOM_URL;

	public static String Import_Too_many_arguments;

	public static String ImportPreferences_Unable_to_open_file_0;

	public static String InstallerJob_Installing;

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

	public static String LocalReader_local_reader_cannot_materialize;

	public static String LocalReaderType_Resolution_not_created_using_LocalReader;

	public static String MainResolver_Unable_to_resolve;

	public static String MalformedProviderURIException_A_reader_of_type_0_cannot_use_the_uri_1;

	public static String MaterializationContext_Unable_to_determine_suffix_for_unpack_of_0;

	public static String MaterializationContext_WorkspaceLocation_0_in_node_matching_1_cannot_be_relative_unless;

	public static String MaterializationDirectiveHandler_Invalid_value_for_attribute_0;

	public static String MaterializationJob_Materializing;

	public static String MaterializerJob_Materialization_of_0;

	public static String MaxParallelMaterializations__0_illegal_value_for_maxParallelMaterialisations;

	public static String MaxParallelResolutions__0_illegal_value_for_maxParallelResolutions;

	public static String MetadataSynchronizer_Buckminster_workspace_catch_up;

	public static String MetadataSynchronizer_Metadata_refresh;

	public static String MetadataSynchronizer_Problem_during_meta_data_refresh_0;

	public static String MetadataSynchronizer_Project_0_now_has_dynamic_dependencies_to;

	public static String MetadataSynchronizer_Project_0_references_closed_project_1;

	public static String MetadataSynchronizer_Project_refresh_on_0_failed_1;

	public static String MetadataSynchronizer_Refreshing_0;

	public static String MetadataSynchronizer_Refreshing_project_meta_data;

	public static String MissingAttributeException_CSpec_0_has_no_1_action_group_or_local_artifact_named_2;

	public static String MissingBuilderException_No_Component_Specification_cspec_builder_with_id_0_has_been_registered_with_extension_point_1;

	public static String MissingComponentException_No_component_0_known_to_Buckminster;

	public static String MissingComponentTypeException_No_component_type_with_id_0_has_been_registered_with_extension_point_1;

	public static String MissingCSpecSourceException_Provider_0_1_Missing_CSpec_source_required_by_component_type_2;

	public static String MissingDependencyException_Component_0_has_no_declared_dependency_to_component_1;

	public static String MissingMaterializerException_No_materializer_with_id_0_has_been_registered_with_extension_point_1;

	public static String MissingPathException_CSpec_0_attribute_1_does_not_define_path_2;

	public static String MissingPrerequisiteException_CSpec_0_attribute_1_does_not_define_prerequisite_2;

	public static String MissingPropertyException_CSpec_0_attribute_1_has_no_2_named_3;

	public static String MissingReaderTypeException_No_reader_type_with_id_0_has_been_registered_with_extension_point_1;

	public static String MissingVersionConverterException_No_version_converter_with_id_0_has_been_registered_with_extension_point_1;

	public static String MissingVersionTypeException_No_version_type_with_id_0_has_been_registered_with_extension_point_1;

	public static String NamespaceDeprecationFilter_XML_namespace_0_deprecated_Use_1_instead_2;

	public static String NodeQuery_Not_designated_by_0;

	public static String NodeQuery_not_in_path_0;

	public static String NodeQuery_ResolutionContext_requested_during_Materialization;

	public static String NodeQuery_Unable_to_obtain_component_type;

	public static String NoSuchActorException_Action_0_refers_to_actor_with_id_1_but_no_such_actor_registered_with_extension_point_2;

	public static String NoSuitableProviderException_No_suitable_provider_for_component_0_was_found_in_searchPath_1;

	public static String ParserFactory_Unable_to_load_parser_extensions;

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

	public static String PerformContext_Action_prerequisite_base_can_not_be_absolute;

	public static String PerformManager_Actions_to_perform_in_order;

	public static String PreferenceMappingManager_and;

	public static String PreferenceMappingManager_No_preference_matches_0;

	public static String PreferenceMappingManager_Preference_0_is_ambiguous;

	public static String PrerequisiteAlreadyDefinedException_CSpec_0_attribute_1_already_has_a_prerequisite_named_2;

	public static String ProductsHandler__0_is_not_a_valid_UpToDatePolicy;

	public static String ProjectDescReader_Loading_project_description;

	public static String ProjectNameMismatchException_ProjectBinding_name_conflict_information_indicates_0_for_project_named_1;

	public static String PropertiesEmitter_Deleting;

	public static String PropertiesEmitter_Emitting_properties;

	public static String PropertiesEmitter_Generated_by_Buckminster_Do_not_edit;

	public static String PropertyAlreadyDefinedException_CSpec_0_attribute_1_already_has_a_2_named_3;

	public static String PropertyRef_The_property_0_has_not_been_set_and_will_default_to_null;

	public static String Provider_Components_of_type_0_are_not_supported;

	public static String Provider_No_component_match_was_found;

	public static String Provider_Referenced_from_Resolution;

	public static String Provider_Score_is_below_threshold;

	public static String ReferentialIntegrityException_Unable_to_0_the_1_with_id_2_3;

	public static String ReplaceHandler_pattern_but_no_replacement;

	public static String ReplaceHandler_replacement_but_no_pattern;

	public static String Resolution_Name;

	public static String ResolutionHandler_Missing_required_element_0;

	public static String ResolutionHandler_Unable_to_parse_legacy_version_selector_string_0;

	public static String Resolve_An_SSL_handshake_exception_occurred_keystore;

	public static String Resolve_Query_complete;

	public static String Resolve_Too_many_arguments;

	public static String ResolvedNodeHandler_wrapper_0_does_not_wrap_resolution;

	public static String ResolverDecisionType_Branch_0_rejected_1;

	public static String ResolverDecisionType_branches_will_be_searched;

	public static String ResolverDecisionType_Filter_0_does_not_match_the_current_property_set;

	public static String ResolverDecisionType_Found_match_0;

	public static String ResolverDecisionType_Match_0_was_rejected_1;

	public static String ResolverDecisionType_No_branches_were_found;

	public static String ResolverDecisionType_No_provider_was_found_that_could_resolve_the_request;

	public static String ResolverDecisionType_No_searchPath_was_found_with_a_matching_pattern;

	public static String ResolverDecisionType_No_tags_were_found;

	public static String ResolverDecisionType_Redirecting_to_resource_map_0;

	public static String ResolverDecisionType_Rejecting_provider_0_1_2;

	public static String ResolverDecisionType_Resolution_attempt_ended_with_exception_0;

	public static String ResolverDecisionType_Revision_0_rejected_1;

	public static String ResolverDecisionType_Space_0_rejected_1;

	public static String ResolverDecisionType_Tag_0_rejected_1;

	public static String ResolverDecisionType_Timestamp_0_rejected_1;

	public static String ResolverDecisionType_Trunk_Head_rejected_0;

	public static String ResolverDecisionType_Trying_provider_0_1;

	public static String ResolverDecisionType_tags_will_be_searched;

	public static String ResolverDecisionType_trunk_head_will_be_searched;

	public static String ResolverDecisionType_Using_provider_0_1;

	public static String ResolverDecisionType_Using_resolver_0;

	public static String ResolverDecisionType_Using_resource_map_0;

	public static String ResolverDecisionType_Using_search_path_0;

	public static String ResolverDecisionType_Using_version_converter_0_trunk_head_not_considered;

	public static String ResolverDecisionType_Version_0_rejected_1;

	public static String ResolverDecisionType_Version_0_was_converted_from_branch_1;

	public static String ResolverDecisionType_Version_0_was_converted_from_tag_1;

	public static String ResolverDecisionType_VersionSelector_for_0_discriminates_all_1;

	public static String ResolverDecisionType_Wrong_component_type_0;

	public static String ResolverFactoryMaintainer_Unable_to_instantiate_Query_Resolver_Factory_0;

	public static String ResourceMap_No_suitable_provider_for_component_0_was_found_in_searchPath_1;

	public static String ResourceMap_not_designated_by_0;

	public static String ResourceMapResolver_Building_workspace;

	public static String ResourceMapResolver_Periodic_workspace_save;

	public static String ResourceMapResolverFactory_Maximum_number_of_resolver_threads;

	public static String ResourceMapResolverFactory_Override_URL_in_Component_Query;

	public static String ResourceMapResolverFactory_Perform_local_resolution;

	public static String ResourceMapResolverFactory_Resource_map_URL;

	public static String RxAssembly_URI_pattern_group_count_was_0_expected_1;

	public static String RxPattern_Unbalanced_parenthesis_in_pattern_0;

	public static String SearchPath__0_1_is_producing_a_better_match;

	public static String SearchPathNotFoundException_Unable_to_find_a_searchPath_for_0;

	public static String SetPreference__0_is_not_an_assignment;

	public static String SetPreference__0_set_to_the_value_1;

	public static String SetPreference_You_must_provide_at_least_one_preference_assignment;

	public static String ShortDurationFileCache_File_is_closed;

	public static String SimulationActor_finished_working;

	public static String SimulationActor_Simulation_0_working_for_1_ticks;

	public static String SiteFeatureReader_A_SiteFeatureReader_is_not_capable_of_materializing;

	public static String SiteFeatureReaderType_Site_reader_can_only_be_used_with_site_feature;

	public static String SiteFeatureReaderType_Unable_to_obtain_site_from_0;

	public static String SiteFeatureResolutionBuilder__0_resolution_builder_can_only_work_with_a_site_feature_reader;

	public static String SiteReader_Loading_site_definition;

	public static String SmartArrayList_Bad_value_list_0;

	public static String SmartArrayList_Unbalanced_double_quotes_0;

	public static String StringVersion_Not_a_StringVersion;

	public static String StringVersionType_Not_a_valid_String_version;

	public static String TargetPlatform_No_targetPlatformProvider_registered_with_targetPlatformProviders_extension_point;

	public static String TargetPlatformMaterializer_Could_not_find_a_site_to_install_to;

	public static String TargetPlatformMaterializer_No_target_platform_found_at_0;

	public static String TimestampVersion_Not_a_TimestampVersion;

	public static String TimestampVersionType_Not_a_valid_Timestamp_version;

	public static String TopLevelAttribute_Unable_to_determine_a_unique_product_path_for_0;

	public static String TransformerMismatchException_The_substitution_0_1_is_not_reversed_by_2_3;

	public static String TripletVersionType_Not_a_valid_0_version;

	public static String UnresolvedNodeException_Attempt_to_use_an_unresolved_node_Request_is_0;

	public static String UnsetPreference_Too_many_arguments;

	public static String UnsetPreference_Unset_the_value_for_0;

	public static String UnsetPreference_You_must_provide_a_preference_name;

	public static String URLCatalogReader_Only_file_protocol_is_supported_at_this_time;

	public static String URLFileReader_Copying_from_0;

	public static String UUIDUtil_MD5_not_supported;

	public static String VersionDesignator_expected_comma;

	public static String VersionDesignator_expected_squared_or_round_brackets;

	public static String VersionDesignator_Negative_version_range;

	public static String VersionDesignator_Version_string_cannot_be_empty;

	public static String VersionDesignator_Version_string_cannot_be_null;

	public static String VersionSelector_A_branch_tag_qualifier_cannot_be_empty;

	public static String VersionSelector_Branch;

	public static String VersionSelector_Tag;

	public static String VersionSelector_The_0_character_is_illegal_in_branch_tag_qualifier;

	public static String VersionSelector_The_slash_character_only_legal_at_first_position_of_branch_tag_qualifier;

	public static String VersionSyntaxException_Syntax_error_in_version_string_0_at_position_1_2;

	public static String WellknownActions_Unexpected_name_0;

	public static String WorkspaceBindingInstallJob_workspace_binding_installer;

	public static String WorkspaceCommand_Error_while_refreshing_workspace_0;

	public static String WorkspaceCommand_Error_while_saving_workspace_0;

	public static String WorkspaceInfo_Found_two_entries_for_component_0_Version_1_located_at_2_and_version_3_at_4;

	public static String WorkspaceInfo_Problems_during_metadata_refresh;

	public static String WorkspaceInfo_Refreshing_meta_data;

	public static String WorkspaceInitCommand_Only_folders_zip_and_jar_files_can_be_uses_as_workspace_template;

	public static String WorkspaceInitCommand_Only_zip_and_jar_files_allowed_for_remote_workspace_templates;

	public static String WorkspaceInitCommand_Workspace_at_0_is_not_empty;

	public static String WorkspaceMaterializer_Binding_0;

	public static String WorkspaceMaterializer_Unable_to_create_file_link_from_workspace_0_to_1_link_origin_2_already_in_use;

	public static String WorkspaceMaterializer_Unable_to_create_folder_link_from_workspace_0_to_1_2_already_in_use;

	public static String WorkspaceMaterializer_Unable_to_determine_project_root_when_binding_0_to_workspace_1;

	public static String WorkspaceMaterializer_Unable_to_obtain_resource_0_from_workspace_1;

	public static String ZipArchiveReader_ZipArchiveReader_cannot_materialize;
	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}
}
