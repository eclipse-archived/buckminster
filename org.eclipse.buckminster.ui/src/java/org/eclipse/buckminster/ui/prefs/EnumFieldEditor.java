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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Editor used for <code>enum</code> type values. The stored preference is the ordinal
 * of the enum. A {@link Combo} is used for displaying and changing the value. The displayed
 * values are obtained by calling the {@link Enum#toString()}
 * in the respective <code>enum</code> values.
 *
 * @author Thomas Hallgren
 */
public class EnumFieldEditor extends FieldEditor
{
	private Combo m_combo;

	private Enum<?> m_value;
	
	private final Enum<?>[] m_enumValues;

	public EnumFieldEditor(String name, String labelText, Enum<?>[] enumValues, Composite parent)
	{
		m_enumValues = enumValues;
		init(name, labelText);
		createControl(parent);
	}

	@Override
	protected void adjustForNumColumns(int numColumns)
	{
		Control control = getLabelControl();
		if(control != null)
		{
			((GridData)control.getLayoutData()).horizontalSpan = numColumns - 1;
			numColumns = 1;
		}
		((GridData)m_combo.getLayoutData()).horizontalSpan = numColumns;
	}

	@Override
	protected void doFillIntoGrid(Composite parent, int numColumns)
	{
		Control control = getLabelControl(parent);
		GridData gd = new GridData();
		gd.horizontalSpan = numColumns;
		control.setLayoutData(gd);
		control = getControl(parent);
		gd = new GridData();
		gd.horizontalSpan = numColumns;
		control.setLayoutData(gd);
	}

	@Override
	protected void doLoad()
	{
		updateComboForValue(getPreferenceStore().getInt(getPreferenceName()));
	}

	@Override
	protected void doLoadDefault()
	{
		updateComboForValue(getPreferenceStore().getDefaultInt(getPreferenceName()));
	}

	@Override
	protected void doStore()
	{
		if(m_value == null)
		{
			getPreferenceStore().setToDefault(getPreferenceName());
			return;
		}

		getPreferenceStore().setValue(getPreferenceName(), m_value.ordinal());
	}

	@Override
	public int getNumberOfControls()
	{
		return 2;
	}

	public Combo getControl(Composite parent)
	{
		if(m_combo == null)
		{
			m_combo = new Combo(parent, SWT.READ_ONLY);
			int top = m_enumValues.length;
			for(int idx = 0; idx < top; ++idx)
				m_combo.add(m_enumValues[idx].toString(), idx);
			m_combo.setFont(parent.getFont());
			m_combo.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent evt)
				{
					Enum<?> oldValue = m_value;
					m_value = getValueForName(m_combo.getText());
					setPresentsDefaultValue(false);
					fireValueChanged(VALUE, oldValue, m_value);
				}
			});
		}
		return m_combo;
	}

	protected Enum<?> getValueForName(String name)
	{
		int idx = m_enumValues.length;
		while(--idx >= 0)
		{
			Enum<?> enumValue = m_enumValues[idx];
			if(enumValue.toString().equals(name))
				return enumValue;
		}
		return null;
	}

	/*
	 * Set the name in the combo widget to match the specified value.
	 */
	protected void updateComboForValue(int ordinal)
	{
		int max = m_enumValues.length;
		if(ordinal < 0 || ordinal >= max)
		{
			// Out of range. Use first value
			//
			if(m_enumValues.length == 0)
				return;
			m_value = m_enumValues[0];
		}
		else
			m_value = m_enumValues[ordinal];
		m_combo.setText(m_value.toString());
	}
}
