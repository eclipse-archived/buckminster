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
	static final String ACTOR_COPY_TARGET_FRAGMENTS = "copyTargetFragments"; //$NON-NLS-1$

	static final String ALIAS_BUNDLES = "bundles"; //$NON-NLS-1$

	static final String ALIAS_FEATURES = "features"; //$NON-NLS-1$

	static final String ALIAS_OUTPUT = "action.output"; //$NON-NLS-1$

	static final String ALIAS_REQUIREMENTS = "action.requirements"; //$NON-NLS-1$

	static final String ALIAS_MANIFEST = "manifest"; //$NON-NLS-1$

	static final String ALIAS_PROPERTIES = "properties"; //$NON-NLS-1$

	static final String ACTION_COPY_PLUGINS = "copy.plugins"; //$NON-NLS-1$

	/**
	 * The feature in a format suitable for an update site. No sub-features nor bundles are included. The base of the
	 * attribute
	 * 
	 * @{link org.eclipse.buckminster.core.cspec.PathGroup PathGroup} will be the folder containing the feature.
	 */
	static final String ATTRIBUTE_FEATURE_JAR = "feature.jar"; //$NON-NLS-1$

	/**
	 * Denotes group consisting of this feature in jar format and all included features, also in jar format.
	 */
	static final String ATTRIBUTE_FEATURE_JARS = "feature.jars"; //$NON-NLS-1$

	/**
	 * Denotes group consisting of this feature and all included features and bundles in a format sutiable for
	 * publishing on an update site. The base of the attribute
	 * 
	 * @{link org.eclipse.buckminster.core.cspec.PathGroup PathGroup} will be folder that contains the
	 *        <code>features</code> and <code>plugins</code> folders.
	 */
	static final String ATTRIBUTE_FEATURE_EXPORTS = "feature.exports"; //$NON-NLS-1$

	/**
	 * The attribut that, when triggered, will clean out all built artifacts including the Eclipse build. This attribute
	 * has no resulting product.
	 */
	static final String ATTRIBUTE_FULL_CLEAN = WellknownActions.BUCKMINSTER.CLEAN.toString();

	/**
	 * Denotes a fully deployable bundle in jar format
	 */
	static final String ATTRIBUTE_BUNDLE_JAR = "bundle.jar"; //$NON-NLS-1$

	static final String ATTRIBUTE_SOURCE_BUNDLE_JAR = "source.bundle.jar"; //$NON-NLS-1$

	/**
	 * Denotes a fully deployable bundle in jar format plus fragments from the target platform
	 */
	static final String ATTRIBUTE_BUNDLE_AND_FRAGMENTS = "bundle.and.fragments"; //$NON-NLS-1$

	/**
	 * Denotes a fully deployable bundle in jar format imported into a wrapper using import
	 */
	static final String ATTRIBUTE_IMPORTED_JAR = "imported.jar"; //$NON-NLS-1$

	/**
	 * Denotes a list consisting of a fully deployable bundle in jar format along with all bundles that it re-exports,
	 * also in jar format.
	 */
	static final String ATTRIBUTE_BUNDLE_JARS = "bundle.jars"; //$NON-NLS-1$

	/**
	 * Denotes a bundle in unpacked format
	 */
	static final String ATTRIBUTE_BUNDLE_FOLDER = "bundle.folder"; //$NON-NLS-1$

	/**
	 * Denotes the external classpath for the bundle. This will be the sum of all exported binaries from the imported
	 * bundles plus the exported contribution of this bundle.
	 */
	static final String ATTRIBUTE_JAVA_BINARIES = WellKnownExports.JAVA_BINARIES;

	/**
	 * Denotes the bundle classpath. This will be the sum of all exported binaries from the imported bundles plus the
	 * full contribution of this bundle.
	 */
	static final String ATTRIBUTE_BUNDLE_CLASSPATH = "bundle.classpath"; //$NON-NLS-1$

	static final String ATTRIBUTE_BUILD_PROPERTIES = "build.properties"; //$NON-NLS-1$

	static final String ATTRIBUTE_JAR_CONTENTS = "jar.contents"; //$NON-NLS-1$

	static final String ATTRIBUTE_MANIFEST = "manifest"; //$NON-NLS-1$

	static final String ATTRIBUTE_SOURCE_MANIFEST = "source.manifest"; //$NON-NLS-1$

	/**
	 * Appoints all files that PDE features wants to copy to the root of a product they are included in.
	 */
	static final String ATTRIBUTE_PRODUCT_ROOT_FILES = "product.root.files"; //$NON-NLS-1$

	static final String ATTRIBUTE_RAW_MANIFEST = "raw.manifest"; //$NON-NLS-1$

	/**
	 * Appoints an action that will copy the fragments for the current component from the workspace or target platform
	 */
	static final String ATTRIBUTE_TARGET_FRAGMENTS = "target.fragments"; //$NON-NLS-1$

	static final String BUILD_FILE_ID = "buckminster.pdetasks"; //$NON-NLS-1$

	static final String BUILD_PROPERTIES_FILE = IPDEBuildConstants.PROPERTIES_FILE;

	static final String BUNDLE_FILE = IPDEBuildConstants.MANIFEST_FOLDER + '/' + IPDEBuildConstants.MANIFEST;

	static final String FEATURE_FILE = "feature.xml"; //$NON-NLS-1$

	static final String FEATURE_PROPERTIES_FILE = "feature.properties"; //$NON-NLS-1$

	static final String FEATURE_NATURE = "org.eclipse.pde.FeatureNature"; //$NON-NLS-1$

	static final String FEATURES_FOLDER = IPDEBuildConstants.DEFAULT_FEATURE_LOCATION;

	static final String FRAGMENT_FILE = "fragment.xml"; //$NON-NLS-1$

	static final String HINT_PERMISSIONS = "permissions"; //$NON-NLS-1$

	static final String MANIFEST = IPDEBuildConstants.MANIFEST;

	static final String SOURCE_MANIFEST = "SOURCE_" + IPDEBuildConstants.MANIFEST; //$NON-NLS-1$

	static final IPath OUTPUT_DIR = new Path(KeyConstants.ACTION_OUTPUT_REF + '/');

	static final String PLUGIN_FILE = "plugin.xml"; //$NON-NLS-1$

	static final String PLUGIN_PROPERTIES_FILE = "plugin.properties"; //$NON-NLS-1$

	static final String PLUGIN_NATURE = "org.eclipse.pde.PluginNature"; //$NON-NLS-1$

	static final String PLUGINS_FOLDER = IPDEBuildConstants.DEFAULT_PLUGIN_LOCATION;

	static final Pattern PRODUCT_CONFIGURATION_FILE_PATTERN = Pattern.compile("^.*\\.product$"); //$NON-NLS-1$

	static final String PROP_DELETE_DIR = "dir.to.delete"; //$NON-NLS-1$

	static final String PROP_DELETE_FILE = "file.to.delete"; //$NON-NLS-1$

	static final String PROP_DELETE_UILAUNCHER = "buckminster.eclipse.deleteuilauncher"; //$NON-NLS-1$

	static final String PROP_PRODUCT_FILE = "buckminster.eclipse.productFile"; //$NON-NLS-1$

	static final String SITE_FILE = "site.xml"; //$NON-NLS-1$

	static final String TASK_COPY_GROUP = "copy.group"; //$NON-NLS-1$

	static final String TASK_EXPAND_BUNDLE_VERSION = "expand.bundle.version"; //$NON-NLS-1$

	static final String TASK_EXPAND_FEATURE_VERSION = "expand.feature.version"; //$NON-NLS-1$

	static final String TASK_CREATE_BUNDLE_JAR = "create.bundle.jar"; //$NON-NLS-1$

	static final String TASK_CREATE_FEATURE_JAR = "create.feature.jar"; //$NON-NLS-1$

	static final String TASK_CREATE_JAR = "create.jar"; //$NON-NLS-1$

	static final String TASK_RECREATE_JAR = "recreate.jar"; //$NON-NLS-1$

	static final String TASK_CREATE_ZIP = "create.zip"; //$NON-NLS-1$

	static final String TASK_CREATE_ECLIPSE_PRODUCT = "create.eclipse.product"; //$NON-NLS-1$

	static final String TASK_CREATE_JAR_WM = "create.jar.with.manifest"; //$NON-NLS-1$

	static final String TASK_DELETE_DIR = "delete.dir"; //$NON-NLS-1$

	static final String TASK_DELETE_FILE = "delete.file"; //$NON-NLS-1$

	static final String TASK_DELETE_GROUP = "delete.group"; //$NON-NLS-1$

	static final String TASK_UNJAR_NAMED = "unjar.named"; //$NON-NLS-1$

	static final String TASK_UNZIP = "unzip"; //$NON-NLS-1$

	static final String TASK_CREATE_SOURCE_MANIFEST = "create.source.manifest"; //$NON-NLS-1$

	static final IPath TEMP_DIR = new Path(KeyConstants.ACTION_TEMPDIR_REF + '/');

	static final String TOP_FOLDER_SUFFIX = ".topfolder"; //$NON-NLS-1$

	static final String MANIFEST_HEADER_SOURCE_BUNDLE = "Eclipse-SourceBundle"; //$NON-NLS-1$

	public static final String FEATURE_GROUP = ".feature.group"; //$NON-NLS-1$
}
