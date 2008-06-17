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

package org.eclipse.equinox.p2.authoring.forms;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.p2.authoring.forms.validators.IEditValidator;
import org.eclipse.equinox.p2.authoring.forms.validators.NullValidator;
import org.eclipse.equinox.p2.authoring.internal.IUndoOperationSupport;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * An EditAdapter is used to hook a model object property via an {@link IMutator}, and an input validator in the form of
 * an {@link IEditValidator}. The EditAdapter needs access to an instance of {@link EditAdapterFormPart} to enable
 * setting and clearing of validation messages and handling of dirty state.
 * 
 * An instance of EditAdapter is associated with a Control via the control's application data set with
 * {@link Control#setData(String, Object)} using the String "editAdapter" as the key. This enables widget oriented code
 * access to the EditAdapter.
 * 
 * The EditAdapter supports {@link Text} and {@link Button} (check/radio) controls.
 * 
 * @author Henrik Lindberg
 * 
 */
public class EditAdapter implements ModifyListener, SelectionListener, VerifyListener
{
	private boolean m_dirty;

	private boolean m_enabled;

	private boolean m_valid;

	private int m_refreshLock;
	
	private String m_currentStringValue;
	
	private String m_undoLabel;

	final private IEditValidator m_validator;

	final private Control m_control;

	final EditAdapterFormPart m_formPart;

	final IMutator m_mutator;

	/**
	 * Create an edit adapter with a null validator (input is always valid), and a null mutator.
	 * 
	 * @param control
	 * @param formPart
	 */
	public EditAdapter(Control control, EditAdapterFormPart formPart)
	{
		this(control, formPart, NullValidator.instance(), new Mutator()
		{
			@Override
			public void setValue(String input) throws CoreException
			{
				// do nothing
			}

			@Override
			public String getValue()
			{
				return ""; //$NON-NLS-1$
			}
		});
	}

	/**
	 * Creates and adds the adapter to the EditAdapterFormPart.
	 * 
	 * @param key
	 *            the key used in the EditAdapterFormPart (for lookup and removal).
	 * @param control
	 *            the control being adapted
	 * @param formPart
	 *            the EditAdapterFormPart to add this adapter too
	 * @param validator
	 *            a value validator
	 * @param mutator
	 *            a mutator that updates the control and the model
	 */
	public EditAdapter(Control control, EditAdapterFormPart formPart, //
			IEditValidator validator, IMutator mutator)
	{
		m_dirty = false;
		m_enabled = true;
		m_valid = true;
		m_validator = validator;
		m_control = control;
		m_formPart = formPart;
		m_mutator = mutator;
		m_refreshLock = 0;
		m_undoLabel = "edit";

		m_control.setData("editAdapter", this);
		if(m_control instanceof Text)
		{
			((Text)m_control).addModifyListener(this);
//			((Text)m_control).addVerifyListener(this);
		}
		else if(m_control instanceof Button)
		{
			((Button)m_control).addSelectionListener(this);
		}
		else if(m_control instanceof StyledText)
		{
			((StyledText)m_control).addModifyListener(this);
		}
	}

	public String getUndoLabel()
	{
		return m_undoLabel;
	}

	public void setUndoLabel(String undoLabel)
	{
		m_undoLabel = undoLabel;
	}

	/**
	 * Tells the form part that dirty state changed.
	 */
	public void modifyText(ModifyEvent e)
	{
		// validate and set messages
		String input;
		if(m_control instanceof Text)
			input = ((Text)m_control).getText();
		else if(m_control instanceof StyledText)
			input = ((StyledText)m_control).getText();
		else
			throw new IllegalArgumentException("EditAdapter not attached to Text or StyledText");

		// If the validator filters out characters, do that before input is validated
		// by setting the filtered text in the control.
		//
		String filtered = m_validator.inputFilter(input);
		if(filtered != null)
		{
			((Text)m_control).setText(filtered);
			input = filtered;
		}
		m_valid = validate(input);
		if(m_refreshLock == 0)
		{
			FormToolkit toolkit = m_formPart.getManagedForm().getToolkit();
			if(toolkit instanceof IUndoOperationSupport)
			{
				Object container = m_formPart.getManagedForm().getContainer();
				Object memento = container instanceof IPageMementoProvider ? ((IPageMementoProvider)container).getPageMemento() : null;

				EditStringAdapterOperation op = new EditStringAdapterOperation(this,memento, m_currentStringValue, input);
				op.addContext(((IUndoOperationSupport)toolkit).getUndoContext());
				((IUndoOperationSupport)toolkit).getOperationHistory().add(op);
			}
			m_currentStringValue = input;
			setDirty(true);			
		}

	}

	/**
	 * Commit the data in the widget to the model.
	 */
	public void commit(boolean onSave)
	{
		if(m_control instanceof Text || m_control instanceof StyledText)
			commitText(onSave);
		else if(m_control instanceof Button)
			commitButton(onSave);
	}

	private void commitText(boolean onSave)
	{
		// validate and set messages
		String input;
		if(m_control instanceof Text)
			input = ((Text)m_control).getText();
		else if(m_control instanceof StyledText)
			input = ((StyledText)m_control).getText();
		else
			throw new IllegalArgumentException("EditAdapter not attached to Text or StyledText");

		// If the validator filters out characters, do that before input is validated
		// by setting the filtered text in the control.
		//
		String filtered = m_validator.inputFilter(input);
		if(filtered != null)
		{
			((Text)m_control).setText(filtered);
			input = filtered;
		}
		m_valid = validate(input);

		// update the model
		try
		{
			m_mutator.setValue(input);
			if(onSave)
				setDirty(false); // model is updated
		}
		catch(Exception e1)
		{

			// if the input was validated false by the validator - use the message
			// set by the validator, else use the message from the exception (this because
			// the validator should catch all errors, or there is something really wrong, so we
			// also print a stack trace.
			//
			if(m_valid)
				setErrorMessage(e1.getMessage());
			m_valid = false;
			e1.printStackTrace();
			// mark as dirty as we could not set the value
			setDirty(true);
		}

	}

	private void commitButton(boolean onSave)
	{
		// validate and set messages
		boolean input = ((Button)m_control).getSelection();

		// update the model
		try
		{
			m_mutator.setValue(input);
			if(onSave)
				setDirty(false); // model is updated
		}
		catch(Exception e1)
		{
			// set message from exception as message - this should not really happen when setting a boolean.
			setErrorMessage(e1.getMessage());
			m_valid = false;
			e1.printStackTrace();
			// mark as dirty as we could not set the value
			setDirty(true);
		}

	}

	public boolean isEnabled()
	{
		return m_enabled;
	}

	public void setEnabled(boolean enabled)
	{
		m_enabled = enabled;
	}

	public boolean isValid()
	{
		return m_valid;
	}

	public void setValid(boolean valid)
	{
		m_valid = valid;
	}

	public IEditValidator getValidator()
	{
		return m_validator;
	}

	/**
	 * Validates the input. Sets/Clear message as a side effect.
	 * 
	 * @param input
	 * @return true if input is valid, false otherwise
	 */
	public boolean validate(String input)
	{
		return m_validator.isValid(input, this);
	}

	/**
	 * Sets a single error message keyed by this edit adapter. Replaces any earlier message set for the edit adapter.
	 * 
	 * @param message
	 */
	public void setErrorMessage(String message)
	{
		m_formPart.getManagedForm().getMessageManager().addMessage(this, message, null, IMessageProvider.ERROR,
				m_control);
	}

	/**
	 * Sets a single warning message keyed by this edit adapter. Replaces any earlier set message for the edit adapter.
	 * 
	 * @param message
	 */
	public void setWarningMessage(String message)
	{
		m_formPart.getManagedForm().getMessageManager().addMessage(this, message, null, IMessageProvider.WARNING,
				m_control);
	}

	/**
	 * Clears the message keyed by this edit adapter. Any previously set message will be cleared.
	 */
	public void clearMessages()
	{
		m_formPart.getManagedForm().getMessageManager().removeMessage(this, m_control);
	}

	/**
	 * Refreshes the control's enablement state.
	 */
	public void refreshEnablement()
	{
		m_control.setEnabled(isEnabled());
	}

	/**
	 * Updates the control (if it is a Text control) with the value in the EditAdapter, and calls refresh enablement.
	 */
	public void refresh()
	{
		m_refreshLock++;
		try
		{
			if(m_control instanceof Text)
			{
				String input = m_mutator.getValue();
				m_currentStringValue = input;
				((Text)m_control).setText(input);
				// make sure the value is validated (i.e. that appropriate messages are set/cleared).
				validate(input);
			}
			else if(m_control instanceof StyledText)
			{
				String input = m_mutator.getValue();
				m_currentStringValue = input;
				((StyledText)m_control).setText(input);
				// make sure the value is validated (i.e. that appropriate messages are set/cleared).
				validate(input);
			}
			else if(m_control instanceof Button)
			{
				((Button)m_control).setSelection(m_mutator.getBooleanValue());
			}
			refreshEnablement();
			// setDirty(false);
		}
		finally
		{
			m_refreshLock--;
		}
	}
	private void setValue(String val, Object memento)
	{
		m_refreshLock++;
		try
		{
			if(m_control instanceof Text)
			{
				// Switch focus/context first (as this will change the text in some cases).
				switchFocus(memento);
				String input = val;
				m_currentStringValue = input;
				if(m_control instanceof Text)
					((Text)m_control).setText(input);
				else if(m_control instanceof StyledText)
					((StyledText)m_control).setText(input);
				// make sure the value is validated (i.e. that appropriate messages are set/cleared).
				validate(input);
			}
			refreshEnablement();
			setDirty(true);
		}
		finally
		{
			m_refreshLock--;
		}
	}
	private void setValue(boolean val, Object memento)
	{
		m_refreshLock++;
		try
		{
			switchFocus(memento);
			if(m_control instanceof Button)
			{
				((Button)m_control).setSelection(val);
			}
			refreshEnablement();
			setDirty(true);
		}
		finally
		{
			m_refreshLock--;
		}
	}
	/**
	 * Switches focus in the editor to the control's page, sets the page memento see {@link IPageMementoProvider} if the page
	 * supports page memento, and then focuses the control.
	 */
	private void switchFocus(Object memento)
	{
		Object container = m_formPart.getManagedForm().getContainer();
		if(container instanceof IFormPage)
		{
			IFormPage wantedPage = (IFormPage)container;
			FormEditor editor = wantedPage.getEditor();
			IFormPage currentPage = editor.getActivePageInstance();
			if(!wantedPage.getId().equals(currentPage.getId()))
				editor.setActivePage(wantedPage.getId());
			if(wantedPage instanceof IPageMementoProvider)
				((IPageMementoProvider)wantedPage).setPageMemento(memento);
		}
		m_control.setFocus();
	}
	/**
	 * Updates the control's enabled state based on the values in the control's attached edit adapter. Does nothing if
	 * control does not have an attached edit adapter.
	 * 
	 * @param control
	 */
	public static void refreshControl(Control control)
	{
		Object data = control.getData("editAdapter"); //$NON-NLS-1$
		if(data instanceof EditAdapter)
			((EditAdapter)data).refreshEnablement();
	}

	/**
	 * Sets the enabled of the control based on combination of normal and wanted state. This method is typically used
	 * when enabling/disabling fields based on some grouping. When re-enabling fields in a disabled group, only fields
	 * that were enabled before the group disablement should be disabled.
	 * 
	 * @param control
	 * @param enabled
	 */
	public static void enable(Control control, boolean enabled)
	{
		Object data = control.getData("editAdapter"); //$NON-NLS-1$
		boolean normal = true;
		if(data instanceof EditAdapter)
			normal = ((EditAdapter)data).isEnabled();
		boolean result = enabled && normal;
		control.setEnabled(result);
	}

	public boolean isDirty()
	{
		return m_dirty;
	}

	public void setDirty(boolean dirty)
	{
		m_dirty = dirty;
		if(m_dirty)
			m_formPart.markDirty();
	}

	public Control getControl()
	{
		return m_control;
	}

	public void widgetDefaultSelected(SelectionEvent e)
	{
		widgetSelected(e);
	}

	public void widgetSelected(SelectionEvent e)
	{
		FormToolkit toolkit = m_formPart.getManagedForm().getToolkit();
		boolean input = ((Button)m_control).getSelection();
		if(toolkit instanceof IUndoOperationSupport)
		{
			Object container = m_formPart.getManagedForm().getContainer();
			Object memento = container instanceof IPageMementoProvider ? ((IPageMementoProvider)container).getPageMemento() : null;
			EditBooleanAdapterOperation op = new EditBooleanAdapterOperation(this, memento, input);
			op.addContext(((IUndoOperationSupport)toolkit).getUndoContext());
			// no need to execute this operation, the change has already taken place.
			((IUndoOperationSupport)toolkit).getOperationHistory().add(op);
		}
		setDirty(true);		
	}

	/**
	 * Callback from widget (not curently used).
	 */
	public void verifyText(VerifyEvent e)
	{
	}
	public class EditStringAdapterOperation extends AbstractOperation
	{
		private String m_oldVal;
		private String m_newVal;
		private Object m_pageMemento;
		
		public EditStringAdapterOperation(EditAdapter editAdapter, Object pageMemento, String oldVal, String newVal)
		{
			super(editAdapter.getUndoLabel());
			m_oldVal = oldVal;
			m_newVal = newVal;
			m_pageMemento = pageMemento;
		}

		@Override
		public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			// Nothing is needed here, as the change has already taken place
			if(monitor != null)
				monitor.done();
			return Status.OK_STATUS;
		}

		@Override
		public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			setValue(m_newVal, m_pageMemento);
			if(monitor != null)
				monitor.done();
			return Status.OK_STATUS;
		}

		@Override
		public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			setValue(m_oldVal, m_pageMemento);
			if(monitor != null)
				monitor.done();
			return Status.OK_STATUS;
		}
		
	}
	public class EditBooleanAdapterOperation extends AbstractOperation
	{
		private boolean m_newVal;
		private Object m_pageMemento;
		
		public EditBooleanAdapterOperation(EditAdapter editAdapter, Object pageMemento, boolean newVal)
		{
			super(editAdapter.getUndoLabel());
			m_newVal = newVal;
			m_pageMemento = pageMemento;
		}

		@Override
		public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			// Nothing is needed here, as the change has already taken place
			if(monitor != null)
				monitor.done();
			return Status.OK_STATUS;
		}

		@Override
		public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			setValue(m_newVal, m_pageMemento);
			if(monitor != null)
				monitor.done();
			return Status.OK_STATUS;
		}

		@Override
		public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
		{
			setValue(!m_newVal, m_pageMemento);
			if(monitor != null)
				monitor.done();
			return Status.OK_STATUS;
		}
		
	}
}
