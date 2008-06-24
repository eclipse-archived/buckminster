/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.change;

import java.net.URL;

import org.eclipse.equinox.internal.provisional.p2.core.repository.IRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;

/**
 * @author Thomas Hallgren
 */
public class AddReference extends RepositoryChange
{
	private static final long serialVersionUID = 4143309261875036277L;

	private URL m_location;

	private int m_type;

	private int m_options;

	@Override
	public void apply(IRepository repository)
	{
		((IMetadataRepository)repository).addReference(m_location, m_type, m_options);
	}

	public URL getLocation()
	{
		return m_location;
	}

	public int getOptions()
	{
		return m_options;
	}

	public int getType()
	{
		return m_type;
	}

	public void setLocation(URL location)
	{
		m_location = location;
	}

	public void setOptions(int options)
	{
		m_options = options;
	}

	public void setType(int type)
	{
		m_type = type;
	}
}
