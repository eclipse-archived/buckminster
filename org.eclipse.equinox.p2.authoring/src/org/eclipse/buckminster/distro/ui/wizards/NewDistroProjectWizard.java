/***********************************************************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others. All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation Remy Chi Jian Suen <remy.suen@gmail.com> - Bug 44162
 * [Wizards] Define constants for wizard ids of new.file, new.folder, and new.project
 **********************************************************************************************************************/

/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/

package org.eclipse.buckminster.distro.ui.wizards;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.p2.authoring.P2AuthoringUIPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IPluginContribution;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.activities.IActivityManager;
import org.eclipse.ui.activities.IIdentifier;
import org.eclipse.ui.activities.IWorkbenchActivitySupport;
import org.eclipse.ui.activities.WorkbenchActivityHelper;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.dialogs.WizardNewProjectReferencePage;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.undo.CreateProjectOperation;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;
import org.eclipse.ui.internal.IPreferenceConstants;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.ide.IDEInternalPreferences;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.ide.StatusUtil;
import org.eclipse.ui.internal.registry.PerspectiveDescriptor;
import org.eclipse.ui.internal.util.PrefUtil;
import org.eclipse.ui.internal.wizards.newresource.ResourceMessages;
import org.eclipse.ui.statushandlers.StatusAdapter;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.xml.sax.SAXException;

/**
 * Standard workbench wizard that creates a new distro project resource in the workspace.
 * <p>
 * This class may be instantiated and used without further configuration; this class is not intended to be subclassed.
 * </p>
 * <p>
 * Example:
 * 
 * <pre>
 * IWorkbenchWizard wizard = new NewDistroProjectWizard();
 * wizard.init(workbench, selection);
 * WizardDialog dialog = new WizardDialog(shell, wizard);
 * dialog.open();
 * </pre>
 * 
 * During the call to <code>open</code>, the wizard dialog is presented to the user. When the user hits Finish, a
 * project resource with the user-specified name is created, the dialog closes, and the call to <code>open</code>
 * returns.
 * </p>
 * This class is a clone of BasicNewProjectResourceWizard because it is not intended for subclassing,
 * and certain aspects of the class can not be modified in a subclass.
 * TODO: Don't know if this class is needed - there may be no need to create some new type of project
 */
@SuppressWarnings("restriction")
public class NewDistroProjectWizard extends BasicNewResourceWizard implements IExecutableExtension
{

	private static final String NEW_DISTRO_PROJECT = "New Distro Project";
	private static final String DISTRO_NATURE_ID = "org.eclipse.buckminster.distroNature"; //$NON-NLS-1$

	/**
	 * The wizard id for creating new projects in the workspace.
	 * 
	 * @since 3.4
	 */
	public static final String WIZARD_ID = "org.eclipse.ui.wizards.new.project"; //$NON-NLS-1$

	private WizardNewProjectCreationPage mainPage;

	private WizardNewProjectReferencePage referencePage;

	// cache of newly-created project
	private IProject newProject;

	/**
	 * The config element which declares this wizard.
	 */
	private IConfigurationElement configElement;

	private static String WINDOW_PROBLEMS_TITLE = ResourceMessages.NewProject_errorOpeningWindow;

	/**
	 * Extension attribute name for final perspective.
	 */
	private static final String FINAL_PERSPECTIVE = "finalPerspective"; //$NON-NLS-1$

	/**
	 * Extension attribute name for preferred perspectives.
	 */
	private static final String PREFERRED_PERSPECTIVES = "preferredPerspectives"; //$NON-NLS-1$

	/**
	 * Creates a wizard for creating a new project resource in the workspace.
	 */
	public NewDistroProjectWizard()
	{
		IDialogSettings workbenchSettings = IDEWorkbenchPlugin.getDefault().getDialogSettings();
		IDialogSettings section = workbenchSettings.getSection("NewDistroWizard");//$NON-NLS-1$
		if(section == null)
		{
			section = workbenchSettings.addNewSection("NewDistroWizard");//$NON-NLS-1$
		}
		setDialogSettings(section);
	}

