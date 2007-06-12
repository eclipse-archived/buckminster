/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.pde.cspecgen;

import org.eclipse.buckminster.ant.AntBuilderConstants;
import org.eclipse.buckminster.ant.actor.AntActor;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.DependencyBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.ComponentRequestConflictException;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.pde.IPDEConstants;
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
 * @author Thomas Hallgren
 *
 */
@SuppressWarnings("restriction")
public abstract class CSpecGenerator implements IBuildPropertiesConstants, IPDEConstants
{
	public static final IPath OUTPUT_DIR_TEMP = OUTPUT_DIR.append("temp");

	public static final IPath OUTPUT_DIR_JAR = OUTPUT_DIR.append("jar");

	public static final IPath OUTPUT_DIR_SITE = OUTPUT_DIR.append("site");

	private final CSpecBuilder m_cspecBuilder;

	protected CSpecGenerator(CSpecBuilder cspecBuilder)
	{
		m_cspecBuilder = cspecBuilder;
	}

	public CSpecBuilder getCSpec()
	{
		return m_cspecBuilder;
	}

	public abstract void generate(IProgressMonitor monitor) throws CoreException;
	
	protected ActionBuilder addAntAction(String actionName, String targetName, boolean asPublic) throws CoreException
	{
		ActionBuilder action = m_cspecBuilder.addAction(actionName, asPublic, AntActor.ID, false);
		action.addActorProperty(AntBuilderConstants.ANT_ACTOR_PROPERTY_TARGETS, targetName, false);
		action.addActorProperty(AntBuilderConstants.ANT_ACTOR_PROPERTY_BUILD_FILE_ID, BUILD_FILE_ID, false);
		return action;
	}

	protected boolean addDependency(ComponentRequest dependency) throws CoreException
	{
		DependencyBuilder old = m_cspecBuilder.getDependency(dependency.getName());
		if(old == null)
		{
			m_cspecBuilder.addDependency(dependency);
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
			m_cspecBuilder.removeDependency(oldDep.getName());
			dependency = mergedDep;
			m_cspecBuilder.addDependency(dependency);
		}
		return false;
	}

	protected AttributeBuilder generateRemoveDirAction(String dirTag, IPath dirPath, boolean publ) throws CoreException
	{
		return generateRemoveDirAction(dirTag, dirPath, publ, "buckminster.rm." + dirTag + ".dir");
	}

	protected AttributeBuilder generateRemoveDirAction(String dirTag, IPath dirPath, boolean publ, String actionName)
	throws CoreException
	{
		ActionBuilder rmDir = addAntAction(actionName, TASK_DELETE_DIR, publ);
		rmDir.addProperty(PROP_DELETE_DIR, dirPath.toPortableString(), false);
		return rmDir;
	}

	protected ComponentRequest createComponentRequest(String name, String category, String version)
	throws CoreException
	{
		if(version.equals("0.0.0"))
			version = null;
		return new ComponentRequest(name, category, version, VersionFactory.OSGiType.getId());
	}

	protected ComponentRequest createComponentRequest(String name, String category, String version, int pdeMatchRule)
	throws CoreException
	{
		return new ComponentRequest(name, category, convertMatchRule(pdeMatchRule, version),
			VersionFactory.OSGiType.getId());
	}

	protected static String buildArtifactName(String id, String ver, boolean asJar)
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

	public static String convertMatchRule(int pdeMatchRule, String version)
	{
		if(version == null || version.length() == 0 || version.equals("0.0.0"))
			return null;

		char c = version.charAt(0);
		if(c == '[' || c == '(')
			//
			// Already an OSGi range, just ignor the rule then.
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
			version = "[" + version + ',' + version + "]";
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

	protected ComponentRequest createComponentRequest(IPluginModelBase pluginModelBase) throws CoreException
	{
		IPluginBase base = pluginModelBase.getPluginBase();
		return createComponentRequest(base.getId(), KeyConstants.PLUGIN_CATEGORY, base.getVersion());
	}

	protected ComponentRequest createComponentRequest(IPluginReference pluginReference, String category)
	throws CoreException
	{
		return createComponentRequest(pluginReference.getId(), category, pluginReference.getVersion(),
			pluginReference.getMatch());
	}
}
