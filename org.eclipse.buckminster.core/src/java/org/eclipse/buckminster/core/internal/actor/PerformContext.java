/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.internal.actor;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.IAttributeFilter;
import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.ActionArtifact;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.Group;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.cspec.model.TopLevelAttribute;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;

/**
 * @author kolwing
 * 
 */
public class PerformContext implements IActionContext
{
	final static String PRODUCT_PREFIX = TopLevelAttribute.PROPERTY_PREFIX + "product."; //$NON-NLS-1$

	final static String REQUIREMENT_PREFIX = TopLevelAttribute.PROPERTY_PREFIX + "requirement."; //$NON-NLS-1$

	private static PathGroup[] normalizePathGroups(PathGroup[] pathGroups) throws CoreException
	{
		if(pathGroups.length == 0)
			return pathGroups;

		HashMap<IPath, ArrayList<IPath>> newPathGroups = new HashMap<IPath, ArrayList<IPath>>();
		for(PathGroup pathGroup : pathGroups)
		{
			IPath base = pathGroup.getBase();
			ArrayList<IPath> currentGroup = newPathGroups.get(base);
			if(currentGroup == null)
			{
				currentGroup = new ArrayList<IPath>();
				newPathGroups.put(base, currentGroup);
			}
			for(IPath path : pathGroup.getPaths())
				currentGroup.add(path);
		}

		ArrayList<PathGroup> normalized = new ArrayList<PathGroup>();
		for(Map.Entry<IPath, ArrayList<IPath>> entry : newPathGroups.entrySet())
		{
			ArrayList<IPath> paths = entry.getValue();
			normalizePaths(paths);
			normalized.add(new PathGroup(entry.getKey(), paths.toArray(new IPath[paths.size()])));
		}
		return normalized.toArray(new PathGroup[normalized.size()]);
	}

	private static void normalizePaths(ArrayList<IPath> paths)
	{
		// Remove all paths that has a parent path in the array
		//
		int topDown = paths.size();
		while(--topDown >= 0)
		{
			IPath path = paths.get(topDown);
			if(path.segmentCount() == 1 && ".".equals(path.lastSegment())) //$NON-NLS-1$
			{
				paths.clear();
				return;
			}

			int top = paths.size();
			for(int idx = 0; idx < top; ++idx)
			{
				if(idx != topDown && paths.get(idx).isPrefixOf(path))
				{
					paths.remove(topDown);
					break;
				}
			}
		}
	}

	private static PathGroup[] trimNonExistentBases(PathGroup[] pathGroups) throws CoreException
	{
		if(pathGroups.length == 0)
			return pathGroups;

		ArrayList<PathGroup> existentBases = new ArrayList<PathGroup>();
		for(PathGroup pathGroup : pathGroups)
		{
			File file = pathGroup.getBase().toFile().getAbsoluteFile();
			if(file.exists())
				existentBases.add(pathGroup);
			else
				CorePlugin.getLogger().debug("Base: %s: No such file or directory", file); //$NON-NLS-1$
		}
		return existentBases.toArray(new PathGroup[existentBases.size()]);
	}

	private final IProgressMonitor m_cancellationMonitor;

	private final Action m_action;

	private final PrintStream m_errorStream;

	private final GlobalContext m_globalCtx;

	private final PrintStream m_outputStream;

	private final Map<String, ? extends Object> m_properties;

	public PerformContext(GlobalContext globalCtx, Action action, PrintStream out, PrintStream err,
			IProgressMonitor cancellationMonitor) throws CoreException
	{
		m_globalCtx = globalCtx;
		m_action = action;
		m_properties = globalCtx.getExecutionProperties(action);
		m_outputStream = out;
		m_errorStream = err;
		m_cancellationMonitor = cancellationMonitor;
	}

