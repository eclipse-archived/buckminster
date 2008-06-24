/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.change;

import org.eclipse.equinox.internal.provisional.p2.core.repository.IRepository;

/**
 * @author Thomas Hallgren
 */
public class SetProperty extends RepositoryChange
{
	private static final long serialVersionUID = 1402787089945350035L;

	private String m_key;

	private String m_value;

	@Override
	public void apply(IRepository repository)
	{
		repository.setProperty(m_key, m_value);
	}

	public String getKey()
	{
		return m_key;
	}

	public String getValue()
	{
		return m_value;
	}

	public void setKey(String key)
	{
		m_key = key;
	}

	public void setValue(String value)
	{
		m_value = value;
	}
}
