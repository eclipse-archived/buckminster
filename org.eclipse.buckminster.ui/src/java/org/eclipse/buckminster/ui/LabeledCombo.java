/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class LabeledCombo extends Composite
{
	private final Label m_label;
	private final Combo m_combo;

	public LabeledCombo(Composite parent, int style)
	{
		super(parent, style);
		GridLayout gd = new GridLayout(2, false);
		gd.marginWidth = gd.marginHeight = 0;
		this.setLayout(gd);
		m_label = new Label(this, SWT.NONE);
		m_label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		m_combo = new Combo(this, style);
		m_combo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
	}

	public void add(String string)
	{
		m_combo.add(string);
	}

	public void add(String string, int index)
	{
		m_combo.add(string, index);
	}

	@Override
	public void setEnabled(boolean flag)
	{
		m_label.setEnabled(flag);
		m_combo.setEnabled(flag);
		super.setEnabled(flag);
	}

	public void setLabel(String labelText)
	{
		m_label.setText(labelText);
	}

	public void select(int index)
	{
		m_combo.select(index);
	}

	public void addSelectionListener(SelectionListener listener)
	{
		m_combo.addSelectionListener(listener);
	}

	public void removeSelectionListener(SelectionListener listener)
	{
		m_combo.removeSelectionListener(listener);
	}

	public int indexOf(String string)
	{
		return m_combo.indexOf(string);
	}

	public String getItem(int index)
	{
		return m_combo.getItem(index);
	}

	public Point getSelection()
	{
		return m_combo.getSelection();
	}

	public int getSelectionIndex()
	{
		return m_combo.getSelectionIndex();
	}

	public void setItems(String[] items)
	{
		m_combo.setItems(items);
	}
}
