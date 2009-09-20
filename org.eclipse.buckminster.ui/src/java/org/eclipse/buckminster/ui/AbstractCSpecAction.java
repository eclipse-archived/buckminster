/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.metadata.MetadataSynchronizer;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.runtime.AttachableProgressMonitor;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;

/**
 * Operate on the selected object adapted to CSpec.class.
 * 
 * @author Thomas Hallgren
 * @author Henrik Lindberg
 */
public abstract class AbstractCSpecAction implements IObjectActionDelegate
{
	protected IWorkbenchPart m_activePart;

	private CSpec m_selectedComponent;

	private IAdaptable m_selection;

	/**
	 * tries to obtain a CSpec from the current selection. This operation is potentially long running if
	 * {@link WorkspaceInfo} has not been initialized yet.
	 * 
	 * @param progressMonitor
	 * @return the cspec for the given component or <code>null</code> if not available
	 * @see WorkspaceInfo#isFullyInitialized()
	 */
	public CSpec fetchCSpec(final IProgressMonitor progressMonitor)
	{
		if(m_selectedComponent != null)
		{
			return m_selectedComponent;
		}
		else if(m_selection != null)
		{
			if(WorkspaceInfo.isFullyInitialized() || Job.getJobManager().isSuspended())
			{
				// Query the cspec directly if the WorkspaceInfo is already initialized or fall back to
				// blocking mode if the job manager is currently suspended.
				m_selectedComponent = (CSpec)m_selection.getAdapter(CSpec.class);
				progressMonitor.done();
			}
			else
			{
				// if WorkspaceInfo has not been initialized and the JobManager is not suspended we try to attach the
				// given progress monitor to the CatchUpWorkspaceJob
				IJobChangeListener listener = new JobListener(progressMonitor);
				Job.getJobManager().addJobChangeListener(listener);
				m_selectedComponent = (CSpec)m_selection.getAdapter(CSpec.class);
				Job.getJobManager().removeJobChangeListener(listener);
			}

		}
		return m_selectedComponent;
	}

	public void run(IAction action)
	{
		if(m_activePart == null)
			return;

		IWorkbenchPartSite site = m_activePart.getSite();
		final Shell shell = site.getShell();
		if(m_selectedComponent == null)
		{
			run(shell);
		}
		else
		{
			run(m_selectedComponent, shell);
		}

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
		// If the selected object is a CSpec, or adaptable to CSpec, use it.
		if(first instanceof IAdaptable)
		{
			// if the WorkspaceInfo is already initialized, it's safe to query directly for the adapter
			if(WorkspaceInfo.isFullyInitialized())
			{
				m_selectedComponent = (CSpec)((IAdaptable)first).getAdapter(CSpec.class);
				action.setEnabled(m_selectedComponent != null);
			}
			else
			{
				action.setEnabled(true);
			}

			m_selection = (IAdaptable)first;
		}

	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart)
	{
		m_activePart = targetPart;
	}

	protected abstract void run(CSpec cspec, Shell shell);

	protected void run(Shell shell)
	{
		MessageDialog.openInformation(shell, null, Messages.no_component_is_selected);

	}

	protected void setSelectedComponent(CSpec cspec)
	{
		m_selectedComponent = cspec;
	}
}

/**
 * Monitors the jobs that are changed to running and scheduled state until it finds the WorkspaceCatchUpJob. Once this
 * job is found, it is asked for an {@link AttachableProgressMonitor}. The given {@link IProgressMonitor} will be
 * attached to the # {@link AttachableProgressMonitor} and the listener removes itself from the {@link IJobManager}
 * 
 * 
 * @see AttachableProgressMonitor
 * @see IJobManager#removeJobChangeListener(IJobChangeListener)
 * @author Johannes Utzig
 * 
 */
class JobListener extends JobChangeAdapter
{

	private IProgressMonitor m_monitorToAttach;

	private static final QualifiedName QN_ATTACHABLE_PROGRESS_MONITOR = new QualifiedName(CorePlugin.getID(),
			"attachableProgressMonitor"); //$NON-NLS-1$

	public JobListener(IProgressMonitor monitorToAttach)
	{
		super();
		this.m_monitorToAttach = monitorToAttach;
	}

	@Override
	public void running(IJobChangeEvent event)
	{
		handleEvent(event);
	}

	@Override
	public void scheduled(IJobChangeEvent event)
	{
		handleEvent(event);
	}

	private void handleEvent(IJobChangeEvent event)
	{
		if(event.getJob().belongsTo(MetadataSynchronizer.class))
		{
			Job job = event.getJob();

			Object o = job.getProperty(QN_ATTACHABLE_PROGRESS_MONITOR);
			if(o instanceof AttachableProgressMonitor)
			{
				// that we got here means that we found the CatchUpWorkspaceJob so the listener can deregister itself
				// now
				AttachableProgressMonitor monitor = (AttachableProgressMonitor)o;
				Job.getJobManager().removeJobChangeListener(this);
				monitor.attachProgressMonitor(m_monitorToAttach);

			}
		}
	}

}
