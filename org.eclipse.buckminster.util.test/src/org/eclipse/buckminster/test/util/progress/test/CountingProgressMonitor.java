/*******************************************************************************
 * Copyright (c) 2009 Cloudsmith Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cloudsmith Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.buckminster.test.util.progress.test;

import org.eclipse.core.runtime.IProgressMonitorWithBlocking;
import org.eclipse.core.runtime.IStatus;

/**
 * Progress monitor that support cancellation, and counts the number of times each
 * method is called. Keeps track of work amount in {@link #totWork}, and reported amount in
 * {@link #internalWork}.
 * 
 * @author henrik.lindberg@cloudsmith.com
 *
 */
public class CountingProgressMonitor implements IProgressMonitorWithBlocking
{

	public int totWork;
	public int beginCounter = 0;
	public int doneCounter = 0;
	public double internalWork = 0.0;
	public int internalWorkCounter = 0;
	private boolean canceled = false;
	public int cancelCounter = 0;
	public int isCanceledCounter = 0;
	public int taskNameCounter =0;
	public int subTaskCounter =0;
	public int workedAmount =0;
	public int workedCounter=0;
	public int clearBlockedCounter =0;
	public int setBlockedCounter = 0;

	public void beginTask(String name, int totalWork)
	{
		totWork = totalWork;
		beginCounter ++;
	}

	public void done()
	{
		doneCounter ++;
	}

	public void internalWorked(double work)
	{
		internalWorkCounter++;
		internalWork += work;
	}

	public boolean isCanceled()
	{
		isCanceledCounter ++;
		return canceled ;
	}

	public void setCanceled(boolean value)
	{
		canceled = value;
		cancelCounter++;
	}

	public void setTaskName(String name)
	{
		taskNameCounter++;
	}

	public void subTask(String name)
	{
		subTaskCounter++;
	}

	public void worked(int work)
	{
		workedAmount += work;
		workedCounter++;
	}

	public void clearBlocked()
	{
		clearBlockedCounter++;
	}

	public void setBlocked(IStatus reason)
	{
		setBlockedCounter++;
	}

}