	/*
	 * (non-Javadoc) Method declared on IWizard.
	 */
	@Override
	public void addPages()
	{
		super.addPages();

		mainPage = new WizardNewProjectCreationPage("basicNewProjectPage"){ //$NON-NLS-1$
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.ui.dialogs.WizardNewProjectCreationPage#createControl(org.eclipse.swt.widgets.Composite)
			 */
			@Override
			public void createControl(Composite parent)
			{
				super.createControl(parent);
				createWorkingSetGroup((Composite)getControl(), getSelection(),
						new String[] { "org.eclipse.ui.resourceWorkingSetPage" }); //$NON-NLS-1$
				Dialog.applyDialogFont(getControl());
			}
		};
		mainPage.setTitle("Distro Project");
		mainPage.setDescription("Creates a new Distro Project");
		this.addPage(mainPage);

		// only add page if there are already projects in the workspace
		if(ResourcesPlugin.getWorkspace().getRoot().getProjects().length > 0)
		{
			referencePage = new WizardNewProjectReferencePage("basicReferenceProjectPage");//$NON-NLS-1$
			referencePage.setTitle(ResourceMessages.NewProject_referenceTitle);
			referencePage.setDescription(ResourceMessages.NewProject_referenceDescription);
			this.addPage(referencePage);
		}
	}

	/**
	 * Creates a new project resource with the selected name.
	 * <p>
	 * In normal usage, this method is invoked after the user has pressed Finish on the wizard; the enablement of the
	 * Finish button implies that all controls on the pages currently contain valid values.
	 * </p>
	 * <p>
	 * Note that this wizard caches the new project once it has been successfully created; subsequent invocations of
	 * this method will answer the same project resource without attempting to create it again.
	 * </p>
	 * 
	 * @return the created project resource, or <code>null</code> if the project was not created
	 */
	@SuppressWarnings("deprecation")
	private IProject createNewProject()
	{
		if(newProject != null)
		{
			return newProject;
		}

		// get a project handle
		final IProject newProjectHandle = mainPage.getProjectHandle();

		// get a project descriptor
		URI location = null;
		if(!mainPage.useDefaults())
		{
			location = mainPage.getLocationURI();
		}

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final IProjectDescription description = workspace.newProjectDescription(newProjectHandle.getName());
		description.setLocationURI(location);

		// update the referenced project if provided
		if(referencePage != null)
		{
			IProject[] refProjects = referencePage.getReferencedProjects();
			if(refProjects.length > 0)
			{
				description.setReferencedProjects(refProjects);
			}
		}

		// create the new project operation
		IRunnableWithProgress op = new IRunnableWithProgress()
		{
			public void run(IProgressMonitor monitor) throws InvocationTargetException
			{
				CreateProjectOperation op1 = new CreateProjectOperation(description, NEW_DISTRO_PROJECT);
				try
				{
					PlatformUI.getWorkbench().getOperationSupport().getOperationHistory().execute(op1, monitor,
							WorkspaceUndoUtil.getUIInfoAdapter(getShell()));
				}
				catch(ExecutionException e)
				{
					throw new InvocationTargetException(e);
				}
			}
		};

		// run the new project creation operation
		try
		{
			getContainer().run(true, true, op);
		}
		catch(InterruptedException e)
		{
			return null;
		}
		catch(InvocationTargetException e)
		{
			Throwable t = e.getTargetException();
			if(t instanceof ExecutionException && t.getCause() instanceof CoreException)
			{
				CoreException cause = (CoreException)t.getCause();
				StatusAdapter status;
				if(cause.getStatus().getCode() == IResourceStatus.CASE_VARIANT_EXISTS)
				{
					status = new StatusAdapter(StatusUtil.newStatus(IStatus.WARNING, NLS.bind(
							ResourceMessages.NewProject_caseVariantExistsError, newProjectHandle.getName()), cause));
				}
				else
				{
					status = new StatusAdapter(StatusUtil.newStatus(cause.getStatus().getSeverity(),
							ResourceMessages.NewProject_errorMessage, cause));
				}
				status.setProperty(StatusAdapter.TITLE_PROPERTY, ResourceMessages.NewProject_errorMessage);
				StatusManager.getManager().handle(status, StatusManager.BLOCK);
			}
			else
			{
				StatusAdapter status = new StatusAdapter(new Status(IStatus.WARNING, IDEWorkbenchPlugin.IDE_WORKBENCH,
						0, NLS.bind(ResourceMessages.NewProject_internalError, t.getMessage()), t));
				status.setProperty(StatusAdapter.TITLE_PROPERTY, ResourceMessages.NewProject_errorMessage);
				StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.BLOCK);
			}
			return null;
		}

		newProject = newProjectHandle;

