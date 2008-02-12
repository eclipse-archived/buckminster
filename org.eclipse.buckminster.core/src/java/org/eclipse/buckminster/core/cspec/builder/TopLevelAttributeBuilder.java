/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import java.util.Map;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.TopLevelAttribute;

/**
 * @author Thomas Hallgren
 */
public abstract class TopLevelAttributeBuilder extends AttributeBuilder
{
	private ExpandingProperties m_installerHints = null;

	private boolean m_public = false;

	TopLevelAttributeBuilder(CSpecBuilder cspecBuilder)
	{
		super(cspecBuilder);
	}

	public final void addExternalPrerequisite(String name, String attr) throws PrerequisiteAlreadyDefinedException
	{
		addPrerequisite(createPrerequisite(name, attr, null));
	}

	public void addInstallerHint(String key, String hint)
	{
		getInstallerHintsForAdd().put(key, hint, false);
	}

	public void addInstallerHint(String key, String hint, boolean mutable)
	{
		getInstallerHintsForAdd().put(key, hint, mutable);
	}

	public void addInstallerHints(Map<String,String> hints)
	{
		if(hints != null && hints.size() > 0)
			getInstallerHintsForAdd().putAll(hints, true);
	}

	public final void addLocalPrerequisite(AttributeBuilder attr) throws PrerequisiteAlreadyDefinedException
	{
		addLocalPrerequisite(attr.getName());
	}

	public final void addLocalPrerequisite(String attr) throws PrerequisiteAlreadyDefinedException
	{
		addPrerequisite(createPrerequisite(null, attr, null));
	}

	public final void addLocalPrerequisite(String attr, String alias) throws PrerequisiteAlreadyDefinedException
	{
		addPrerequisite(createPrerequisite(null, attr, alias));
	}

	public void addPrerequisite(PrerequisiteBuilder prerequisite) throws PrerequisiteAlreadyDefinedException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear()
	{
		super.clear();
		m_installerHints = null;
		m_public = false;
	}

	@Override
	public abstract Attribute createAttribute();

	public PrerequisiteBuilder createPrerequisiteBuilder()
	{
		return new PrerequisiteBuilder(this);
	}

	public ExpandingProperties getInstallerHints()
	{
		return m_installerHints;
	}

	public ExpandingProperties getInstallerHintsForAdd()
	{
		if(m_installerHints == null)
			m_installerHints = new ExpandingProperties();
		return m_installerHints;
	}

	@Override
	public boolean isPublic()
	{
		return m_public;
	}

	public void setPublic(boolean flag)
	{
		m_public = flag;
	}

	@Override
	public void initFrom(NamedElement namedElement)
	{
		TopLevelAttribute attribute = (TopLevelAttribute)namedElement;
		super.initFrom(attribute);
		m_installerHints = null;
		m_public = attribute.isPublic();
		addInstallerHints(attribute.getInstallerHints());
	}

	private PrerequisiteBuilder createPrerequisite(String component, String name, String alias)
	{
		PrerequisiteBuilder bld = createPrerequisiteBuilder();
		bld.setComponent(component);
		bld.setName(name);
		bld.setAlias(alias);
		return bld;
	}

	public void removePrerequisite(Prerequisite pq)
	{
		removePrerequisite(pq.toString());
	}

	public void removePrerequisite(String prerequisiteName)
	{
		throw new UnsupportedOperationException();
	}
}