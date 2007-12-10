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
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.DependencyBuilder;
import org.eclipse.buckminster.core.cspec.model.Dependency;
import org.eclipse.buckminster.core.cspec.model.DependencyAlreadyDefinedException;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.pde.core.plugin.IMatchRules;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.internal.build.IBuildPropertiesConstants;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.Version;

/**
 * @author Thomas Hallgren
 *
 */
@SuppressWarnings("restriction")
public abstract class CSpecGenerator implements IBuildPropertiesConstants, IPDEConstants
{
	public static final IPath OUTPUT_DIR_JAR = OUTPUT_DIR.append("jar");

	public static final IPath OUTPUT_DIR_SITE = OUTPUT_DIR.append("site");

	public static final IPath OUTPUT_DIR_TEMP = OUTPUT_DIR.append("temp");

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
	
	private final CSpecBuilder m_cspecBuilder;

	private final ICatalogReader m_reader;

	protected CSpecGenerator(CSpecBuilder cspecBuilder, ICatalogReader reader)
	{
		m_cspecBuilder = cspecBuilder;
		m_reader = reader;
	}

	public abstract void generate(IProgressMonitor monitor) throws CoreException;
	
	public CSpecBuilder getCSpec()
	{
		return m_cspecBuilder;
	}

	public ICatalogReader getReader()
	{
		return m_reader;
	}

	protected ActionBuilder addAntAction(String actionName, String targetName, boolean asPublic) throws CoreException
	{
		ActionBuilder action = m_cspecBuilder.addAction(actionName, asPublic, AntActor.ID, false);
		action.addActorProperty(AntBuilderConstants.ANT_ACTOR_PROPERTY_TARGETS, targetName, false);
		action.addActorProperty(AntBuilderConstants.ANT_ACTOR_PROPERTY_BUILD_FILE_ID, BUILD_FILE_ID, false);
		return action;
	}

	protected boolean addDependency(Dependency dependency) throws CoreException
	{
		DependencyBuilder old = m_cspecBuilder.getDependency(dependency.getName());
		if(old == null)
		{
			m_cspecBuilder.addDependency(dependency);
			return true;
		}

		IVersionDesignator vd = old.getVersionDesignator();
		IVersionDesignator nvd = dependency.getVersionDesignator();
		if(vd == null)
			vd = nvd;
		else
		{
			if(nvd != null)
			{
				vd = vd.merge(nvd);
				if(vd == null)
					//
					// Version ranges were not possible to merge, i.e. no intersection
					//
					throw new DependencyAlreadyDefinedException(old.getCSpecName(), old.getName());
			}
		}

		Filter fl = old.getFilter();
		Filter nfl = dependency.getFilter();
		if(fl == null || nfl == null)
			fl = null;
		else
		{
			if(!fl.equals(nfl))
			{
				try
				{
					fl = FilterUtils.createFilter("(|" + fl + nfl + ')');
				}
				catch(InvalidSyntaxException e)
				{
					throw BuckminsterException.wrap(e);
				}
			}
		}

		if(vd == old.getVersionDesignator() && fl == old.getFilter())
			return false;

		DependencyBuilder bld = m_cspecBuilder.createDependencyBuilder();
		bld.initFrom(dependency);
		bld.setVersionDesignator(vd);
		bld.setFilter(fl);
		m_cspecBuilder.removeDependency(dependency.getName());
		m_cspecBuilder.addDependency(bld);
		return false;
	}

	protected Dependency createDependency(IPluginReference pluginReference, String category)
	throws CoreException
	{
		return createDependency(pluginReference.getId(), category, pluginReference.getVersion(), pluginReference.getMatch(), null);
	}

	protected Dependency createDependency(String name, String componentType, String version, Filter filter)
	throws CoreException
	{
		if(version.equals("0.0.0"))
			version = null;
		return new Dependency(name, componentType, version, VersionFactory.OSGiType.getId(), filter);
	}

	protected Dependency createDependency(String name, String componentType, String version, int pdeMatchRule, Filter filter)
	throws CoreException
	{
		return new Dependency(name, componentType, convertMatchRule(pdeMatchRule, version),
				VersionFactory.OSGiType.getId(), filter);
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

	protected void setFilter(String filterStr) throws CoreException
	{
		filterStr = TextUtils.emptyTrimmedStringAsNull(filterStr);
		if(filterStr == null)
			return;

		try
		{
			Filter filter = FilterUtils.createFilter(filterStr);
			getCSpec().setFilter(FilterUtils.replaceAttributeNames(filter, "osgi", TargetPlatform.TARGET_PREFIX));
		}
		catch(InvalidSyntaxException e)
		{
			NodeQuery query = m_reader.getNodeQuery();
			IStatus status = new Status(IStatus.WARNING, PDEPlugin.getPluginId(),
					"Manifest has malformed LDAP rule for " + ICoreConstants.PLATFORM_FILTER + ": " + e.getMessage());
			RMContext ctx = query.getContext();
			if(!ctx.isContinueOnError())
				throw new CoreException(status);
			ctx.addException(query.getComponentRequest(), status);
		}
	}
}
