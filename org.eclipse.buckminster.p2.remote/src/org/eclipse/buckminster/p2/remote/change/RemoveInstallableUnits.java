/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.change;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.internal.provisional.p2.core.repository.IRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.query.Query;

/**
 * @author Thomas Hallgren
 */
public class RemoveInstallableUnits extends RepositoryChange
{
	private static String makeKey(IInstallableUnit iu)
	{
		return iu.getId() + '/' + iu.getVersion();
	}

	private Set<String> m_removals;

	public void addUnitToRemove(IInstallableUnit unit)
	{
		if(m_removals == null)
			m_removals = new HashSet<String>();
		m_removals.add(makeKey(unit));
	}

	@Override
	public void apply(IRepository repository)
	{
		((IMetadataRepository)repository).removeInstallableUnits(new Query()
		{
			@Override
			public boolean isMatch(Object candidate)
			{
				return candidate instanceof IInstallableUnit
					&& m_removals.contains(makeKey((IInstallableUnit)candidate));
			}
		}, new NullProgressMonitor());
	}

	public Set<String> getRemovals()
	{
		return m_removals;
	}

	public void setRemovals(Set<String> removals)
	{
		m_removals = removals;
	}
}
