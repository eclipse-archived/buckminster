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

import org.eclipse.buckminster.core.build.ExternalCommandBuilderConstants;
import org.eclipse.buckminster.ui.DynamicTableLayout;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * @author kolwing
 * 
 */
/* package */class ExternalCommandBuilderEditorDialogControls implements ExternalCommandBuilderConstants
{
	private final ExternalCommandBuilderEditorDialog m_dlg;

	/* package */Table m_launcherDefinitionsTable;

	/* package */Text m_launcherDefinitionsFile;

	/* package */Text m_launcherDefinitionToUse;

	/* package */Text m_additionalArguments;

	/* package */Text m_currentCommandLine;

	/* package */Button m_addButton;

	/* package */Button m_editButton;

	/* package */Button m_removeButton;

	/* package */Button m_moveUpButton;

	/* package */Button m_moveDownButton;

	/* package */ExternalCommandBuilderEditorDialogControls(ExternalCommandBuilderEditorDialog dlg)
	{
		m_dlg = dlg;
	}

	/* package */Composite createControls(Composite parent)
	{
		Composite topComposite = new Composite(parent, SWT.NONE);
		topComposite.setLayout(new GridLayout(1, false));
		topComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		this.createDefinitionManagementComposite(topComposite);
		this.createSettingsComposite(topComposite);
		this.createCurrentCommandlineComposite(topComposite);

		return topComposite;
	}

	private void createDefinitionManagementComposite(Composite parent)
	{
		Group grp = new Group(parent, SWT.NONE);
		grp.setLayout(new GridLayout(2, false));
		grp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		grp.setText("Available launcher definitions:");

		Composite left = new Composite(grp, SWT.NONE);
		left.setLayout(new GridLayout(1, false));
		left.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		m_launcherDefinitionsTable = new Table(left, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		String[] columnNames = new String[] { "Pattern", "Command line" };
		int[] columnWeights = new int[] { 5, 15 };

		m_launcherDefinitionsTable.setHeaderVisible(true);
		DynamicTableLayout layout = new DynamicTableLayout(300);
		for (int idx = 0; idx < columnNames.length; idx++)
		{
			TableColumn tableColumn = new TableColumn(m_launcherDefinitionsTable, SWT.LEFT, idx);
			tableColumn.setText(columnNames[idx]);
			layout.addColumnData(new ColumnWeightData(columnWeights[idx], true));
		}
		m_launcherDefinitionsTable.setLayout(layout);
		m_launcherDefinitionsTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		m_launcherDefinitionsTable.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetDefaultSelected(SelectionEvent se)
			{
				Table t = (Table) se.getSource();
				TableItem ti = (TableItem) se.item;
				t.setSelection(new TableItem[] { ti });
				m_dlg.editEvent();
			}

			@Override
			public void widgetSelected(SelectionEvent se)
			{
				Table t = (Table) se.getSource();
				TableItem ti = (TableItem) se.item;
				t.setSelection(new TableItem[] { ti });
				m_dlg.tableSelectionEvent();
			}
		});

		Composite right = new Composite(grp, SWT.NONE);
		right.setLayout(new GridLayout(1, false));
		right.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));

		m_addButton = new Button(right, SWT.PUSH);
		m_addButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_addButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				m_dlg.addEvent();
			}
		});
		m_addButton.setText("Add...");

		m_editButton = new Button(right, SWT.PUSH);
		m_editButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_editButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				m_dlg.editEvent();
			}
		});
		m_editButton.setText("Edit...");
		m_editButton.setEnabled(false);

		m_removeButton = new Button(right, SWT.PUSH);
		m_removeButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_removeButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				m_dlg.removeEvent();
			}
		});
		m_removeButton.setText("Remove...");
		m_removeButton.setEnabled(false);

		m_moveUpButton = new Button(right, SWT.PUSH);
		m_moveUpButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_moveUpButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				m_dlg.moveUpEvent();
			}
		});
		m_moveUpButton.setText("Move up");
		m_moveUpButton.setEnabled(false);

		m_moveDownButton = new Button(right, SWT.PUSH);
		m_moveDownButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_moveDownButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				m_dlg.moveDownEvent();
			}
		});
		m_moveDownButton.setText("Move down");
		m_moveDownButton.setEnabled(false);

		Point sz = grp.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
		grp.setSize(sz.x * 2, sz.y);
	}

	private void createSettingsComposite(Composite parent)
	{
		Group grp = new Group(parent, SWT.NONE);
		grp.setLayout(new GridLayout(2, false));
		grp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		grp.setText("Settings:");

		new Label(grp, SWT.NONE).setText("Launcher definitions file:");
		m_launcherDefinitionsFile = new Text(grp, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
		m_launcherDefinitionsFile.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		String definitionsFile = m_dlg.getArg(LAUNCHERDEFINITIONS_FILE_KEY);
		m_launcherDefinitionsFile.setText(definitionsFile.length() > 0 ? definitionsFile : DEFAULT_LAUNCHERDEFINITIONS_FILE);

		new Label(grp, SWT.NONE).setText("Launcher definition to use:");
		m_launcherDefinitionToUse = new Text(grp, SWT.SINGLE | SWT.BORDER);
		m_launcherDefinitionToUse.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		String definitionToUse = m_dlg.getArg(LAUNCHERDEFINITION_TO_USE_KEY);
		m_launcherDefinitionToUse.setText(definitionToUse.length() > 0 ? definitionToUse : DEFAULT_LAUNCHERDEFINITION_TO_USE);
		m_launcherDefinitionToUse.addModifyListener(new ModifyListener()
				{
					public void modifyText(ModifyEvent fe)
					{
						m_dlg.toUseModifyEvent();
					}
				});

		new Label(grp, SWT.NONE).setText("Additional arguments:");
		m_additionalArguments = new Text(grp, SWT.SINGLE | SWT.BORDER);
		m_additionalArguments.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_additionalArguments.setText(m_dlg.getArg(ADDITIONAL_ARGUMENTS_KEY));
		m_additionalArguments.setFocus();
		m_additionalArguments.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent fe)
			{
				m_dlg.additionalArgumentsModifyEvent();
			}
		});
	}

	private void createCurrentCommandlineComposite(Composite parent)
	{
		Group grp = new Group(parent, SWT.NONE);
		grp.setLayout(new GridLayout(1, true));
		grp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		grp.setText("Current command line:");

		m_currentCommandLine = new Text(grp, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
		m_currentCommandLine.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}

}
