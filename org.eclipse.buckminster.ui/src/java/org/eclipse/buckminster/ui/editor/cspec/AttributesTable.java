/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.util.List;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.editor.EditorUtils;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.structured.StructuredTable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 * 
 */
public abstract class AttributesTable<T extends TopLevelAttributeBuilder> extends StructuredTable<T> {
	private static final String ERROR_MESSAGE_EMPTY_NAME = Messages.name_cannnot_be_empty;

	private CSpecEditor editor;

	private CSpecBuilder cspec;

	private Text nameText;

	private Button publicCheck;

	private Text documentationText;

	private T currentBuilder;

	public AttributesTable(CSpecEditor editor, List<T> data, CSpecBuilder cspec, boolean readOnly) {
		super(data, readOnly);
		this.editor = editor;
		this.cspec = cspec;
	}

	public void enableFields(boolean enabled) {
		nameText.setEnabled(enabled);
		publicCheck.setEnabled(enabled);
		documentationText.setEnabled(enabled);
	}

	public CSpecEditor getCSpecEditor() {
		return editor;
	}

	public T getCurrentBuilder() {
		return currentBuilder;
	}

	public String[] getTableViewerColumnHeaders() {
		return new String[] { Messages.name, Messages.public_label };
	}

	public int[] getTableViewerColumnWeights() {
		return new int[] { 80, 20 };
	}

	public Object getTableViewerField(T builder, int columnIndex) {
		switch (columnIndex) {
			case 0:
				return builder.getName();
			case 1:
				return Boolean.valueOf(builder.isPublic());
			default:
				return null;
		}
	}

	protected Control createDocumentationStackLayer(Composite stackComposite) {
		Composite docComposite = new Composite(stackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		docComposite.setLayout(layout);

		EditorUtils.createHeaderLabel(docComposite, Messages.documentation, 1);

		documentationText = UiUtils.createGridText(docComposite, 1, 0, isReadOnly(), SWT.MULTI);
		documentationText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		documentationText.addModifyListener(FIELD_LISTENER);

		docComposite.setData("focusControl", documentationText); //$NON-NLS-1$

		return docComposite;
	}

	protected CSpecBuilder getCSpecBuilder() {
		return cspec;
	}

	protected Text getNameText() {
		return nameText;
	}

	@Override
	protected void refreshRow(T builder) {
		currentBuilder = builder;

		nameText.setText(TextUtils.notNullString(builder.getName()));
		publicCheck.setSelection(builder.isPublic());

		Documentation doc = builder.getDocumentation();
		documentationText.setText(TextUtils.notNullString(doc == null ? null : doc.toString()));
	}

	protected void setNameText(Text nameText) {
		this.nameText = nameText;
		nameText.addModifyListener(FIELD_LISTENER);
	}

	protected void setPublicCheck(Button publicCheck) {
		this.publicCheck = publicCheck;
		publicCheck.addSelectionListener(FIELD_LISTENER);
	}

	@Override
	protected void setRowValues(T builder) throws ValidatorException {
		if (UiUtils.trimmedValue(nameText) == null) {
			throw new ValidatorException(ERROR_MESSAGE_EMPTY_NAME);
		}

		builder.setName(UiUtils.trimmedValue(nameText));
		builder.setPublic(publicCheck.getSelection());

		String doc = UiUtils.trimmedValue(documentationText);

		try {
			builder.setDocumentation(doc == null ? null : Documentation.parse(doc));
		} catch (Exception e) {
			throw new ValidatorException(e.getMessage());
		}
	}

}
