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

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.p2.authoring.forms.IMasterDetailsController;
import org.eclipse.equinox.p2.authoring.forms.IPageMementoProvider;
import org.eclipse.equinox.p2.authoring.forms.TreeMasterDetailsBlock;

import org.eclipse.equinox.p2.authoring.internal.IEditEventBusProvider;
import org.eclipse.equinox.p2.authoring.internal.IEditorListener;
import org.eclipse.equinox.p2.authoring.internal.IUndoOperationSupport;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder;
import org.eclipse.equinox.p2.authoring.internal.ModelChangeEvent;
import org.eclipse.equinox.p2.authoring.internal.ModelPart;
import org.eclipse.equinox.p2.authoring.internal.P2AuthoringLabelProvider;
import org.eclipse.equinox.p2.authoring.internal.P2StyledLabelProvider;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.Parameter;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.TouchpointActionBuilder;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.TouchpointDataBuilder;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.TouchpointInstructionBuilder;
import org.eclipse.equinox.p2.authoring.spi.ITouchpointActionDescriptor;
import org.eclipse.equinox.p2.authoring.spi.ITouchpointActionParameterDescriptor;
import org.eclipse.equinox.p2.authoring.spi.ITouchpointTypeDescriptor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.ui.forms.AbstractFormPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IDetailsPageProvider;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * A MasterDetails block for TouchpointData that displays the Touchpoint instructions and actions in a tree and allows
 * for editing of elements in the tree.
 * 
 * @author Henrik Lindberg
 * 
 */
public class TouchpointBodyBlock extends TreeMasterDetailsBlock implements IDetailsPageProvider,
		IMasterDetailsController, IPageMementoProvider
{

	private static final String ACTION_PAGE = "action"; // $NON-NLS1$

	private static final String INSTRUCTION_PAGE = "instruction"; // $NON-NLS1$

	private static final String TOUCHPOINT_PAGE = "touchpoint"; // $NON-NLS1$

	private IDetailsPage m_actionPage;

	private IDetailsPage m_touchpointDataPage;

	private IDetailsPage m_instructionPage;

	private IBaseLabelProvider m_labelProvider;

	private ITreeContentProvider m_contentProvider;

	private MasterFormPart m_masterFormPart;

	public TouchpointBodyBlock(FormPage page, Object layoutData)
	{
		super(page, layoutData);
	}

	@Override
	protected void createMasterPart(final IManagedForm managedForm, Composite parent)
	{
		super.createMasterPart(managedForm, parent); // create the view
		m_masterFormPart = new MasterFormPart(); // create the manager of the view data lifecycle
		managedForm.addPart(m_masterFormPart); // and make it part of the overall lifecycle
	}

	@Override
	public String getName()
	{
		return "Instructions";
	}

	@Override
	public String getDescription()
	{
		return "Edit the actions for touchpoint instructions";
	}

	public void add()
	{
		// TODO Unused - uses specific methods in a menu to add things...
	}

	public void addAction()
	{
		// An instruciton or action must be selected to find the correct place to add
		// the action
		IStructuredSelection ssel = (IStructuredSelection)m_viewer.getSelection();
		Object element = ssel.getFirstElement();
		TouchpointInstructionBuilder tib = null;
		if(element != null)
		{
			if(element instanceof TouchpointInstructionBuilder)
				tib = (TouchpointInstructionBuilder)element;
			else if(element instanceof TouchpointActionBuilder)
				tib = (TouchpointInstructionBuilder)((TouchpointActionBuilder)element).getParent();
		}
		InstallableUnitEditor editor = ((InstallableUnitEditor)m_formPage.getEditor());
		final ITouchpointTypeDescriptor desc = editor.getTouchpointType(editor.getInstallableUnit().getTouchpointType());
		if(desc.isNull())
		{
			MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
					"Select Touchpoint Type first",
					"This unit has no touchpoint type. Please select the Touchpoint Type.");
			return;
		}
		if(desc.isUnknown())
		{
			MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
					"Unknown Touchpoint Type",
					"This unit has an unknown touchpoint type. Actions can not be added.");
			return;
		}
	
		if(tib == null)
		{
			MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
					"Select instruction first",
					"Please select the instruction where the action should be added and try again.");
			return;
		}
		// Let user select an action
		ListDialog listDialog = new ListDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		listDialog.setTitle("Select Action to Add");
		listDialog.setContentProvider(new IStructuredContentProvider(){

			public Object[] getElements(Object inputElement)
			{
				return desc.getActions();
			}

			public void dispose()
			{				
			}

			public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
			{
			}
			
		});
		listDialog.setLabelProvider(new P2StyledLabelProvider());
		listDialog.setInput(this); 
		listDialog.open();
		Object[] result = listDialog.getResult();
		// Add the actions to the instruction
		if(result == null || result.length < 1)
			return;
		ITouchpointActionDescriptor instr = (ITouchpointActionDescriptor)result[0];
		String actionKey = instr.getKey();
		ITouchpointActionParameterDescriptor[] p = instr.getParameters();
		List<Parameter> params = new ArrayList<Parameter>(p.length);
		for(int i = 0; i < p.length;i++)
			params.add(new Parameter(p[i].getKey(), p[i].getDefaultValue()));
		TouchpointActionBuilder action = new TouchpointActionBuilder(actionKey, params);
		// Execute the undoable add action
		addRemoveTouchpointInstruction(tib, action, true);
		
	}
	public void addRemoveTouchpointInstruction(TouchpointInstructionBuilder instruction, TouchpointActionBuilder action, boolean add)
	{
		FormToolkit toolkit = m_formPage.getManagedForm().getToolkit();
		if(toolkit instanceof IUndoOperationSupport)
		{
			AddRemoveActionOperation op = new AddRemoveActionOperation(instruction, action, add);
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
			if(add)
				instruction.addAction(action);
			else
				instruction.removeAction(action);
		}

	}
	public void addTouchpointData()
	{
		ITouchpointTypeDescriptor desc = P2AuthoringUIPlugin.getDefault().getTouchpointType(getIU().getTouchpointType());
		if(desc.isNull() || desc.isUnknown())
		{
			MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
					"Can not add instruction block",
					"Not possible to add an instruction block for missing or unknown touchpoint types. "+
					"Select a different touchpoint type.");
			return;

		}
		
		TouchpointDataBuilder data = new TouchpointDataBuilder();
		// give the new block a default name
		data.setName("Instruction block " + Integer.toString(getIU().getTouchpointData().length + 1));

