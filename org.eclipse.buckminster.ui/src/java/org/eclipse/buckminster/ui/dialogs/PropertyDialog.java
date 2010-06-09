/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.dialogs;

import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 * 
 */
public class PropertyDialog extends Dialog {

	private Property property = null;

	private boolean newProperty = false;

	private Text keyText;

	private Text valueText;

	private Button okButton;

	/**
	 * PropertyDialog constructor
	 * 
	 * @param parent
	 *            the parent shell
	 */
	public PropertyDialog(Shell parent, Property property) {
		super(parent);

		this.property = property;
		this.newProperty = property == null;
	}

	public Property getProperty() {
		return property;
	}

	/**
	 * Handles a button press
	 * 
	 * @param buttonId
	 *            the ID of the pressed button
	 */
	@Override
	protected void buttonPressed(int buttonId) {
		// If they press I Dunno, close the dialog
		if (buttonId == IDialogConstants.CANCEL_ID) {
			property = null;
		} else {
			property = new Property(keyText.getText(), valueText.getText());
		}

		setReturnCode(buttonId);
		close();
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);

		if (newProperty) {
			newShell.setText(Messages.new_property);
		} else {
			newShell.setText(Messages.edit_property);
		}
	}

	/**
	 * Creates the buttons
	 * 
	 * @param parent
	 *            the parent composite
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		keyText.notifyListeners(SWT.Modify, new Event());
	}

	/**
	 * Creates the dialog area
	 * 
	 * @param parent
	 *            the parent composite
	 * @return Control
	 */
	@Override
	protected Control createDialogArea(Composite parent) {

		Composite textComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginTop = 7;
		layout.marginWidth = 10;
		textComposite.setLayout(layout);
		textComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label label = UiUtils.createGridLabel(textComposite, Messages.key_with_colon, 1, 0, SWT.NONE);
		keyText = UiUtils.createGridText(textComposite, 1, 0, SWT.NONE);
		GridData data = (GridData) keyText.getLayoutData();
		data.widthHint = 300;
		keyText.setLayoutData(data);

		if (!newProperty) {
			label.setEnabled(false);
			keyText.setEnabled(false);
		}

		UiUtils.createGridLabel(textComposite, Messages.value_with_colon, 1, 0, SWT.NONE);
		valueText = UiUtils.createGridText(textComposite, 1, 0, SWT.NONE);

		keyText.setText(property == null ? "" : property.getKey()); //$NON-NLS-1$
		valueText.setText(property == null ? "" : property.getValue()); //$NON-NLS-1$

		keyText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String key = keyText.getText();

				if (key != null && key.length() > 0) {
					okButton.setEnabled(true);
				} else {
					okButton.setEnabled(false);
				}
			}
		});

		return textComposite;
	}
}
