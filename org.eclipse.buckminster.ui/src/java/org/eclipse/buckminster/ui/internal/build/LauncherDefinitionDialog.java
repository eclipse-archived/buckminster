/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ui.internal.build;

import org.eclipse.buckminster.core.build.LauncherDefinition;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author kolwing
 *
 */
public class LauncherDefinitionDialog extends Dialog
{
	private final LauncherDefinition m_launcherDefinition;
	
	private Text m_patternText;
	
	private String m_pattern;
	
	private Text m_commandLineText;
	
	private String m_commandLine;
	
	public LauncherDefinitionDialog(Shell parentShell, LauncherDefinition launcherDefinition)
	{
		super(parentShell);
		m_launcherDefinition = launcherDefinition;
		m_pattern = (m_launcherDefinition != null) ? m_launcherDefinition.getPattern() : "";
		m_commandLine = (m_launcherDefinition != null) ? m_launcherDefinition.getCommandLine() : "";
	}

	public LauncherDefinition getLauncherDefinition()
	{
		return new LauncherDefinition(m_pattern, m_commandLine);
	}
	
	@Override
	public void create()
	{
		super.create();

		Shell myShell = this.getShell();
		Point sz = myShell.getSize();
		myShell.setSize(sz.x * 2, sz.y);

		this.verify();
		
		myShell.setText((m_launcherDefinition == null ? "Add" : "Edit") + " launcher definition");
	}

	@Override
	protected Control createDialogArea(Composite container)
	{
		Composite composite = (Composite)super.createDialogArea(container);

		Composite nameComposite = new Composite(composite, SWT.NONE);
		nameComposite.setLayout(new GridLayout(2, false));
		nameComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Label lbl = new Label(nameComposite, SWT.NONE);
		lbl.setText("Pattern:");

		m_patternText = new Text(nameComposite, SWT.SINGLE | SWT.BORDER);
		m_patternText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_patternText.setText(m_pattern);
		m_patternText.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent me)
			{
				m_pattern = m_patternText.getText().trim();
				LauncherDefinitionDialog.this.verify();
			}
		});
		m_patternText.setFocus();

		Label lbl2 = new Label(nameComposite, SWT.NONE);
		lbl2.setText("Command line:");

		m_commandLineText = new Text(nameComposite, SWT.SINGLE | SWT.BORDER);
		m_commandLineText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_commandLineText.setText(m_commandLine);
		m_commandLineText.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent me)
			{
				m_commandLine = m_commandLineText.getText().trim();
				LauncherDefinitionDialog.this.verify();
			}
		});

		return composite;
	}

	private void verify()
	{
		this.getButton(IDialogConstants.OK_ID).setEnabled(m_pattern.length() > 0 && m_commandLine.length() > 0);
	}
}
