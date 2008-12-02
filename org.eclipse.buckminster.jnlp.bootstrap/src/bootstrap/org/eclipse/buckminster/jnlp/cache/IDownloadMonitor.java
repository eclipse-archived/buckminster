/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.cache;

import java.net.URL;

import org.eclipse.buckminster.jnlp.bootstrap.OperationCanceledException;

/**
 * @author Filip Hrbek
 * 
 *         A monitor similar to JNLP DownloadServiceListener (easy to refactor implementing classes for JNLP technology)
 */
public interface IDownloadMonitor
{
	public void checkCanceled() throws OperationCanceledException;

	public void downloadFailed(URL url, String version);

	public boolean isCanceled();

	public void progress(URL url, String version, long readSoFar, long total, int overallPercent);

	public void setCanceled(boolean canceled);
}
