/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.helpers;

import java.util.HashSet;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;

/**
 * Block job execution based on the name or class of the job.
 *
 * @author Thomas Hallgren
 */
public class JobBlocker extends JobChangeAdapter
{
	private final HashSet<String> m_blockByName = new HashSet<String>();
	private final HashSet<Class<? extends Job>> m_blockByClass = new HashSet<Class<? extends Job>>();

	public JobBlocker()
	{
		Job.getJobManager().addJobChangeListener(this);
	}

	public void release()
	{
		Job.getJobManager().removeJobChangeListener(this);
	}

	public void addNameBlock(String nameToBlock)
	{
		m_blockByName.add(nameToBlock);
	}

	public void addClassBlock(Class<? extends Job> classToBlock)
	{
		m_blockByClass.add(classToBlock);
	}

	@Override
	public void aboutToRun(IJobChangeEvent event)
	{
		Job job = event.getJob();
		String jobName = job.getName();
		if(m_blockByName.contains(jobName) || m_blockByClass.contains(job.getClass()))
		{
			job.cancel();
			Logger logger = CorePlugin.getLogger();
			if(logger.isDebugEnabled())
				logger.debug(String.format("blocked(%s[%s])", job.getClass(), jobName));
			return;
		}
	}
}
