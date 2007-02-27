/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ui.internal.build;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.build.AbstractBuckminsterBuilder;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * @author kolwing
 */
public class ProjectBuildersPropertyPage extends PropertyPage implements IWorkbenchPropertyPage
{
	private final ProjectBuildersPropertyPageControls m_controls;

	private IProject m_project;

	private IProjectDescription m_projDesc;

	private final Map<String, IConfigurationElement> m_builderImplementors = new HashMap<String, IConfigurationElement>();

	/**
	 * 
	 */
	public ProjectBuildersPropertyPage()
	{
		super();

		this.noDefaultAndApplyButton();

		m_controls = new ProjectBuildersPropertyPageControls(this);
		this.findBuilderImplementors();
	}

	@Override
	public void dispose()
	{
		m_controls.dispose();

		super.dispose();
	}

	@Override
	public boolean performOk()
	{
		WorkspaceModifyOperation op = new WorkspaceModifyOperation()
		{
			@Override
			protected void execute(IProgressMonitor monitor) throws CoreException
			{
				m_project.setDescription(m_projDesc, IResource.KEEP_HISTORY, monitor);
			}
		};

		boolean reslt = false;
		try
		{
			new ProgressMonitorDialog(this.getShell()).run(true, true, op);
			reslt = true;
		}
		catch(InterruptedException ie)
		{
			// ignore - it was cancelled
		}
		catch(InvocationTargetException ite)
		{
			Throwable t = ite.getTargetException();
			if(t instanceof CoreException)
			{
				CoreException ce = (CoreException)t;
				ErrorDialog.openError(this.getShell(), UiPlugin.getResourceString("NIY"), null,
					ce.getStatus());
			}
			else
			{
				String title = UiPlugin.getResourceString("NIY");
				String rawMsg = t.getLocalizedMessage();
				if(rawMsg == null)
					rawMsg = t.toString();
				String msg = MessageFormat.format(UiPlugin.getResourceString("NIY ERROR"),
					new Object[] { rawMsg });
				MessageDialog.openError(this.getShell(), title, msg);
			}
		}

		return reslt;
	}

	@Override
	public void setVisible(boolean visible)
	{
		super.setVisible(visible);
		if(visible)
			this.fillTable();
	}

	@SuppressWarnings("unchecked")
	/* package */void addEvent()
	{
		SortedMap<String, IConfigurationElement> input = new TreeMap<String, IConfigurationElement>();
		for(IConfigurationElement elem : m_builderImplementors.values())
			input.put(AbstractBuckminsterBuilder.bestNameForBuilder(null, elem), elem);

		AddBuilderDialog dlg = new AddBuilderDialog(this.getShell());
		dlg.setTitle("Select builder to add");
		dlg.setInput(input);
		dlg.setContentProvider(new ArrayContentProvider()
		{
			@Override
			public Object[] getElements(Object inputMap)
			{
				return ((Map<?, ?>)inputMap).entrySet().toArray();
			}
		});
		dlg.setLabelProvider(new LabelProvider()
		{
			@Override
			public String getText(Object element)
			{
				Map.Entry<String, IConfigurationElement> entry = (Map.Entry<String, IConfigurationElement>)element;
				return entry.getKey();
			}
		});

		int ret = dlg.open();
		String name = dlg.getName();
		Object[] o = dlg.getResult();
		if(ret == Window.OK && o.length > 0)
		{
			assert o.length == 1 : "Unexpected array size";
			Map.Entry<String, IConfigurationElement> entry = (Map.Entry<String, IConfigurationElement>)o[0];

			String newBuilderName = entry.getValue().getDeclaringExtension().getUniqueIdentifier();
			ICommand[] oldBuilders = m_projDesc.getBuildSpec();
			ICommand newBuilder = m_projDesc.newCommand();
			newBuilder.setBuilderName(newBuilderName);
			if(name != null)
			{
				Map args = new HashMap();
				args.put(AbstractBuckminsterBuilder.ARG_GIVEN_NAME_KEY, name);
				newBuilder.setArguments(args);
			}

			ICommand[] newBuilders = new ICommand[oldBuilders.length + 1];
			System.arraycopy(oldBuilders, 0, newBuilders, 0, oldBuilders.length);
			newBuilders[oldBuilders.length] = newBuilder;
			m_projDesc.setBuildSpec(newBuilders);
			this.fillTable();
			Table buildersTable = m_controls.getBuildersTable();
			buildersTable.setSelection(buildersTable.getItemCount() - 1);
			this.tableSelectionEvent(buildersTable.getSelection()[0]);
		}
	}

	/* package */void editEvent()
	{
		Table buildersTable = m_controls.getBuildersTable();
		int indexToEdit = buildersTable.getSelectionIndex();
		TableItem ti = buildersTable.getItem(indexToEdit);
		ICommand builder = (ICommand)ti.getData();
		String fullName = ti.getText();
		ICommand[] builders = m_projDesc.getBuildSpec();
		EditBuilderDialog dlg = new EditBuilderDialog(this.getShell(), m_project, builder, fullName,
			m_builderImplementors.get(builder.getBuilderName()));
		if(dlg.open() == Window.OK)
		{
			builders[indexToEdit] = dlg.getBuilder();
			m_projDesc.setBuildSpec(builders);
			this.fillTable();
		}
	}

