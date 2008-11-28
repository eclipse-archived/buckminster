package org.eclipse.buckminster.p4.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.p4.ui.messages"; //$NON-NLS-1$

	public static String _does_not_exist;

	public static String add_client_depot_mapping;

	public static String add_P4_client;

	public static String add_P4_server;

	public static String browse_with_dots;

	public static String change_P4_port;

	public static String client_name;

	public static String depot_mapping_name;

	public static String depot_mappings;

	public static String depot_pattern_with_colon;

	public static String error_reading_preferences;

	public static String export_with_dots;

	public static String import_with_dots;

	public static String known_P4_ports;

	public static String local_replacement_with_colon;

	public static String local_root_with_colon;

	public static String name;

	public static String name_is_invalid;

	public static String newLabel;

	public static String ok_to_replace_server;

	public static String P4_client_root;

	public static String P4_clients;

	public static String P4_port;

	public static String P4_port_with_colon;

	public static String password;

	public static String passwords_dont_match;

	public static String remove;

	public static String rename_client_depot_mapping;

	public static String rename;

	public static String rename_P4_client;

	public static String retype_password;

	public static String this_is_the_default_client;

	public static String this_is_the_default_server;

	public static String a_regular_expression_used_when_creating_a_client_root_relative_path_that_will_be_used_for_component_materialization_based_on_the_full_depot_path_of_a_component;

	public static String the_replacement_string_used_when_creating_the_client_root_relative_path_that_will_be_used_for_component_materialization_based_on_the_full_depot_path_of_a_component;

	public static String unable_to_save_file_0;

	public static String user;
	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}
}
