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
public abstract class RepositoryChange
{
	private long m_timestamp;

	public abstract void apply(IRepository repository);

	public long getTimestamp()
	{
		return m_timestamp;
	}

	public void setTimestamp(long changeNumber)
	{
		m_timestamp = changeNumber;
	}
}
