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

package org.eclipse.equinox.p2.authoring;

import java.util.EventObject;

import javax.swing.event.ChangeEvent;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.p2.authoring.forms.IMasterDetailsController;
import org.eclipse.equinox.p2.authoring.forms.IPageMementoProvider;
import org.eclipse.equinox.p2.authoring.forms.TableMasterDetailsBlock;
import org.eclipse.equinox.p2.authoring.internal.IEditEventBusProvider;
import org.eclipse.equinox.p2.authoring.internal.IEditorListener;
import org.eclipse.equinox.p2.authoring.internal.IUndoOperationSupport;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder;
import org.eclipse.equinox.p2.authoring.internal.P2AuthoringLabelProvider;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.RequiredCapabilityBuilder;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.AbstractFormPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IDetailsPageProvider;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * A MasterDetails block for IU Required Capabilities that display's the capabilities in a list and allows for editing
 * of elements in the tree. Supports undo of operations.
 * 
 * @author Henrik Lindberg
 * 
 */
public class RequiredBodyBlock extends TableMasterDetailsBlock implements IDetailsPageProvider,
		IMasterDetailsController, IPageMementoProvider
{

	private IDetailsPage m_requiredPage;

	private IBaseLabelProvider m_labelProvider;

	private IStructuredContentProvider m_contentProvider;
	
	private MasterFormPart m_masterFormPart;

	public RequiredBodyBlock(FormPage page, Object layoutData)
	{
		super(page, layoutData, 350);
	}
	@Override
	protected void createMasterPart(final IManagedForm managedForm, Composite parent)
	{
		super.createMasterPart(managedForm, parent); // create the view
		m_masterFormPart = new MasterFormPart();	// create the manager of the view data lifecycle
		managedForm.addPart(m_masterFormPart);		// and make it part of the overall lifecycle
	}
	@Override
	public String getName()
	{
		return "Required Capabilities";
	}

	@Override
	public String getDescription()
	{
		return "Edit the capabilities required by this installable unit.";
	}
	/**
	 * Adds a default required capability for editing. Operation can be undone.
	 */
	public void add()
	{
		RequiredCapabilityBuilder required = new RequiredCapabilityBuilder( //
				"", 					//$NON-NLS-1$
				"some.name", 			//
				"org.equinox.p2.iu", 	// $NON-NLS-1$
				"",  					// $NON-NLS-1$
				false, false, false);

		addRemove(required, true);
	}
	
	/**
	 * Method common to both add and remove. Operation can be undone.
	 * @param required what to add or remove
	 * @param add true if required should be added, false to remove
	 */
	private void addRemove(RequiredCapabilityBuilder required, boolean add)
	{
		FormToolkit toolkit = m_formPage.getManagedForm().getToolkit();
		if(toolkit instanceof IUndoOperationSupport)
		{
			AddRemoveOperation op = new AddRemoveOperation(required, add);
			op.addContext(((IUndoOperationSupport)toolkit).getUndoContext());
			try
			{
				((IUndoOperationSupport)toolkit).getOperationHistory().execute(op, null, null);
			}
			catch(ExecutionException e)
			{
				// TODO Proper logging
				e.printStackTrace();
			}
		}
		else
		{
			// without undo support - just add it... (should not happen)
			InstallableUnitBuilder iu = ((InstallableUnitEditor)m_formPage.getEditor()).getInstallableUnit();
			iu.addRequiredCapability(required);
		}
	}

	/**
	 * Configures the add button to have a menu with different add types
	 * TODO: clean up popup menu code if it will not be used
	 */
	@Override
	protected void configureAddButton(final Button b)
	{
		// Create a listener for menu items so that the correct
		// type of add operation is performed.
		//
		SelectionListener listener = new SelectionListener()
		{

			public void widgetDefaultSelected(SelectionEvent e)
			{
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e)
			{
				add();
			}

		};
		b.addSelectionListener(listener);
	}
	/**
	 * Moves selected required capability down in the list. Operation can be undone.
	 */
	public void down()
	{
		move(1);
	}
	/**
	 * Moves selected required capability up in the list. Operation can be undone.
	 */
	public void up()
	{
		move(-1);
	}
	
	/**
	 * Common move operation - moved required capability up or down in the list. Operation can be undone.
	 */
	private void move(int delta)
	{
		IStructuredSelection ssel = (IStructuredSelection)m_viewer.getSelection();
		if(ssel == null || ssel.size() != 1)
			return; // nothing to move (or too many)
		RequiredCapabilityBuilder required = (RequiredCapabilityBuilder)ssel.getFirstElement();

		FormToolkit toolkit = m_formPage.getManagedForm().getToolkit();
		if(toolkit instanceof IUndoOperationSupport)
		{
			MoveOperation op = new MoveOperation(required, delta);
			op.addContext(((IUndoOperationSupport)toolkit).getUndoContext());
			try
			{
				((IUndoOperationSupport)toolkit).getOperationHistory().execute(op, null, null);
			}
			catch(ExecutionException e)
			{
				// TODO Proper logging
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public IDetailsPageProvider getDetailsPageProvider()
	{
		return this;
	}

	public IDetailsPage getRequiredPage()
	{
		// TODO: no need to cache?  Master detail editor creates this on demand and keeps it,
		// but then the undo history may refer to deleted (and recreated elements).
		//
		if(m_requiredPage == null)
			m_requiredPage = new RequiredCapabilityPage();
		return m_requiredPage;
	}

	/**
	 * Returns a content provider for required capabilities from the editors installable unit.
	 */
	@Override
	public IStructuredContentProvider getMasterContentProvider()
	{
		if(m_contentProvider == null)
			m_contentProvider = new IStructuredContentProvider()
			{
				public Object[] getElements(Object inputElement)
				{
					InstallableUnitBuilder iu = ((InstallableUnitEditor)m_formPage.getEditor()).getInstallableUnit();
					return iu.getRequiredCapabilities();
				}

				public void dispose()
				{
				}

				public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
				{
				}

			};
		return m_contentProvider;
	}

	/**
	 * Returns 'this' as a handler of add, remove, up, down,...
	 */
	@Override
	public IMasterDetailsController getMasterDetailsController()
	{
		return this;
	}

	/**
	 * Returns a styled label provider using the {@link P2AuthoringLabelProvider} as the base label provider.
	 */
	@Override
	public IBaseLabelProvider getMasterLabelProvider()
	{
		if(m_labelProvider == null)
			m_labelProvider = new DelegatingStyledCellLabelProvider(new P2AuthoringLabelProvider());
		return m_labelProvider;
	}

	/**
	 * Returns a page for a page key returned by {@link #getPageKey(Object)}. Currently, there is only one type of
	 * details page.
	 */
	public IDetailsPage getPage(Object key)
	{
		return getRequiredPage();
	}

	/**
	 * Selects a page key for the selected object. See {@link #getPage(Object)}. Currently, there is only one type
	 * of details page key - "required".
	 */
	public Object getPageKey(Object object)
	{
		return "required";
	}

	/**
	 * Removes the selected required capability. The operation can be undone.
	 */
	public void remove()
	{
		// remove everything selected
		IStructuredSelection ssel = (IStructuredSelection)m_viewer.getSelection();
		if(ssel == null || ssel.getFirstElement() == null)
			return; // nothing to remove
		addRemove((RequiredCapabilityBuilder)ssel.getFirstElement(), false);
	}

	
	private class MasterFormPart extends AbstractFormPart
	{

		@Override
		public void initialize(IManagedForm form)
		{
			super.initialize(form);
			// register a listener to Required Capability change events
			if(form.getToolkit() instanceof IEditEventBusProvider)
			{
				((IEditEventBusProvider)form.getToolkit()).getEventBus().addListener(new IEditorListener(){

					public void notify(EventObject o)
					{
						if(o instanceof ChangeEvent && o.getSource() instanceof RequiredCapabilityBuilder)
							RequiredBodyBlock.this.m_viewer.refresh(o.getSource(), true);
					}
					
				});
			}
		}

		/**
		 * Refreshes the viewer with stale model changes
		 */
		@Override
		public void refresh()
		{
			RequiredBodyBlock.this.m_viewer.refresh();
			super.refresh();
		}
		
	}
	/**
	 * Returns a memento that restores this page selection.
	 */
	public Object getPageMemento()
	{
		return ((IStructuredSelection)m_viewer.getSelection()).getFirstElement();
	}
	/**
	 * Restores this page selection from the memento.
	 */
	public void setPageMemento(Object memento)
	{
		if(memento != null)
			m_viewer.setSelection(new StructuredSelection(memento), true);
	}
	/**
	 * Switches focus in the editor to the page where this required body block is.
	 */
	private void switchFocus(RequiredCapabilityBuilder select)
	{
		FormEditor editor = m_formPage.getEditor();
		IFormPage currentPage = editor.getActivePageInstance();
		if(!m_formPage.getId().equals(currentPage.getId()))
			editor.setActivePage(m_formPage.getId());
		if(select != null)
			m_viewer.setSelection(new StructuredSelection(select), true);
	}

	/**
	 * Undoable operation for add/remove of required capability.
	 * @author Henrik Lindberg
	 *
	 */
	private class AddRemoveOperation extends AbstractOperation
	{
		private RequiredCapabilityBuilder m_required;
		private boolean  m_add;
		private int m_index;
		
		public AddRemoveOperation(RequiredCapabilityBuilder required, boolean add)
		{
			super((add ? "Add" : "Remove") + " Required Capability"); 
			m_required = required;
			m_add = add;
		}
		private void updatePageState(boolean select)
		{
			m_masterFormPart.markStale();
			m_masterFormPart.markDirty();
			switchFocus(select ? m_required : null); // switch focus if on another page
		}
		@Override
		public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			return redo(monitor, info);
		}

		@Override
		public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			InstallableUnitBuilder iu = ((InstallableUnitEditor)m_formPage.getEditor()).getInstallableUnit();
			if(m_add)
				m_index = iu.addRequiredCapability(m_required);
			else
				m_index = iu.removeRequiredCapability(m_required);
			updatePageState(m_add);
			if(monitor != null)
				monitor.done();
			return Status.OK_STATUS;
		}

		@Override
		public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			InstallableUnitBuilder iu = ((InstallableUnitEditor)m_formPage.getEditor()).getInstallableUnit();
			if(m_add)
				iu.removeRequiredCapability(m_required);
			else
				iu.addRequiredCapability(m_required, m_index);
			updatePageState(!m_add);
			if(monitor != null)
				monitor.done();
			return Status.OK_STATUS;
		}
		
	}
	/**
	 * Undoable operation class for moving required capability.
	 * @author Henrik Lindberg
	 *
	 */
	private class MoveOperation extends AbstractOperation
	{
		private RequiredCapabilityBuilder m_required;
		private int  m_delta;
		
		public MoveOperation(RequiredCapabilityBuilder required, int delta)
		{
			super("Move Required Capability"); 
			m_required = required;
			m_delta = delta;
		}
		private void updatePageState()
		{
			m_masterFormPart.markStale();
			m_masterFormPart.markDirty();
			switchFocus(m_required); // switch focus if on another page
		}
		@Override
		public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			return redo(monitor, info);
		}

		@Override
		public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			return xxdo(monitor, info, m_delta);
		}
		public IStatus xxdo(IProgressMonitor monitor, IAdaptable info, int delta) throws ExecutionException
		{
			InstallableUnitBuilder iu = ((InstallableUnitEditor)m_formPage.getEditor()).getInstallableUnit();
			iu.moveRequiredCapability(m_required, delta);
			updatePageState();
			if(monitor != null)
				monitor.done();
			return Status.OK_STATUS;
		}

		@Override
		public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			return xxdo(monitor, info, -m_delta);		
		}
		
	}

}