	public void addPrerequisitePathGroups(Map<String, PathGroup[]> pgas) throws CoreException
	{
		Action action = getAction();
		Collection<Prerequisite> prereqs = action.getPrerequisites();
		if(prereqs.size() == 0)
			return;

		CSpec cspec = action.getCSpec();
		Map<String, ? extends Object> properties = getProperties();
		IPath prereqRebase = action.getPrerequisiteRebase();
		if(prereqRebase != null)
		{
			prereqRebase = PerformManager.expandPath(properties, prereqRebase);
			if(prereqRebase.isAbsolute())
				throw BuckminsterException.fromMessage(Messages.Action_prerequisite_base_can_not_be_absolute);
			prereqRebase = cspec.getComponentLocation().append(prereqRebase);
		}

		String mainRQ = REQUIREMENT_PREFIX + action.getName();
		String prefix = mainRQ + '.';
		ArrayList<PathGroup> allRequiredPaths = new ArrayList<PathGroup>();
		Stack<IAttributeFilter> filters = null;
		for(Prerequisite prereq : prereqs)
		{
			Attribute ag = prereq.getReferencedAttribute(cspec, this);
			PathGroup[] paths;
			if(ag == null)
				paths = new PathGroup[0];
			else
			{
				if(prereq.isPatternFilter())
				{
					if(filters == null)
						filters = new Stack<IAttributeFilter>();
					filters.push(prereq);
					paths = ag.getPathGroups(this, filters);
					filters.pop();
				}
				else
					paths = ag.getPathGroups(this, filters);

				paths = normalizePathGroups(paths);
				paths = trimNonExistentBases(paths);
				if(!prereq.isExternal())
				{
					if(prereqRebase != null)
						paths = Group.rebase(prereqRebase, paths);
				}
			}
			String alias = prereq.getAlias();
			if(alias != null)
				pgas.put(alias, paths);
			else
				pgas.put(prefix + prereq, paths);

			for(PathGroup path : paths)
				allRequiredPaths.add(path);
		}

		if(allRequiredPaths.size() > 0)
		{
			PathGroup[] pathGroups = normalizePathGroups(allRequiredPaths
					.toArray(new PathGroup[allRequiredPaths.size()]));
			String alias = action.getPrerequisitesAlias();
			if(alias != null)
				pgas.put(alias, pathGroups);
			else
				pgas.put(mainRQ, pathGroups);
		}
	}

	public void addProductPathGroup(Map<String, PathGroup[]> pgas) throws CoreException
	{
		Action action = getAction();
		List<ActionArtifact> productArtifacts = action.getProductArtifacts();
		if(productArtifacts.size() == 0)
		{
			PathGroup[] product = action.getPathGroups(this, null);
			if(product.length > 0)
			{
				PathGroup[] pathGroups = normalizePathGroups(product);
				String alias = action.getProductAlias();
				if(alias != null)
					pgas.put(alias, pathGroups);
				else
					pgas.put(PRODUCT_PREFIX + action.getName(), pathGroups);
			}
		}
		else
		{
			for(ActionArtifact productArtifact : productArtifacts)
			{
				PathGroup[] pathGroups = normalizePathGroups(productArtifact.getPathGroups(this, null));
				String alias = productArtifact.getAlias();
				if(alias != null)
					pgas.put(alias, pathGroups);
				else
					pgas.put(PRODUCT_PREFIX + productArtifact.getName(), pathGroups);
			}
		}
	}

	public CSpec findCSpec(ICSpecData ownerCSpec, ComponentRequest request) throws CoreException
	{
		return m_globalCtx.findCSpec(ownerCSpec, request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.buckminster.core.actor.IActionContext#getAction()
	 */
	public Action getAction()
	{
		return m_action;
	}

	public IProgressMonitor getCancellationMonitor()
	{
		return new SubProgressMonitor(m_cancellationMonitor, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.buckminster.core.actor.IActionContext#getComponentLocation()
	 */
	public IPath getComponentLocation() throws CoreException
	{
		return getCSpec().getComponentLocation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.buckminster.core.actor.IActionContext#getCSpec()
	 */
	public CSpec getCSpec() throws CoreException
	{
		return m_action.getCSpec();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.buckminster.core.actor.IActionContext#getErrorStream()
	 */
	public PrintStream getErrorStream()
	{
		return m_errorStream;
	}

	public GlobalContext getGlobalContext()
	{
		return m_globalCtx;
	}

	public Map<String, PathGroup[]> getNamedPathGroupArrays() throws CoreException
	{
		HashMap<String, PathGroup[]> pgas = new HashMap<String, PathGroup[]>();
		addPrerequisitePathGroups(pgas);
		addProductPathGroup(pgas);
		return pgas;
	}

	public PrintStream getOutputStream()
	{
		return m_outputStream;
	}

	public Map<String, PathGroup[]> getPathGroupsCache()
	{
		return m_globalCtx.getPathGroupsCache();
	}

	public Map<String, ? extends Object> getProperties()
	{
		return m_properties;
	}

	public boolean isForced()
	{
		return m_globalCtx.isForcedExecution() || m_action.isAlways();
	}

	public boolean isQuiet()
	{
		return m_globalCtx.isQuietExecution();
	}

	public File makeAbsolute(File file) throws CoreException
	{
		if(!file.isAbsolute())
			file = getComponentLocation().append(file.toString()).toFile();
		return file;
	}

	public IPath makeAbsolute(IPath path) throws CoreException
	{
		if(!path.isAbsolute())
			path = getComponentLocation().append(path);
		return path;
	}
}
