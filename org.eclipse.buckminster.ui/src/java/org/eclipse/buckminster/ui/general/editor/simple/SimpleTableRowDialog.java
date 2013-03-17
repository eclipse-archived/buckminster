/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor.simple;

import java.util.Arrays;

import org.eclipse.buckminster.ui.general.editor.TableRowDialog;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * Row editor of general table editor TableEditor
 * 
 * @author Karel Brezina
 */
public class SimpleTableRowDialog<T> extends TableRowDialog {
	private final ISimpleTable<T> table;

	private final int row;

	private final boolean newRow;

	private IWidgetin[] widgetins;

	/**
	 * Creates row editor
	 * 
	 * @param parent
	 *            parent shell
	 * @param windowImage
	 *            window icon
	 * @param windowTitle
	 *            window title
	 * @param wizardImage
	 *            wizard image
	 * @param helpURL
	 *            URL to help info. If not null - help link or icon is displayed
	 *            for accessing help
	 * @param table
	 *            wrapped editor data
	 * @param row
	 *            table row number that will be edited or -1 for new row
	 */
	public SimpleTableRowDialog(Shell parent, Image windowImage, String windowTitle, Image wizardImage, String helpURL, ISimpleTable<T> table,
			int row, boolean readOnly) {
		super(parent, windowImage, windowTitle, wizardImage, helpURL, (row == -1), readOnly);

		this.table = table;
		this.row = row;
		this.newRow = (row == -1);
		this.widgetins = new IWidgetin[table.getColumns()];
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			Object[] fields = new Object[table.getColumns()];

			for (int i = 0; i < table.getColumns(); i++) {
				fields[i] = widgetins[i].getData();
			}

			try {
				if (newRow) {
					table.addRow(fields);
				} else {
					table.setRow(row, fields);
				}
			} catch (ValidatorException e) {
				setErrorMessage(e.getMessage());
				getButton(IDialogConstants.OK_ID).setEnabled(false);

				return;
			}
		}

		setReturnCode(buttonId);
		close();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		Listener rowModifyListener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				setErrorMessage(event.text);
				enableDisableOkButton();
			}
		};

		Composite textComposite = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginTop = 7;
		layout.marginWidth = 10;
		textComposite.setLayout(layout);
		textComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Object[] fields = null;

		if (!newRow) {
			fields = table.toRowArray(table.getRow(row));
		} else {
			fields = new Object[table.getColumns()];
			Arrays.fill(fields, null);
		}

		widgetins = table.fillGrid(textComposite, fields);

		for (int i = 0; i < table.getColumns(); i++) {
			widgetins[i].addListener(ISimpleTable.ERROR_MESSAGE_EVENT_TYPE, rowModifyListener);
		}

		return textComposite;
	}

	@Override
	protected void enableDisableOkButton() {
		if (getButton(IDialogConstants.OK_ID) != null) {
			boolean valid = true;

			try {
				for (int i = 0; i < table.getColumns(); i++) {
					table.getFieldValidator(i).validate(widgetins[i].getData());
				}
			} catch (ValidatorException e1) {
				valid = false;
			}

			getButton(IDialogConstants.OK_ID).setEnabled(valid);
		}
	}
}
