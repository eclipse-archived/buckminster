/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ant.ui.prefs;

import org.eclipse.buckminster.ant.AntPreferences;
import org.eclipse.buckminster.ui.LabeledCombo;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.prefs.LogLevel;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * @author Thomas Hallgren
 *
 */
public class AntPreferencePage extends PreferencePage implements IWorkbenchPreferencePage
{
	private LabeledCombo m_logLevel;

	@Override
	protected Control createContents(Composite parent)
	{
		Group loggingGroup = new Group(parent, SWT.NONE);
		loggingGroup.setLayout(new GridLayout(1, false));
		loggingGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		loggingGroup.setText("Logging control");
		m_logLevel = UiUtils.createEnumCombo(loggingGroup, "Level:", LogLevel.values(), null);
		LogLevel.setComboLogLevel(m_logLevel, AntPreferences.getLogLevel());
		return loggingGroup;
	}

	public void init(IWorkbench workbench)
	{
	}

	@Override
	public boolean performOk()
	{
		AntPreferences.setLogLevel(LogLevel.values()[m_logLevel.getSelectionIndex()].getLogLevel());
		return true;
	}
}
