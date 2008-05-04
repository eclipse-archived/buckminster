/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.rssowl;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public class OwlSyncJob extends Job
{

	public OwlSyncJob()
	{
		super("Synchronizing component rss feed bookmarks");
	}

	@Override
	public IStatus run(IProgressMonitor monitor)
	{
		monitor.worked(1);
		try
		{
			OwlSynchronizer.syncAllResolutions();
		}
		catch(CoreException e)
		{
			e.printStackTrace();
			return new Status(Status.ERROR, Activator.PLUGIN_ID, "exception while synchronizing RSS OWL bookmarks", e);
		}
		monitor.done();
		return Status.OK_STATUS;
	}	
}