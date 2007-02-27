/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.ui.prefs;

import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Thomas Hallgren
 */
abstract class NodeListPrefPane extends Composite
{
	private static final Pattern s_namePattern = Pattern.compile("^(?:(?:\\$\\{[\\w\\.-]+\\})|[\\w\\.:-])+$");

	private final PreferencePage m_prefPage;

	private static final Map<String, String> s_tooltipScope;

	protected static ModifyListener s_tooltipRefresh = new ModifyListener()
	{
		public void modifyText(ModifyEvent e)
		{
			Text source = (Text)e.getSource();
			setTooltipText(source, source.getText());
		}
	};

	static
	{
		s_tooltipScope = new BMProperties(BMProperties.getSystemProperties());
		s_tooltipScope.putAll(ComponentQuery.getGlobalPropertyAdditions());
	}

	public NodeListPrefPane(PreferencePage prefPage, Composite parent, int colSpan)
	{
		super(parent, SWT.NONE);
		m_prefPage = prefPage;
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		this.setLayout(layout);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.horizontalSpan = colSpan;
		this.setLayoutData(gd);
	}

	private List m_list;

	private Button m_newButton;

	private Button m_renameButton;

	private Button m_removeButton;

	protected Composite createListContents(String listLabel)
	{
		Label label = new Label(this, SWT.BOLD);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, false, false);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);
		label.setText(listLabel);

		m_list = new List(this, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL);
		m_list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		m_list.setFont(this.getFont());
		m_list.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				selectionChanged();
			}
		});

		Composite buttonBox = new Composite(this, SWT.NONE);
		buttonBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		GridLayout layout = new GridLayout(1, true);
		layout.marginWidth = 0;
		buttonBox.setLayout(layout);

		m_newButton = UiUtils.createPushButton(buttonBox, "New", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				try
				{
					newNode();
				}
				catch(Throwable t)
				{
					t.printStackTrace();
				}
			}
		});
		m_newButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		m_removeButton = UiUtils.createPushButton(buttonBox, "Remove", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String item = getSelectedItem();
				if(item != null)
				{
					removeNode(item);
					updateList();
					selectionChanged();
				}
			}
		});
		m_removeButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		m_renameButton = UiUtils.createPushButton(buttonBox, "Rename", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				try
				{
					renameNode();
				}
				catch(Throwable t)
				{
					t.printStackTrace();
				}
			}
		});
		m_renameButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		return buttonBox;
	}

	protected void addAndSelect(String item)
	{
		m_list.add(item);
		m_list.setSelection(m_list.getItemCount() - 1);
		selectionChanged();
	}

	protected void updateAndSelect()
	{
		int idx = m_list.getSelectionIndex();
		this.updateList();
		m_list.setSelection(idx);
		selectionChanged();
	}

	public void setErrorMessage(String message)
	{
		m_prefPage.setErrorMessage(message);
	}

	protected abstract boolean isNewEnabled();

	protected abstract void newNode();

	protected abstract void renameNode();

	protected abstract void editNode(String item);

	protected abstract void removeNode(String item);

	protected abstract String[] getListContents();

	protected final String getSelectedItem()
	{
		int index = m_list.getSelectionIndex();
		int size = m_list.getSelectionCount();
		return (index >= 0 && size == 1) ? m_list.getItem(index) : null;
	}

	protected final PreferencePage getPreferencePage()
	{
		return m_prefPage;
	}

	protected void updateList()
	{
		m_list.setItems(this.getListContents());
	}

	/**
	 * Notifies that the list selection has changed.
	 */
	protected void selectionChanged()
	{
		int index = m_list.getSelectionIndex();
		int size = m_list.getSelectionCount();

		m_newButton.setEnabled(this.isNewEnabled());
		m_removeButton.setEnabled(index >= 0);
		m_renameButton.setEnabled(index >= 0);
		this.editNode((index >= 0 && size == 1) ? m_list.getItem(index) : null);
	}

	protected static void displayException(Shell parentShell, Exception e)
	{
		ErrorDialog.openError(parentShell, "Error reading preferences", null, BuckminsterException.wrap(e).getStatus());
	}

	protected static void setTooltipText(Text text, String value)
	{
		if(value != null && value.contains("${"))
			text.setToolTipText(ExpandingProperties.expand(s_tooltipScope, value, 0));
		else
			text.setToolTipText(null);
	}

	protected String queryNodeName(String title, String label, String initial)
	{
		InputDialog nameDialog = new InputDialog(this.getShell(), title, label, initial, new IInputValidator()
		{
			public String isValid(String newText)
			{
				if(!s_namePattern.matcher(newText).matches())
					return "Name is invalid";
				return null;
			}
		});

		return (nameDialog.open() == Window.OK) ? nameDialog.getValue() : null;
	}
}
