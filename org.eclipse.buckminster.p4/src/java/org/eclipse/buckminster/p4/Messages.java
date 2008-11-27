package org.eclipse.buckminster.p4;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {private static final String BUNDLE_NAME = "org.eclipse.buckminster.p4.messages"; //$NON-NLS-1$
public static String current;public static String defaultLabel;public static String default_can_only_be_used_with_import;public static String export;public static String importLabel;public static String list;
public static String max_2_paths_allowed_for_AltRoots;public static String no_action_was_specified;public static String no_p4_servers_have_been_configured;public static String no_such_P4_server_0;public static String only_one_action_per_invocation_please;public static String overwrite;public static String overwrite_can_only_be_used_with_import;
public static String p4_info_failed;
public static String process_died_with_exit_code_0;public static String remove;
public static String root_cannot_be_null_or_relative;
public static String depot_path_0_is_already_mapped_to_1_in_client_2;
public static String local_path_0_is_not_a_root_or_altroot_of_client_1;
public static String no_such_option_0;
public static String weird_responds_from_p4_where_0;public static String too_many_arguments;public static String unknown_option;
static {
// initialize resource bundle
NLS.initializeMessages(BUNDLE_NAME, Messages.class);
}
private Messages(){
}
}
