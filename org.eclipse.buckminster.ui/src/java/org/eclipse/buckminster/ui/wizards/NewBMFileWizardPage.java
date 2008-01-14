/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

public abstract class NewBMFileWizardPage extends WizardPage
{

	private Text containerText;

	private Text fileText;

	private String m_fileName;

	protected ISelection m_selection;

	private String m_extension;

	protected NewBMFileWizardPage(ISelection selection, String fileName, String extension)
	{
		super("wizardPage");
		m_selection = selection;
		m_fileName = fileName;
		m_extension = extension;
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		Label label = new Label(container, SWT.NULL);
		label.setText("&Container:");

		containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		containerText.setLayoutData(gd);
		containerText.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				dialogChanged();
			}
		});

		Button button = new Button(container, SWT.PUSH);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				handleBrowse();
			}
		});
		label = new Label(container, SWT.NULL);
		label.setText("&File name:");

		fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		fileText.setLayoutData(gd);
		fileText.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				dialogChanged();
			}
		});
		initialize();
		dialogChanged();
		setControl(container);
	}

	/**
	 * Tests if the current workbench m_selection is a suitable container to use.
	 */
	private void initialize()
	{
		if(m_selection != null && m_selection.isEmpty() == false && m_selection instanceof IStructuredSelection)
		{
			IStructuredSelection ssel = (IStructuredSelection)m_selection;
			if(ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
			
			if(obj instanceof IProjectNature)
			{
				obj = ((IProjectNature)obj).getProject();
			}
			
			if(obj instanceof IResource)
			{
				IContainer container;
				if(obj instanceof IContainer)
					container = (IContainer)obj;
				else
					container = ((IResource)obj).getParent();
				containerText.setText(container.getFullPath().toString());
			}
		}
		fileText.setText(m_fileName);
	}

	/**
	 * Uses the standard container m_selection dialog to choose the new value for the container field.
	 */
	private void handleBrowse()
	{
		ContainerSelectionDialog dialog = new ContainerSelectionDialog(getShell(), ResourcesPlugin.getWorkspace()
				.getRoot(), false, "Select new file container");
		if(dialog.open() == Window.OK)
		{
			Object[] result = dialog.getResult();
			if(result.length == 1)
			{
				containerText.setText(((Path)result[0]).toString());
			}
		}
	}

	/**
	 * Ensures that both text fields are set.
	 */
	private void dialogChanged()
	{
		IResource container = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(getContainerName()));
		String fileName = getFileName();

		if(getContainerName().length() == 0)
		{
			updateStatus("File container must be specified");
			return;
		}
		if(container == null || (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0)
		{
			updateStatus("File container must exist");
			return;
		}
		if(!container.isAccessible())
		{
			updateStatus("Project must be writable");
			return;
		}
		if(fileName.length() == 0)
		{
			updateStatus("File name must be specified");
			return;
		}
		if(fileName.replace('\\', '/').indexOf('/', 1) > 0)
		{
			updateStatus("File name must be valid");
			return;
		}
		int dotLoc = fileName.lastIndexOf('.');
		if(dotLoc != -1)
		{
			String ext = fileName.substring(dotLoc + 1);
			if(ext.equalsIgnoreCase(m_extension) == false)
			{
				updateStatus("File extension must be \"" + m_extension + "\"");
				return;
			}
		}
		updateStatus(null);
	}

	private void updateStatus(String message)
	{
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	public String getContainerName()
	{
		return containerText.getText();
	}

	public String getFileName()
	{
		return fileText.getText();
	}

}
