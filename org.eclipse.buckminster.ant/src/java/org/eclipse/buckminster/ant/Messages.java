package org.eclipse.buckminster.ant;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.ant.messages"; //$NON-NLS-1$

	public static String AntActor_Extension_found_using_0_1_appoints_non_existing_resource;

	public static String AntActor_No_extension_found_defines_0_1;

	public static String AntActor_Properties_0_and_1_are_mutually_exclusive;

	public static String AntActor_Property_not_set_0;

	public static String AntActor_Unable_to_load_bundle_0;

	public static String AntActor_Unexpected_protocol_0;

	public static String AntBuilder_the_script_file_name_must_be_relative_to_the_project_root_0;

	public static String SignatureCleanerTask_Unable_to_rename_jar_0_to_tmp_1;

	public static String SignatureCleanerTask_Unable_to_rename_tmp_1_to_jar_1;

	public static String TarExpander_unable_to_unzip_into_directory_0;

	public static String VersionQualifierTask_Unable_to_qualify_version;

	public static String VersionQualifierTask_Unable_to_read_properties_from_0;
	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}
}
