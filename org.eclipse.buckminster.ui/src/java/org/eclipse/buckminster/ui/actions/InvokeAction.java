/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;

/**
 * @author Thomas Hallgren
 */
public class InvokeAction implements IObjectActionDelegate
{
	private static final String LAST_ACTION_PROPERTIES_FILE = "lastActionPropertiesFile";

	private class ActionsDialog extends Dialog
	{
		private final List<Attribute> m_attributes;

		private ListViewer m_viewer;

		private String m_title;

		private Attribute m_selectedAttribute;

		private Text m_propertiesFileText;

		private Button m_propertiesFileBrowseButton;

		private Text m_errorMessageText;

		ActionsDialog(Shell parentShell, String title, List<Attribute> attributes)
		{
			super(parentShell);
			m_title = title;
			m_attributes = attributes;
		}

		Attribute getSelectedAttribute()
		{
			return m_selectedAttribute;
		}

		@Override
		protected void configureShell(Shell newShell)
		{
			super.configureShell(newShell);
			newShell.setText(m_title);
		}

		@Override
		protected Control createDialogArea(Composite parent)
		{
			Composite composite = (Composite)super.createDialogArea(parent);
			org.eclipse.swt.widgets.List list = new org.eclipse.swt.widgets.List(composite, SWT.BORDER);
			list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			m_viewer = new ListViewer(list);
			m_viewer.setContentProvider(new ArrayContentProvider());
			m_viewer.setLabelProvider(new LabelProvider()
			{
				@Override
				public String getText(Object element)
				{
					return element == null ? "" : ((Attribute)element).getName();
				}
			});
			m_viewer.addSelectionChangedListener(new ISelectionChangedListener()
			{
				public void selectionChanged(SelectionChangedEvent event)
				{
					IStructuredSelection selection = (IStructuredSelection)event.getSelection();
					getButton(IDialogConstants.OK_ID).setEnabled(selection.size() == 1);
				}
			});
			m_viewer.setInput(m_attributes);

			Group propertiesGroup = new Group(composite, SWT.NONE);
			propertiesGroup.setText("Action properties");
			GridLayout layout = new GridLayout(3, false);
			layout.marginHeight = 0;
			layout.marginWidth = 3;
			propertiesGroup.setLayout(layout);
			propertiesGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
			m_propertiesFileText = UiUtils.createLabeledText(propertiesGroup, "File:", SWT.NONE, new ModifyListener()
			{
				public void modifyText(ModifyEvent e)
				{
					String txt = m_propertiesFileText.getText().trim();
					if(txt.length() > 0 && new File(txt).exists())
					{
						m_propertiesFile = txt;
						setErrorMessage(null);
					}
					else
					{
						m_propertiesFile = null;
						setErrorMessage("File does not exist");
					}
				}
			});
			if(m_propertiesFile != null)
				m_propertiesFileText.setText(m_propertiesFile);

			m_propertiesFileBrowseButton = new Button(propertiesGroup, SWT.PUSH);
			m_propertiesFileBrowseButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			m_propertiesFileBrowseButton.setText("Browse...");
			m_propertiesFileBrowseButton.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent se)
				{
					FileDialog dlg = new FileDialog(getShell());
					dlg.setFilterExtensions(new String[] { "*.properties" });
					m_propertiesFileText.setText(TextUtils.notNullString(dlg.open()));
				}
			});
	        m_errorMessageText = new Text(composite, SWT.READ_ONLY);
	        m_errorMessageText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
	        m_errorMessageText.setBackground(m_errorMessageText.getDisplay()
	                .getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
			return composite;
		}

		public void setErrorMessage(String errorMessage)
		{
			if(m_errorMessageText != null && !m_errorMessageText.isDisposed())
			{
				m_errorMessageText.setText(errorMessage == null ? "" : errorMessage); //$NON-NLS-1$
				m_errorMessageText.getParent().update();
				Control button = getButton(IDialogConstants.OK_ID);
				if(button != null)
				{
					button.setEnabled(errorMessage == null);
				}
			}
		}

		@Override
		protected void initializeBounds()
		{
			super.initializeBounds();
			Shell shell = getShell();
			Point size = shell.getSize();
			Point loc = shell.getLocation();
			shell.setBounds(getConstrainedShellBounds(new Rectangle(loc.x, loc.y, 350, size.y)));
			((org.eclipse.swt.widgets.List)m_viewer.getControl()).select(0);
		}

		@Override
		protected void okPressed()
		{
			IStructuredSelection selection = (IStructuredSelection)m_viewer.getSelection();
			if(selection.size() == 1)
			{
				m_selectedAttribute = (Attribute)selection.getFirstElement();
				super.okPressed();
			}
		}
	}

	private IWorkbenchPart m_activePart;

	private CSpec m_selectedComponent;

	private String m_propertiesFile;

	class ActionJob extends WorkspaceJob
	{
		ActionJob(String name)
		{
			super(name);
			setPriority(LONG);
			setUser(true);
			setSystem(false);
		}

		@Override
		public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException
		{
			IPerformManager pm = CorePlugin.getPerformManager();
			Map<String, String> props = new HashMap<String, String>();
			String outputRoot = System.getProperty(KeyConstants.ACTION_OUTPUT_ROOT);
			if(outputRoot != null)
				props.put(KeyConstants.ACTION_OUTPUT_ROOT, outputRoot);

			if(m_propertiesFile != null)
			{
				BufferedInputStream input = null;
				try
				{
					input = new BufferedInputStream(new FileInputStream(m_propertiesFile));
					props.putAll(new BMProperties(input));
				}
				catch(IOException e)
				{
					return BuckminsterException.wrap(e).getStatus();
				}
				finally
				{
					IOUtils.close(input);
				}
			}
			try
			{
				return pm.perform(m_selectedComponent, this.getName(), props, false, monitor);
			}
			catch(Throwable e)
			{
				final IStatus status = BuckminsterException.wrap(e).getStatus();
				CorePlugin.logWarningsAndErrors(status);
				Display.getDefault().asyncExec(new Runnable()
				{
					public void run()
					{
						ErrorDialog.openError(null, "Action error", null, status);
					}	
				});
				return Status.OK_STATUS;
			}
		}
	}

	public void run(IAction action)
	{
		if(m_activePart == null)
			return;

		IWorkbenchPartSite site = m_activePart.getSite();
		final Shell shell = site.getShell();
		if(m_selectedComponent == null)
		{
			MessageDialog.openInformation(shell, null, "No component is selected");
			return;
		}
		List<Attribute> viableAttributes;
		try
		{
			viableAttributes = m_selectedComponent.getAttributesProducedByActions(false);
		}
		catch(CoreException e)
		{
			UiUtils.openError(shell, "Errors during action perform", e);
			return;
		}

		IPreferenceStore preferences = UiPlugin.getDefault().getPreferenceStore();
		m_propertiesFile = preferences.getString(LAST_ACTION_PROPERTIES_FILE);
		ActionsDialog dialog = new ActionsDialog(shell, "Actions of " + m_selectedComponent.getName(),
			viableAttributes);
		if(dialog.open() != Window.OK)
			return;

		if(m_propertiesFile != null)
			preferences.setValue(LAST_ACTION_PROPERTIES_FILE, m_propertiesFile);

		ActionJob job = new ActionJob(dialog.getSelectedAttribute().getName());
		job.schedule();
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart)
	{
		m_activePart = targetPart;
	}

	public void selectionChanged(IAction action, ISelection selection)
	{
		m_selectedComponent = null;
		if(!(selection instanceof IStructuredSelection))
			return;

		IStructuredSelection s = (IStructuredSelection)selection;
		if(s.size() != 1)
			return;

		Object first = s.getFirstElement();
		if(!(first instanceof IResource))
			return;

		try
		{
			m_selectedComponent = WorkspaceInfo.getCSpec((IResource)first);
		}
		catch(CoreException e)
		{
			CorePlugin.getLogger().warning(e.getMessage(), e);
		}
	}
}
