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


import java.io.PrintWriter;
import java.io.StringWriter;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.forms.HyperlinkSettings;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.IMessage;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.forms.AbstractFormPart;

/**
 * A base class for Eclipse form pages that adds support for showing messages and sets wanted defaults.
 * 
 * @author Henrik Lindberg
 * 
 */
public abstract class RichFormPage extends FormPage implements IGlobalActionPerformer
{
	private IExpansionListener m_reflowOnExpansion;

	/** The number of columns in the outermost TableWrapLayout - default is 2 */
	protected int m_numColumns = 2;

	/** The header string - default is "Undefined" */
	protected String m_header = "Undefined";

	protected boolean m_makeColumnsEqualWidth = false;

	protected Image m_headerImage = null;

	public RichFormPage(FormEditor editor, String id, String tabLabel)
	{
		super(editor, id, tabLabel);
	}

	/**
	 * Creates the form content:
	 * <ul>
	 * <li>The outermost ScrolledForm gets a TableWrapLayout</li>
	 * <li>The outermost table gets a default of 2 columns - change by setting {@link #m_numColumns }</li>
	 * <li>Left and right margins are set to 10 so the form contents does not kiss the edges</li>
	 * <li>The Eclipse 3.3. default header decoration is turned on</li>
	 * <li>Hyperlinks are set to underline on hover</li>
	 * <li>The header is set to the value of the protected {@link #m_header}</li>
	 * <li>A section expansion listener that reflows the layout is created, and can be used when setting up the contents
	 * of the form. See {@link #getReflowListener()}.</li>
	 * <li>A hyperlink listener is set up to display a list of formatted messages from the MessageManager.</li>
	 * </ul>
	 * 
	 */
	@Override
	protected final void createFormContent(IManagedForm managedForm)
	{
		final ScrolledForm scrolledForm = managedForm.getForm();
		final FormToolkit toolkit = managedForm.getToolkit();

		// Set header.
		scrolledForm.setText(m_header);
		if(m_headerImage != null)
			scrolledForm.setImage(m_headerImage);

		// Use default 3.3. form page header (old style required an image)
		toolkit.decorateFormHeading(scrolledForm.getForm());

		// underline hyperlinks on hover
		toolkit.getHyperlinkGroup().setHyperlinkUnderlineMode(HyperlinkSettings.UNDERLINE_HOVER);

		TableWrapLayout layout = new TableWrapLayout();
		layout.leftMargin = 5;
		layout.rightMargin = 5;
		layout.numColumns = m_numColumns;
		layout.makeColumnsEqualWidth = m_makeColumnsEqualWidth;
		scrolledForm.getBody().setLayout(layout);

		// Create the reflowing expansion listener
		//
		m_reflowOnExpansion = new ExpansionAdapter()
		{

			@Override
			public void expansionStateChanged(ExpansionEvent e)
			{
				scrolledForm.reflow(true);
			}
		};

		// Add a popup with formatted message to a hyperlink
		// TODO: This sample code probably pops up an empty window for non message links
		// Needs to be fixed if other hyperlinks are added to the form...

		scrolledForm.getForm().addMessageHyperlinkListener(new HyperlinkAdapter()
		{
			@Override
			public void linkActivated(HyperlinkEvent e)
			{
				String title = e.getLabel();
				// String details = title;
				Object href = e.getHref();
				if(href instanceof IMessage[])
				{
					// details =
					// managedForm.getMessageManager().createSummary((IMessage[])href);
				}
				// int type = form.getForm().getMessageType();
				/*
				 * switch (type) { case IMessageProvider.NONE: case IMessageProvider.INFORMATION:
				 * MessageDialog.openInformation(form.getShell(), title, details); break; case IMessageProvider.WARNING:
				 * MessageDialog.openWarning(form.getShell(), title, details); break; case IMessageProvider.ERROR:
				 * MessageDialog.openError(form.getShell(), title, details); break; }
				 */
				Point hl = ((Control)e.widget).toDisplay(0, 0);
				hl.x += 10;
				hl.y += 10;
				// Note, must be application modal, or it is possible to navigate the form and make changes that affects the
				// message state. (And perhaps even worse, to open the popup again).
				final Shell shell = new Shell(scrolledForm.getShell(), SWT.ON_TOP | SWT.TOOL | SWT.APPLICATION_MODAL);
				shell.setImage(getImage(scrolledForm.getMessageType()));
				shell.setText(title);
				shell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
				FillLayout fillLayout = new FillLayout();
				fillLayout.marginHeight = 5;
				fillLayout.marginWidth = 5;

				shell.setLayout(fillLayout);
				// ScrolledFormText stext = new ScrolledFormText(shell, false);
				// stext.setBackground(toolkit.getColors().getBackground());
				FormText text = toolkit.createFormText(shell, false);
				configureFormText(scrolledForm.getForm(), text);
				// stext.setFormText(text);
				if(href instanceof IMessage[])
					text.setText(createFormTextContent((IMessage[])href), true, false);
				shell.setLocation(hl);
				// Point size = shell.computeSize(400, SWT.DEFAULT);
				/*
				 * richToolTipMessageManager.setActive(false); shell.addDisposeListener(new DisposeListener() { public
				 * void widgetDisposed(DisposeEvent e) { richToolTipMessageManager.setActive(true); } });
				 */
				
				// must have a key listener to make it possible to close the popup shell on ESC key
				shell.addKeyListener(new KeyAdapter(){

					@Override
					public void keyPressed(KeyEvent event)
					{
						// close shell on escape key
						if(event.keyCode == SWT.ESC)
							shell.dispose();
					}					
				});
				shell.pack();
				shell.open();
				shell.forceFocus();
			}
		});
		scrolledForm.addTraverseListener(new TraverseListener(){

			public void keyTraversed(TraverseEvent e)
			{
				System.err.print("Traverse Event Occured in RichFormPage");
			}
			
		});
		addFormContent(managedForm);
	}

