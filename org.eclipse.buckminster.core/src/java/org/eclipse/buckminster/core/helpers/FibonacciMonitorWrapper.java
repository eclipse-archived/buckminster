/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.helpers;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ProgressMonitorWrapper;

/**
 * @author Thomas Hallgren
 */
public class FibonacciMonitorWrapper extends ProgressMonitorWrapper
{
	private int m_multiplier = 0x10000;
	private double m_totalWork = 0;
	private double m_worked = 0;
	private static final double s_goldenRatio = 89.0/55.0;

	public FibonacciMonitorWrapper(IProgressMonitor monitor)
	{
		super(monitor);
	}

	@Override
	public void beginTask(String name, int totalWork)
	{
		totalWork *= m_multiplier;
		m_totalWork = totalWork;
		this.getWrappedProgressMonitor().beginTask(name, totalWork);
	}

	@Override
	public void internalWorked(double work)
	{
		double attempt = work * m_multiplier;
		while(m_worked + attempt > m_totalWork / s_goldenRatio &&  m_multiplier > 1)
		{
			m_multiplier >>= 1;
			m_totalWork -= m_worked;
			m_worked = 0;
			attempt = work * m_multiplier;
			continue;
		}

		m_worked += attempt;
		if(m_worked >= m_totalWork)
		{
			attempt = 0;
			m_worked = m_totalWork;
		}
		this.getWrappedProgressMonitor().internalWorked(attempt);
	}

	@Override
	public void worked(int work)
	{
        this.internalWorked(work);
	}
}
