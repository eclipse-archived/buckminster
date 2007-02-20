/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.pde.internal;

import org.eclipse.buckminster.ant.AntPlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.cspec.AbstractResolutionBuilder;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.DependencyBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.ComponentRequestConflictException;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.pde.core.plugin.IMatchRules;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.internal.build.IBuildPropertiesConstants;
import org.osgi.framework.Version;

/**
 * This abstract builder contains all functionality that is common to the PDE Cspec builders.
 * Subclasses must implement {@link #parseFile(IComponentReader reader)}.
 * @author thhal
 */
@SuppressWarnings("restriction")
abstract class PDEBuilder extends AbstractResolutionBuilder implements IPDEConstants
{
	/**
	 * Name of the default generated target.
	 */
	public static final String DEFAULT_TARGET = "default";

	/**
	 * Name of the optional target.
	 */
	public static final String OPTIONAL_TARGET = "optional";

	private final CSpecBuilder m_cspec = new CSpecBuilder();

	private boolean m_usingInstalledReader;

	/**
	 * A version might end with the keyword &quot;.qualifier&quot;. This tells Eclipse to calculate
	 * that part of the qualifier when exporting a plugin/feature. For Buckminster, this means that
	 * this part of the version doesn't exist yet.
	 */
	public static String sanitizeVersion(String versionStr)
	{
		if(versionStr != null)
		{
			int qualifierIdx = versionStr.indexOf('.' + IBuildPropertiesConstants.PROPERTY_QUALIFIER);
			if(qualifierIdx != -1)
				versionStr = versionStr.substring(0, qualifierIdx);
		}
		return versionStr;
	}

	public synchronized DepNode build(IComponentReader[] readerHandle, IProgressMonitor monitor) throws CoreException
	{
		IComponentReader reader = readerHandle[0];
		if(!(reader instanceof ICatalogReader))
			throw new IllegalArgumentException("PDE builders can only work with catalog readers");

		monitor.beginTask(null, 2000);
		monitor.subTask("Generating cspec from PDE artifacts");
		try
		{
			m_usingInstalledReader = reader instanceof EclipsePlatformReader;
			parseFile((ICatalogReader)reader, MonitorUtils.subMonitor(monitor, 1000));
			CSpec cspec = applyExtensions(m_cspec.createCSpec(), reader, MonitorUtils.subMonitor(
				monitor, 1000));
			return new ResolvedNode(reader.getNodeQuery(), new Resolution(cspec, reader));
		}
		finally
		{
			m_cspec.clear();
			monitor.done();
		}
	}

	ComponentRequest createComponentRequest(String name, String category, String version)
	throws CoreException
	{
		if(version.equals("0.0.0"))
			version = null;
		return new ComponentRequest(name, category, sanitizeVersion(version), VersionFactory.OSGiType.getId());
	}

	ComponentRequest createComponentRequest(String name, String category, String version, int pdeMatchRule)
	throws CoreException
	{
		return new ComponentRequest(name, category, convertMatchRule(pdeMatchRule, version),
			VersionFactory.OSGiType.getId());
	}

	static String buildArtifactName(String id, String ver, boolean asJar)
	{
		StringBuilder bld = new StringBuilder();
		bld.append(id);
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

	public static String convertMatchRule(int pdeMatchRule, String origVersion)
	{
		String version = sanitizeVersion(origVersion);
		if(version == null || version.length() == 0 || version.equals("0.0.0"))
			return null;

		char c = version.charAt(0);
		if(c == '[' || c == '(')
			//
			// OSGi range, just ignor the rule then.
			//
			return version;

		Version v;
		StringBuilder vbld;
		switch(pdeMatchRule)
		{
		case IMatchRules.GREATER_OR_EQUAL:
			break;
		case IMatchRules.PERFECT:
			// If the version was sanitized, a perfect match will not find
			// it.
			//
			String toVersion;
			if(origVersion.length() == version.length())
				toVersion = version;
			else
				// Should cover most calculated version ranges
				//
				toVersion = version + ".zzzzzzzz";

			version = "[" + version + ',' + toVersion + "]";
			break;
		case IMatchRules.EQUIVALENT:
			//
			// Create a range that requires major and minor numbers
			// to be equal.
			//
			v = Version.parseVersion(version);
			vbld = new StringBuilder();
			vbld.append('[');
			vbld.append(version);
			vbld.append(',');
			vbld.append(v.getMajor());
			vbld.append('.');
			vbld.append(v.getMinor() + 1);
			vbld.append(')');
			version = vbld.toString();
			break;
		case IMatchRules.COMPATIBLE:
			//
			// Create a range that requires major and minor numbers
			// to be equal.
			//
			v = Version.parseVersion(version);
			vbld = new StringBuilder();
			vbld.append('[');
			vbld.append(version);
			vbld.append(',');
			vbld.append(v.getMajor() + 1);
			vbld.append(')');
			version = vbld.toString();
		}
		return version;
	}

	ComponentRequest createComponentRequest(IPluginModelBase pluginModelBase) throws CoreException
	{
		IPluginBase base = pluginModelBase.getPluginBase();
		return createComponentRequest(base.getId(), KeyConstants.PLUGIN_CATEGORY, base.getVersion());
	}

	ComponentRequest createComponentRequest(IPluginReference pluginReference, String category)
	throws CoreException
	{
		return createComponentRequest(pluginReference.getId(), category, pluginReference.getVersion(),
			pluginReference.getMatch());
	}

	boolean isUsingInstalledReader()
	{
		return m_usingInstalledReader;
	}

	AttributeBuilder generateRemoveDirAction(String dirTag, IPath dirPath, boolean publ) throws CoreException
	{
		return generateRemoveDirAction(dirTag, dirPath, publ, "buckminster.rm." + dirTag + ".dir");
	}

	AttributeBuilder generateRemoveDirAction(String dirTag, IPath dirPath, boolean publ, String actionName)
	throws CoreException
	{
		CSpecBuilder cspec = getCSpec();
		ArtifactBuilder dir = cspec.addArtifact("buckminster." + dirTag + ".dir", false, null, null);
		dir.addPath(dirPath);
		ActionBuilder rmDir = cspec.addAction(actionName, publ, "ant", true);
		rmDir.addActorProperty(AntPlugin.ANT_ACTOR_PROPERTY_TARGETS, TASK_DELETE_DIR, false);
		rmDir.addActorProperty(AntPlugin.ANT_ACTOR_PROPERTY_BUILD_FILE_ID, BUILD_FILE_ID, false);
		rmDir.addLocalPrerequisite(dir);
		rmDir.setPrerequisitesAlias(PROP_DELETE_DIR);
		return rmDir;
	}

	abstract void parseFile(ICatalogReader reader, IProgressMonitor monitor) throws CoreException;

	final CSpecBuilder getCSpec()
	{
		return m_cspec;
	}

	boolean addDependency(ComponentRequest dependency) throws CoreException
	{
		DependencyBuilder old = m_cspec.getDependency(dependency.getName());
		if(old == null)
		{
			m_cspec.addDependency(dependency);
			return true;
		}

		ComponentRequest mergedDep;
		ComponentRequest oldDep = old.createDependency();
		try
		{
			mergedDep = dependency.mergeDesignator(oldDep);
		}
		catch(ComponentRequestConflictException e)
		{
			// We don not catch a conflict that arises here. We want
			// that to be propagated.
			//
			mergedDep = oldDep.mergeDesignator(dependency);
		}
		if(mergedDep != oldDep)
		{
			m_cspec.removeDependency(oldDep.getName());
			dependency = mergedDep;
			m_cspec.addDependency(dependency);
		}
		return false;
	}
}
