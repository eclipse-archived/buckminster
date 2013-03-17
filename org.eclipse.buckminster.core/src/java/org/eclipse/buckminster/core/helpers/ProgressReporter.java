/*******************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
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
 *         This class is used for reporting a good progress information even if
 *         the download is stalled for a while. If specified timeout expires
 *         before something is read from the input, a new progress message is
 *         generated. This enables e.g. reporting recalculated download speed
 *         which would be frozen if we simply waited for data.
 */
public class ProgressReporter extends Thread {
	private final IProgressMonitor reporterMonitor;

	private final ProgressStatistics reporterProgress;

	private final String format;

	private final int timeout;

	private boolean running;

	public ProgressReporter(IProgressMonitor reporterMonitor, ProgressStatistics reporterProgress, String format, int timeout) {
		this.reporterMonitor = reporterMonitor;
		this.reporterProgress = reporterProgress;
		this.format = format;
		this.timeout = timeout;
		this.running = true;
	}

	@Override
	public void run() {
		while (running && !reporterMonitor.isCanceled()) {
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				// ignore, it's ok
			}

			synchronized (this) {
				if (running && reporterProgress.shouldReport())
					reporterMonitor.subTask(String.format(format, reporterProgress.report()));
			}
		}
	}

	public synchronized void stopReporting() {
		running = false;
		interrupt();
	}
}
