/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.builder;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.IAttribute;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspecext.model.AlterAttribute;

/**
 * @author Thomas Hallgren
 */
public abstract class AlterAttributeBuilder
{
	private final AttributeBuilder m_baseBuilder;
	private final HashSet<String> m_removedHints = new HashSet<String>();
	private final ExpandingProperties m_alteredHints = new ExpandingProperties();
	private String m_cspecName = null;

	protected AlterAttributeBuilder(AttributeBuilder baseBuilder)
	{
		m_baseBuilder = baseBuilder;
	}

	public void clear()
	{
		m_removedHints.clear();
		m_alteredHints.clear();
		m_baseBuilder.clear();
	}

	public abstract AlterAttribute<?> createAlterAttribute();

	public void addRemovedInstallerHint(String key)
	{
		m_removedHints.add(key);
	}

	public void addAlteredInstallerHInt(String key, String value)
	{
		m_alteredHints.put(key, value);
	}

	public AttributeBuilder getBaseBuilder()
	{
		return m_baseBuilder;
	}

	public ExpandingProperties getAlteredHints()
	{
		return m_alteredHints;
	}

	public Set<String> getRemovedHints()
	{
		return m_removedHints;
	}

	public String getName()
	{
		return m_baseBuilder.getName();
	}

	public String getCSpecName()
	{
		return m_cspecName;
	}

	public void setCSpecName(String cspecName)
	{
		m_cspecName = cspecName;
	}

	IAttribute createBase()
	{
		return getBaseBuilder().createAttribute();
	}
}
