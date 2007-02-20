/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.builder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.model.Group;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.buckminster.core.cspecext.model.AlterAttribute;
import org.eclipse.buckminster.core.cspecext.model.AlterGroup;

/**
 * @author Thomas Hallgren
 */
public class AlterGroupBuilder extends AlterAttributeBuilder
{
	public AlterGroupBuilder(GroupBuilder baseBuilder)
	{
		super(baseBuilder);
	}

	private final HashMap<String,Prerequisite> m_alteredPrerequisites = new HashMap<String, Prerequisite>();
	private final HashSet<String> m_removedPrerequisites = new HashSet<String>();

	@Override
	public void clear()
	{
		super.clear();
		m_alteredPrerequisites.clear();
		m_removedPrerequisites.clear();
	}

	@Override
	public AlterAttribute<?> createAlterAttribute()
	{
		return new AlterGroup((Group)createBase(), 
				getRemovedHints(), getAlteredHints(),
				m_removedPrerequisites, m_alteredPrerequisites);
	}

	public void addRemovePrerequisite(String key)
	{
		m_removedPrerequisites.add(key);
	}

	public void addAlterPrerequisite(Prerequisite value) throws PrerequisiteAlreadyDefinedException
	{
		String key = value.toString();
		if(m_alteredPrerequisites.containsKey(key))
			throw new PrerequisiteAlreadyDefinedException(getCSpecName(), getName(), key);

		List<PrerequisiteBuilder> basePreqs = ((ActionBuilder)getBaseBuilder()).getPrerequisitesBuilder().getPrerequisites();
		if(GroupBuilder.indexOfPrerequisite(basePreqs, key) >= 0)
			throw new PrerequisiteAlreadyDefinedException(getCSpecName(), getName(), key);

		m_alteredPrerequisites.put(key, value);
	}
}
