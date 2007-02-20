/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.internal.actor.PerformManager;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.core.metadata.model.UUIDKeyed;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


/**
 * @author Thomas Hallgren
 */
public class Group extends Attribute
{
	public static final String ATTR_REBASE = "rebase";

	/**
	 * Rebase all paths that, when resolved, are paths prefixed by newBase
	 * @param newBase The base to use as replacement
	 * @param paths The path groups to rebase
	 * @return A new set of path groups
	 * @throws CoreException
	 */
	public static PathGroup[] rebase(IPath newBase, PathGroup[] pathGroups) throws CoreException
	{
		if(newBase == null)
			return pathGroups;

		HashMap<IPath, ArrayList<IPath>> bld = new HashMap<IPath, ArrayList<IPath>>();
		for(PathGroup pathGroup : pathGroups)
		{
			IPath currentBase = pathGroup.getBase();
			IPath[] paths = pathGroup.getPaths();
			if(paths.length == 0)
				addPathGroup(bld, newBase, currentBase, null);
			else
			{
				for(IPath path : paths)
					addPathGroup(bld, newBase, currentBase, path);
			}
		}

		ArrayList<PathGroup> newPathGroups = new ArrayList<PathGroup>();
		for(Map.Entry<IPath, ArrayList<IPath>> entry : bld.entrySet())
		{
			ArrayList<IPath> paths = entry.getValue();
			newPathGroups.add(new PathGroup(entry.getKey(), paths.toArray(new IPath[paths.size()])));
		}
		return newPathGroups.toArray(new PathGroup[newPathGroups.size()]);
	}

	private static void addPathGroup(HashMap<IPath, ArrayList<IPath>> bld, IPath newBase, IPath currentBase, IPath path)
	{
		IPath base;
		IPath absolute = currentBase;
		if(path != null)
			absolute = absolute.append(path);

		if(newBase.isPrefixOf(absolute))
		{
			base = newBase;
			path = absolute.removeFirstSegments(newBase.segmentCount()).setDevice(null);
		}
		else
			base = currentBase;

		ArrayList<IPath> group = bld.get(base);
		if(group == null)
		{
			group = new ArrayList<IPath>();
			bld.put(base, group);
		}
		if(path != null)
			group.add(path);
	}

	protected static String getPrereqKey(String componentName, String attributeName)
	{
		return componentName + ':' + attributeName;
	}

	private final List<Prerequisite> m_prerequisites;

	private final IPath m_prerequisiteRebase;

	public Group(String name, boolean publ, Map<String,String> installerHints, Documentation documentation, IPath prerequisiteRebase, List<Prerequisite> prerequisites)
	{
		super(name, publ, installerHints, documentation);
		m_prerequisiteRebase = prerequisiteRebase;
		m_prerequisites = UUIDKeyed.createUnmodifiableList(prerequisites);
	}

	public IPath getPrerequisiteRebase()
	{
		return m_prerequisiteRebase;
	}

	@Override
	public List<Prerequisite> getPrerequisites()
	{
		return m_prerequisites;
	}

	@Override
	public boolean isEnabled(IModelCache ctx)
	{
		try
		{
			CSpec cspec = getCSpec();
			int idx = m_prerequisites.size();
			while(--idx >= 0)
				if(!m_prerequisites.get(idx).getReferencedAttribute(cspec, ctx).isEnabled(ctx))
					return false;
			return true;
		}
		catch(CoreException e)
		{
			return false;
		}
	}

	@Override
	public boolean isProducedByActions(IModelCache ctx) throws CoreException
	{
		CSpec cspec = getCSpec();
		int idx = m_prerequisites.size();
		while(--idx >= 0)
			if(m_prerequisites.get(idx).getReferencedAttribute(cspec, ctx).isProducedByActions(ctx))
				return true;
		return false;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		super.addAttributes(attrs);
		IPath prereqsRebase = getPrerequisiteRebase();
		if(prereqsRebase != null)
			Utils.addAttribute(attrs, ATTR_REBASE, prereqsRebase.toPortableString());
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		super.emitElements(handler, namespace, prefix);
		for(Prerequisite pr : getPrerequisites())
			pr.toSax(handler, namespace, prefix, pr.getDefaultTag());
	}

	@Override
	protected PathGroup[] internalGetPathGroups(IModelCache ctx, Map<String, String> local)
			throws CoreException
	{
		IPath prereqRebase = getPrerequisiteRebase();
		if(prereqRebase != null)
		{
			prereqRebase = PerformManager.expandPath(local, prereqRebase);
			if(!prereqRebase.isAbsolute())
				prereqRebase = getCSpec().getComponentLocation().append(prereqRebase);
		}

		CSpec cspec = getCSpec();
		ArrayList<PathGroup> bld = new ArrayList<PathGroup>();
		for(Prerequisite pr : getPrerequisites())
		{
			if(!pr.isContributor())
				continue;

			PathGroup[] pathGroups = pr.getReferencedAttribute(cspec, ctx).getPathGroups(ctx);
			if(!pr.isExternal() && prereqRebase != null)
				pathGroups = rebase(prereqRebase, pathGroups);

			for(PathGroup path : pathGroups)
			{
				if(!bld.contains(path))
					bld.add(path);
			}
		}
		return bld.toArray(new PathGroup[bld.size()]);
	}
}
