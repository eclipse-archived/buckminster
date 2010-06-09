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
 * Editor used for <code>enum</code> type values. The stored preference is the
 * ordinal of the enum. A {@link Combo} is used for displaying and changing the
 * value. The displayed values are obtained by calling the
 * {@link Enum#toString()} in the respective <code>enum</code> values.
 * 
 * @author Thomas Hallgren
 */
public class EnumFieldEditor extends FieldEditor {
	private Combo combo;

	private Enum<?> value;

	private final Enum<?>[] enumValues;

	public EnumFieldEditor(String name, String labelText, Enum<?>[] enumValues, Composite parent) {
		this.enumValues = enumValues;
		init(name, labelText);
		createControl(parent);
	}

	public Combo getControl(Composite parent) {
		if (combo == null) {
			combo = new Combo(parent, SWT.READ_ONLY);
			int top = enumValues.length;
			for (int idx = 0; idx < top; ++idx)
				combo.add(enumValues[idx].toString(), idx);
			combo.setFont(parent.getFont());
			combo.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent evt) {
					Enum<?> oldValue = value;
					value = getValueForName(combo.getText());
					setPresentsDefaultValue(false);
					fireValueChanged(VALUE, oldValue, value);
				}
			});
		}
		return combo;
	}

	@Override
	public int getNumberOfControls() {
		return 2;
	}

	@Override
	protected void adjustForNumColumns(int numColumns) {
		Control control = getLabelControl();
		if (control != null) {
			((GridData) control.getLayoutData()).horizontalSpan = numColumns - 1;
			numColumns = 1;
		}
		((GridData) combo.getLayoutData()).horizontalSpan = numColumns;
	}

	@Override
	protected void doFillIntoGrid(Composite parent, int numColumns) {
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
	protected void doLoad() {
		updateComboForValue(getPreferenceStore().getInt(getPreferenceName()));
	}

	@Override
	protected void doLoadDefault() {
		updateComboForValue(getPreferenceStore().getDefaultInt(getPreferenceName()));
	}

	@Override
	protected void doStore() {
		if (value == null) {
			getPreferenceStore().setToDefault(getPreferenceName());
			return;
		}

		getPreferenceStore().setValue(getPreferenceName(), value.ordinal());
	}

	protected Enum<?> getValueForName(String name) {
		int idx = enumValues.length;
		while (--idx >= 0) {
			Enum<?> enumValue = enumValues[idx];
			if (enumValue.toString().equals(name))
				return enumValue;
		}
		return null;
	}

	/*
	 * Set the name in the combo widget to match the specified value.
	 */
	protected void updateComboForValue(int ordinal) {
		int max = enumValues.length;
		if (ordinal < 0 || ordinal >= max) {
			// Out of range. Use first value
			//
			if (enumValues.length == 0)
				return;
			value = enumValues[0];
		} else
			value = enumValues[ordinal];
		combo.setText(value.toString());
	}
}
