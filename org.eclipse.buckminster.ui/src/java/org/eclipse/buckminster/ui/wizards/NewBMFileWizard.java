/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.wizards;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public abstract class NewBMFileWizard extends Wizard
{

	private NewBMFileWizardPage m_page;

	/**
	 * Sets the single page for this wizard and adds it as a wizard page
	 * as expected in the callback to addPages.
	 * A derived class should call this method with the wanted page in its
	 * addPages method.
	 * @param page the page to use
	 */
	protected void setPage(NewBMFileWizardPage page)
	{
		m_page = page;
		addPage(m_page);
	}

	private ISelection m_selection;

	protected ISelection getSelection()
	{
		return m_selection;
	}

	protected NewBMFileWizard()
	{
		super();
		setNeedsProgressMonitor(true);
	}

	public String getContainerName()
	{
		return m_page.getContainerName();
	}

	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using
	 * wizard as execution context.
	 */
	@Override
	public boolean performFinish()
	{
		final String containerName = getContainerName();
		final String fileName = m_page.getFileName();
		IRunnableWithProgress op = new IRunnableWithProgress()
		{
			public void run(IProgressMonitor monitor) throws InvocationTargetException
			{
				try
				{
					doFinish(containerName, fileName, monitor);
				}
				catch(CoreException e)
				{
					throw new InvocationTargetException(e);
				}
				finally
				{
					monitor.done();
				}
			}
		};
		try
		{
			getContainer().run(true, false, op);
		}
		catch(InterruptedException e)
		{
			return false;
		}
		catch(InvocationTargetException e)
		{
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * The worker method. It will find the container, create the file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 */
	private void doFinish(String containerName, String fileName, IProgressMonitor monitor) throws CoreException
	{
		// create a sample file
		monitor.beginTask("Creating " + fileName, 2);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if(!resource.exists() || !(resource instanceof IContainer))
		{
			throwCoreException("Container \"" + containerName + "\" does not exist.");
		}
		IContainer container = (IContainer)resource;
		final IFile file = container.getFile(new Path(fileName));
		try
		{
			InputStream stream = openContentStream(containerName, fileName);
			if(file.exists())
			{
				file.setContents(stream, true, true, monitor);
			}
			else
			{
				file.create(stream, true, monitor);
			}
			stream.close();
		}
		catch(IOException e)
		{
		}
		monitor.worked(1);
		monitor.setTaskName("Opening file for editing...");
		getShell().getDisplay().asyncExec(new Runnable()
		{
			public void run()
			{
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try
				{
					IDE.openEditor(page, file, true);
				}
				catch(PartInitException e)
				{
				}
			}
		});
		monitor.worked(1);
	}

	/**
	 * Create the file without content. 
	 * This default implementation returns empty content.
	 */
	protected InputStream openContentStream(String containerName, String fileName)
	{
		String contents = "";
		return new ByteArrayInputStream(contents.getBytes());
	}

	protected void throwCoreException(String message) throws CoreException
	{
		IStatus status = new Status(IStatus.ERROR, "org.eclipse.buckminster.bmview", IStatus.OK, message, null);
		throw new CoreException(status);
	}

	/**
	 * We will accept the m_selection in the workbench to see if we can initialize from it.
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection)
	{
		this.m_selection = selection;
	}

}
