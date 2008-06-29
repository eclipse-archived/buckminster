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

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.p2.authoring.forms.IPageMementoProvider;
import org.eclipse.equinox.p2.authoring.forms.RichFormPage;
import org.eclipse.equinox.p2.authoring.internal.IUndoOperationSupport;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder;
import org.eclipse.equinox.p2.authoring.internal.P2StyledLabelProvider;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.TouchpointTypeBuilder;
import org.eclipse.equinox.p2.authoring.spi.ITouchpointTypeDescriptor;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.AbstractFormPart;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

/**
 * A Page for editing touchpoint type and touchpoint data for a p2 IU.
 * @author Henrik Lindberg
 * 
 */
public class TouchpointPage extends RichFormPage implements IPageMementoProvider
{
	public static final String PAGE_ID = "touchpoint.id";
	private final TouchpointBodyBlock m_touchpointBodyBlock;
	private ComboViewer m_touchpointTypeViewer;
	private org.eclipse.equinox.p2.authoring.TouchpointPage.MasterFormPart m_masterFormPart;
	private boolean m_loopLock;
	
	public TouchpointPage(FormEditor editor)
	{
		super(editor, PAGE_ID, "Touchpoint");
		m_header = "Touchpoint";
		m_numColumns = 1;
		TableWrapData wrapData = new TableWrapData(TableWrapData.FILL_GRAB,TableWrapData.FILL_GRAB);
		wrapData.indent = 0;

		m_touchpointBodyBlock = new TouchpointBodyBlock(this, wrapData);
		m_masterFormPart = new MasterFormPart(); // create the manager of the touchpoint type lifecycle
	}

	@Override
	protected void addFormContent(IManagedForm managedForm)
	{
		final ScrolledForm scrolledForm = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		
		Section section = createGeneralSection(toolkit, scrolledForm.getBody());
		section.addExpansionListener(getReflowListener());
		
		m_touchpointBodyBlock.createContent(managedForm);
		getManagedForm().addPart(m_masterFormPart); // and make it part of the overall lifecycle				
	}
	/**
	 * Creates a general section above the master detail to allow setting the touchpoint type from 
	 * a selection of available touchpoint type/versions.
	 * 
	 * @param toolkit
	 * @param parent
	 * @return
	 */
	private Section createGeneralSection(FormToolkit toolkit, Composite parent)
	{
		Section section = toolkit.createSection(parent, //
				Section.DESCRIPTION | //
				Section.TITLE_BAR | //
				Section.TWISTIE | // Expandable by user
				Section.EXPANDED);

		TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP);
		td.colspan = 1;
		section.setLayoutData(td);
		section.setText("Touchpoint Type");
		section.setDescription("The Touchpoint Type determines the type of installation.");
		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout(2, false);
		sectionClient.setLayout(layout);
		
		FormColors colors = toolkit.getColors();
		Label label = toolkit.createLabel(sectionClient, "Type:");
		label.setForeground(colors.getColor("org.eclipse.ui.forms.TITLE"));
		
