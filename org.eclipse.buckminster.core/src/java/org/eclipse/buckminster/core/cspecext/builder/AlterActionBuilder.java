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

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.buckminster.core.cspecext.model.AlterAction;
import org.eclipse.buckminster.core.cspecext.model.AlterAttribute;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public class AlterActionBuilder extends AlterAttributeBuilder
{
	public AlterActionBuilder(ActionBuilder baseBuilder)
	{
		super(baseBuilder);
	}

	private final HashMap<String, Prerequisite> m_alteredPrerequisites = new HashMap<String, Prerequisite>();

	private final HashSet<String> m_removedPrerequisites = new HashSet<String>();

	private final ExpandingProperties m_alteredActorProperties = new ExpandingProperties();

	private final HashSet<String> m_removedActorProperties = new HashSet<String>();

	private final ExpandingProperties m_alteredProperties = new ExpandingProperties();

	private final HashSet<String> m_removedProperties = new HashSet<String>();

	private final HashSet<IPath> m_removedPaths = new HashSet<IPath>();

	@Override
	public void clear()
	{
		super.clear();
		m_alteredPrerequisites.clear();
		m_removedPrerequisites.clear();
		m_alteredActorProperties.clear();
		m_removedActorProperties.clear();
		m_alteredProperties.clear();
		m_removedProperties.clear();
		m_removedPaths.clear();
	}

	@Override
	public AlterAttribute<?> createAlterAttribute()
	{
		return new AlterAction(createBase(), getRemovedHints(), getAlteredHints(),
				m_removedPrerequisites, m_alteredPrerequisites, m_removedActorProperties,
				m_alteredActorProperties, m_removedProperties, m_alteredProperties,
				m_removedPaths);
	}

	public void addRemoveProductPath(IPath path)
	{
		m_removedPaths.add(path);
	}

	public void addRemoveActorProperty(String key)
	{
		m_removedActorProperties.add(key);
	}

	public void addAlterActorProperty(String key, String value)
	{
		m_alteredActorProperties.put(key, value);
	}

	public void addRemoveProperty(String key)
	{
		m_removedProperties.add(key);
	}

	public void addAlterProperty(String key, String value)
	{
		m_alteredProperties.put(key, value);
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

	public ExpandingProperties getAlterActorProperties()
	{
		return m_alteredActorProperties;
	}

	public ExpandingProperties getAlterProperties()
	{
		return m_alteredProperties;
	}

	@Override
	Action createBase()
	{
		return ((ActionBuilder)getBaseBuilder()).createAttribute();
	}
}