		return newProject;
	}

	/**
	 * Returns the newly created project.
	 * 
	 * @return the created project, or <code>null</code> if project not created
	 */
	public IProject getNewProject()
	{
		return newProject;
	}

	/*
	 * (non-Javadoc) Method declared on IWorkbenchWizard.
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection currentSelection)
	{
		super.init(workbench, currentSelection);
		setNeedsProgressMonitor(true);
		setWindowTitle(NEW_DISTRO_PROJECT);
	}

	/*
	 * (non-Javadoc) Method declared on BasicNewResourceWizard.
	 */
	@Override
	protected void initializeDefaultPageImageDescriptor()
	{
		ImageDescriptor desc = P2AuthoringUIPlugin.imageDescriptorFromPlugin(P2AuthoringUIPlugin.getDefault().getBundle().getSymbolicName(),
				"icons/new_wizbox.png");
		setDefaultPageImageDescriptor(desc);
	}

	/*
	 * (non-Javadoc) Opens a new window with a particular perspective and input.
	 */
	private static void openInNewWindow(IPerspectiveDescriptor desc)
	{

		// Open the page.
		try
		{
			PlatformUI.getWorkbench().openWorkbenchWindow(desc.getId(), ResourcesPlugin.getWorkspace().getRoot());
		}
		catch(WorkbenchException e)
		{
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			if(window != null)
			{
				ErrorDialog.openError(window.getShell(), WINDOW_PROBLEMS_TITLE, e.getMessage(), e.getStatus());
			}
		}
	}

	/*
	 * (non-Javadoc) Method declared on IWizard.
	 */
	@Override
	public boolean performFinish()
	{
		createNewProject();

		if(newProject == null)
		{
			return false;
		}

		IWorkingSet[] workingSets = mainPage.getSelectedWorkingSets();
		getWorkbench().getWorkingSetManager().addToWorkingSets(newProject, workingSets);

		setupProject();
		updatePerspective();
		selectAndReveal(newProject);

		return true;
	}
	private boolean setupProject()
	{
		// Add the Distro Nature
		// 
		try
		{
			IProjectDescription description = newProject.getDescription();
			List<String> newIds = new ArrayList<String>();
			newIds.addAll(Arrays.asList(description.getNatureIds()));
			// probably impossible that it already has the DISTRO nature, but check anyway
			int index = newIds.indexOf(DISTRO_NATURE_ID);
			if(index == -1)
			{
				newIds.add(DISTRO_NATURE_ID);
			}
			description.setNatureIds(newIds.toArray(new String[newIds.size()]));
			newProject.setDescription(description, null);
		}
		catch(CoreException e1)
		{
			MessageDialog.openError(getShell(), "Error", e1.getLocalizedMessage());
			e1.printStackTrace();
		}
		// Add the Distro File
		//
		final IContainer container = newProject;
		IRunnableWithProgress op = new IRunnableWithProgress()
		{
			public void run(IProgressMonitor monitor) throws InvocationTargetException
			{
				try
				{
					doFinish(container, "buckminster.distro", monitor);
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
	/*
	 * (non-Javadoc) Replaces the current perspective with the new one.
	 */
	private static void replaceCurrentPerspective(IPerspectiveDescriptor persp)
	{

		// Get the active page.
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if(window == null)
		{
			return;
		}
		IWorkbenchPage page = window.getActivePage();
		if(page == null)
		{
			return;
		}

		// Set the perspective.
		page.setPerspective(persp);
	}

	/**
	 * Stores the configuration element for the wizard. The config element will be used in <code>performFinish</code>
	 * to set the result perspective.
	 */
	public void setInitializationData(IConfigurationElement cfig, String propertyName, Object data)
	{
		configElement = cfig;
	}

	/**
	 * Updates the perspective for the active page within the window.
	 */
	protected void updatePerspective()
	{
		updatePerspective(configElement);
	}

	/**
	 * Updates the perspective based on the current settings in the Workbench/Perspectives preference page.
	 * 
	 * Use the setting for the new perspective opening if we are set to open in a new perspective.
	 * <p>
	 * A new project wizard class will need to implement the <code>IExecutableExtension</code> interface so as to gain
	 * access to the wizard's <code>IConfigurationElement</code>. That is the configuration element to pass into this
	 * method.
	 * </p>
	 * 
	 * @param configElement -
	 *            the element we are updating with
	 * 
	 * @see IPreferenceConstants#OPM_NEW_WINDOW
	 * @see IPreferenceConstants#OPM_ACTIVE_PAGE
	 * @see IWorkbenchPreferenceConstants#NO_NEW_PERSPECTIVE
	 */
	@SuppressWarnings("unchecked")
	public static void updatePerspective(IConfigurationElement configElement)
	{
		// Do not change perspective if the configuration element is
		// not specified.
		if(configElement == null)
		{
			return;
		}

		// Retrieve the new project open perspective preference setting
		String perspSetting = PrefUtil.getAPIPreferenceStore().getString(IDE.Preferences.PROJECT_OPEN_NEW_PERSPECTIVE);

		String promptSetting = IDEWorkbenchPlugin.getDefault().getPreferenceStore().getString(
				IDEInternalPreferences.PROJECT_SWITCH_PERSP_MODE);

		// Return if do not switch perspective setting and are not prompting
		if(!(promptSetting.equals(MessageDialogWithToggle.PROMPT))
				&& perspSetting.equals(IWorkbenchPreferenceConstants.NO_NEW_PERSPECTIVE))
		{
			return;
		}

		// Read the requested perspective id to be opened.
		String finalPerspId = configElement.getAttribute(FINAL_PERSPECTIVE);
		if(finalPerspId == null)
		{
			return;
		}

		// Map perspective id to descriptor.
		IPerspectiveRegistry reg = PlatformUI.getWorkbench().getPerspectiveRegistry();

		// leave this code in - the perspective of a given project may map to
		// activities other than those that the wizard itself maps to.
		IPerspectiveDescriptor finalPersp = reg.findPerspectiveWithId(finalPerspId);
		if(finalPersp != null && finalPersp instanceof IPluginContribution)
		{
			IPluginContribution contribution = (IPluginContribution)finalPersp;
			if(contribution.getPluginId() != null)
			{
				IWorkbenchActivitySupport workbenchActivitySupport = PlatformUI.getWorkbench().getActivitySupport();
				IActivityManager activityManager = workbenchActivitySupport.getActivityManager();
				IIdentifier identifier = activityManager.getIdentifier(WorkbenchActivityHelper
						.createUnifiedId(contribution));
				Set idActivities = identifier.getActivityIds();

				if(!idActivities.isEmpty())
				{
					Set enabledIds = new HashSet(activityManager.getEnabledActivityIds());

					if(enabledIds.addAll(idActivities))
					{
						workbenchActivitySupport.setEnabledActivityIds(enabledIds);
					}
				}
			}
		}
		else
		{
			IDEWorkbenchPlugin.log("Unable to find persective " //$NON-NLS-1$
					+ finalPerspId + " in NewDistroProjectWizard.updatePerspective"); //$NON-NLS-1$
			return;
		}

		// gather the preferred perspectives
		// always consider the final perspective (and those derived from it)
		// to be preferred
		ArrayList preferredPerspIds = new ArrayList();
		addPerspectiveAndDescendants(preferredPerspIds, finalPerspId);
		String preferred = configElement.getAttribute(PREFERRED_PERSPECTIVES);
		if(preferred != null)
		{
			StringTokenizer tok = new StringTokenizer(preferred, " \t\n\r\f,"); //$NON-NLS-1$
			while(tok.hasMoreTokens())
			{
				addPerspectiveAndDescendants(preferredPerspIds, tok.nextToken());
			}
		}

		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if(window != null)
		{
			IWorkbenchPage page = window.getActivePage();
			if(page != null)
			{
				IPerspectiveDescriptor currentPersp = page.getPerspective();

				// don't switch if the current perspective is a preferred
				// perspective
				if(currentPersp != null && preferredPerspIds.contains(currentPersp.getId()))
				{
					return;
				}
			}

			// prompt the user to switch
			if(!confirmPerspectiveSwitch(window, finalPersp))
			{
				return;
			}
		}

		int workbenchPerspectiveSetting = WorkbenchPlugin.getDefault().getPreferenceStore().getInt(
				IPreferenceConstants.OPEN_PERSP_MODE);

		// open perspective in new window setting
		if(workbenchPerspectiveSetting == IPreferenceConstants.OPM_NEW_WINDOW)
		{
			openInNewWindow(finalPersp);
			return;
		}

		// replace active perspective setting otherwise
		replaceCurrentPerspective(finalPersp);
	}

	/**
	 * Adds to the list all perspective IDs in the Workbench who's original ID matches the given ID.
	 * 
	 * @param perspectiveIds
	 *            the list of perspective IDs to supplement.
	 * @param id
	 *            the id to query.
	 * @since 3.0
	 */
	@SuppressWarnings("unchecked")
	private static void addPerspectiveAndDescendants(List perspectiveIds, String id)
	{
		IPerspectiveRegistry registry = PlatformUI.getWorkbench().getPerspectiveRegistry();
		IPerspectiveDescriptor[] perspectives = registry.getPerspectives();
		for(int i = 0; i < perspectives.length; i++)
		{
			// @issue illegal ref to workbench internal class;
			// consider adding getOriginalId() as API on IPerspectiveDescriptor
			PerspectiveDescriptor descriptor = ((PerspectiveDescriptor)perspectives[i]);
			if(descriptor.getOriginalId().equals(id))
			{
				perspectiveIds.add(descriptor.getId());
			}
		}
	}

	/**
	 * Prompts the user for whether to switch perspectives.
	 * 
	 * @param window
	 *            The workbench window in which to switch perspectives; must not be <code>null</code>
	 * @param finalPersp
	 *            The perspective to switch to; must not be <code>null</code>.
	 * 
	 * @return <code>true</code> if it's OK to switch, <code>false</code> otherwise
	 */
	private static boolean confirmPerspectiveSwitch(IWorkbenchWindow window, IPerspectiveDescriptor finalPersp)
	{
		IPreferenceStore store = IDEWorkbenchPlugin.getDefault().getPreferenceStore();
		String pspm = store.getString(IDEInternalPreferences.PROJECT_SWITCH_PERSP_MODE);
		if(!IDEInternalPreferences.PSPM_PROMPT.equals(pspm))
		{
			// Return whether or not we should always switch
			return IDEInternalPreferences.PSPM_ALWAYS.equals(pspm);
		}
		String desc = finalPersp.getDescription();
		String message;
		if(desc == null || desc.length() == 0)
			message = NLS.bind(ResourceMessages.NewProject_perspSwitchMessage, finalPersp.getLabel());
		else
			message = NLS.bind(ResourceMessages.NewProject_perspSwitchMessageWithDesc, new String[] {
					finalPersp.getLabel(), desc });

		MessageDialogWithToggle dialog = MessageDialogWithToggle.openYesNoQuestion(window.getShell(),
				ResourceMessages.NewProject_perspSwitchTitle, message,
				null /* use the default message for the toggle */, false /* toggle is initially unchecked */, store,
				IDEInternalPreferences.PROJECT_SWITCH_PERSP_MODE);
		int result = dialog.getReturnCode();

		// If we are not going to prompt anymore propagate the choice.
		if(dialog.getToggleState())
		{
			String preferenceValue;
			if(result == IDialogConstants.YES_ID)
			{
				// Doesn't matter if it is replace or new window
				// as we are going to use the open perspective setting
				preferenceValue = IWorkbenchPreferenceConstants.OPEN_PERSPECTIVE_REPLACE;
			}
			else
			{
				preferenceValue = IWorkbenchPreferenceConstants.NO_NEW_PERSPECTIVE;
			}

			// update PROJECT_OPEN_NEW_PERSPECTIVE to correspond
			PrefUtil.getAPIPreferenceStore().setValue(IDE.Preferences.PROJECT_OPEN_NEW_PERSPECTIVE, preferenceValue);
		}
		return result == IDialogConstants.YES_ID;
	}
	/**
	 * The worker method. It will find the container, create the file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 */
	private void doFinish(IContainer container, String fileName, IProgressMonitor monitor) throws CoreException
	{
		// create a sample file
		monitor.beginTask("Creating " + fileName, 2);
		final IFile file = container.getFile(new Path(fileName));
		try
		{
			InputStream stream = openContentStream(container.getName(), fileName);
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

//	/**
//	 * Create a file in the project without content. 
//	 * This default implementation returns empty content.
//	 */
//	protected InputStream openContentStream(String containerName, String fileName)
//	{
//		String contents = "";
//		return new ByteArrayInputStream(contents.getBytes());
//	}
	protected void throwCoreException(String message) throws CoreException
	{
		IStatus status = new Status(IStatus.ERROR, "org.eclipse.buckminster.bmview", IStatus.OK, message, null);
		throw new CoreException(status);
	}
	/**
	 * Initialize file contents with a sample text.
	 * @throws SAXException 
	 */
	protected InputStream openContentStream(String containerName, String fileName)
	{
		return DistroContentFactory.openContentStream(containerName, fileName);
	}

}
