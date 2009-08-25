/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Cloudsmith Inc. - creation of new class ComboInputDialog from original org.eclipse.jface.dialogs.InputDialog
 *******************************************************************************/

package org.eclipse.buckminster.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * A simple input dialog that uses combo widget.
 * <p>
 * This concrete dialog class can be instantiated as is, or further subclassed as required.
 * </p>
 */
@SuppressWarnings("hiding")
public class ComboInputDialog extends Dialog
{
	/**
	 * The title of the dialog.
	 */
	private String title;

	/**
	 * The message to display, or <code>null</code> if none.
	 */
	private String message;

	/**
	 * The input value; the empty string by default.
	 */
	private String value = "";//$NON-NLS-1$

	/**
	 * The input validator, or <code>null</code> if none.
	 */
	private IInputValidator validator;

	/**
	 * Ok button widget.
	 */
	private Button okButton;

	/**
	 * Choices for combo widget
	 */
	private String[] choices;

	/**
	 * Input combo widget.
	 */
	private Combo combo;

	/**
	 * Error message label widget.
	 */
	private Text errorMessageText;

	/**
	 * Error message string.
	 */
	private String errorMessage;

	/**
	 * Creates an input dialog with OK and Cancel buttons. Note that the dialog will have no visual representation (no
	 * widgets) until it is told to open.
	 * <p>
	 * Note that the <code>open</code> method blocks for input dialogs.
	 * </p>
	 * 
	 * @param parentShell
	 *            the parent shell, or <code>null</code> to create a top-level shell
	 * @param dialogTitle
	 *            the dialog title, or <code>null</code> if none
	 * @param dialogMessage
	 *            the dialog message, or <code>null</code> if none
	 * @param choices
	 *            choices for combo widget
	 * @param initialValue
	 *            the initial input value, or <code>null</code> if none (equivalent to the empty string)
	 * @param validator
	 *            an input validator, or <code>null</code> if none
	 */
	public ComboInputDialog(Shell parentShell, String dialogTitle, String dialogMessage, String[] choices,
			String initialValue, IInputValidator validator)
	{
		super(parentShell);
		this.title = dialogTitle;
		message = dialogMessage;
		this.choices = choices;
		if(initialValue == null)
		{
			value = "";//$NON-NLS-1$
		}
		else
		{
			value = initialValue;
		}
		this.validator = validator;
	}

	/**
	 * Returns the string typed into this input dialog.
	 * 
	 * @return the input string
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Sets or clears the error message. If not <code>null</code>, the OK button is disabled.
	 * 
	 * @param errorMessage
	 *            the error message, or <code>null</code> to clear
	 * @since 3.0
	 */
	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
		if(errorMessageText != null && !errorMessageText.isDisposed())
		{
			errorMessageText.setText(errorMessage == null
					? " \n " : errorMessage); //$NON-NLS-1$
			// Disable the error message text control if there is no error, or
			// no error text (empty or whitespace only). Hide it also to avoid
			// color change.
			// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=130281
			boolean hasError = errorMessage != null && (StringConverter.removeWhiteSpaces(errorMessage)).length() > 0;
			errorMessageText.setEnabled(hasError);
			errorMessageText.setVisible(hasError);
			errorMessageText.getParent().update();
			// Access the ok button by id, in case clients have overridden button creation.
			// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=113643
			Control button = getButton(IDialogConstants.OK_ID);
			if(button != null)
			{
				button.setEnabled(errorMessage == null);
			}
		}
	}

	/*
	 * (non-Javadoc) Method declared on Dialog.
	 */
	@Override
	protected void buttonPressed(int buttonId)
	{
		if(buttonId == IDialogConstants.OK_ID)
		{
			value = combo.getText();
		}
		else
		{
			value = null;
		}
		super.buttonPressed(buttonId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	@Override
	protected void configureShell(Shell shell)
	{
		super.configureShell(shell);
		if(title != null)
		{
			shell.setText(title);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent)
	{
		// create OK and Cancel buttons by default
		okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		// do this here because setting the text will set enablement on the ok
		// button
		combo.setFocus();
		if(value != null)
		{
			combo.setText(value);
			// combo.selectAll();
		}
	}

	/*
	 * (non-Javadoc) Method declared on Dialog.
	 */
	@Override
	protected Control createDialogArea(Composite parent)
	{
		// create composite
		Composite composite = (Composite)super.createDialogArea(parent);
		// create message
		if(message != null)
		{
			Label label = new Label(composite, SWT.WRAP);
			label.setText(message);
			GridData data = new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL
					| GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_CENTER);
			data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
			label.setLayoutData(data);
			label.setFont(parent.getFont());
		}
		combo = new Combo(composite, SWT.NONE);
		combo.setItems(choices);
		combo.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		combo.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				validateInput();
			}
		});
		errorMessageText = new Text(composite, SWT.READ_ONLY | SWT.WRAP);
		errorMessageText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		errorMessageText.setBackground(errorMessageText.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		// Set the error message text
		// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=66292
		setErrorMessage(errorMessage);

		applyDialogFont(composite);
		return composite;
	}

	/**
	 * Returns the combo.
	 * 
	 * @return the combo
	 */
	protected Combo getCombo()
	{
		return combo;
	}

	/**
	 * Returns the ok button.
	 * 
	 * @return the ok button
	 */
	protected Button getOkButton()
	{
		return okButton;
	}

	/**
	 * Returns the validator.
	 * 
	 * @return the validator
	 */
	protected IInputValidator getValidator()
	{
		return validator;
	}

	/**
	 * Validates the input.
	 * <p>
	 * The default implementation of this framework method delegates the request to the supplied input validator object;
	 * if it finds the input invalid, the error message is displayed in the dialog's message line. This hook method is
	 * called whenever the text changes in the input field.
	 * </p>
	 */
	protected void validateInput()
	{
		String errorMessage = null;
		if(validator != null)
		{
			errorMessage = validator.isValid(combo.getText());
		}
		// Bug 16256: important not to treat "" (blank error) the same as null
		// (no error)
		setErrorMessage(errorMessage);
	}
}
