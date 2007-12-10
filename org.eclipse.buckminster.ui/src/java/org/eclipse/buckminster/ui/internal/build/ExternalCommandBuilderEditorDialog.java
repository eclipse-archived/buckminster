/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ui.internal.build;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.build.ExternalCommandBuilder;
import org.eclipse.buckminster.core.build.ExternalCommandBuilderConstants;
import org.eclipse.buckminster.core.build.LauncherDefinition;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * @author kolwing
 */
public class ExternalCommandBuilderEditorDialog extends Dialog implements ExternalCommandBuilderConstants
{
	/* package */final IProject m_project;

	/* package */final String m_builderName;

	/* package */final Map<String, String> m_args;

	private List<LauncherDefinition> m_launcherDefinitions = new ArrayList<LauncherDefinition>();

	private final ExternalCommandBuilderEditorDialogControls m_controls;

	private File m_launcherDefinitionsFile;

	public ExternalCommandBuilderEditorDialog(Shell shell, IProject project, String builderName,
			Map<String, String> args) throws CoreException
	{
		super(shell);

		m_project = project;

		m_controls = new ExternalCommandBuilderEditorDialogControls(this);

		m_builderName = builderName;

		m_args = (args == null ? new HashMap<String, String>() : args);

		this.initLauncherDefinitionsList();
	}

	public Map<String, String> getArgs()
	{
		return m_args;
	}

	@Override
	protected void okPressed()
	{
		boolean ok = true;
		try
		{
			this.writeLauncherDefinitions();
		}
		catch (Exception e)
		{
			MessageDialog.openError(this.getShell(),
									"Failed to write the launcher definitions",
									"Error while writing: " + e.toString());
			ok = false;
		}
		if (ok)
		{
			String definitionToUse = m_controls.m_launcherDefinitionToUse.getText().trim();
			if (DEFAULT_LAUNCHERDEFINITION_TO_USE.equals(definitionToUse))
				definitionToUse = null;
			this.updateArg(LAUNCHERDEFINITION_TO_USE_KEY, definitionToUse);

			this.updateArg(ADDITIONAL_ARGUMENTS_KEY, m_controls.m_additionalArguments.getText().trim());

			super.okPressed();
		}
	}

