/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor;

import org.eclipse.buckminster.ui.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.events.HelpEvent;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Karel Brezina
 * 
 */
public abstract class TableRowDialog extends TitleAreaDialog {
	private final Image windowImage;

	private final String windowTitle;

	private final Image wizardImage;

	private final String helpURL;

	private final boolean newRow;

	private final boolean readOnly;

	public TableRowDialog(Shell parentShell, Image windowImage, String windowTitle, Image wizardImage, String helpURL, boolean newRow,
			boolean readOnly) {
		super(parentShell);
		this.windowImage = windowImage;
		this.windowTitle = windowTitle;
		this.wizardImage = wizardImage;
		this.helpURL = helpURL;
		this.newRow = newRow;
		this.readOnly = readOnly;
	}

	@Override
	public boolean isHelpAvailable() {
		return helpURL != null;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(windowTitle);

		if (windowImage != null) {
			newShell.setImage(windowImage);
		}
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		if (!isReadOnly()) {
			createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
			enableDisableOkButton();
		}
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);

		if (wizardImage != null) {
			setTitleImage(wizardImage);
		}

		if (isReadOnly()) {
			setTitle(Messages.view_row);
			setMessage(Messages.view_row_fields);
		} else {
			if (newRow) {
				setTitle(Messages.new_row);
				setMessage(Messages.enter_new_row_fields);
			} else {
				setTitle(Messages.edit_row);
				setMessage(Messages.edit_row_fields);
			}
		}
		return contents;
	}

	@Override
	protected Control createHelpControl(Composite parent) {
		Control helpControl = super.createHelpControl(parent);
		helpControl.addHelpListener(new HelpListener() {

			@Override
			public void helpRequested(HelpEvent e) {
				if (helpURL != null) {
					Program.launch(helpURL);
				}
			}
		});

		return helpControl;
	}

	protected abstract void enableDisableOkButton();

	protected boolean isReadOnly() {
		return readOnly;
	}
}
