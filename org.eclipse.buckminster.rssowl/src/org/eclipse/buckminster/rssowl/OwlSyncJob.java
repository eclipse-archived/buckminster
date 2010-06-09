/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.rssowl;

import org.eclipse.buckminster.generic.utils.ProgressUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.rssowl.core.internal.InternalOwl;
import org.rssowl.core.util.LoggingSafeRunnable;

@SuppressWarnings("restriction")
public class OwlSyncJob extends Job
{

	public OwlSyncJob()
	{
		super("Synchronizing Component RSS Feed Bookmarks");
	}

	@Override
	public IStatus run(final IProgressMonitor monitor)
	{
		final IProgressMonitor waitOwlMonitor = ProgressUtils.submon(monitor, 1);
		waitOwlMonitor.beginTask("Waiting for RSS OWL", IProgressMonitor.UNKNOWN);
		
		monitor.worked(1);
		try
		{
			if(!InternalOwl.getDefault().isStarted())
			{
				final IProgressMonitor initOwlMonitor = ProgressUtils.submon(monitor, 1);
				initOwlMonitor.beginTask("Initializing RSS OWL", 2);
				/* Activate Internal Owl (Done by UI in RSS OWL M6) - and we are not in UI here */
				SafeRunner.run(new LoggingSafeRunnable()
				{
					public void run() throws Exception
					{
						Display.getDefault().asyncExec(new Runnable(){

							public void run()
							{
								initOwlMonitor.worked(1);
								// Trigger loading of the rssowl ui plugin.
								org.rssowl.ui.internal.Activator.getDefault();
								initOwlMonitor.worked(1);
								initOwlMonitor.done();
							}
							
						});
					}
				});
			}
			// wait for OWL to start
			while(!InternalOwl.getDefault().isStarted())
			{
				try
				{
					waitOwlMonitor.worked(1);
					Thread.sleep(1000);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
					return new Status(Status.ERROR, Activator.PLUGIN_ID,
							"exception while synchronizing RSS OWL bookmarks", e);
				}
			}
			waitOwlMonitor.done();
			OwlSynchronizer.syncAllResolutions(monitor);
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
