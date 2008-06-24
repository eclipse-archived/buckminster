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
public class SetProvider extends RepositoryChange
{
	private static final long serialVersionUID = 8194967874566800599L;

	private String m_provider;

	@Override
	public void apply(IRepository repository)
	{
		repository.setProvider(m_provider);
	}

	public String getProvider()
	{
		return m_provider;
	}

	public void setProvider(String provider)
	{
		m_provider = provider;
	}
}
