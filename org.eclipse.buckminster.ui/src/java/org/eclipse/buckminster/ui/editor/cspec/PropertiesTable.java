/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.util.List;

import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.general.editor.IValidator;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTable;

/**
 * @author Karel Brezina
 * 
 */
public class PropertiesTable extends SimpleTable<Property> {
	public PropertiesTable(List<Property> data, boolean readOnly) {
		super(data, readOnly);
	}

	public Property createRowClass() {
		return new Property(null, null);
	}

	public String[] getColumnHeaders() {
		return new String[] { Messages.key, Messages.value };
	}

	public int[] getColumnWeights() {
		return new int[] { 20, 30 };
	}

	@Override
	public IValidator getFieldValidator(int idx) {
		switch (idx) {
			case 0:
				return SimpleTable.createNotEmptyStringValidator(Messages.key_cannot_be_empty);
			default:
				return SimpleTable.getEmptyValidator();
		}
	}

	public Object[] toRowArray(Property t) {
		return new Object[] { t.getKey(), t.getValue() };
	}

	public void updateRowClass(Property property, Object[] args) throws ValidatorException {
		property.setKey((String) args[0]);
		property.setValue((String) args[1]);
	}
}

class Property {
	private String key;

	private String value;

	public Property(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
