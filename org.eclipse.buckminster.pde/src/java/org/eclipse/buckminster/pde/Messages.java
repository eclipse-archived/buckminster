/*****************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.pde.messages"; //$NON-NLS-1$

	public static String not_an_OSGi_manifest;

	public static String unable_to_manifest_from_0;

	public static String manifest_has_malformed_LDAP_rule_for;

	public static String invalid_import_type_0;

	public static String site_is_not_local;

	public static String _0_is_higher;

	public static String fetching_remote_feature_references;

	public static String downloading_0;

	public static String unable_to_load_localized_model_for_0;

	public static String unable_to_load_model_for_0;

	public static String building_feature_list_for_site_0;

	public static String building_plugin_list_for_site_0;

	public static String connection_to_0_failed_on_1_retry_attempt_2_started;

	public static String import_location_0_contains_invalid_feature;

	public static String invalid_url_fore_remote_import_0;

	public static String localizing_0;

	public static String problems_loading_feature;

	public static String unable_to_find_0_in_map_1;

	public static String plugin_requested_from_feature_reader;

	public static String bogus_ref_to_0_in_1_at_2;

	public static String not_designated_by_0;

	public static String placeholder_feature;

	public static String unable_to_obtain_URI_for_0;

	public static String unable_to_obtain_URL_for_0;

	public static String unable_to_parse_feature_0;

	public static String unable_to_parse_feature_from_0;

	public static String unable_to_parse_feature_manifest_file_0;

	public static String unable_to_save_feature_model;

	public static String utf8_not_supported;

	public static String error_while_closing_0;

	public static String unable_to_read_0;

	public static String importing_feature_0;

	public static String project_0_already_exists;

	public static String missing_product_base_in_ctf_actor;

	public static String badly_formatted_version_0_in_PDEmap_1;

	public static String fetch_factory_0_unable_to_parse_1_in_PDEmap_2;

	public static String fetch_type_COPY_not_supported_map_0;

	public static String no_factory_found_for_0_in_PDEmap_1;

	public static String Unable_to_obtain_readertype_for_fetchtype_0_in_PDEmap_1;

	public static String unrecognized_component_type_0_in_PDEmap_1;

	public static String generating_cspec_from_PDE_artifacts;

	public static String PDEMapProvider_0_for_1_unable_to_find_2_in_map;

	public static String Please_use_selfhost_vmargs;

	public static String provider_0_for_1_score_below_treshold;

	public static String copying_imported_source;

	public static String copying_source;

	public static String execution_env_misfit_skipping_plugin_0;

	public static String importing_plugin_0;

	public static String linking_imported_plugin;

	public static String artifact_not_found_0;

	public static String Active_target_0_has_no_containers;

	public static String Active_target_0_is_too_complex;

	public static String Found_no_target_definition_named_0;

	public static String Missing_exe_launcher_for_config_0;

	public static String No_active_target_platform;

	public static String Suggest_install_launchers_feature;

	public static String Unable_to_determine_path_for_active_target_0;

	public static String DataTransfer_importTask;

	public static String ImportOperation_cannotCopy;

	public static String ImportOperation_importProblems;

	public static String ImportOperation_openStreamError;

	public static String ImportOperation_closeStreamError;

	public static String ImportOperation_coreImportError;

	public static String ImportOperation_targetSameAsSourceError;

	public static String resetting_target_platform_0;

	static
	{
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
