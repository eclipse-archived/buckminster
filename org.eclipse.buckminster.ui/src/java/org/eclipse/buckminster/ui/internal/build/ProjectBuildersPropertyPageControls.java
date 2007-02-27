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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * @author kolwing
 */
/* package */class ProjectBuildersPropertyPageControls
{
	private final ProjectBuildersPropertyPage m_page;

	private Table m_buildersTable;

	private Button m_addButton;

	private Button m_editButton;

	private Button m_removeButton;

	private Button m_moveUpButton;

	private Button m_moveDownButton;

	private Font m_itemItalicFont;

	/**
	 * 
	 */
	/* package */ProjectBuildersPropertyPageControls(ProjectBuildersPropertyPage page)
	{
		m_page = page;
	}

	/* package */Composite createControls(Composite parent)
	{
		Composite topComposite = new Composite(parent, SWT.NONE);
		topComposite.setLayout(new GridLayout(2, false));
		topComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		this.createBuildersTable(topComposite);
		this.createButtons(topComposite);

		return topComposite;
	}

	/* package */void dispose()
	{
		if(m_itemItalicFont != null)
			m_itemItalicFont.dispose();
		m_itemItalicFont = null;
	}

	/* package */Table getBuildersTable()
	{
		return m_buildersTable;
	}

	/* package */Button getEditButton()
	{
		return m_editButton;
	}

	/* package */Button getRemoveButton()
	{
		return m_removeButton;
	}

	/* package */Button getMoveUpButton()
	{
		return m_moveUpButton;
	}

	/* package */Button getMoveDownButton()
	{
		return m_moveDownButton;
	}

	/* package */Font getItemItalicFont()
	{
		return m_itemItalicFont;
	}

	private void createBuildersTable(Composite parent)
	{
		m_buildersTable = new Table(parent, SWT.BORDER | SWT.SINGLE | SWT.CHECK | SWT.H_SCROLL | SWT.V_SCROLL);
		m_buildersTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		m_buildersTable.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetDefaultSelected(SelectionEvent se)
			{
				Table t = (Table)se.getSource();
				TableItem ti = (TableItem)se.item;
				t.setSelection(new TableItem[] { ti });
				if(ti.getData() != null)
					m_page.editEvent();
			}

			@Override
			public void widgetSelected(SelectionEvent se)
			{
				Table t = (Table)se.getSource();
				TableItem ti = (TableItem)se.item;
				t.setSelection(new TableItem[] { ti });
				m_page.tableSelectionEvent(ti);
			}
		});

		TableItem ti = new TableItem(m_buildersTable, SWT.NONE);
		Font f = ti.getFont();
		FontData[] fda = f.getFontData();
		for(FontData fd : fda)
			fd.setStyle(SWT.ITALIC);
		m_itemItalicFont = new Font(Display.getCurrent(), fda);
		m_buildersTable.removeAll();
	}

	private void createButtons(Composite parent)
	{
		Composite buttonsComposite = new Composite(parent, SWT.NONE);
		buttonsComposite.setLayout(new GridLayout(1, false));
		buttonsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));

		m_addButton = new Button(buttonsComposite, SWT.PUSH);
		m_addButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_addButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				m_page.addEvent();
			}
		});
		m_addButton.setText("Add...");

		m_editButton = new Button(buttonsComposite, SWT.PUSH);
		m_editButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_editButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				m_page.editEvent();
			}
		});
		m_editButton.setText("Edit...");
		m_editButton.setEnabled(false);

		m_removeButton = new Button(buttonsComposite, SWT.PUSH);
		m_removeButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_removeButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				m_page.removeEvent();
			}
		});
		m_removeButton.setText("Remove...");
		m_removeButton.setEnabled(false);

		m_moveUpButton = new Button(buttonsComposite, SWT.PUSH);
		m_moveUpButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_moveUpButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				m_page.moveUpEvent();
			}
		});
		m_moveUpButton.setText("Move up");
		m_moveUpButton.setEnabled(false);

		m_moveDownButton = new Button(buttonsComposite, SWT.PUSH);
		m_moveDownButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_moveDownButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				m_page.moveDownEvent();
			}
		});
		m_moveDownButton.setText("Move down");
		m_moveDownButton.setEnabled(false);
	}
}
