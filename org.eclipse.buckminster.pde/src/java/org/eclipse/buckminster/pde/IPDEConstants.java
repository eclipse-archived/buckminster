package org.eclipse.buckminster.pde;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.cspec.WellKnownExports;
import org.eclipse.buckminster.core.cspec.WellknownActions;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.internal.build.IPDEBuildConstants;

@SuppressWarnings("restriction")
public interface IPDEConstants
{
	public static final String ALIAS_OUTPUT = "action.output";

	public static final String ALIAS_REQUIREMENTS = "action.requirements";

	/**
	 * The feature in a format suitable for an update site. No sub-features nor bundles are
	 * included. The base of the attribute
	 * @{link org.eclipse.buckminster.core.cspec.PathGroup PathGroup} will be the folder containing
	 * the feature.
	 */
	public static final String ATTRIBUTE_FEATURE_EXPORT = "feature.export";

	/**
	 * Denotes a fully deployable feature with all included features and bundles in a format
	 * sutiable for publishing on an update site. The base of the attribute
	 * @{link org.eclipse.buckminster.core.cspec.PathGroup PathGroup} will be folder that contains
	 * the <code>features</code> and <code>plugins</code> folders.
	 */
	public static final String ATTRIBUTE_FEATURE_EXPORTS = "feature.exports";

	/**
	 * The feature in a format suitable for a runtime. No sub-features nor bundles are included. The
	 * base of the attribute
	 * @{link org.eclipse.buckminster.core.cspec.PathGroup PathGroup} will be the folder containing
	 * the feature.
	 */
	public static final String ATTRIBUTE_FEATURE_RUNTIME = "feature.runtime";

	/**
	 * Denotes a fully deployable feature with all included features and bundles in a format
	 * sutiable for a runtime. This target is intended for product builds. The base of the attribute
	 * @{link org.eclipse.buckminster.core.cspec.PathGroup PathGroup} will be the folder that
	 * contains the <code>features</code> and <code>plugins</code> folders.
	 */
	public static final String ATTRIBUTE_FEATURE_RUNTIMES = "feature.runtimes";

	/**
	 * The attribut that, when triggered, will clean out all built artifacts including the Eclipse
	 * build. This attribute has no resulting product.
	 */
	public static final String ATTRIBUTE_FULL_CLEAN = WellknownActions.BUCKMINSTER.CLEAN.toString();

	/**
	 * Denotes a list consisting the self artifact and the state closure of all dependencies
	 */
	public static final String ATTRIBUTE_BUNDLE_CLOSURE = "bundle.state.closure";

	/**
	 * Denotes a fully deployable bundle in packed formet
	 */
	public static final String ATTRIBUTE_BUNDLE_EXPORT = "bundle.export";

	/**
	 * Denotes a list consisting of a fully deployable bundle in jar format along with all
	 * bundles that it reexports.
	 */
	public static final String ATTRIBUTE_BUNDLE_EXPORTS = "bundle.exports";

	/**
	 * Denotes a fully deployable bundle in unpacked format
	 */
	public static final String ATTRIBUTE_BUNDLE_FOLDER = "bundle.folder";

	/**
	 * Denotes a fully deployable bundle, packed or unpacked, whichever is best for the given
	 * bundle.
	 */
	public static final String ATTRIBUTE_BUNDLE_RUNTIME = "bundle.runtime";

	/**
	 * Denotes a list consisting of a fully deployable bundle along with all bundles that it
	 * reexports. The bundles are packed or unpacked, whichever is best for each given bundle.
	 */
	public static final String ATTRIBUTE_BUNDLE_RUNTIMES = "bundle.runtimes";

	/**
	 * Denotes the external classpath for the bundle. This will be the sum of all exported
	 * binaries from the imported bundles plus the exported contribution of this bundle.
	 */
	public static final String ATTRIBUTE_JAVA_BINARIES = WellKnownExports.JAVA_BINARIES;

	/**
	 * Denotes the bundle classpath. This will be the sum of all exported
	 * binaries from the imported bundles plus the full contribution of this bundle.
	 */
	public static final String ATTRIBUTE_BUNDLE_CLASSPATH = "bundle.classpath";

	public static final String BUILD_FILE_ID = "buckminster.pdetasks";

	public static final String BUILD_PROPERTIES_FILE = IPDEBuildConstants.PROPERTIES_FILE;

	public static final String BUNDLE_FILE = IPDEBuildConstants.MANIFEST_FOLDER + '/'
		+ IPDEBuildConstants.MANIFEST;

	public static final String FEATURE_FILE = "feature.xml";

	public static final String FEATURE_NATURE = "org.eclipse.pde.FeatureNature";

	public static final String FEATURES_FOLDER = IPDEBuildConstants.DEFAULT_FEATURE_LOCATION;

	public static final String FRAGMENT_FILE = "fragment.xml";

	public static final String HINT_PERMISSIONS = "permissions";

	public static final String MANIFEST = IPDEBuildConstants.MANIFEST;

	public static final IPath OUTPUT_DIR = new Path(KeyConstants.ACTION_OUTPUT_REF + '/');

	public static final String PLUGIN_FILE = "plugin.xml";

	public static final String PLUGIN_NATURE = "org.eclipse.pde.PluginNature";

	public static final String PLUGINS_FOLDER = IPDEBuildConstants.DEFAULT_PLUGIN_LOCATION;

	/**
	 * Appoints all files that PDE features wants to copy to the root of a product they are included
	 * in.
	 */
	public static final String PRODUCT_ROOT_FILES = "product.root.files";

	public static final String PROP_DELETE_DIR = "dir.to.delete";

	public static final String PROP_DELETE_FILE = "file.to.delete";

	public static final String SITE_FILE = "site.xml";

	public static final String TASK_COPY_GROUP = "copy.group";

	public static final String TASK_CREATE_JAR = "create.jar";

	public static final String TASK_CREATE_JAR_WM = "create.jar.with.manifest";

	public static final String TASK_DELETE_DIR = "delete.dir";

	public static final String TASK_DELETE_FILE = "delete.file";

	public static final String TASK_DELETE_GROUP = "delete.group";

	public static final String TASK_UNJAR_NAMED = "unjar.named";

	public static final String TASK_UNZIP = "unzip";

	public static final IPath TEMP_DIR = new Path(KeyConstants.ACTION_TEMPDIR_REF + '/');
}
