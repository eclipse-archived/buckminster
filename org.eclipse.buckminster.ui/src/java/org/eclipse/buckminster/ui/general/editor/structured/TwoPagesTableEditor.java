/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor.structured;

import org.eclipse.buckminster.ui.general.editor.TableRowDialog;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Karel Brezina
 * 
 */
public class TwoPagesTableEditor<T> extends StructuredTableEditor<T> {
	class EditRowDialog extends TableRowDialog {
		public EditRowDialog(Shell parentShell, Image windowImage, String windowTitle, Image wizardImage, String helpURL, boolean newRow,
				boolean readOnly) {
			super(parentShell, windowImage, windowTitle, wizardImage, helpURL, newRow, readOnly);
		}

		@Override
		protected void buttonPressed(int buttonId) {
			if (buttonId == IDialogConstants.OK_ID) {
				try {
					saveRow();
				} catch (ValidatorException e) {
					setErrorMessage(e.getMessage());
					return;
				}
			}

			setReturnCode(buttonId);
			close();
		}

		@Override
		protected Control createDialogArea(Composite parent) {
			Composite composite = (Composite) super.createDialogArea(parent);

			Composite rowComposite = new Composite(composite, SWT.NONE);

			GridLayout layout = new GridLayout(2, false);
			layout.marginHeight = layout.marginWidth = 10;
			rowComposite.setLayout(layout);
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
			rowComposite.setLayoutData(gridData);

			createStackOptions(rowComposite);

			createStack(rowComposite);

			fillStackOptions();

			refreshRow();

			focusStackComposite();

			return rowComposite;
		}

		@Override
		protected void enableDisableOkButton() {
			if (isReadOnly())
				getButton(IDialogConstants.OK_ID).setEnabled(true);
		}
	}

	private final Image windowImage;

	private final String windowTitle;

	private final Image wizardImage;

	private final String helpURL;

	public TwoPagesTableEditor(Composite parent, IStructuredTable<T> table, boolean swapButtonsFlag, Image windowImage, String windowTitle,
			Image wizardImage, String helpURL, int style) {
		super(parent, table, swapButtonsFlag, style);
		this.windowImage = windowImage;
		this.windowTitle = windowTitle;
		this.wizardImage = wizardImage;
		this.helpURL = helpURL;
	}

	@Override
	public void refresh() {
		refreshTable();
		enableDisableButtonGroup();
	}

	public boolean show(T row) {
		if (!selectRow(row))
			return false;

		// editRow();

		return true;
	}

	@Override
	protected Composite createTableGroupComposite(Composite parent) {
		Composite componentTableGroup = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout(2, false);
		gl.marginHeight = gl.marginWidth = 0;
		componentTableGroup.setLayout(gl);
		componentTableGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		return componentTableGroup;
	}

	@Override
	protected void editRow(boolean newRow, boolean readOnly) {
		new EditRowDialog(this.getShell(), windowImage, windowTitle, wizardImage, helpURL, newRow, readOnly).open();
	}

	@Override
	protected void initComposite() {
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		setLayout(layout);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		setLayoutData(gridData);

		createTableGroup(this);
	}

	@Override
	protected void newRow() {
		getTableViewer().getTable().deselectAll();
		updateLastRow();
		editRow(true, false);
	}

	@Override
	protected boolean rowSelectionEvent() {
		enableDisableButtonGroup();
		return true;
	}
}
