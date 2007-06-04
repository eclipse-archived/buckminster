/**
 * 
 */
package org.eclipse.buckminster.jnlp.bootstrap;

import java.net.URL;

import javax.jnlp.DownloadServiceListener;

public class ProgressFacade implements DownloadServiceListener
{
	private static long s_numberOfUnits;
	private static long s_unitsProduced;

	// From DownloadServiceListener
	/**
	 * Displays progress as 0% - resets progress.
	 */
	public void downloadFailed(URL arg0, String arg1)
	{
		SplashWindow.setTaskName("Failed");
		// Don't know what to do... errors are up to someone else to display
		SplashWindow.setProgress(0);
	}

	// From DownloadServiceListener
	/**
	 * Displays progress by showing percentage of total/readSoFar. All other parameters are ignored.
	 */
	public void progress(URL url, String version, long readSoFar, long total, int overallPercent)
	{
		SplashWindow.setTaskName("Progress");
		int progress = (int)((total <= 0) ? 0 : (readSoFar * 100) / total);
		SplashWindow.setProgress(progress);
	}

	// From DownloadServiceListener
	/**
	 * Displays progress by showing patchPercent. All other parameters are ignored.
	 */
	public void upgradingArchive(URL url, String version, int patchPercent, int overallPercent)
	{
		SplashWindow.setTaskName("Upgrading");
		SplashWindow.setProgress(patchPercent);
	}

	// From DownloadServiceListener
	/**
	 * Displays progress by showing percentage of total/entry. All other parameters are ignored.
	 */
	public void validating(URL url, String version, long entry, long total, int overallPercent)
	{
		SplashWindow.setTaskName("Validating");
		int progress = (int)((total <= 0) ? 0 : (entry * 100) / total);
		SplashWindow.setProgress(progress);
	}
	
	/**
	 * Starts a progress sequence. Sets a numberOfUntis to reach. Progress is reported via
	 * taskProgress, taskIncrementalProgress, or taskDone. Sets progress to 0%. 
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
	 * Report progress - how much of the task is done measured in same unit as set int
	 * setTask. 
	 * @param unitsProduced
	 */
	public void taskProgress(long unitsProduced)
	{
		s_unitsProduced = unitsProduced;
		int progress = (int)((s_numberOfUnits <= 0) ? 0 : (unitsProduced * 100) / s_numberOfUnits);
		SplashWindow.setProgress(progress);
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
}