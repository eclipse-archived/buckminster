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
import java.util.Stack;

import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
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

	public Group(GroupBuilder builder)
	{
		super(builder);
		m_prerequisiteRebase = builder.getRebase();
		m_prerequisites = UUIDKeyed.createUnmodifiableList(builder.getPrerequisiteList());
	}

	public IPath getPrerequisiteRebase()
	{
		return m_prerequisiteRebase;
	}

	@Override
	public List<Prerequisite> getPrerequisites(Stack<IAttributeFilter> filters)
	{
		if(filters == null || filters.size() == 0 || m_prerequisites.size() == 0)
			return m_prerequisites;

		ArrayList<Prerequisite> matched = new ArrayList<Prerequisite>(m_prerequisites.size());
		for(Prerequisite pq : m_prerequisites)
		{
			String cname = pq.getComponentName();
			if(cname == null)
				cname = getCSpec().getName();
			String attr = pq.getAttribute();

			boolean isMatch = false;
			for(IAttributeFilter filter : filters)
			{
				if(filter.isMatch(cname, attr))
				{
					isMatch = true;
					break;
				}
			}
			if(isMatch)
				matched.add(pq);
		}
		return matched.size() == m_prerequisites.size() ? m_prerequisites : matched;
	}

	@Override
	public boolean isEnabled(IModelCache ctx) throws CoreException
	{
		if(!super.isEnabled(ctx))
			return false;

		// Return true if at least one of the prerequisites are enabled
		//
		CSpec cspec = getCSpec();
		int idx = m_prerequisites.size();
		if(idx == 0)
			return true;

		while(--idx >= 0)
			if(m_prerequisites.get(idx).isEnabled(ctx, cspec))
				return true;
		return false;
	}

	@Override
	public boolean isProducedByActions(IModelCache ctx) throws CoreException
	{
		CSpec cspec = getCSpec();
		int idx = m_prerequisites.size();
		while(--idx >= 0)
		{
			Attribute attr = m_prerequisites.get(idx).getReferencedAttribute(cspec, ctx);
			if(attr != null && attr.isProducedByActions(ctx))
				return true;
		}
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
	protected AttributeBuilder createAttributeBuilder(CSpecBuilder cspecBuilder)
	{
		return cspecBuilder.createGroupBuilder();
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		super.emitElements(handler, namespace, prefix);
		for(Prerequisite pr : getPrerequisites())
			pr.toSax(handler, namespace, prefix, pr.getDefaultTag());
	}

	@Override
	protected PathGroup[] internalGetPathGroups(IModelCache ctx, Map<String, String> local, Stack<IAttributeFilter> filters)
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
		for(Prerequisite pr : getPrerequisites(filters))
		{
			if(!pr.isContributor())
				continue;

			Attribute ag = pr.getReferencedAttribute(cspec, ctx);
			if(ag == null)
				continue;

			PathGroup[] pathGroups;
			if(pr.isPatternFilter())
			{
				if(filters == null)
					filters = new Stack<IAttributeFilter>();
				filters.push(pr);
				pathGroups = ag.getPathGroups(ctx, filters);
				filters.pop();
			}
			else
				pathGroups = ag.getPathGroups(ctx, filters);

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
