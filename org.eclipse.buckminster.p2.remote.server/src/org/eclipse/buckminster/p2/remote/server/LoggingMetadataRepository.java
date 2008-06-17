/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.p2.remote.server;

import java.io.File;
import java.net.URL;

import org.eclipse.buckminster.p2.remote.change.AddInstallableUnits;
import org.eclipse.buckminster.p2.remote.change.AddReference;
import org.eclipse.buckminster.p2.remote.change.RemoveAll;
import org.eclipse.buckminster.p2.remote.change.RemoveInstallableUnits;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.query.Query;

public class LoggingMetadataRepository extends LoggingRepository implements IMetadataRepository
{
	public LoggingMetadataRepository(IMetadataRepository remoteRepository, File changeLogFile)
	throws CoreException
	{
		super(remoteRepository, changeLogFile);
	}

	public void addInstallableUnits(IInstallableUnit[] installableUnits)
	{
		AddInstallableUnits change = new AddInstallableUnits();
		change.setAdditions(installableUnits);
		getChangeLog().addChange(change);
		getWrapped().addInstallableUnits(installableUnits);
	}

	public void addReference(URL location, int type, int options)
	{
		AddReference change = new AddReference();
		change.setLocation(location);
		change.setType(type);
		change.setOptions(options);
		getChangeLog().addChange(change);
		getWrapped().addReference(location, type, options);
	}

	public Collector query(Query query, Collector collector, IProgressMonitor monitor)
	{
		return getWrapped().query(query, collector, monitor);
	}

	public void removeAll()
	{
		getChangeLog().addChange(new RemoveAll());
		getWrapped().removeAll();
	}

	public boolean removeInstallableUnits(Query query, IProgressMonitor monitor)
	{
		final RemoveInstallableUnits removal = new RemoveInstallableUnits();
		SubMonitor subMon = SubMonitor.convert(monitor, 200);
		query(query, new Collector()
		{
			@Override
			public boolean accept(Object object)
			{
				if(object instanceof IInstallableUnit)
					removal.addUnitToRemove((IInstallableUnit)object);
				return true;
			}
		}, subMon.newChild(100));
		getChangeLog().addChange(removal);
		return getWrapped().removeInstallableUnits(query, subMon.newChild(100));
	}

	private IMetadataRepository getWrapped()
	{
		return (IMetadataRepository)wrappedRepository;
	}
}
