/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.util.List;

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.GeneratorBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.general.editor.IValidator;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.IWidgetin;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTable;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Karel Brezina
 * 
 */
public class GeneratorsTable extends SimpleTable<GeneratorBuilder> {
	private CSpecEditor editor;

	private CSpecBuilder cspecBuilder;

	public GeneratorsTable(CSpecEditor editor, List<GeneratorBuilder> data, CSpecBuilder cspecBuilder, boolean readOnly) {
		super(data, readOnly);
		this.editor = editor;
		this.cspecBuilder = cspecBuilder;
	}

	@Override
	public GeneratorBuilder createRowClass() {
		return cspecBuilder.createGeneratorBuilder();
	}

	@Override
	public String[] getColumnHeaders() {
		return new String[] { Messages.generatesName, Messages.generatesType, Messages.generatesVersion, Messages.component, Messages.attribute };
	}

	@Override
	public int[] getColumnWeights() {
		return new int[] { 25, 15, 15, 25, 15 };
	}

	@Override
	public IValidator getFieldValidator(int idx) {
		switch (idx) {
			case 0:
				return SimpleTable.createNotEmptyStringValidator(Messages.generator_name_cannot_be_empty);
			case 4:
				return SimpleTable.createNotEmptyStringValidator(Messages.attribute_cannot_be_empty);
			default:
				return SimpleTable.getEmptyValidator();
		}
	}

	@Override
	public IWidgetin getWidgetin(Composite parent, int idx, Object value) {
		switch (idx) {
			case 0:
				return getTextWidgetin(parent, idx, value);
			case 3:
				return getComboWidgetin(parent, idx, value, editor.getComponentNames(), SWT.NONE);
			case 4:
				return getComboWidgetin(parent, idx, value, editor.getAttributeNames(null), SWT.NONE);
			default:
				return getTextWidgetin(parent, idx, value);
		}
	}

	@Override
	public Object[] toRowArray(GeneratorBuilder t) {
		Object[] array = new Object[getColumns()];

		ComponentIdentifier ci = t.getGeneratedIdentifier();
		array[0] = ci.getName();
		array[1] = ci.getComponentTypeID();
		array[2] = ci.getVersion() == null ? null : ci.getVersion().toString();
		array[3] = t.getComponent();
		array[4] = t.getAttribute();

		return array;
	}

	@Override
	public void updateRowClass(GeneratorBuilder builder, Object[] args) throws ValidatorException {
		builder.setName(TextUtils.notEmptyString((String) args[0]));
		builder.setGeneratesType(TextUtils.notEmptyString((String) args[1]));
		builder.setGeneratesVersion(Version.create(TextUtils.notEmptyString((String) args[2])));
		builder.setComponent(TextUtils.notEmptyString((String) args[3]));
		builder.setAttribute(TextUtils.notEmptyString((String) args[4]));
	}
}
