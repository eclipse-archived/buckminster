/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import org.eclipse.buckminster.core.cspec.IAttribute;
import org.eclipse.buckminster.core.cspec.IPrerequisite;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.buckminster.osgi.filter.Filter;

/**
 * @author Thomas Hallgren
 */
public abstract class TopLevelAttributeBuilder extends AttributeBuilder
{
	private boolean m_public = false;

	TopLevelAttributeBuilder(CSpecBuilder cspecBuilder)
	{
		super(cspecBuilder);
	}

	public final void addExternalPrerequisite(String name, String type, String attr)
			throws PrerequisiteAlreadyDefinedException
	{
		addPrerequisite(createPrerequisite(name, type, attr, null, null));
	}

	public final void addLocalPrerequisite(AttributeBuilder attr) throws PrerequisiteAlreadyDefinedException
	{
		addLocalPrerequisite(attr.getName());
	}

	public final void addLocalPrerequisite(String attr) throws PrerequisiteAlreadyDefinedException
	{
		addPrerequisite(createPrerequisite(null, null, attr, null, null));
	}

	public final void addLocalPrerequisite(String attr, String alias) throws PrerequisiteAlreadyDefinedException
	{
		addPrerequisite(createPrerequisite(null, null, attr, alias, null));
	}

	public final void addLocalPrerequisite(String attr, String alias, Filter filter)
			throws PrerequisiteAlreadyDefinedException
	{
		addPrerequisite(createPrerequisite(null, null, attr, alias, filter));
	}

	public void addPrerequisite(PrerequisiteBuilder prerequisite) throws PrerequisiteAlreadyDefinedException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear()
	{
		super.clear();
		m_public = false;
	}

	@Override
	public abstract Attribute createAttribute();

	public PrerequisiteBuilder createPrerequisiteBuilder()
	{
		return new PrerequisiteBuilder(this);
	}

	@Override
	public void initFrom(IAttribute attribute)
	{
		super.initFrom(attribute);
		m_public = attribute.isPublic();
	}

	@Override
	public boolean isPublic()
	{
		return m_public;
	}

	public void removePrerequisite(IPrerequisite pq)
	{
		removePrerequisite(pq.toString());
	}

	public void removePrerequisite(String prerequisiteName)
	{
		throw new UnsupportedOperationException();
	}

	public void setPublic(boolean flag)
	{
		m_public = flag;
	}

	private PrerequisiteBuilder createPrerequisite(String component, String type, String name, String alias, Filter filter)
	{
		PrerequisiteBuilder bld = createPrerequisiteBuilder();
		bld.setComponentName(component);
		bld.setComponentType(type);
		bld.setName(name);
		bld.setAlias(alias);
		bld.setFilter(filter);
		return bld;
	}
}
