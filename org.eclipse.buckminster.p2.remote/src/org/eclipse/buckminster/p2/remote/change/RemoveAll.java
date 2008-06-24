/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.change;

import org.eclipse.equinox.internal.provisional.p2.core.repository.IRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;

/**
 * @author Thomas Hallgren
 */
public class RemoveAll extends RepositoryChange
{
	private static final long serialVersionUID = 808635763319242879L;

	@Override
	public void apply(IRepository repository)
	{
		((IMetadataRepository)repository).removeAll();
	}
}
