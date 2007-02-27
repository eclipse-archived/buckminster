/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ant.ui.internal.build;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.ant.AntBuilderConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author kolwing
 */
public class AntBuilderEditorDialog extends Dialog implements AntBuilderConstants
{
	private final String m_builderName;
	
	private final Map<String, String> m_args;

	private Text m_scriptFile;
	
	private Text m_basedir;
	
	private Text m_autoTarget;
	
	private Text m_incrementalTarget;
	
	private Text m_fullTarget;
	
	private Text m_cleanTarget;
	
	private Text m_componentNameProperty;
	
	private Text m_buildKindProperty;
	
	public AntBuilderEditorDialog(Shell shell, String builderName, Map<String, String> args)
	{
		super(shell);

		m_builderName = builderName;
		m_args = args == null ? new HashMap<String, String>() : args;
	}

	public Map<String, String> getArgs()
	{
		return m_args;
	}

	@Override
	protected void okPressed()
	{
		String scriptFile = m_scriptFile.getText().trim();
		if(DEFAULT_SCRIPT_FILE.equals(scriptFile))
			scriptFile = "";
		this.updateArg(ARG_SCRIPT_FILE_KEY, scriptFile);
		this.updateArg(ARG_OVERRIDE_BASEDIR_KEY, m_basedir.getText().trim());
		this.updateArg(ARG_AUTO_KIND_TARGET_KEY, m_autoTarget.getText().trim());
		this.updateArg(ARG_INCREMENTAL_KIND_TARGET_KEY, m_incrementalTarget.getText().trim());
		this.updateArg(ARG_FULL_KIND_TARGET_KEY, m_fullTarget.getText().trim());
		String cleanTarget = m_cleanTarget.getText().trim();
		if(DEFAULT_CLEAN_KIND_TARGET.equals(cleanTarget))
			cleanTarget = "";
		this.updateArg(ARG_CLEAN_KIND_TARGET_KEY, cleanTarget);
		this.updateArg(ARG_COMPONENT_NAME_PROPERTY_KEY, m_componentNameProperty.getText().trim());
		this.updateArg(ARG_BUILD_KIND_PROPERTY_KEY, m_buildKindProperty.getText().trim());
		
		super.okPressed();
	}

	@Override
	protected Control createContents(Composite parent)
	{
		this.getShell().setText("Edit " + m_builderName);

		Composite topComposite = new Composite(parent, SWT.NONE);
		topComposite.setLayout(new GridLayout(1, false));
		topComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		this.createEditFieldsComposite(topComposite);

		Composite buttonComposite = new Composite(topComposite, SWT.NONE);
		buttonComposite.setLayout(new GridLayout(1, false));
		buttonComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.NONE, false, false));
		this.createButtonBar(buttonComposite);

		return topComposite;
	}

	private void createEditFieldsComposite(Composite parent)
	{
		Composite c = new Composite(parent, SWT.NONE);
		c.setLayout(new GridLayout(2, true));
		c.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		new Label(c, SWT.NONE).setText("Script file:");
		m_scriptFile = new Text(c, SWT.SINGLE | SWT.BORDER);
		m_scriptFile.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		String scriptFile = this.getArg(ARG_SCRIPT_FILE_KEY);
		m_scriptFile.setText(scriptFile.length() > 0 ? scriptFile : DEFAULT_SCRIPT_FILE);
		m_scriptFile.setFocus();

		new Label(c, SWT.NONE).setText("Override basedir:");
		m_basedir = new Text(c, SWT.SINGLE | SWT.BORDER);
		m_basedir.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_basedir.setText(this.getArg(ARG_OVERRIDE_BASEDIR_KEY));

		new Label(c, SWT.NONE).setText("AUTO target:");
		m_autoTarget = new Text(c, SWT.SINGLE | SWT.BORDER);
		m_autoTarget.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_autoTarget.setText(this.getArg(ARG_AUTO_KIND_TARGET_KEY));

		new Label(c, SWT.NONE).setText("INCREMENTAL target:");
		m_incrementalTarget = new Text(c, SWT.SINGLE | SWT.BORDER);
		m_incrementalTarget.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_incrementalTarget.setText(this.getArg(ARG_INCREMENTAL_KIND_TARGET_KEY));

		new Label(c, SWT.NONE).setText("FULL target:");
		m_fullTarget = new Text(c, SWT.SINGLE | SWT.BORDER);
		m_fullTarget.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_fullTarget.setText(this.getArg(ARG_FULL_KIND_TARGET_KEY));

		new Label(c, SWT.NONE).setText("CLEAN target:");
		m_cleanTarget = new Text(c, SWT.SINGLE | SWT.BORDER);
		m_cleanTarget.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		String cleanTarget = this.getArg(ARG_CLEAN_KIND_TARGET_KEY);
		m_cleanTarget.setText(cleanTarget.length() > 0 ? cleanTarget : DEFAULT_CLEAN_KIND_TARGET);

		new Label(c, SWT.NONE).setText("Component name property:");
		m_componentNameProperty = new Text(c, SWT.SINGLE | SWT.BORDER);
		m_componentNameProperty.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_componentNameProperty.setText(this.getArg(ARG_COMPONENT_NAME_PROPERTY_KEY));

		new Label(c, SWT.NONE).setText("Build kind property:");
		m_buildKindProperty = new Text(c, SWT.SINGLE | SWT.BORDER);
		m_buildKindProperty.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		m_buildKindProperty.setText(this.getArg(ARG_BUILD_KIND_PROPERTY_KEY));

	}
	
	private String getArg(String key)
	{
		String s = m_args.get(key);
		return s == null ? "" : s;
	}
	
	private void updateArg(String key, String value)
	{
		if (value == null || value.length() == 0)
			m_args.remove(key);
		else
			m_args.put(key, value);
	}
}