	/* package */void removeEvent()
	{
		Table buildersTable = m_controls.getBuildersTable();
		int indexToRemove = buildersTable.getSelectionIndex();
		TableItem ti = buildersTable.getItem(indexToRemove);
		if(MessageDialog.openConfirm(this.getShell(), "Remove builder", "Are you sure you wish to remove '"
			+ ti.getText() + "'?"))
		{
			ICommand[] builders = m_projDesc.getBuildSpec();
			ICommand[] newBuilders = new ICommand[builders.length - 1];
			System.arraycopy(builders, 0, newBuilders, 0, indexToRemove);
			System.arraycopy(builders, indexToRemove + 1, newBuilders, indexToRemove, newBuilders.length
				- indexToRemove);
			m_projDesc.setBuildSpec(newBuilders);
			this.fillTable();
			int itemsLeft = buildersTable.getItemCount();
			if(itemsLeft > 0)
			{
				buildersTable.setSelection(indexToRemove > buildersTable.getItemCount() - 1
					? indexToRemove - 1 : indexToRemove);
				this.tableSelectionEvent(buildersTable.getSelection()[0]);
			}
			else
				this.tableSelectionEvent(null);
		}
	}

	/* package */void moveUpEvent()
	{
		Table buildersTable = m_controls.getBuildersTable();
		int indexToMoveUp = buildersTable.getSelectionIndex();
		ICommand[] builders = m_projDesc.getBuildSpec();
		if(indexToMoveUp > 0)
		{
			ICommand tmp = builders[indexToMoveUp - 1];
			builders[indexToMoveUp - 1] = builders[indexToMoveUp];
			builders[indexToMoveUp] = tmp;
			m_projDesc.setBuildSpec(builders);
			this.fillTable();
			buildersTable.setSelection(indexToMoveUp - 1);
			this.tableSelectionEvent(buildersTable.getSelection()[0]);
		}
	}

	/* package */void moveDownEvent()
	{
		Table buildersTable = m_controls.getBuildersTable();
		int indexToMoveDown = buildersTable.getSelectionIndex();
		ICommand[] builders = m_projDesc.getBuildSpec();
		if(indexToMoveDown < builders.length - 1)
		{
			ICommand tmp = builders[indexToMoveDown + 1];
			builders[indexToMoveDown + 1] = builders[indexToMoveDown];
			builders[indexToMoveDown] = tmp;
			m_projDesc.setBuildSpec(builders);
			this.fillTable();
			buildersTable.setSelection(indexToMoveDown + 1);
			this.tableSelectionEvent(buildersTable.getSelection()[0]);
		}
	}

	/* package */void tableSelectionEvent(TableItem ti)
	{
		Object o = null;
		if(ti != null)
			o = ti.getData();

		m_controls.getEditButton().setEnabled(o != null);
		m_controls.getRemoveButton().setEnabled(o != null);
		m_controls.getMoveUpButton().setEnabled(ti != null);
		m_controls.getMoveDownButton().setEnabled(ti != null);
		if(o == null)
		{
			if(ti != null)
				ti.setChecked(true);
		}
		else
		{
			ICommand builder = (ICommand)o;
			Map<String, String> args = getBuilderArgs(builder);
			AbstractBuckminsterBuilder.setDisabled(args, ti == null || !ti.getChecked());
			builder.setArguments(args);
			Table buildersTable = m_controls.getBuildersTable();
			int indexToEdit = buildersTable.getSelectionIndex();
			ICommand[] builders = m_projDesc.getBuildSpec();
			builders[indexToEdit] = builder;
			m_projDesc.setBuildSpec(builders);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent)
	{
		try
		{
			m_project = (IProject)this.getElement().getAdapter(IProject.class);
			m_projDesc = m_project.getDescription();
		}
		catch(CoreException ce)
		{
			this.setErrorMessage("Failed to retrieve project description: " + ce.toString());
		}

		return m_controls.createControls(parent);
	}

	private void findBuilderImplementors()
	{
		IExtensionRegistry er = Platform.getExtensionRegistry();

		for(IConfigurationElement elem : er.getConfigurationElementsFor(CorePlugin.INCREMENTAL_BUILDERS_POINT))
			m_builderImplementors.put(elem.getAttribute("ID"), null);

		for(IConfigurationElement builderElem : er.getConfigurationElementsFor("org.eclipse.core.resources.builders"))
		{
			String id = builderElem.getDeclaringExtension().getUniqueIdentifier();
			if(m_builderImplementors.containsKey(id))
				m_builderImplementors.put(id, builderElem);
		}
	}

	private void fillTable()
	{
		Table buildersTable = m_controls.getBuildersTable();
		buildersTable.removeAll();
		for(ICommand builder : m_projDesc.getBuildSpec())
		{
			TableItem ti = new TableItem(buildersTable, SWT.NONE);
			IConfigurationElement ce = m_builderImplementors.get(builder.getBuilderName());
			if(ce != null)
			{
				Map<String, String> args = getBuilderArgs(builder);
				ti.setText(AbstractBuckminsterBuilder.bestNameForBuilder(
					args.get(AbstractBuckminsterBuilder.ARG_GIVEN_NAME_KEY), ce));
				ti.setChecked(!AbstractBuckminsterBuilder.isDisabled(args));
				ti.setData(builder);
			}
			else
			{
				ti.setText(builder.getBuilderName());
				ti.setFont(m_controls.getItemItalicFont());
				ti.setChecked(true);
			}
		}
		if(buildersTable.getItemCount() > 0)
		{
			buildersTable.setSelection(0);
			this.tableSelectionEvent(buildersTable.getSelection()[0]);
		}

	}

	@SuppressWarnings("unchecked")
	private static Map<String, String> getBuilderArgs(ICommand builder)
	{
		return builder.getArguments();
	}
}
