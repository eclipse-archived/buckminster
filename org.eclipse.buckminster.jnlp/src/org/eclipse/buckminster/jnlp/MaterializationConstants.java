/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

/**
 * @author Karel Brezina
 * 
 */
public interface MaterializationConstants
{
	public static final String STEP_START = "StartStep"; //$NON-NLS-1$
	
	public static final String STEP_LOGIN = "LoginStep"; //$NON-NLS-1$
	
	public static final String STEP_RESTRICTION = "FolderRestrictionStep"; //$NON-NLS-1$
	
	public static final String STEP_SELECT_DISTRO = "SelectDistroStep"; //$NON-NLS-1$
	
	public static final String STEP_DOWNLOAD_LOCATION = "SimpleDownloadLocationStep"; //$NON-NLS-1$
	
	public static final String STEP_ADVANCED_SETTINGS = "SimpleAdvancedSettingsStep"; //$NON-NLS-1$
	
	public static final String STEP_OPERATION = "OperationStep"; //$NON-NLS-1$
	
	public static final String STEP_DONE = "DoneStep"; //$NON-NLS-1$
	
	public static final String STEP_FEEDS = "InfoStep"; //$NON-NLS-1$
	
	public static final String STEP_PUBLISH_LOGIN = "LoginStep"; //$NON-NLS-1$

	public static final String STEP_PUBLISH = "PublishStep"; //$NON-NLS-1$

	public static final String STEP_TP_INTRO = "TPIntroStep"; //$NON-NLS-1$

	public static final String STEP_TP_NEW_OR_CURRENT = "TPNewOrCurrent"; //$NON-NLS-1$
	
	public static final String STEP_TP_NEW_RECOMMENDED = "TPNewRecommended"; //$NON-NLS-1$

	public static final String STEP_TP_NEW_LOCATION = "TPNewLocation"; //$NON-NLS-1$

	public static final String STEP_TP_BACKUP_FOLDER = "TPNewLocation"; //$NON-NLS-1$
	
	public static final String STEP_TP_UPTODATE = "TPUptodate"; //$NON-NLS-1$
	
	public static final String STEP_TP_TOOL_SELECTION = "TPToolSelection"; //$NON-NLS-1$

	public static final String STEP_TP_OPERATION = "TPOperation"; //$NON-NLS-1$

	public static final String STEP_TP_DONE = "TPDone"; //$NON-NLS-1$

	public static final String PROP_PROPVERSION = "propVersion"; //$NON-NLS-1$

	public static final String PROP_POPUP_DELAY = "popupDelay"; //$NON-NLS-1$

	public static final String PROP_PROFILE_IMAGE_URL = "profileImageURL"; //$NON-NLS-1$

	public static final String PROP_PROFILE_TEXT = "profileText"; //$NON-NLS-1$

	public static final String PROP_ARTIFACT_URL = "artifactURL"; //$NON-NLS-1$

	public static final String PROP_ARTIFACT_NAME = "artifactName"; //$NON-NLS-1$

	public static final String PROP_ARTIFACT_VERSION = "artifactVersion"; //$NON-NLS-1$

	public static final String PROP_ARTIFACT_DESCRIPTION = "artifactDescription"; //$NON-NLS-1$

	public static final String PROP_ARTIFACT_DOCUMENTATION = "artifactDocumentation"; //$NON-NLS-1$

	public static final String PROP_BASE_PATH_URL = "basePathURL"; //$NON-NLS-1$

	public static final String PROP_FOLDER_PATH = "folderPath"; //$NON-NLS-1$

	public static final String PROP_WINDOW_TITLE = "windowTitle"; //$NON-NLS-1$

	public static final String PROP_WINDOW_ICON = "windowIcon"; //$NON-NLS-1$

	public static final String PROP_WIZARD_ICON = "wizardIcon"; //$NON-NLS-1$

	public static final String PROP_MATERIALIZATION_IMAGE = "splashImage"; //$NON-NLS-1$

	public static final String PROP_HELP_URL = "helpURL"; //$NON-NLS-1$

	public static final String PROP_MORE_INFO_URL = "moreInfoURL"; //$NON-NLS-1$

	public static final String PROP_ERROR_URL = "errorURL"; //$NON-NLS-1$

	public static final String PROP_LOGIN_REQUIRED = "loginRequired"; //$NON-NLS-1$

	public static final String PROP_LEARN_MORE_URL = "learnMoreURL"; //$NON-NLS-1$
	
	public static final String PROP_LEARN_MORE_CLOUDFEEDS_URL = "learnMoreCloudfeedsURL"; //$NON-NLS-1$
	
	public static final String PROP_LEARN_MORE_CLOUDREADER_URL = "learnMoreCloudreaderURL"; //$NON-NLS-1$

	public static final String PROP_HOME_PAGE_URL = "homePageURL"; //$NON-NLS-1$

	public static final String PROP_SERVICE_PROVIDER = "serviceProvider"; //$NON-NLS-1$

	public static final String PROP_LOGIN_KEY = "loginKey"; //$NON-NLS-1$
	
	public static final String PROP_DRAFT = "draft"; //$NON-NLS-1$
	
