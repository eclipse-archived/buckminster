/*****************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.pde.cspecgen.bundle;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.UpToDatePolicy;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.pde.cspecgen.CSpecGenerator;
import org.eclipse.buckminster.pde.internal.model.ExternalBundleModel;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.core.plugin.IFragmentModel;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.bundle.BundlePluginBase;
import org.eclipse.pde.internal.core.ibundle.IBundle;
import org.osgi.framework.Constants;

/**
 * A CSpec builder that creates a cspec using the META-INF/MANIFEST.MF,
 * plugin.xml and fragment.xml files.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class CSpecFromBinary extends CSpecGenerator {
	private static final String SYSTEM_BUNDLE = "org.eclipse.osgi"; //$NON-NLS-1$

	private static final ComponentName SYSTEM_BUNDLE_CNAME = new ComponentName(SYSTEM_BUNDLE, IComponentType.OSGI_BUNDLE);

	private final IPluginBase plugin;

	public CSpecFromBinary(CSpecBuilder cspecBuilder, ICatalogReader reader, IPluginBase plugin) {
		super(cspecBuilder, reader);
		this.plugin = plugin;
	}

	/**
	 * Creates the attributes needed for a prebuilt bundle. The target bundle
	 * can be represented as a folder or a jar file.
	 * 
	 * @param monitor
	 * @throws CoreException
	 */
	@Override
	public void generate(IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, 20);

		CSpecBuilder cspec = getCSpec();
		GroupBuilder classpath = cspec.addGroup(ATTRIBUTE_JAVA_BINARIES, true);
		GroupBuilder bundleJars = cspec.addGroup(ATTRIBUTE_BUNDLE_JARS, true);
		GroupBuilder bundleAndFragments = cspec.addGroup(ATTRIBUTE_BUNDLE_AND_FRAGMENTS, true);

		bundleJars.addLocalPrerequisite(bundleAndFragments);

		// We need an empty group for binary bundles
		cspec.addGroup(ATTRIBUTE_BUNDLE_AND_FRAGMENTS_SOURCE, true);
		cspec.addGroup(ATTRIBUTE_PRODUCT_CONFIG_EXPORTS, true);

		IPluginModelBase model = plugin.getPluginModel();
		if (model instanceof IFragmentModel)
			addBundleHostDependency((IFragmentModel) model);
		else {
			ActionBuilder copyTargetFragments = cspec.addAction(ATTRIBUTE_TARGET_FRAGMENTS, false, ACTOR_COPY_TARGET_FRAGMENTS, false);
			copyTargetFragments.setProductAlias(ALIAS_OUTPUT);
			copyTargetFragments.setProductBase(OUTPUT_DIR_FRAGMENTS);
			copyTargetFragments.setUpToDatePolicy(UpToDatePolicy.ACTOR);
			bundleAndFragments.addLocalPrerequisite(copyTargetFragments);
		}

		// There are two types of binaries. The one that contain jar files (and
		// must be unpacked
		// in order to function) and the one that is a jar file in itself.
		//
		addImports();
		MonitorUtils.worked(monitor, 10);

		IPath parentDir = new Path(".."); //$NON-NLS-1$
		String location = model.getInstallLocation();
		File locationFile = (location != null) ? new File(location) : null;
		boolean isFile = (locationFile != null) && locationFile.isFile();

		cspec.setShortDesc(expand(plugin.getName()));

		if (isFile) {
			// No unpacked bundle exists (or should ever exist). We're happy
			// with what we have.
			//
			cspec.addGroup(ATTRIBUTE_FULL_CLEAN, true);
			ArtifactBuilder pluginExport = cspec.addArtifact(ATTRIBUTE_BUNDLE_JAR, true, null);
			pluginExport.addPath(Path.fromOSString(locationFile.getName()));
			pluginExport.setBase(parentDir); // we want the site/plugins folder,
												// i.e. the parent of the jar
			classpath.addLocalPrerequisite(pluginExport);
			bundleAndFragments.addLocalPrerequisite(pluginExport);
		} else {
			// This bundle is a folder. Gather artifacts to be included in the
			// classpath
			//
			IBundle bundle = null;
			if (plugin instanceof BundlePluginBase)
				bundle = ((BundlePluginBase) plugin).getBundle();
			else {
				if (locationFile != null && locationFile.isDirectory()) {
					InputStream input = null;
					try {
						input = new BufferedInputStream(new FileInputStream(new File(locationFile, BUNDLE_FILE)));
						ExternalBundleModel ebm = new ExternalBundleModel(locationFile);
						ebm.load(input, false);
						bundle = ebm.getBundle();
					} catch (IOException e) {
					} finally {
						IOUtils.close(input);
					}
				}
			}

			String bundleClassPath = null;
			if (bundle != null) {
				bundleClassPath = bundle.getHeader(Constants.BUNDLE_CLASSPATH);
				setFilter(bundle.getHeader(ICoreConstants.PLATFORM_FILTER));
			}

			String jarName = buildArtifactName(plugin.getId(), plugin.getVersion(), true);
			boolean isImportedBundle = false;

			ArtifactBuilder bundleClasspath = null;
			if (bundleClassPath == null)
				classpath.addSelfRequirement();
			else {
				// Create an artifact that contains all entries listed in the
				// classpath
				//
				bundleClasspath = cspec.addArtifact(ATTRIBUTE_BUNDLE_CLASSPATH, false, null);
				StringTokenizer tokens = new StringTokenizer(bundleClassPath, ","); //$NON-NLS-1$
				while (tokens.hasMoreTokens()) {
					String token = tokens.nextToken().trim();
					if (token.equals(jarName))
						isImportedBundle = true;
					bundleClasspath.addPath(new Path(token));
				}
				classpath.addLocalPrerequisite(bundleClasspath);
			}

			ActionBuilder bundleExport;
			if (!isImportedBundle) {
				// In order to create a jar of the unpackedPlugin, we need a
				// temporary directory
				// since this artifact is not a workspace artifact
				//
				bundleExport = addAntAction(ATTRIBUTE_BUNDLE_JAR, TASK_RECREATE_JAR, true);
				bundleExport.addProductPath(Path.fromPortableString(jarName));
				bundleExport.getPrerequisitesBuilder().addSelfRequirement();
			} else {
				bundleExport = addAntAction(ATTRIBUTE_BUNDLE_JAR, TASK_COPY_GROUP, true);
				if (bundleClasspath.getPaths().size() == 1)
					bundleExport.getPrerequisitesBuilder().addLocalPrerequisite(bundleClasspath);
				else {
					ArtifactBuilder importedJar = cspec.addArtifact(ATTRIBUTE_IMPORTED_JAR, false, null);
					importedJar.addPath(Path.fromPortableString(jarName));
					bundleExport.getPrerequisitesBuilder().addLocalPrerequisite(importedJar);
				}
			}
			bundleExport.setProductAlias(ALIAS_OUTPUT);
			bundleExport.setProductBase(OUTPUT_DIR);
			bundleExport.setPrerequisitesAlias(ALIAS_REQUIREMENTS);
			bundleAndFragments.addLocalPrerequisite(bundleExport);
			generateRemoveDirAction("build", OUTPUT_DIR, true, ATTRIBUTE_FULL_CLEAN); //$NON-NLS-1$
		}
		monitor.done();
	}

	@Override
	protected String getProductOutputFolder(String productId) {
		return null;
	}

	@Override
	protected String getPropertyFileName() {
		return PLUGIN_PROPERTIES_FILE;
	}

	private void addImports() throws CoreException {
		IPluginModelBase model = plugin.getPluginModel();
		boolean isFragment = model.isFragmentModel();

		NodeQuery query = getReader().getNodeQuery();
		ComponentQuery cquery = query.getComponentQuery();
		CSpecBuilder cspec = getCSpec();

		GroupBuilder reExports = cspec.getRequiredGroup(ATTRIBUTE_JAVA_BINARIES);
		GroupBuilder bundleJars = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_JARS);

		ImportSpecification[] imports = getImports(plugin);
		if (imports.length == 0) {
			// Just add the mandatory system bundle. It's needed since
			// that bundle defines the execution environments.
			//
			if (!(isFragment || SYSTEM_BUNDLE.equals(cspec.getName()) || cquery.skipComponent(SYSTEM_BUNDLE_CNAME, query.getContext())))
				cspec.addDependency(createDependency(SYSTEM_BUNDLE, IComponentType.OSGI_BUNDLE, (String) null, null));
			return;
		}

		for (ImportSpecification pluginImport : imports) {
			if (pluginImport.isOptional())
				//
				// We don't care about expressing dependencies to
				// optional plugins when we peruse the runtime
				// environment.
				//
				continue;

			String pluginId = pluginImport.getName();
			if (pluginId.equals(Constants.SYSTEM_BUNDLE_SYMBOLICNAME))
				continue;

			ComponentRequestBuilder dependency = createDependency(pluginImport, IComponentType.OSGI_BUNDLE);
			if (skipComponent(cquery, dependency) || !addDependency(dependency))
				continue;

			addExternalPrerequisite(bundleJars, dependency, ATTRIBUTE_BUNDLE_JARS);
			if (pluginImport.isExported())
				addExternalPrerequisite(reExports, dependency, ATTRIBUTE_JAVA_BINARIES);
		}
	}
}
