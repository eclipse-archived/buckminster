/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.ctype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.WellKnownExports;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.model.AttributeAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractComponentType extends AbstractExtension implements IComponentType
{
	static class MetaFile implements IMetaFile
	{
		private final IPath[] m_aliases;
		
		private final boolean m_optional;
		
		private final IPath m_path;

		public MetaFile(IPath path, boolean optional, IPath[] aliases)
		{
			m_path = path;
			m_optional = optional;
			m_aliases = aliases;
		}

		public IPath[] getAliases()
		{
			return m_aliases;
		}

		public IPath getPath()
		{
			return m_path;
		}

		public boolean isOptional()
		{
			return m_optional;
		}

	}
	private static final IMetaFile[] s_noMetaFiles = new IMetaFile[0];

	/**
	 * Helper methods used by component types that manifest themselfs as one single jar file.
	 * 
	 * @param cspec
	 * @return An export where other jars that this component depends on can be added
	 */
	public static GroupBuilder addSelfAsJarArtifactGroups(CSpecBuilder cspec) throws PrerequisiteAlreadyDefinedException, AttributeAlreadyDefinedException
	{
		GroupBuilder archives = cspec.createGroupBuilder();
		archives.setName(WellKnownExports.JAVA_BINARY_ARCHIVES);
		archives.setPublic(true);
		archives.addSelfRequirement();
		cspec.addAttribute(archives);

		GroupBuilder generic = cspec.createGroupBuilder();
		generic.setName(WellKnownExports.JAVA_BINARIES);
		generic.setPublic(true);
		generic.addLocalPrerequisite(archives);
		cspec.addAttribute(generic);
		return generic;
	}

	public static IComponentType[] getComponentTypes() throws CoreException
	{
		CorePlugin plugin = CorePlugin.getDefault();
		String[] cids = getComponentTypeIDs(false);
		int idx = cids.length;
		IComponentType[] ctypes = new IComponentType[idx];
		while(--idx >= 0)
			ctypes[idx] = plugin.getComponentType(cids[idx]);
		return ctypes;
	}

	public static String[] getComponentTypeIDs(boolean includeEmptyEntry)
	{
		IConfigurationElement[] elems = getElements();
		int idx = elems.length;
		ArrayList<String> names = new ArrayList<String>(idx+1);
		if(includeEmptyEntry)
			names.add("");
		while(--idx >= 0)
			names.add(elems[idx].getAttribute("id"));
		Collections.sort(names);
		return names.toArray(new String[names.size()]);
	}

	private static IConfigurationElement[] getElements()
	{
		IExtensionRegistry exReg = Platform.getExtensionRegistry();
		return exReg.getConfigurationElementsFor(CorePlugin.COMPONENT_TYPE_POINT);
	}

	private Pattern m_desiredNamePattern;

	private IMetaFile[] m_metaFiles = s_noMetaFiles;

	private String m_nameSubstitution;

	private IPath m_relativeLocation;

	private Pattern m_substituteNamePattern;

	public IVersion getComponentVersion(ProviderMatch rInfo, IProgressMonitor monitor) throws CoreException
	{
		BOMNode node = getResolution(rInfo, true, monitor);
		return node.getResolution().getComponentIdentifier().getVersion();
	}

	public Pattern getDesiredNamePattern()
	{
		return m_desiredNamePattern;
	}

	public IMetaFile[] getMetaFiles()
	{
		return m_metaFiles;
	}

	public String getNameSubstitution()
	{
		return m_nameSubstitution;
	}

	public IPath getRelativeLocation()
	{
		return m_relativeLocation;
	}

	public final BOMNode getResolution(ProviderMatch rInfo, IProgressMonitor monitor) throws CoreException
	{
		return getResolution(rInfo, false, monitor);
	}

	public Pattern getSubstituteNamePattern()
	{
		return m_substituteNamePattern;
	}

	public IVersionDesignator getTypeSpecificDesignator(IVersionDesignator designator)
	{
		return designator;
	}

	public boolean hasAllRequiredMetaFiles(IPath path)
	{
		for(IMetaFile metaFile : getMetaFiles())
		{
			if(metaFile.isOptional() || path.append(metaFile.getPath()).toFile().exists())
				continue;

			boolean found = false;
			for(IPath alias : metaFile.getAliases())
			{
				if(path.append(alias).toFile().exists())
				{
					found = true;
					break;
				}
			}
			if(!found)
				return false;
		}
		return true;
	}

	public boolean isMetaFileBased()
	{
		for(IMetaFile metaFile : getMetaFiles())
			if(!metaFile.isOptional())
				return true;
		return false;
	}

	@Override
	public void setExtensionParameter(String key, String value) throws CoreException
	{
		if("relativeLocation".equals(key))
			m_relativeLocation = value == null ? null : Path.fromPortableString(value);
		else if("desiredNamePattern".equals(key))
			m_desiredNamePattern = value == null ? null : Pattern.compile(value);
		else if("substituteNamePattern".equals(key))
			m_substituteNamePattern = value == null ? null : Pattern.compile(value);
		else if("nameSubstitution".equals(key))
			m_nameSubstitution = value;
		else super.setExtensionParameter(key, value);
	}

	public String getProjectName(String componentName) throws CoreException
	{
		if(componentName == null)
			return null;

		Pattern desiredMatch = getDesiredNamePattern();
		if(desiredMatch == null || desiredMatch.matcher(componentName).find())
			//
			// We have a component type but no desire to change the name
			//
			return componentName;

		Pattern repFrom = getSubstituteNamePattern();
		String repTo = getNameSubstitution();

		if(repFrom == null || repTo == null)
			throw BuckminsterException.fromMessage("Component type %s defines desiredNamePattern but no substitution", getId());

		Matcher matcher = repFrom.matcher(componentName);
		if(matcher.matches())
		{
			String repl = matcher.replaceAll(repTo).trim();
			if(repl.length() > 0)
				componentName = repl;
		}
		return componentName;
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException
	{
		super.setInitializationData(config, propertyName, data);

		String tmp = config.getAttribute("relativeLocation");
		m_relativeLocation = tmp == null ? null : Path.fromPortableString(tmp);
		tmp = config.getAttribute("desiredNamePattern");
		m_desiredNamePattern = tmp == null ? null : Pattern.compile(tmp);
		tmp = config.getAttribute("substituteNamePattern");
		m_substituteNamePattern = tmp == null ? null : Pattern.compile(tmp);
		m_nameSubstitution = config.getAttribute("nameSubstitution");

		ArrayList<IMetaFile> metaFiles = null;
		for(IConfigurationElement metaFile : config.getChildren("metaFile"))
		{
			tmp = metaFile.getAttribute("path");
			if(tmp != null)
			{
				tmp = tmp.trim();
				if(tmp.length() == 0)
					tmp = null;
			}
			if(tmp == null)
				continue;

			IPath path = Path.fromPortableString(tmp);
			boolean optional = "true".equalsIgnoreCase(metaFile.getAttribute("optional"));
			List<IPath> aliasesBld = null;
			for(String alias : TextUtils.split(metaFile.getAttribute("aliases"), ","))
			{
				alias = alias.trim();
				if(alias.length() > 0)
				{
					if(aliasesBld == null)
						aliasesBld = new ArrayList<IPath>();
					aliasesBld.add(new Path(alias));
				}
			}
			IPath[] aliases = (aliasesBld == null) ? Trivial.EMPTY_PATH_ARRAY : aliasesBld.toArray(new IPath[aliasesBld.size()]);
			if(metaFiles == null)
				metaFiles = new ArrayList<IMetaFile>();
			metaFiles.add(new MetaFile(path, optional, aliases));
		}
		m_metaFiles = (metaFiles == null) ? s_noMetaFiles : metaFiles.toArray(new IMetaFile[metaFiles.size()]);
	}

	protected BOMNode getResolution(ProviderMatch rInfo, boolean forResolutionAidOnly, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 2000);
		IComponentReader[] reader = new IComponentReader[1];
		try
		{
			reader[0] = rInfo.getReader(MonitorUtils.subMonitor(monitor, 200));
			ComponentRequest request = rInfo.getNodeQuery().getComponentRequest();
			String componentType = request.getComponentTypeID();
			if(componentType != null && !getId().equals(componentType))
				throw new ComponentTypeMismatchException(request.getName(), componentType, getId());

			IResolutionBuilder builder = getResolutionBuilder(reader[0],MonitorUtils.subMonitor(monitor, 800));
			return builder.build(reader, forResolutionAidOnly, MonitorUtils.subMonitor(monitor, 1000));
		}
		finally
		{
			IOUtils.close(reader[0]);
		}
	}
}
