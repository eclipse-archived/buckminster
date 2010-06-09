/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.download.test;

import java.io.PrintStream;

import org.eclipse.core.runtime.IProgressMonitor;

public class PrintingMonitor implements IProgressMonitor
{
	private int m_previousSoFar;
	private int m_total;
	private boolean m_cancelled;
	private double m_worked;

	public void beginTask(String name, int totalWork)
	{
		System.out.format("-- beginTask(%s, %d)%n", name, Integer.valueOf(totalWork));
		m_total = totalWork;
		m_worked = 0.0;
		m_previousSoFar = -1;
	}

	public void done()
	{
		System.out.println("-- done()");
	}

	public void internalWorked(double work)
	{
		m_worked += work;
		int soFar = (int)(50.0 * m_worked / m_total);
		if(soFar > 50)
			soFar = 50;
		if(soFar != m_previousSoFar)
		{
			m_previousSoFar = soFar;
			PrintStream out = System.out;
			for(int idx = 0; idx < soFar; ++idx)
				out.write('#');
			for(;soFar < 50; ++soFar)
				out.write('-');
			System.out.println();
		}
	}

	public boolean isCanceled()
	{
		return m_cancelled;
	}

	public void setCanceled(boolean value)
	{
		m_cancelled = value;
	}

	public void setTaskName(String name)
	{
		System.out.format("-- setTaskName(%s)%n", name);
	}

	public void subTask(String name)
	{
		System.out.format("-- subTask(%s)%n", name);
	}

	public void worked(int work)
	{
		internalWorked(work);
	}
}