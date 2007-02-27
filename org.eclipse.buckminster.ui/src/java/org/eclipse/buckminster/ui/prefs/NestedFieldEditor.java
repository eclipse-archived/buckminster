/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.ui.prefs;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

/**
 * A FieldEditor containing other FieldEditors
 *
 * @author Thomas Hallgren
 */
public class NestedFieldEditor extends FieldEditor
{
	private Composite m_group;
	private Label m_label;

	public NestedFieldEditor(String title, Composite parent)
	{
		init("", title);
		createControl(parent);
	}

	public Composite getControl()
	{
		return m_group;
	}

	@Override
	protected void adjustForNumColumns(int numColumns)
	{
		Layout layout = m_group.getLayout();
		if(layout instanceof GridLayout)
		{
			GridLayout gl = (GridLayout)layout;
			gl.numColumns = numColumns;
			gl.marginHeight = 0;
			gl.marginWidth = 0;
			gl.makeColumnsEqualWidth = false;
		}
		GridData gd = (GridData)m_group.getLayoutData();
		gd.horizontalSpan = numColumns;
		gd = (GridData)m_label.getLayoutData();
		gd.horizontalSpan = numColumns;
	}

	@Override
	protected void doFillIntoGrid(Composite parent, int numColumns)
	{
		m_group = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = numColumns;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.makeColumnsEqualWidth = false;
        m_group.setLayout(layout);
		m_group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, numColumns, 1));
		m_label = new Label(m_group, SWT.NONE);
		m_label.setText(getLabelText());
		m_label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, numColumns, 1));
	}

	@Override
	protected void doLoad()
	{
	}

	@Override
	protected void doLoadDefault()
	{
	}

	@Override
	protected void doStore()
	{
	}

	@Override
	public int getNumberOfControls()
	{
		return 1;
	}
}
