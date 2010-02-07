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

import org.eclipse.buckminster.core.Messages;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.osgi.util.NLS;

/**
 * Block job execution based on the name or class of the job.
 * 
 * @author Thomas Hallgren
 */
public class JobBlocker extends JobChangeAdapter {
	private static void trace(String format, Object... args) {
		// We can't trust that the CorePlugin is still active
		// since some jobs might outlive it.
		//
		// try
		// {
		// CorePlugin.getLogger().debug(format, args);
		// }
		// catch(Throwable e)
		// {
		// if(args.length > 0)
		// format = String.format(format, args);
		// System.out.println(format);
		// }
	}

	private final Set<String> blockByName = Collections.synchronizedSet(new HashSet<String>());

	private final Set<String> blockByClass = Collections.synchronizedSet(new HashSet<String>());

	public JobBlocker() {
		Job.getJobManager().addJobChangeListener(this);
	}

	@Override
	public void aboutToRun(IJobChangeEvent event) {
		Job job = event.getJob();
		String jobName = job.getName();
		String className = job.getClass().getName();
		trace(NLS.bind(Messages.JOB_AboutToRun_0, jobName));
		if (blockByName.contains(jobName) || blockByClass.contains(className)) {
			job.cancel();
			trace(NLS.bind(Messages.Blocked_0_1, className, jobName));
			return;
		}
	}

	public void addClassBlock(Class<? extends Job> classToBlock) {
		blockByClass.add(classToBlock.getName());
	}

	public void addClassBlock(String className) {
		blockByClass.add(className);
	}

	public void addNameBlock(String nameToBlock) {
		blockByName.add(nameToBlock);
	}

	public void release() {
		Job.getJobManager().removeJobChangeListener(this);
	}

	public void removeClassBlock(Class<? extends Job> classToBlock) {
		blockByClass.remove(classToBlock.getName());
	}

	public void removeClassBlock(String className) {
		blockByClass.remove(className);
	}

	public void removeNameBlock(String nameToBlock) {
		blockByName.remove(nameToBlock);
	}
}
