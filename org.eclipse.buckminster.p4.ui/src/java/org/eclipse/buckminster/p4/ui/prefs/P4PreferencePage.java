/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.ui.prefs;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * @author Thomas Hallgren
 */
public class P4PreferencePage extends PreferencePage implements IWorkbenchPreferencePage
{
	private RootPane m_rootPane;

	public P4PreferencePage()
	{
		super();
		this.noDefaultAndApplyButton();
	}

	@Override
	protected Control createContents(Composite parent)
	{
		m_rootPane = new RootPane(this, parent);
		m_rootPane.init();
		return m_rootPane;
	}

	public void init(IWorkbench workbench)
	{
	}

	@Override
	public boolean performOk()
	{
		return m_rootPane.performOk();
	}
}
