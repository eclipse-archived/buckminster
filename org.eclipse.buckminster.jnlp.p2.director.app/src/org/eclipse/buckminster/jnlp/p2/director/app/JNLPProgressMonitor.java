/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.p2.director.app;

import org.eclipse.buckminster.jnlp.p2.bootstrap.ProgressFacade;
import org.eclipse.buckminster.jnlp.p2.bootstrap.SplashWindow;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Karel Brezina
 *
 */
public class JNLPProgressMonitor implements IProgressMonitor
{
	private ProgressFacade m_progressFacade;
	
	public JNLPProgressMonitor()
	{
		m_progressFacade = SplashWindow.getDownloadServiceListener();
	}
	
	public void beginTask(String name, int totalWork)
	{
		m_progressFacade.setTask(name, totalWork);	
	}

	public void done()
	{
		m_progressFacade.taskDone();	
	}

	public void internalWorked(double work)
	{
		worked((int)work);
	}

	public boolean isCanceled()
	{
		return m_progressFacade.isCanceled();
	}

	public void setCanceled(boolean value)
	{
		m_progressFacade.setCanceled(value);	
	}

	public void setTaskName(String name)
	{
		// not supported		
	}

	public void subTask(String name)
	{
		// not supported	
	}

	public void worked(int work)
	{
		m_progressFacade.taskIncrementalProgress(work);	
	}

}
