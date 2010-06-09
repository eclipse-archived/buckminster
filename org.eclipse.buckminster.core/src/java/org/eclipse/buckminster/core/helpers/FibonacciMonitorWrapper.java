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
public class FibonacciMonitorWrapper extends ProgressMonitorWrapper {
	private int multiplier = 0x10000;

	private double totalWork = 0;

	private double worked = 0;

	private static final double goldenRatio = 89.0 / 55.0;

	public FibonacciMonitorWrapper(IProgressMonitor monitor) {
		super(monitor);
	}

	@Override
	public void beginTask(String name, int work) {
		totalWork *= multiplier;
		totalWork = work;
		this.getWrappedProgressMonitor().beginTask(name, work);
	}

	@Override
	public void internalWorked(double work) {
		double attempt = work * multiplier;
		while (worked + attempt > totalWork / goldenRatio && multiplier > 1) {
			multiplier >>= 1;
			totalWork -= worked;
			worked = 0;
			attempt = work * multiplier;
			continue;
		}

		worked += attempt;
		if (worked >= totalWork) {
			attempt = 0;
			worked = totalWork;
		}
		this.getWrappedProgressMonitor().internalWorked(attempt);
	}

	@Override
	public void worked(int work) {
		this.internalWorked(work);
	}
}
