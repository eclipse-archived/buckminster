package org.eclipse.buckminster.pde;

import java.util.regex.Pattern;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.cspec.WellKnownExports;
import org.eclipse.buckminster.core.cspec.WellknownActions;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.internal.build.IPDEBuildConstants;

@SuppressWarnings("restriction")
public interface IPDEConstants
{
	static final String ACTOR_COPY_TARGET_FRAGMENTS = "copyTargetFragments";

	static final String ALIAS_BUNDLES = "bundles";

	static final String ALIAS_FEATURES = "features";

	static final String ALIAS_OUTPUT = "action.output";

	static final String ALIAS_REQUIREMENTS = "action.requirements";

	static final String ALIAS_MANIFEST = "manifest";

	static final String ALIAS_PROPERTIES = "properties";

	static final String ACTION_COPY_PLUGINS = "copy.plugins";

	/**
	 * The feature in a format suitable for an update site. No sub-features nor bundles are
	 * included. The base of the attribute
	 * @{link org.eclipse.buckminster.core.cspec.PathGroup PathGroup} will be the folder containing
	 * the feature.
	 */
	static final String ATTRIBUTE_FEATURE_JAR = "feature.jar";

	/**
	 * Denotes group consisting of this feature in jar format and all included features,
	 * also in jar format.
	 */
	static final String ATTRIBUTE_FEATURE_JARS = "feature.jars";

	/**
	 * Denotes group consisting of this feature and all included features and bundles in a format
	 * sutiable for publishing on an update site. The base of the attribute
	 * @{link org.eclipse.buckminster.core.cspec.PathGroup PathGroup} will be folder that contains
	 * the <code>features</code> and <code>plugins</code> folders.
	 */
	static final String ATTRIBUTE_FEATURE_EXPORTS = "feature.exports";

	/**
	 * The attribut that, when triggered, will clean out all built artifacts including the Eclipse
	 * build. This attribute has no resulting product.
	 */
	static final String ATTRIBUTE_FULL_CLEAN = WellknownActions.BUCKMINSTER.CLEAN.toString();

	/**
	 * Denotes a fully deployable bundle in jar format
	 */
	static final String ATTRIBUTE_BUNDLE_JAR = "bundle.jar";

	/**
	 * Denotes a fully deployable bundle in jar format plus fragments from the target platform
	 */
	static final String ATTRIBUTE_BUNDLE_AND_FRAGMENTS = "bundle.and.fragments";

	/**
	 * Denotes a fully deployable bundle in jar format imported into a wrapper using import
	 */
	static final String ATTRIBUTE_IMPORTED_JAR = "imported.jar";

	/**
	 * Denotes a list consisting of a fully deployable bundle in jar format along with all
	 * bundles that it re-exports, also in jar format.
	 */
	static final String ATTRIBUTE_BUNDLE_JARS = "bundle.jars";

	/**
	 * Denotes a bundle in unpacked format
	 */
	static final String ATTRIBUTE_BUNDLE_FOLDER = "bundle.folder";

	/**
	 * Denotes the external classpath for the bundle. This will be the sum of all exported
	 * binaries from the imported bundles plus the exported contribution of this bundle.
	 */
	static final String ATTRIBUTE_JAVA_BINARIES = WellKnownExports.JAVA_BINARIES;

	/**
	 * Denotes the bundle classpath. This will be the sum of all exported
	 * binaries from the imported bundles plus the full contribution of this bundle.
	 */
	static final String ATTRIBUTE_BUNDLE_CLASSPATH = "bundle.classpath";

	static final String ATTRIBUTE_BUILD_PROPERTIES = "build.properties";

	static final String ATTRIBUTE_JAR_CONTENTS = "jar.contents";

	static final String ATTRIBUTE_MANIFEST = "manifest";

	/**
	 * Appoints all files that PDE features wants to copy to the root of a product they are included
	 * in.
	 */
	static final String ATTRIBUTE_PRODUCT_ROOT_FILES = "product.root.files";

	static final String ATTRIBUTE_RAW_MANIFEST = "raw.manifest";

	/**
	 * Appoints an action that will copy the fragments for the current component from the
	 * workspace or target platform
	 */
	static final String ATTRIBUTE_TARGET_FRAGMENTS = "target.fragments";

	static final String BUILD_FILE_ID = "buckminster.pdetasks";

	static final String BUILD_PROPERTIES_FILE = IPDEBuildConstants.PROPERTIES_FILE;

	static final String BUNDLE_FILE = IPDEBuildConstants.MANIFEST_FOLDER + '/'
		+ IPDEBuildConstants.MANIFEST;

	static final String FEATURE_FILE = "feature.xml";

	static final String FEATURE_NATURE = "org.eclipse.pde.FeatureNature";

	static final String FEATURES_FOLDER = IPDEBuildConstants.DEFAULT_FEATURE_LOCATION;

	static final String FRAGMENT_FILE = "fragment.xml";

	static final String HINT_PERMISSIONS = "permissions";

	static final String MANIFEST = IPDEBuildConstants.MANIFEST;

	static final IPath OUTPUT_DIR = new Path(KeyConstants.ACTION_OUTPUT_REF + '/');

	static final String PLUGIN_FILE = "plugin.xml";

	static final String PLUGIN_NATURE = "org.eclipse.pde.PluginNature";

	static final String PLUGINS_FOLDER = IPDEBuildConstants.DEFAULT_PLUGIN_LOCATION;

	static final Pattern PRODUCT_CONFIGURATION_FILE_PATTERN = Pattern.compile("^.*\\.product$");

	static final String PROP_DELETE_DIR = "dir.to.delete";

	static final String PROP_DELETE_FILE = "file.to.delete";

	static final String PROP_DELETE_UILAUNCHER="buckminster.eclipse.deleteuilauncher";

	static final String PROP_PRODUCT_FILE="buckminster.eclipse.productFile";

	static final String SITE_FILE = "site.xml";

	static final String TASK_COPY_GROUP = "copy.group";

	static final String TASK_EXPAND_BUNDLE_VERSION = "expand.bundle.version";

	static final String TASK_EXPAND_FEATURE_VERSION = "expand.feature.version";

	static final String TASK_CREATE_BUNDLE_JAR = "create.bundle.jar";

	static final String TASK_CREATE_FEATURE_JAR = "create.feature.jar";

	static final String TASK_CREATE_JAR = "create.jar";

	static final String TASK_CREATE_ZIP = "create.zip";

	static final String TASK_CREATE_ECLIPSE_PRODUCT = "create.eclipse.product";

	static final String TASK_CREATE_JAR_WM = "create.jar.with.manifest";

	static final String TASK_DELETE_DIR = "delete.dir";

	static final String TASK_DELETE_FILE = "delete.file";

	static final String TASK_DELETE_GROUP = "delete.group";

	static final String TASK_UNJAR_NAMED = "unjar.named";

	static final String TASK_UNZIP = "unzip";

	static final IPath TEMP_DIR = new Path(KeyConstants.ACTION_TEMPDIR_REF + '/');
	
	static final String TOP_FOLDER_SUFFIX = ".topfolder";
}
