/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.p2.bootstrap;

/**
 * @author Karel Brezina
 * 
 */
public interface BootstrapConstants
{
	public static final String APP_LAUNCHED_SYNC_STRING = "sync info: application launched"; //$NON-NLS-1$

	public static final int DEFAULT_MAX_CAPTURED_LINES = 1000;

	public static final int DEFAULT_STARTUP_TIME = 4000;

	public static final int DEFAULT_STARTUP_TIMEOUT = 60000;

	public static final int SPLASH_WINDOW_DELAY = 4000;

	public static final String ERROR_CODE_ARTIFACT_SAX_EXCEPTION = "ArtifactSAXException"; //$NON-NLS-1$

	public static final String ERROR_CODE_CORRUPTED_FILE_EXCEPTION = "CorruptedFileException"; //$NON-NLS-1$

	public static final String ERROR_CODE_DEFAULT_BROWSER_NOT_AVAILABLE_EXCEPTION = "DefaultBrowserNotAvailableException"; //$NON-NLS-1$

	public static final String ERROR_CODE_DIRECTOR_NOT_STARTED_EXCEPTION = "DirectorNotStartedException"; //$NON-NLS-1$

	public static final String ERROR_CODE_DIRECTORY_EXCEPTION = "DirectoryException"; //$NON-NLS-1$

	public static final String ERROR_CODE_DOWNLOAD_EXCEPTION = "DownloadException"; //$NON-NLS-1$

	public static final String ERROR_CODE_FILE_IO_EXCEPTION = "FileIOException"; //$NON-NLS-1$

	public static final String ERROR_CODE_JAVA_HOME_NOT_SET_EXCEPTION = "JavaHomeNotSetException"; //$NON-NLS-1$

	public static final String ERROR_CODE_JAVA_RUNTIME_EXCEPTION = "JavaRuntimeException"; //$NON-NLS-1$

	public static final String ERROR_CODE_JNLP_SAX_EXCEPTION = "JnlpSAXException"; //$NON-NLS-1$

	public static final String ERROR_CODE_LAUNCHER_NOT_FOUND_EXCEPTION = "LauncherNotFoundException"; //$NON-NLS-1$

	public static final String ERROR_CODE_LAUNCHER_NOT_STARTED_EXCEPTION = "LauncherNotStartedException"; //$NON-NLS-1$

	public static final String ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION = "MalformedPropertyException"; //$NON-NLS-1$

	public static final String ERROR_CODE_MATERIALIZATION_EXCEPTION = "MaterializationException"; //$NON-NLS-1$

	public static final String ERROR_CODE_MATERIALIZER_EXECUTION_EXCEPTION = "MaterializerExecutionException"; //$NON-NLS-1$

	public static final String ERROR_CODE_MATERIALIZER_INSTALL_EXCEPTION = "MaterializerInstallException"; //$NON-NLS-1$

	public static final String ERROR_CODE_MISSING_ARGUMENT_EXCEPTION = "MissingArgumentException"; //$NON-NLS-1$

	public static final String ERROR_CODE_MISSING_PROPERTY_EXCEPTION = "MissingPropertyException"; //$NON-NLS-1$

	public static final String ERROR_CODE_PROPERTY_IO_EXCEPTION = "PropertyIOException"; //$NON-NLS-1$

	public static final String ERROR_CODE_REMOTE_IO_EXCEPTION = "RemoteIOException"; //$NON-NLS-1$

	public static final String ERROR_CODE_RESOURCE_EXCEPTION = "ResourceException"; //$NON-NLS-1$

	public static final String ERROR_CODE_RUNTIME_EXCEPTION = "RuntimeException"; //$NON-NLS-1$

	public static final String ERROR_CODE_SITE_ROOT_EXCEPTION = "SiteRootException"; //$NON-NLS-1$

	public static final String ERROR_HELP_URL = "http://www.eclipse.org/buckminster/materialization/troubleshooting.php"; //$NON-NLS-1$

	public static final String INSTALLER_FOLDER_NAME = "installer";

	public static final String PROP_AR_URL = "arURL";

	public static final String PROP_BASE_PATH_URL = "basePathURL"; //$NON-NLS-1$

	public static final String PROP_CONFIG_URL = "configURL";

	public static final String PROP_DIRECTOR_ARCHIVE_URL = "directorArchiveURL";

	public static final String PROP_DIRECTOR_BUILD_PROPERTIES_URL = "directorBuildPropertiesURL";

	public static final String PROP_ERROR_URL = "errorURL"; //$NON-NLS-1$

	public static final String PROP_EXTRA = "extra";

	public static final String PROP_MAX_CAPTURED_LINES = "maxErrorLines"; //$NON-NLS-1$

	public static final String PROP_MR_URL = "mrURL";

	public static final String PROP_ROOT_IU = "rootIU";

	public static final String PROP_SERVICE_AVAILABLE = "serviceAvailable"; //$NON-NLS-1$

	public static final String PROP_SERVICE_MESSAGE = "serviceMessage"; //$NON-NLS-1$

	public static final String PROP_SPLASH_IMAGE = "splashImage"; //$NON-NLS-1$

	public static final String PROP_SPLASH_IMAGE_BOOT = "splashImageBoot"; //$NON-NLS-1$

	public static final String PROP_STARTUP_TIME = "startupTime"; //$NON-NLS-1$

	public static final String PROP_STARTUP_TIMEOUT = "startupTimeout"; //$NON-NLS-1$

	public static final String PROP_WINDOW_ICON = "windowIcon"; //$NON-NLS-1$

	public static final String REPORT_ERROR_PREFIX = "Materializator-"; //$NON-NLS-1$

	public static final String REPORT_ERROR_VIEW = "feedback.seam"; //$NON-NLS-1$
}
