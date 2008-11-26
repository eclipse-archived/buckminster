/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.pde.mapfile;

import java.util.Map;

import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.reader.IReaderType;

/**
 * @author Thomas Hallgren
 */
public class MapFileEntry
{
	private final ComponentIdentifier m_componentIdentifier;

	private final IReaderType m_readerType;

	private final Map<String, String> m_properties;

	protected MapFileEntry(ComponentIdentifier componentIdentifier, IReaderType readerType,
			Map<String, String> properties)
	{
		m_componentIdentifier = componentIdentifier;
		m_readerType = readerType;
		m_properties = properties;
	}

	public ComponentIdentifier getComponentIdentifier()
	{
		return m_componentIdentifier;
	}

	public Map<String, String> getProperties()
	{
		return m_properties;
	}

	public IReaderType getReaderType()
	{
		return m_readerType;
	}

	@Override
	public String toString()
	{
		StringBuilder bld = new StringBuilder();
		m_componentIdentifier.toString(bld);
		bld.append(", ");
		bld.append(m_readerType.getId());
		bld.append(", ");
		bld.append(m_properties);
		return bld.toString();
	}
}