		final Combo ttype = new Combo(sectionClient, SWT.READ_ONLY);
		ttype.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true,false));
		m_touchpointTypeViewer = new ComboViewer(ttype);
		m_touchpointTypeViewer.setLabelProvider(new P2StyledLabelProvider());
		m_touchpointTypeViewer.setContentProvider(new IStructuredContentProvider(){

			public Object[] getElements(Object inputElement)
			{
				return P2AuthoringUIPlugin.getDefault().getTouchpointTypes();
			}

			public void dispose()
			{
			}

			public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
			{
				m_touchpointTypeViewer.refresh();
			}
			
		});
		m_touchpointTypeViewer.setInput(this); // input irrelevant - the view shows static data
		m_touchpointTypeViewer.addSelectionChangedListener(new ISelectionChangedListener(){

			public void selectionChanged(SelectionChangedEvent event)
			{
				if(m_loopLock)
					return;
				int index = ttype.getSelectionIndex();
				ITouchpointTypeDescriptor desc = P2AuthoringUIPlugin.getDefault().getTouchpointTypes()[index];
				TouchpointTypeBuilder ttb = new TouchpointTypeBuilder(desc.getTypeId(), desc.getVersionString());
				setTouchpointType(ttb);
			}
			
		});
		toolkit.adapt(ttype, true, true);

		section.setClient(sectionClient);
		return section;
	}
	public Object getPageMemento()
	{
		return m_touchpointBodyBlock.getPageMemento();
	}

	public void setPageMemento(Object memento)
	{
		m_touchpointBodyBlock.setPageMemento(memento);
	}
	/**
	 * Common move operation - moved required capability up or down in the list. Operation can be undone.
	 */
	private void setTouchpointType(TouchpointTypeBuilder touchpointType)
	{
		FormToolkit toolkit = getManagedForm().getToolkit();
		if(toolkit instanceof IUndoOperationSupport)
		{
			SetTouchpointTypeOperation op = new SetTouchpointTypeOperation(touchpointType);
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
	
	private class MasterFormPart extends AbstractFormPart
	{
// Possibly listen to IU, and change selection if touchpoint type changed in the model,
// But not needed yet since only change comes from the UI.
//		@Override
//		public void initialize(IManagedForm form)
//		{
//			super.initialize(form);
//			// register a listener to Required Capability change events
//			if(form.getToolkit() instanceof IEditEventBusProvider)
//			{
//				((IEditEventBusProvider)form.getToolkit()).getEventBus().addListener(new IEditorListener()
//				{
//
//					public void notify(EventObject o)
//					{
//						if(!(o instanceof ChangeEvent))
//							return;
//						Object source = o.getSource();
//						if(source instanceof TouchpointDataBuilder 
//								|| source instanceof TouchpointInstructionBuilder
//								|| source instanceof TouchpointActionBuilder)
//							m_touchpointTypeViewer.refresh(o.getSource(), true);
//					}
//
//				});
//			}
//		}
		/**
		 * Refreshes the viewer with stale model changes
		 */
		@Override
		public void refresh()
		{
			TouchpointTypeBuilder type = ((InstallableUnitEditor)getEditor()).getInstallableUnit().getTouchpointType();
			ITouchpointTypeDescriptor desc = P2AuthoringUIPlugin.getDefault().getTouchpointType(type);
			try {
				m_loopLock = true;
				m_touchpointTypeViewer.setSelection(new StructuredSelection(new Object[]{desc}), true);
			}
			finally
			{
				m_loopLock = false;
			}
			super.refresh();
		}

	}
	/**
	 * Switches focus in the editor to this page, and sets focus to the touchpoint type viewer's control.
	 */
	private void switchFocus(TouchpointTypeBuilder type)
	{
		FormEditor editor = getEditor();
		IFormPage currentPage = editor.getActivePageInstance();
		if(!getId().equals(currentPage.getId()))
			editor.setActivePage(getId());
		ITouchpointTypeDescriptor desc = P2AuthoringUIPlugin.getDefault().getTouchpointType(type);
		m_touchpointTypeViewer.getControl().setFocus();
		try {
			m_loopLock = true;
			m_touchpointTypeViewer.setSelection(new StructuredSelection(new Object[]{desc}), true);
		}
		finally
		{
			m_loopLock = false;
		}
	}
	
	/**
	 * Undoable operation class for Changing TouchpointType.
	 * @author Henrik Lindberg
	 *
	 */
	private class SetTouchpointTypeOperation extends AbstractOperation
	{
		private TouchpointTypeBuilder m_old;
		private TouchpointTypeBuilder m_new;
		
		public SetTouchpointTypeOperation(TouchpointTypeBuilder theNew)
		{
			super("Touchpoint Type Change"); 
			m_new = theNew;
			m_old = null;
		}
		private void updatePageState(TouchpointTypeBuilder type)
		{
			m_masterFormPart.markStale();
			m_masterFormPart.markDirty();
			switchFocus(type); // switch focus if on another page
		}
		@Override
		public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			InstallableUnitBuilder iu = getIU();
			m_old = iu.getTouchpointType();
			return redo(monitor, info);
		}
		private InstallableUnitBuilder getIU()
		{
			return ((InstallableUnitEditor)getEditor()).getInstallableUnit();
		}
		@Override
		public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			return xxdo(m_new, monitor, info);
		}
		
		private IStatus xxdo(TouchpointTypeBuilder t, IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			InstallableUnitBuilder iu = getIU();
			iu.setTouchpointType(t);
			updatePageState(t);
			if(monitor != null)
				monitor.done();
			return Status.OK_STATUS;
		}

		@Override
		public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			return xxdo(m_old, monitor, info);
		}
		
	}

}
