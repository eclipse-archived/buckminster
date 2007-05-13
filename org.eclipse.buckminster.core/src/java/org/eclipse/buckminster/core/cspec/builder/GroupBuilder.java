/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.Group;
import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public class GroupBuilder extends AttributeBuilder
{
	private final ArrayList<PrerequisiteBuilder> m_prerequisites = new ArrayList<PrerequisiteBuilder>();
	
	private IPath m_rebase;

	GroupBuilder(CSpecBuilder cspecBuilder)
	{
		super(cspecBuilder);
	}

	public static int indexOfPrerequisite(List<PrerequisiteBuilder> prerequisites, String prerequisiteKey)
	{
		int idx = prerequisites.size();
		while(--idx >= 0)
			if(prerequisites.get(idx).toString().equals(prerequisiteKey))
				return idx;
		return -1;
	}

	@Override
	public void addPrerequisite(PrerequisiteBuilder prerequisite) throws PrerequisiteAlreadyDefinedException
	{
		String key = prerequisite.toString();
		if(indexOfPrerequisite(m_prerequisites, key) >= 0)
			throw new PrerequisiteAlreadyDefinedException(getCSpecName(), getName(), key);
		m_prerequisites.add(prerequisite);
	}

	@Override
	public void removePrerequisite(String prerequisteName)
	{
		int idx = indexOfPrerequisite(m_prerequisites, prerequisteName);
		if(idx >= 0)
			m_prerequisites.remove(idx);
	}

	public void addSelfRequirement() throws PrerequisiteAlreadyDefinedException
	{
		addLocalPrerequisite(CSpec.SELF_ARTIFACT);
	}

	@Override
	public void clear()
	{
		super.clear();
		m_prerequisites.clear();
		m_rebase = null;
	}

	@Override
	public Group createAttribute()
	{
		return new Group(this);
	}

	public IPath getRebase()
	{
		return m_rebase;
	}

	@Override
	public void initFrom(NamedElement namedElement)
	{
		Group group = (Group)namedElement;
		super.initFrom(group);
		for(Prerequisite pq : group.getPrerequisites())
		{
			PrerequisiteBuilder pb = createPrerequisiteBuilder();
			pb.initFrom(pq);
			m_prerequisites.add(pb);
		}
		m_rebase = group.getPrerequisiteRebase();
	}

	public void setRebase(IPath rebase)
	{
		m_rebase = rebase;
	}

	public List<PrerequisiteBuilder> getPrerequisites()
	{
		return m_prerequisites;
	}

	public List<Prerequisite> getPrerequisiteList()
	{
		int top = (m_prerequisites == null) ? 0 : m_prerequisites.size();
		if(top == 0)
			return Collections.emptyList();

		ArrayList<Prerequisite> bld = new ArrayList<Prerequisite>(top);
		for(int idx = 0; idx < top; ++idx)
			bld.add(m_prerequisites.get(idx).createPrerequisite());
		return bld;
	}
}
