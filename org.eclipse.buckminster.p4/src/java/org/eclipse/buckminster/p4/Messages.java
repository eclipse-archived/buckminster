package org.eclipse.buckminster.p4;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {private static final String BUNDLE_NAME = "org.eclipse.buckminster.p4.messages"; //$NON-NLS-1$
public static String client_already_exists;
public static String default_can_only_be_used_with_import;public static String max_2_paths_allowed_for_AltRoots;public static String missing_environment_P4CLIENT;
public static String missing_environment_P4PORT;
public static String name_already_exists;
public static String no_action_was_specified;
public static String no_default_client_exists;public static String no_p4_servers_have_been_configured;public static String no_such_P4_server_0;public static String only_one_action_per_invocation_please;
public static String only_UNC_paths_are_accepted;public static String overwrite_can_only_be_used_with_import;
public static String p4_info_failed;
public static String p4wsad_plugin_is_not_present;
public static String p4wsad_plugin_is_present;
public static String p4wsad_plugin_not_present;
public static String pair_with_depot_path_and_client_path_expected;
public static String process_died_with_exit_code_0;public static String root_cannot_be_null_or_relative;
public static String depot_mapping_already_exists;
public static String depot_path_0_is_already_mapped_to_1_in_client_2;
public static String local_path_0_is_not_a_root_or_altroot_of_client_1;
public static String no_such_option_0;
public static String the_local_path_declared_in_P4_preferences_for_client_0_and_p4_port_1_is_2_it_should_be_3_according_to_the_client_specification_found_on_the_server;
public static String invalid_URI_0_P4_URI_cannot_contain_user_info;
public static String invalid_URI_0_scheme_is_not_p4;
public static String invalid_URL_used_for_P4_provider_0;
public static String missing_ending_quote_view_file_entry_0;
public static String no_P4_server_with_address_0_has_been_configured;
public static String no_preferences_for_P4_client_0_for_server_1;
public static String p4wsad_plugin_cannot_be_loaded_0;
public static String problems_when_sharing_project_to_P4WSAD_0;
public static String server_0_already_exists;
public static String sharing_project_0_to_p4;
public static String unable_to_write_object_of_type_0;
public static String weird_responds_from_p4_where_0;public static String tag_timestamp_and_change_number_are_mutually_exclusive;
public static String the_path_of_a_p4_URL_must_have_at_least_2_segments;
public static String the_path_of_a_p4_URL_must_not_have_a_trailing_separator;
public static String too_many_arguments;public static String unknown_option;
static {
// initialize resource bundle
NLS.initializeMessages(BUNDLE_NAME, Messages.class);
}
private Messages(){
}
}