//		TouchpointInstructionBuilder instruction = data.getInstruction("install");
//		List<Parameter> params = new ArrayList<Parameter>();
//		params.add(new Parameter("source", "some source value"));
//		params.add(new Parameter("target", "some target value"));
//		TouchpointActionBuilder action = new TouchpointActionBuilder("doSomething", params);
//		instruction.addAction(action);
		addRemoveTouchpointData(data, true);
	}

	/**
	 * Configures the add button to have a menu with different add types
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
				Object data = e.widget.getData();
				if("action".equals(data))
					addAction();
				if("touchpoint".equals(data))
					addTouchpointData();
			}

		};
		Menu addMenu = new Menu(b.getShell(), SWT.POP_UP);
		MenuItem mi = new MenuItem(addMenu, SWT.PUSH);
		mi.setText("Add Action...");
		mi.setData("action");
		mi.addSelectionListener(listener);

		mi = new MenuItem(addMenu, SWT.PUSH);
		mi.setText("Add Instruction Block");
		mi.setData("touchpoint");
		mi.addSelectionListener(listener);

		// attach menu to button (pops up on right mouse click)
		b.setMenu(addMenu);
		// attach listener to button so menu pops up on button click (left click)
		b.addSelectionListener(new SelectionListener()
		{

			public void widgetDefaultSelected(SelectionEvent e)
			{
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e)
			{
				b.getMenu().setVisible(true);
			}

		});
	}

	public void down()
	{
		move(1);
	}

	@Override
	public IDetailsPageProvider getDetailsPageProvider()
	{
		return this;
	}

	public IDetailsPage getActionPage()
	{
		if(m_actionPage == null)
			m_actionPage = new TouchpointActionPage();
		return m_actionPage;
	}

	public IDetailsPage getInstructionPage()
	{
		if(m_instructionPage == null)
			m_instructionPage = new TouchpointInstructionPage();
		return m_instructionPage;
	}

	public IDetailsPage getTouchpointDataPage()
	{
		if(m_touchpointDataPage == null)
			m_touchpointDataPage = new TouchpointDataPage();
		return m_touchpointDataPage;
	}

	@Override
	public IStructuredContentProvider getMasterContentProvider()
	{
		if(m_contentProvider == null)
			m_contentProvider = new ITreeContentProvider()
			{

				public Object[] getChildren(Object parentElement)
				{
					if(parentElement instanceof TouchpointDataBuilder)
						return ((TouchpointDataBuilder)parentElement).getInstructions().values().toArray();
					if(parentElement instanceof TouchpointInstructionBuilder)
						return ((TouchpointInstructionBuilder)parentElement).getActions();
					if(parentElement instanceof InstallableUnitBuilder)
						return ((InstallableUnitBuilder)parentElement).getTouchpointData();
					return null;
				}

				public Object getParent(Object element)
				{
					if(element instanceof ModelPart)
						return ((ModelPart)element).getParent();
					return null;
				}

				public boolean hasChildren(Object element)
				{
					return (element instanceof TouchpointDataBuilder || element instanceof TouchpointInstructionBuilder);
				}

				public Object[] getElements(Object inputElement)
				{
					// get the already parsed and handled editor input instead of using the input element
					// which refers to the input "file".
					inputElement = getIU();
					if(inputElement instanceof InstallableUnitBuilder)
						return ((InstallableUnitBuilder)inputElement).getTouchpointData();
					return null;
				}

				public void dispose()
				{
				}

				public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
				{
					// nothing is needed
				}

			};

		return m_contentProvider;
	}

	private InstallableUnitBuilder getIU()
	{
		return ((InstallableUnitEditor)m_formPage.getEditor()).getInstallableUnit();
	}

	/**
	 * Returns 'this' as a handler of add, remove, up, down,...
	 */
	@Override
	public IMasterDetailsController getMasterDetailsController()
	{
		return this;
	}

	@Override
	public IBaseLabelProvider getMasterLabelProvider()
	{
		if(m_labelProvider == null)
			m_labelProvider = new DelegatingStyledCellLabelProvider(new P2AuthoringLabelProvider());
		return m_labelProvider;
	}

	/**
	 * Returns a page for a page key returned by {@link #getPageKey(Object)}.
	 */
	public IDetailsPage getPage(Object key)
	{
		if(TOUCHPOINT_PAGE.equals(key))
			return getTouchpointDataPage();
		if(INSTRUCTION_PAGE.equals(key))
			return getInstructionPage();
		return getActionPage();
	}

	/**
	 * Selects a page key for the selected object. See {@link #getPage(Object)}.
	 */
	public Object getPageKey(Object object)
	{
		if(object instanceof TouchpointDataBuilder)
			return TOUCHPOINT_PAGE;
		if(object instanceof TouchpointInstructionBuilder)
			return INSTRUCTION_PAGE;
		if(object instanceof TouchpointActionBuilder)
			return ACTION_PAGE;
		return null;
	}

	public void remove()
	{
		IStructuredSelection ssel = (IStructuredSelection)m_viewer.getSelection();
		if(ssel == null)
			return;
		// TODO: support removal of more than one at a time
		Object selected = ssel.getFirstElement();
		if(selected instanceof TouchpointDataBuilder)
			addRemoveTouchpointData((TouchpointDataBuilder)selected, false);
		if(selected instanceof TouchpointActionBuilder)
		{
			TouchpointActionBuilder action = (TouchpointActionBuilder)selected;
			TouchpointInstructionBuilder instruction = (TouchpointInstructionBuilder)action.getParent();
			addRemoveTouchpointInstruction(instruction, action, false);
		}
	}

	public void up()
	{
		move(-1);
	}

	/**
	 * Common move operation - moves model part up or down in the list. Operation can be undone.
	 */
	private void move(int delta)
	{
		IStructuredSelection ssel = (IStructuredSelection)m_viewer.getSelection();
		if(ssel == null || ssel.size() != 1)
			return; // nothing to move (or too many)
		ModelPart selected = (ModelPart)ssel.getFirstElement();

		FormToolkit toolkit = m_formPage.getManagedForm().getToolkit();
		if(toolkit instanceof IUndoOperationSupport)
		{
			MoveOperation op = new MoveOperation(selected, delta);
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

	/**
	 * Method common to both add and remove TouchpointDataBuilder. Operation can be undone.
	 * 
	 * @param data
	 *            what to add or remove
	 * @param add
	 *            true if required should be added, false to remove
	 */
	private void addRemoveTouchpointData(TouchpointDataBuilder data, boolean add)
	{
		FormToolkit toolkit = m_formPage.getManagedForm().getToolkit();
		if(toolkit instanceof IUndoOperationSupport)
		{
			AddRemoveOperation op = new AddRemoveOperation(data, add);
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
			iu.addTouchpointData(data);
		}
	}

	/**
	 * Undoable operation for add/remove of TouchpointData
	 * 
	 * @author Henrik Lindberg
	 * 
	 */
	private class AddRemoveOperation extends AbstractOperation
	{
		private TouchpointDataBuilder m_data;

		private boolean m_add;

		private int m_index;

		public AddRemoveOperation(TouchpointDataBuilder data, boolean add)
		{
			super((add
					? "Add"
					: "Remove") + " Touchpoint Data");
			m_data = data;
			m_add = add;
		}

		private void updatePageState(boolean select)
		{
			m_masterFormPart.markStale();
			m_masterFormPart.markDirty();
			switchFocus(select
					? m_data
					: null); // switch focus if on another page
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
				m_index = iu.addTouchpointData(m_data);
			else
				m_index = iu.removeTouchpointData(m_data);
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
				iu.removeTouchpointData(m_data);
			else
				iu.addTouchpointData(m_data, m_index);
			updatePageState(!m_add);
			if(monitor != null)
				monitor.done();
			return Status.OK_STATUS;
		}

	}

	/**
	 * Undoable operation class for moving touchpoint data, and touchpoint action nodes.
	 * 
	 * @author Henrik Lindberg
	 * 
	 */
	private class MoveOperation extends AbstractOperation
	{
		private ModelPart m_moved;

		private int m_delta;

		public MoveOperation(ModelPart moved, int delta)
		{
			super(moved instanceof TouchpointDataBuilder
					? "Move Instruction Block"
					: "Move Action");
			m_moved = moved;
			m_delta = delta;
		}

		private void updatePageState()
		{
			m_masterFormPart.markStale();
			m_masterFormPart.markDirty();
			switchFocus(m_moved); // switch focus if on another page
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
			if(m_moved instanceof TouchpointDataBuilder)
				iu.moveTouchpointData((TouchpointDataBuilder)m_moved, delta);
			else
				((TouchpointInstructionBuilder)m_moved.getParent()).moveAction((TouchpointActionBuilder)m_moved, delta);
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
	/**
	 * Undoable operation for add/remove of TouchpointData
	 * 
	 * @author Henrik Lindberg
	 * 
	 */
	private class AddRemoveActionOperation extends AbstractOperation
	{
		private TouchpointInstructionBuilder m_instruction;
		private TouchpointActionBuilder m_action;
		
		private boolean m_add;

		private int m_index;

		public AddRemoveActionOperation(TouchpointInstructionBuilder instruction, TouchpointActionBuilder action, boolean add)
		{
			super((add
					? "Add"
					: "Remove") + " Touchpoint Action");
			m_instruction = instruction;
			m_action = action;
			m_add = add;
		}

		private void updatePageState(boolean select)
		{
			m_masterFormPart.markStale();
			m_masterFormPart.markDirty();
			switchFocus(select
					? m_action
					: null); // switch focus if on another page
		}

		@Override
		public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			return redo(monitor, info);
		}

		@Override
		public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			if(m_add)
				m_index = m_instruction.addAction(m_action);
			else
				m_index = m_instruction.removeAction(m_action);
			updatePageState(m_add);
			if(monitor != null)
				monitor.done();
			return Status.OK_STATUS;
		}

		@Override
		public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			if(m_add)
				m_instruction.removeAction(m_action);
			else
				m_instruction.addAction(m_action, m_index);
			updatePageState(!m_add);
			if(monitor != null)
				monitor.done();
			return Status.OK_STATUS;
		}

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
				((IEditEventBusProvider)form.getToolkit()).getEventBus().addListener(new IEditorListener()
				{

					public void notify(EventObject o)
					{
						if(!(o instanceof ModelChangeEvent))
							return;
						Object source = ((ModelChangeEvent)o).getDetail();
						if(source instanceof TouchpointDataBuilder || source instanceof TouchpointInstructionBuilder
								|| source instanceof TouchpointActionBuilder)
							TouchpointBodyBlock.this.m_viewer.refresh(source, true);
						else if(source instanceof Parameter)
							TouchpointBodyBlock.this.m_viewer.refresh(((ModelPart)source).getParent(), true);
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
			TouchpointBodyBlock.this.m_viewer.refresh();
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
	private void switchFocus(ModelPart select)
	{
		FormEditor editor = m_formPage.getEditor();
		IFormPage currentPage = editor.getActivePageInstance();
		if(!m_formPage.getId().equals(currentPage.getId()))
			editor.setActivePage(m_formPage.getId());
		if(select != null)
			m_viewer.setSelection(new StructuredSelection(select), true);
	}

	/**
	 * Overrides default handling of enablement of up/down/remove buttons since it is not possible to remove or move an
	 * instruction node.
	 */
	@Override
	protected void setStandardButtonEnablement(StandardButtons buttons, IStructuredSelection selection)
	{
		if(selection != null && selection.size() > 0)
		{
			Object selected = selection.getFirstElement();
			if(selected instanceof TouchpointInstructionBuilder)
			{
				buttons.up.setEnabled(false);
				buttons.down.setEnabled(false);
				buttons.remove.setEnabled(false);
				return;
			}
		}
		super.setStandardButtonEnablement(buttons, selection);
	}

}
