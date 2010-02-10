/*******************************************************************************
 * Copyright (c) 2004, 2006 Subclipse project and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Subclipse project committers - initial API and implementation
 *     Karel Brezina, Cloudsmith - copied from org.tigris.subversion.subclipse.ui.console.ConsolePreferencesPage and modified for Buckminster needs
 ******************************************************************************/
package org.eclipse.buckminster.ui.prefs;

import org.eclipse.buckminster.runtime.IBuckminsterPreferenceConstants;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ConsolePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private ColorFieldEditor messageColorEditor;

	private ColorFieldEditor errorColorEditor;

	private BooleanFieldEditor showOnMessage;

	private BooleanFieldEditor showOnError;

	private BooleanFieldEditor restrictOutput;

	private IntegerFieldEditor highWaterMark;

	public ConsolePreferencePage() {
		super(GRID);
		setDescription(Messages.buckminster_console_preferences);
		setPreferenceStore(UiPlugin.getDefault().getBuckminsterPreferenceStore());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);
		highWaterMark.setEnabled(restrictOutput.getBooleanValue(), getFieldEditorParent());
	}

	@Override
	protected void createFieldEditors() {
		Composite composite = getFieldEditorParent();
		IPreferenceStore store = getPreferenceStore();

		createVerticalSeparator(composite);

		restrictOutput = new BooleanFieldEditor(IBuckminsterPreferenceConstants.PREF_CONSOLE_LIMIT_OUTPUT, Messages.limit_console_output, composite);
		addField(restrictOutput);

		highWaterMark = new IntegerFieldEditor(IBuckminsterPreferenceConstants.PREF_CONSOLE_HIGH_WATER_MARK,
				Messages.console_buffer_size_bracket_characters_bracket_with_colon, composite);
		highWaterMark.setValidRange(1001, Integer.MAX_VALUE - 1);
		addField(highWaterMark);
		highWaterMark.setEnabled(store.getBoolean(IBuckminsterPreferenceConstants.PREF_CONSOLE_LIMIT_OUTPUT), composite);
		FocusListener focusListener = new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				((Text) e.getSource()).selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {
				((Text) e.getSource()).setText(((Text) e.getSource()).getText());
			}
		};
		highWaterMark.getTextControl(composite).addFocusListener(focusListener);

		createVerticalSeparator(composite);

		showOnMessage = new BooleanFieldEditor(IBuckminsterPreferenceConstants.PREF_CONSOLE_SHOW_ON_MESSAGE,
				Messages.show_SVN_console_automatically_when_command_is_run, composite);
		addField(showOnMessage);

		showOnError = new BooleanFieldEditor(IBuckminsterPreferenceConstants.PREF_CONSOLE_SHOW_ON_ERROR,
				Messages.show_SVN_console_automatically_when_an_error_is_encountered, composite);
		addField(showOnError);

		createVerticalSeparator(composite);

		createLabel(composite, Messages.console_text_color_settings_with_colon);

		messageColorEditor = createColorFieldEditor(IBuckminsterPreferenceConstants.PREF_CONSOLE_MESSAGE_COLOR, Messages.message_with_colon,
				composite);
		addField(messageColorEditor);

		errorColorEditor = createColorFieldEditor(IBuckminsterPreferenceConstants.PREF_CONSOLE_ERROR_COLOR, Messages.error_with_colon, composite);
		addField(errorColorEditor);
	}

	/**
	 * Creates a new color field editor.
	 */
	private ColorFieldEditor createColorFieldEditor(String preferenceName, String label, Composite parent) {
		ColorFieldEditor editor = new ColorFieldEditor(preferenceName, label, parent);
		editor.setPage(this);
		editor.setPreferenceStore(getPreferenceStore());
		return editor;
	}

	/**
	 * Utility method that creates a label instance and sets the default layout
	 * data.
	 * 
	 * @param parent
	 *            the parent for the new label
	 * @param text
	 *            the text for the new label
	 * @return the new label
	 */
	private Label createLabel(Composite parent, String text) {
		Label label = new Label(parent, SWT.LEFT);
		label.setText(text);
		GridData data = new GridData();
		data.horizontalSpan = 2;
		data.horizontalAlignment = GridData.FILL;
		label.setLayoutData(data);
		return label;
	}

	private Label createVerticalSeparator(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		GridData data = new GridData();
		data.horizontalSpan = 2;
		data.horizontalAlignment = GridData.FILL;
		data.heightHint = 5;
		label.setLayoutData(data);
		return label;
	}
}
