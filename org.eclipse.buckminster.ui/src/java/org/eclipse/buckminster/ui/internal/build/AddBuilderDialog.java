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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ListDialog;

/**
 * @author kolwing
 */
public class AddBuilderDialog extends ListDialog
{
	private Text m_nameText;

	private String m_name;

	/**
	 * @param parent
	 */
	public AddBuilderDialog(Shell parent)
	{
		super(parent);
	}

	public String getName()
	{
		return (m_name != null && m_name.length() > 0) ? m_name : null;
	}

	@Override
	protected Control createDialogArea(Composite container)
	{
		Composite composite = (Composite)super.createDialogArea(container);

		Composite nameComposite = new Composite(composite, SWT.NONE);
		nameComposite.setLayout(new GridLayout(2, false));
		nameComposite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

		Label lbl = new Label(nameComposite, SWT.NONE);
		lbl.setText("Name:");

		m_nameText = new Text(nameComposite, SWT.SINGLE | SWT.BORDER);
		m_nameText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_nameText.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent me)
			{
				m_name = m_nameText.getText().trim();
			}
		});
		m_nameText.setFocus();

		return composite;
	}
}