	/**
	 * Replacement for {@link #createFormContent(IManagedForm)} which has been made final. This method is called as the
	 * last step, and should complete the creation of the form content, but should not set up everything supported by
	 * the default implementation. See {@link #createFormContent(IManagedForm)} for all the things that are set up by
	 * default.
	 * 
	 * @param managedForm
	 */
	abstract protected void addFormContent(IManagedForm managedForm);

	/**
	 * Returns an IExpansionListener that reflows the form when expansion changes. Attach this listener to expandable
	 * sections in the form.
	 * 
	 * @return null if createFormContent has not been called.
	 */
	protected IExpansionListener getReflowListener()
	{
		return m_reflowOnExpansion;
	}

	private void configureFormText(final Form form, FormText text)
	{
		text.addHyperlinkListener(new HyperlinkAdapter()
		{
			@Override
			public void linkActivated(HyperlinkEvent e)
			{
				String is = (String)e.getHref();
				try
				{
					int index = Integer.parseInt(is);
					IMessage[] messages = form.getChildrenMessages();
					IMessage message = messages[index];
					Control c = message.getControl();
					((FormText)e.widget).getShell().dispose();
					if(c != null)
						c.setFocus();
				}
				catch(ArrayIndexOutOfBoundsException ex)
				{
					// This can happens if message array changes while menu is up
					((FormText)e.widget).getShell().dispose();			
				}
				catch(NumberFormatException ex)
				{
				}
			}
		});
		text.setImage("error", getImage(IMessageProvider.ERROR)); //$NON-NLS-1$
		text.setImage("warning", getImage(IMessageProvider.WARNING)); //$NON-NLS-1$
		text.setImage("info", getImage(IMessageProvider.INFORMATION)); //$NON-NLS-1$
	}

