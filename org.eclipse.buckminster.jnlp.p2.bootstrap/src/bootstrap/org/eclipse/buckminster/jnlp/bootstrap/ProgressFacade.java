/**
 * 
 */
package org.eclipse.buckminster.jnlp.bootstrap;

import java.net.URL;

import org.eclipse.buckminster.jnlp.cache.IDownloadMonitor;

public class ProgressFacade implements IDownloadMonitor // DownloadServiceListener
{
	private static long s_numberOfUnits;

	private static long s_unitsProduced;

	private boolean m_canceled = false;

	public void checkCanceled() throws OperationCanceledException
	{
		if(isCanceled())
			throw new OperationCanceledException();
	}

	// From DownloadServiceListener
	/**
	 * Displays progress as 0% - resets progress.
	 */
	public void downloadFailed(URL arg0, String arg1)
	{
		SplashWindow.setTaskName(Messages.getString("failed")); //$NON-NLS-1$
		// Don't know what to do... errors are up to someone else to display
		SplashWindow.setProgress(0);
	}

	public boolean isCanceled()
	{
		return m_canceled;
	}

	// From DownloadServiceListener
	/**
	 * Displays progress by showing percentage of total/readSoFar. All other parameters are ignored.
	 */
	public void progress(URL url, String version, long readSoFar, long total, int overallPercent)
	{
		SplashWindow.setTaskName(Messages.getString("progress")); //$NON-NLS-1$
		int progress = (int)((total <= 0)
				? 0
				: (readSoFar * 100) / total);
		SplashWindow.setProgress(progress);
	}

	public void setCanceled(boolean canceled)
	{
		m_canceled = canceled;
	}

	/**
	 * Starts a progress sequence. Sets a numberOfUntis to reach. Progress is reported via taskProgress,
	 * taskIncrementalProgress, or taskDone. Sets progress to 0%.
	 * 
	 * @param s
	 * @param numberOfUnits
	 */
	public void setTask(String s, long numberOfUnits)
	{
		SplashWindow.setTaskName(s);
		s_numberOfUnits = numberOfUnits;
		s_unitsProduced = 0L;
		SplashWindow.setProgress(0);
	}

	/**
	 * Sets the task progress to 100%
	 */
	public void taskDone()
	{
		SplashWindow.setProgress(100);
	}

	public void taskIncrementalProgress(long increment)
	{
		taskProgress(s_unitsProduced + increment);
	}

	/**
	 * Report progress - how much of the task is done measured in same unit as set int setTask.
	 * 
	 * @param unitsProduced
	 */
	public void taskProgress(long unitsProduced)
	{
		s_unitsProduced = unitsProduced;
		int progress = (int)((s_numberOfUnits <= 0)
				? 0
				: (unitsProduced * 100) / s_numberOfUnits);
		SplashWindow.setProgress(progress);
	}

	// From DownloadServiceListener
	/**
	 * Displays progress by showing patchPercent. All other parameters are ignored.
	 */
	public void upgradingArchive(URL url, String version, int patchPercent, int overallPercent)
	{
		SplashWindow.setTaskName(Messages.getString("upgrading")); //$NON-NLS-1$
		SplashWindow.setProgress(patchPercent);
	}

	// From DownloadServiceListener
	/**
	 * Displays progress by showing percentage of total/entry. All other parameters are ignored.
	 */
	public void validating(URL url, String version, long entry, long total, int overallPercent)
	{
		SplashWindow.setTaskName(Messages.getString("Validating")); //$NON-NLS-1$
		int progress = (int)((total <= 0)
				? 0
				: (entry * 100) / total);
		SplashWindow.setProgress(progress);
	}
}