	@Override
	protected Control createContents(Composite parent)
	{
		this.getShell().setText("Edit " + m_builderName);

		Composite mainComposite = m_controls.createControls(parent);

		Composite allButtonComposite = new Composite(mainComposite, SWT.NONE);
		allButtonComposite.setLayout(new GridLayout(2, false));
		allButtonComposite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

		Composite left = new Composite(allButtonComposite, SWT.NONE);
		left.setLayout(new GridLayout(1, false));
		left.setLayoutData(new GridData(SWT.LEFT, SWT.NONE, true, false));

		Button b = new Button(left, SWT.PUSH);
		b.setLayoutData(new GridData(SWT.LEFT, SWT.NONE, false, false));
		b.setText("Variable help");
		b.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				showVariableHelp();
			}
		});

		Composite right = new Composite(allButtonComposite, SWT.NONE);
		right.setLayout(new GridLayout(1, false));
		right.setLayoutData(new GridData(SWT.RIGHT, SWT.NONE, true, false));

		this.createButtonBar(right);

		this.fillTable();
		this.evaluateCommandLine();

		return mainComposite;
	}

	/* package */String getArg(String key)
	{
		String s = m_args.get(key);
		return s == null ? "" : s;
	}

	/* package */void updateArg(String key, String value)
	{
		if (value == null || value.length() == 0)
			m_args.remove(key);
		else
			m_args.put(key, value);
	}

	/* package */void tableSelectionEvent()
	{
		Table tbl = m_controls.m_launcherDefinitionsTable;
		TableItem ti = null;
		int count = tbl.getItemCount();
		int selectionIndex = tbl.getSelectionIndex();
		if (selectionIndex > -1)
			ti = tbl.getSelection()[0];

		m_controls.m_editButton.setEnabled(ti != null);
		m_controls.m_removeButton.setEnabled(ti != null);
		m_controls.m_moveUpButton.setEnabled(ti != null && selectionIndex > 0);
		m_controls.m_moveDownButton.setEnabled(ti != null && selectionIndex < count - 1);
	}

	/* package */void addEvent()
	{
		LauncherDefinitionDialog dlg = new LauncherDefinitionDialog(this.getShell(), null);
		int ret = dlg.open();
		if (ret == Window.OK)
		{
			m_launcherDefinitions.add(dlg.getLauncherDefinition());
			this.fillTable();
			this.evaluateCommandLine();
			Table tbl = m_controls.m_launcherDefinitionsTable;
			tbl.setSelection(tbl.getItemCount() - 1);
			this.tableSelectionEvent();
		}
	}

	/* package */void editEvent()
	{
		Table tbl = m_controls.m_launcherDefinitionsTable;
		int indexToEdit = tbl.getSelectionIndex();
		LauncherDefinitionDialog dlg = new LauncherDefinitionDialog(this.getShell(),
																	m_launcherDefinitions.get(indexToEdit));
		int ret = dlg.open();
		if (ret == Window.OK)
		{
			m_launcherDefinitions.set(indexToEdit, dlg.getLauncherDefinition());
			this.fillTable();
			this.evaluateCommandLine();
			this.tableSelectionEvent();
		}
	}

	/* package */void removeEvent()
	{
		Table tbl = m_controls.m_launcherDefinitionsTable;
		int indexToRemove = tbl.getSelectionIndex();
		TableItem ti = tbl.getItem(indexToRemove);
		if (MessageDialog.openConfirm(	this.getShell(),
										"Remove launcher definition",
										"Are you sure you wish to remove '" + ti.getText(0) + "'?"))
		{
			m_launcherDefinitions.remove(indexToRemove);
			this.fillTable();
			this.evaluateCommandLine();
			int itemsLeft = tbl.getItemCount();
			if (itemsLeft > 0)
				tbl.setSelection(indexToRemove > tbl.getItemCount() - 1 ? indexToRemove - 1 : indexToRemove);
			this.tableSelectionEvent();
		}
	}

	/* package */void moveUpEvent()
	{
		Table tbl = m_controls.m_launcherDefinitionsTable;
		int indexToMoveUp = tbl.getSelectionIndex();
		if (indexToMoveUp > 0)
		{
			LauncherDefinition tmp = m_launcherDefinitions.get(indexToMoveUp - 1);
			m_launcherDefinitions.set(indexToMoveUp - 1, m_launcherDefinitions.get(indexToMoveUp));
			m_launcherDefinitions.set(indexToMoveUp, tmp);
			this.fillTable();
			this.evaluateCommandLine();
			tbl.setSelection(indexToMoveUp - 1);
			this.tableSelectionEvent();
		}
	}

	/* package */void moveDownEvent()
	{
		Table tbl = m_controls.m_launcherDefinitionsTable;
		int indexToMoveDown = tbl.getSelectionIndex();
		if (indexToMoveDown < m_launcherDefinitions.size() - 1)
		{
			LauncherDefinition tmp = m_launcherDefinitions.get(indexToMoveDown + 1);
			m_launcherDefinitions.set(indexToMoveDown + 1, m_launcherDefinitions.get(indexToMoveDown));
			m_launcherDefinitions.set(indexToMoveDown, tmp);
			this.fillTable();
			this.evaluateCommandLine();
			tbl.setSelection(indexToMoveDown + 1);
			this.tableSelectionEvent();
		}
	}

	/* package */void toUseModifyEvent()
	{
		this.evaluateCommandLine();
	}

	/* package */void additionalArgumentsModifyEvent()
	{
		this.evaluateCommandLine();
	}

	private void fillTable()
	{
		Table tbl = m_controls.m_launcherDefinitionsTable;
		tbl.removeAll();
		for (LauncherDefinition ld : m_launcherDefinitions)
		{
			TableItem ti = new TableItem(tbl, SWT.NONE);
			ti.setText(new String[] { ld.getPattern(), ld.getCommandLine() });
		}
		if (tbl.getItemCount() > 0)
		{
			tbl.setSelection(0);
			this.tableSelectionEvent();
		}
	}

	private void initLauncherDefinitionsList() throws CoreException
	{
		m_launcherDefinitionsFile = ExternalCommandBuilder.getLauncherDefinitionsFile(m_args, m_project);
		Collections.addAll(	m_launcherDefinitions,
							ExternalCommandBuilder.getLauncherDefinitions(m_launcherDefinitionsFile));
		if (m_launcherDefinitionsFile.exists() && !m_launcherDefinitionsFile.canWrite())
			MessageDialog.openWarning(this.getShell(), "Launcher definition file is not writable", "Warning: the file "
					+ m_launcherDefinitionsFile.getAbsolutePath() + " is not writable");
	}

	private void evaluateCommandLine()
	{
		m_controls.m_currentCommandLine.setText("");
		String launcherDefinitionToUse = m_controls.m_launcherDefinitionToUse.getText().trim();
		String additionalArgs = m_controls.m_additionalArguments.getText().trim();
		try
		{
			String commandLine = ExternalCommandBuilder.getCommandLine(	m_launcherDefinitions.toArray(new LauncherDefinition[0]),
																		launcherDefinitionToUse,
																		additionalArgs,
																		m_project, -1);
			if (commandLine != null)
				m_controls.m_currentCommandLine.setText(commandLine);
		}
		catch (CoreException ce)
		{
			m_controls.m_currentCommandLine.setText("(error : " + ce + ")");
			CorePlugin.getLogger().info("Error when getting commandline (%s)", ce);
		}
	}

	private void writeLauncherDefinitions() throws Exception
	{
		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter(new FileWriter(m_launcherDefinitionsFile));
			for (LauncherDefinition ld : m_launcherDefinitions)
			{
				pw.println(ld.getPattern());
				pw.println(ld.getCommandLine());
			}
		}
		finally
		{
			if (pw != null)
				try
				{
					pw.close();
				}
				catch (Exception e)
				{
					// noop
				}
		}
	}

	private void showVariableHelp()
	{
		// FIXME...
		MessageDialog.openInformation(this.getShell(), "Available variables", "Variables that can be used:\n${system:*} (see Eclipse help}\n${buckminster.project.location} (the current project location)\n${buckminster.build.type} (the build type invoked)");
	}
}
