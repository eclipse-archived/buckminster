/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.change;

import org.eclipse.equinox.internal.provisional.p2.core.repository.IRepository;

public class SetDescription extends RepositoryChange
{
	private static final long serialVersionUID = -7626483755852814672L;

	private String m_description;

	@Override
	public void apply(IRepository repository)
	{
		repository.setDescription(m_description);
	}

	public String getDescription()
	{
		return m_description;
	}

	public void setDescription(String description)
	{
		m_description = description;
	}
}
