/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.helpers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
	private final Set<String> m_blockByName = Collections.synchronizedSet(new HashSet<String>());
	private final Set<String> m_blockByClass = Collections.synchronizedSet(new HashSet<String>());

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

	public void addClassBlock(String className)
	{
		m_blockByClass.add(className);
	}

	public void addClassBlock(Class<? extends Job> classToBlock)
	{
		m_blockByClass.add(classToBlock.getName());
	}

	public void removeNameBlock(String nameToBlock)
	{
		m_blockByName.remove(nameToBlock);
	}

	public void removeClassBlock(String className)
	{
		m_blockByClass.remove(className);
	}

	public void removeClassBlock(Class<? extends Job> classToBlock)
	{
		m_blockByClass.remove(classToBlock.getName());
	}

	@Override
	public void aboutToRun(IJobChangeEvent event)
	{
		Job job = event.getJob();
		String jobName = job.getName();
		String className = job.getClass().getName();
		trace("JOB: AboutToRun: %s", jobName);
		if(m_blockByName.contains(jobName) || m_blockByClass.contains(className))
		{
			job.cancel();
			trace("blocked(%s[%s])", className, jobName);
			return;
		}
	}

	private static void trace(String format, Object...args)
	{
		// We can't trust that the CorePlugin is still active
		// since some jobs might outlive it.
		//
		//try
		//{
		// 	CorePlugin.getLogger().debug(format, args);
		//}
		//catch(Throwable e)
		//{
		// 	if(args.length > 0)
		// 		format = String.format(format, args);
		// 	System.out.println(format);
		//}
	}
}