	/**
	 * Formats messages into a form string
	 * 
	 * @param messages
	 *            - messages to format
	 * @return a form string
	 */
	private String createFormTextContent(IMessage[] messages)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		pw.println("<form>"); //$NON-NLS-1$
		for(int i = 0; i < messages.length; i++)
		{
			IMessage message = messages[i];
			pw.print("<li vspace=\"false\" style=\"image\" indent=\"16\" value=\""); //$NON-NLS-1$
			switch(message.getMessageType())
			{
			case IMessageProvider.ERROR:
				pw.print("error"); //$NON-NLS-1$
				break;
			case IMessageProvider.WARNING:
				pw.print("warning"); //$NON-NLS-1$
				break;
			case IMessageProvider.INFORMATION:
				pw.print("info"); //$NON-NLS-1$
				break;
			}
			pw.print("\"> <a href=\""); //$NON-NLS-1$
			pw.print(i + ""); //$NON-NLS-1$
			pw.print("\">");//$NON-NLS-1$
			if(message.getPrefix() != null)
				pw.print(message.getPrefix());
			pw.print(message.getMessage());
			pw.println("</a></li>"); //$NON-NLS-1$
		}
		pw.println("</form>"); //$NON-NLS-1$
		pw.flush();
		return sw.toString();
	}

	/**
	 * Convenience method to pick up platform images for error and warning.
	 * 
	 * @param type
	 * @return
	 */
	private Image getImage(int type)
	{
		switch(type)
		{
		case IMessageProvider.ERROR:
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
		case IMessageProvider.WARNING:
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK);
		case IMessageProvider.INFORMATION:
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
		}
		return null;
	}

	protected Control getFocusControl() {
		IManagedForm form = getManagedForm();
		if (form == null)
			return null;
		Control control = form.getForm();
		if (control == null || control.isDisposed())
			return null;
		Display display = control.getDisplay();
		Control focusControl = display.getFocusControl();
		if (focusControl == null || focusControl.isDisposed())
			return null;
		return focusControl;
	}

	public boolean doGlobalAction(String actionId) {
		Control focusControl = getFocusControl();
		if (focusControl == null)
			return false;

		if (canPerformDirectly(actionId, focusControl))
			return true;
		AbstractFormPart focusPart = getFocusSection();
		if (focusPart != null) {
			if (focusPart instanceof IGlobalActionPerformer)
				return ((IGlobalActionPerformer) focusPart).doGlobalAction(actionId);
		}
		return false;
	}

	public boolean canPaste(Clipboard clipboard) {
		AbstractFormPart focusPart = getFocusSection();
		if (focusPart != null) {
			if (focusPart instanceof IGlobalActionPerformer) {
				return ((IGlobalActionPerformer) focusPart).canPaste(clipboard);
			}
		}
		// Otherwise, check if focus holder can handle action
		Control focusControl = getFocusControl();
		if (focusControl != null && focusControl instanceof Text && ((Text)focusControl).getEditable())
			return true;
		return false;
	}

	/**
	 * @param selection
	 * @return
	 */
	public boolean canCopy(ISelection selection) {
		// If there is a focused part that wants to handle this, it should do so
		AbstractFormPart focusPart = getFocusSection();
		if (focusPart != null) {
			if (focusPart instanceof IGlobalActionPerformer) {
				return ((IGlobalActionPerformer) focusPart).canCopy(selection);
			}
		}
		// Otherwise, check if focus holder can handle action
		Control focusControl = getFocusControl();
		if (focusControl != null && focusControl instanceof Text)
			return true;
		return false;
	}

	/**
	 * @param selection
	 * @return
	 */
	public boolean canCut(ISelection selection) {
		AbstractFormPart focusPart = getFocusSection();
		if (focusPart != null) {
			if (focusPart instanceof IGlobalActionPerformer) {
				return ((IGlobalActionPerformer) focusPart).canCut(selection);
			}
		}
		// Otherwise, check if focus holder can handle action
		Control focusControl = getFocusControl();
		if (focusControl != null && focusControl instanceof Text && ((Text)focusControl).getEditable())
			return true;
		return false;
	}

	private AbstractFormPart getFocusSection() {
		Control focusControl = getFocusControl();
		if (focusControl == null)
			return null;
		Composite parent = focusControl.getParent();
		AbstractFormPart targetPart = null;
		while (parent != null) {
			Object data = parent.getData("part"); //$NON-NLS-1$
			if (data != null && data instanceof AbstractFormPart) {
				targetPart = (AbstractFormPart) data;
				break;
			}
			parent = parent.getParent();
		}
		return targetPart;
	}
	protected boolean canPerformDirectly(String id, Control control) {
		if (control instanceof Text) {
			Text text = (Text) control;
			if (id.equals(ActionFactory.CUT.getId())) {
				text.cut();
				return true;
			}
			if (id.equals(ActionFactory.COPY.getId())) {
				text.copy();
				return true;
			}
			if (id.equals(ActionFactory.PASTE.getId())) {
				text.paste();
				return true;
			}
			if (id.equals(ActionFactory.SELECT_ALL.getId())) {
				text.selectAll();
				return true;
			}
			if (id.equals(ActionFactory.DELETE.getId())) {
				int count = text.getSelectionCount();
				if (count == 0) {
					int caretPos = text.getCaretPosition();
					text.setSelection(caretPos, caretPos + 1);
				}
				text.insert(""); //$NON-NLS-1$
				return true;
			}
		}
		return false;
	}

}
