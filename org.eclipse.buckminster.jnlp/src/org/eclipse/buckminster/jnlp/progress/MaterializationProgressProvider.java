/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.progress;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.ProgressProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Karel Brezina
 *
 */
public class MaterializationProgressProvider extends ProgressProvider
{
	private Composite m_composite;

	public MaterializationProgressProvider(Composite parent)
	{
		m_composite = new Composite(parent, SWT.NONE);
		m_composite.setLayout(new GridLayout());
		m_composite.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	@Override
	public IProgressMonitor createMonitor(Job job)
	{
		return new MaterializationSubProgressMonitor(m_composite);
	}
}
