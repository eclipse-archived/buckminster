/*******************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.editor;

import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 * 
 */
public class PropertyDialog extends Dialog
{
	private Property m_input = null;

	private boolean m_newProperty = false;

	private Text m_keyText;

	private Text m_valueText;

	private Button m_okButton;

	private Button m_cancelButton;

	public PropertyDialog(Shell parent, boolean newProperty)
	{
		// Pass the default styles here
		this(parent, newProperty, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	}

	public PropertyDialog(Shell parent, boolean newProperty, int style)
	{
		// Let users override the default styles
		super(parent, style);
		m_newProperty = newProperty;

		if(m_newProperty)
		{
			setText("New Property");
		}
		else
		{
			setText("Edit Property");
		}
	}

	public Property getInput()
	{
		return m_input;
	}

	public Property open()
	{
		// Create the dialog window
		Shell shell = new Shell(getParent(), getStyle());
		shell.setText(getText());
		createContents(shell);
		shell.pack();
		shell.open();
		Display display = getParent().getDisplay();
		while(!shell.isDisposed())
		{
			if(!display.readAndDispatch())
			{
				display.sleep();
			}
		}
		// Return the entered value, or null
		return m_input;
	}

	public void setInput(Property input)
	{
		this.m_input = input;
	}

	private void createContents(final Shell shell)
	{

		GridLayout gridLayout = new GridLayout(1, true);
		shell.setLayout(gridLayout);

		Composite textComposite = new Composite(shell, SWT.NONE);
		textComposite.setLayout(new GridLayout(2, false));
		textComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		ModifyListener listener = new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				String key = m_keyText.getText();

				if(key != null && key.length() > 0)
				{
					m_okButton.setEnabled(true);
				}
				else
				{
					m_okButton.setEnabled(false);
				}
			}
		};

		Label label = UiUtils.createGridLabel(textComposite, "Key:", 1, 0, SWT.NONE);
		m_keyText = UiUtils.createGridText(textComposite, 1, 0, listener, SWT.NONE);
		GridData data = (GridData)m_keyText.getLayoutData();
		data.widthHint = 300;
		m_keyText.setLayoutData(data);

		if(!m_newProperty)
		{
			label.setEnabled(false);
			m_keyText.setEnabled(false);
		}

		UiUtils.createGridLabel(textComposite, "Value:", 1, 0, SWT.NONE);
		m_valueText = UiUtils.createGridText(textComposite, 1, 0, null, SWT.NONE);

		Composite buttonComposite = new Composite(shell, SWT.NONE);
		gridLayout = new GridLayout(2, true);
		gridLayout.marginRight = 0;
		buttonComposite.setLayout(gridLayout);
		buttonComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END));

		m_okButton = new Button(buttonComposite, SWT.PUSH);
		m_okButton.setText("OK");
		m_okButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		m_okButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				m_input = new Property(m_keyText.getText(), m_valueText.getText());
				shell.close();
			}
		});

		m_cancelButton = new Button(buttonComposite, SWT.PUSH);
		m_cancelButton.setText("Cancel");
		m_cancelButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		m_cancelButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				m_input = null;
				shell.close();
			}
		});

		m_keyText.setText(m_input == null
				? ""
				: m_input.getKey());
		m_valueText.setText(m_input == null
				? ""
				: m_input.getValue());

		// Set the OK button as the default, so
		// user can type input and press Enter
		// to dismiss
		shell.setDefaultButton(m_okButton);
	}
}
