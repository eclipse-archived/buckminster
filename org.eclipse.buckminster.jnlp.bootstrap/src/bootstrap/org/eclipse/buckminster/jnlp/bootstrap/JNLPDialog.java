/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.bootstrap;

import java.awt.Frame;
import java.awt.Image;

/**
 * @author kaja
 * 
 */
public class JNLPDialog extends Frame
{
	private static final long serialVersionUID = -6941477760104636902L;

	private Thread m_sleepingThread = null;

	public JNLPDialog(Image windowIconImage, String windowTitle)
	{
		super(windowTitle);

		if(windowIconImage != null)
		{
			setIconImage(windowIconImage);
		}
	}

	public void open()
	{
		setVisible(true);

		m_sleepingThread = Thread.currentThread();

		try
		{
			while(true)
			{
				Thread.sleep(60000);
			}
		}
		catch(InterruptedException e1)
		{
			// Dialog was finished
		}
	}

	protected void finish()
	{
		dispose();

		if(m_sleepingThread != null)
		{
			m_sleepingThread.interrupt();
		}
	}
}
