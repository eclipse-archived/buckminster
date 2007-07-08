/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

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
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.pde.cspecgen.CSpecGenerator;
import org.eclipse.buckminster.pde.internal.model.ExternalBundleModel;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginImport;
import org.eclipse.pde.core.plugin.ISharedPluginModel;
import org.eclipse.pde.internal.core.bundle.BundlePluginBase;
import org.eclipse.pde.internal.core.ibundle.IBundle;
import org.osgi.framework.Constants;

/**
 * A CSpec builder that creates a cspec using the META-INF/MANIFEST.MF, plugin.xml and fragment.xml
 * files.
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class CSpecFromBinary extends CSpecGenerator
{
	private static final String SYSTEM_BUNDLE = "org.eclipse.osgi";

	private final ICatalogReader m_reader;
	private final IPluginBase m_plugin;

	public CSpecFromBinary(CSpecBuilder cspecBuilder, ICatalogReader reader, IPluginBase plugin)
	{
		super(cspecBuilder);
		m_reader = reader;
		m_plugin = plugin;
	}

	private void addExternalPrerequisite(GroupBuilder group, String component, String name, boolean optional)
	throws CoreException
	{
		PrerequisiteBuilder pqBld = group.createPrerequisiteBuilder();
		pqBld.setComponent(component);
		pqBld.setName(name);
		pqBld.setOptional(optional);
		group.addPrerequisite(pqBld);
	}

	private void addImports() throws CoreException
	{
		IPluginImport[] imports = m_plugin.getImports();
		boolean isFragment = m_plugin.getPluginModel().isFragmentModel();

		ComponentQuery query = m_reader.getNodeQuery().getComponentQuery();
		CSpecBuilder cspec = getCSpec();

		GroupBuilder reExports = cspec.getRequiredGroup(ATTRIBUTE_JAVA_BINARIES);
		if(imports == null || imports.length == 0)
		{
			// Just add the mandatory system bundle. It's needed since
			// that bundle defines the execution environments.
			//
			if(!(isFragment || SYSTEM_BUNDLE.equals(cspec.getName())))
			{
				ComponentRequest sysDep = new ComponentRequest(SYSTEM_BUNDLE, IComponentType.OSGI_BUNDLE, null);
				if(!query.skipComponent(sysDep))
					cspec.addDependency(sysDep);
			}
			return;
		}

		for(IPluginImport pluginImport : imports)
		{
			if(pluginImport.isOptional())
				//
				// We don't care about expressing dependencies to
				// optional plugins when we peruse the runtime
				// environment.
				//
				continue;

			String pluginId = pluginImport.getId();
			if(pluginId.equals("system.bundle"))
				continue;

			ComponentRequest dependency = createComponentRequest(pluginImport, IComponentType.OSGI_BUNDLE);
			if(query.skipComponent(dependency) || !addDependency(dependency))
				continue;

			String component = dependency.getName();
			if(pluginImport.isReexported())
				addExternalPrerequisite(reExports, component, ATTRIBUTE_JAVA_BINARIES, false);
		}
	}

	/**
	 * Creates the attributes needed for a prebuilt bundle. The target bundle can
	 * be represented as a folder or a jar file.
	 * @param monitor
	 * @throws CoreException
	 */
	@Override
	public void generate(IProgressMonitor monitor)
	throws CoreException
	{
		monitor.beginTask(null, 20);

		CSpecBuilder cspec = getCSpec();
		GroupBuilder classpath = cspec.addGroup(ATTRIBUTE_JAVA_BINARIES, true);

		// There are two types of binaries. The one that contain jar files (and must be unpacked
		// in order to function) and the one that is a jar file in itself.
		//
		addImports();
		MonitorUtils.worked(monitor, 10);

		IPath parentDir = new Path("..");
		ISharedPluginModel model = m_plugin.getModel();
		String location = model.getInstallLocation();
		File locationFile = (location != null) ? new File(location) : null;
		boolean isFile = (locationFile != null) && locationFile.isFile();

		if(isFile)
		{
			// No unpacked bundle exists (or should ever exist). We're happy with what we have.
			//
			cspec.addGroup(ATTRIBUTE_FULL_CLEAN, true);
			ArtifactBuilder pluginExport = cspec.addArtifact(ATTRIBUTE_BUNDLE_JAR, true,
				ATTRIBUTE_BUNDLE_JAR, null);
			pluginExport.addPath(new Path(buildArtifactName(true)));
			pluginExport.setBase(parentDir); // we want the site/plugins folder, i.e. the parent of the jar
			classpath.addLocalPrerequisite(pluginExport);
		}
		else
		{
			// This bundle is a folder. Gather artifacts to be included in the classpath
			//
			IBundle bundle = null;
			if(m_plugin instanceof BundlePluginBase)
				bundle = ((BundlePluginBase)m_plugin).getBundle();
			else
			{
				if(locationFile != null && locationFile.isDirectory())
				{
					InputStream input = null;
					try
					{
						input = new BufferedInputStream(new FileInputStream(new File(locationFile, BUNDLE_FILE)));
						ExternalBundleModel ebm = new ExternalBundleModel();
						ebm.load(input, false);
						bundle = ebm.getBundle();
					}
					catch(IOException e)
					{}
					finally
					{
						IOUtils.close(input);
					}
				}
			}
			String bundleClassPath = null;
			if(bundle != null)
				bundleClassPath = bundle.getHeader(Constants.BUNDLE_CLASSPATH);

			if(bundleClassPath == null)
				classpath.addSelfRequirement();
			else
			{
				// Create an artifact that contains all entries listed in the classpath
				//
				ArtifactBuilder bundleClasspath = cspec.addArtifact(ATTRIBUTE_BUNDLE_CLASSPATH, false,
					ATTRIBUTE_JAVA_BINARIES, null);
				StringTokenizer tokens = new StringTokenizer(bundleClassPath, ",");
				while(tokens.hasMoreTokens())
				{
					String token = tokens.nextToken().trim();
					bundleClasspath.addPath(new Path(token));
				}

				classpath.addLocalPrerequisite(bundleClasspath);
			}

			// In order to create a jar of the unpackedPlugin, we need a temporary directory
			// since this artifact is not a workspace artifact
			//
			String jarName = buildArtifactName(true);
			ActionBuilder bundleExport = addAntAction(ATTRIBUTE_BUNDLE_JAR, TASK_CREATE_ZIP, true);

			bundleExport.setProductAlias(ALIAS_OUTPUT);
			bundleExport.setProductBase(OUTPUT_DIR);
			bundleExport.addProductPath(Path.fromPortableString(jarName));

			bundleExport.setPrerequisitesAlias(ALIAS_REQUIREMENTS);
			bundleExport.getPrerequisitesBuilder().addSelfRequirement();
			generateRemoveDirAction("build", OUTPUT_DIR, true, ATTRIBUTE_FULL_CLEAN);
		}
		monitor.done();
	}

	private String buildArtifactName(boolean asJar)
	{
		StringBuilder bld = new StringBuilder();
		bld.append(m_plugin.getId());
		String ver = m_plugin.getVersion();
		if(ver != null)
		{
			bld.append('_');
			bld.append(ver);
		}
		if(asJar)
			bld.append(".jar");
		else
			bld.append('/');
		return bld.toString();
	}
}
