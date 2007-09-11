/*******************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.core.helpers;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Filip Hrbek
 *
 * This class is used for reporting a good progress information even if the download is stalled
 * for a while. If specified timeout expires before something is read from the input, a new
 * progress message is generated. This enables e.g. reporting recalculated download speed which
 * would be frozen if we simply waited for data.
 */
public class ProgressReporter extends Thread
{
	private final IProgressMonitor m_reporterMonitor;

	private final ProgressStatistics m_reporterProgress;

	private final String m_format;

	private final int m_timeout;

	private boolean m_running;

	public ProgressReporter(IProgressMonitor reporterMonitor,
			ProgressStatistics reporterProgress, String format, int timeout)
	{
		m_reporterMonitor = reporterMonitor;
		m_reporterProgress = reporterProgress;
		m_format = format;
		m_timeout = timeout;
		m_running = true;
	}

	@Override
	public void run()
	{
		while(m_running && !m_reporterMonitor.isCanceled())
		{
			try
			{
				Thread.sleep(m_timeout);
				synchronized(this)
				{
					if(m_running && m_reporterProgress.shouldReport())
						m_reporterMonitor.subTask(String.format(m_format, m_reporterProgress.report()));
				}
			}
			catch(InterruptedException e)
			{
				// ignore, it's ok
			}
		}
	}

	public synchronized void stopReporting()
	{
		m_running = false;
		interrupt();
	}
}