	public static final String PROP_DISTRO_ID = "distroId"; //$NON-NLS-1$
	
	public static final String PROP_CSPEC_ID = "cspecId"; //$NON-NLS-1$
	
	public static final String PROP_CSPEC_NAME = "cspecName"; //$NON-NLS-1$
	
	public static final String PROP_CSPEC_TYPE = "cspecType"; //$NON-NLS-1$
	
	public static final String PROP_CSPEC_VERSION_STRING = "cspecVersionString"; //$NON-NLS-1$
	
	public static final String PROP_CSPEC_VERSION_TYPE = "cspecVersionType"; //$NON-NLS-1$

	public static final String PROP_ECLIPSE_DISTRO_TOOLS_34_UPDATE_SITE_URL = "eclipseDistroTools3.4UpdateSiteURL"; //$NON-NLS-1$

	public static final String PROP_ECLIPSE_DISTRO_TOOLS_33_UPDATE_SITE_URL = "eclipseDistroTools3.3UpdateSiteURL"; //$NON-NLS-1$

	public static final String PROPERTY_DISTRO_NAME = "distro.name"; //$NON-NLS-1$

	public static final String[] MATERIALIZERS = { "filesystem", "workspace" }; //$NON-NLS-1$ //$NON-NLS-2$

	public static final String ARTIFACT_TYPE_MSPEC = "mspec"; //$NON-NLS-1$

	public static final String ARTIFACT_TYPE_UNKNOWN = "unknown"; //$NON-NLS-1$

	public static final String ARTIFACT_UNKNOWN_TEXT = Messages.error_reading_info;

	public static final String WINDOW_TITLE_UNKNOWN = Messages.materialization_wizard;

	public static final String READER_TYPE_CSSITE = "cssite"; //$NON-NLS-1$
	
	public static final String ERROR_WINDOW_TITLE = Messages.materialization_error;

	public static final String ERROR_HELP_TITLE = Messages.troubleshooting_your_materialization;

	public static final String ERROR_HELP_URL = "http://www.eclipse.org/buckminster/materialization/troubleshooting.php"; //$NON-NLS-1$

	public static final String ERROR_CODE_MISSING_ARGUMENT_EXCEPTION = "MissingArgumentException"; //$NON-NLS-1$

	public static final String ERROR_CODE_REMOTE_IO_EXCEPTION = "RemoteIOException"; //$NON-NLS-1$

	public static final String ERROR_CODE_MISSING_PROPERTY_EXCEPTION = "MissingPropertyException"; //$NON-NLS-1$

	public static final String ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION = "MalformedPropertyException"; //$NON-NLS-1$

	public static final String ERROR_CODE_ARTIFACT_EXCEPTION = "ArtifactSAXException"; //$NON-NLS-1$

	public static final String ERROR_CODE_MATERIALIZATION_EXCEPTION = "MaterializationException"; //$NON-NLS-1$

	public static final String ERROR_CODE_RUNTIME_EXCEPTION = "RuntimeException"; //$NON-NLS-1$
	
	public static final String ERROR_CODE_BOM_IO_EXCEPTION = "BOMIOException"; //$NON-NLS-1$
	
	public static final String ERROR_CODE_FILE_IO_EXCEPTION = "FileIOException"; //$NON-NLS-1$
	
	public static final String ERROR_CODE_NO_AUTHENTICATOR_EXCEPTION = "NoAuthenticatorException"; //$NON-NLS-1$
	
	public static final String ERROR_CODE_AUTHENTICATOR_EXCEPTION = "AuthenticatorException"; //$NON-NLS-1$
	
	public static final String ERROR_CODE_NO_PUBLISHER_EXCEPTION = "NoPublisherException"; //$NON-NLS-1$
	
	public static final String ERROR_CODE_PUBLISHER_EXCEPTION = "PublisherException"; //$NON-NLS-1$
	
	public static final String ERROR_CODE_PUBLISHING_EXCEPTION = "PublishingException"; //$NON-NLS-1$
	
	public static final String ERROR_CODE_ALREADY_RUNNING_EXCEPTION = "AlreadyRunningException"; //$NON-NLS-1$
	
	public static final String ERROR_CODE_403_EXCEPTION = "403Exception"; //$NON-NLS-1$
	
	public static final String ERROR_CODE_404_EXCEPTION = "404Exception"; //$NON-NLS-1$
	
	public static final String ERROR_CODE_500_EXCEPTION = "500Exception"; //$NON-NLS-1$
	
	public static final String DEFAULT_MATERIALIZATION_FOLDER = "materializations"; //$NON-NLS-1$
	
	public static final String META_AREA = ".metadata"; //$NON-NLS-1$
	
	public static final String MATERIALIZATOR_PROPERTIES = "materializator.properties"; //$NON-NLS-1$
	
	public static final String LOCALPROP_ENABLE_TP_WIZARD = "enable.tp.wizard"; //$NON-NLS-1$
	
	public static final String VALUE_TRUE = "true"; //$NON-NLS-1$
	
	public static final String VALUE_FALSE = "false"; //$NON-NLS-1$
}
