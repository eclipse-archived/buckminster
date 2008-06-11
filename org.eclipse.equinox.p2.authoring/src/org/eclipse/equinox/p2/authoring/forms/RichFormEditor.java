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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;

import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.ui.DerivedExternalFileEditorInput;
import org.eclipse.buckminster.ui.ExternalFileEditorInput;
import org.eclipse.buckminster.ui.IDerivedEditorInput;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.ObjectUndoContext;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.p2.authoring.P2AuthoringUIPlugin;
import org.eclipse.equinox.p2.authoring.internal.EditorEventBus;
import org.eclipse.equinox.p2.authoring.internal.IEditEventBusProvider;
import org.eclipse.equinox.p2.authoring.internal.IEditorEventBus;
import org.eclipse.equinox.p2.authoring.internal.IUndoOperationSupport;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.editors.text.ILocationProvider;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.operations.RedoActionHandler;
import org.eclipse.ui.operations.UndoActionHandler;

/**
 * @author Henrik Lindberg
 * 
 */
public abstract class RichFormEditor extends FormEditor implements IEditorMatchingStrategy, IGlobalActionPerformer
{
	private String m_tmpPrefix = "richEdit-";

	private String m_tmpSuffix = "tmp";

	protected EditorEventBus m_eventBus = new EditorEventBus();

	private RedoActionHandler m_redoAction;

	private UndoActionHandler m_undoAction;

	private IUndoContext m_undoContext;

	private Clipboard m_clipboard;

	public IEditorEventBus getEventBus()
	{
		return m_eventBus;
	}

	@Override
	protected FormToolkit createToolkit(Display display)
	{
		// Create a toolkit that shares colors between editors.
		return new RichFormToolkit(display);
	}

	@Override
	public void dispose()
	{
		m_eventBus.close();
		if(m_clipboard != null)
			m_clipboard.dispose();
		super.dispose();
	}

	public ExternalFileEditorInput getExternalFileEditorInput(IURIEditorInput input) throws CoreException, IOException
	{
		URI uri = input.getURI();
		URL url = uri.toURL();
		String protocol = url.getProtocol();

		File iuFile = null;

		if(protocol == null || "file".equals(protocol))
		{
			iuFile = new File(uri);
		}

		if(iuFile == null || !iuFile.canWrite())
		{
			iuFile = File.createTempFile(m_tmpPrefix, m_tmpSuffix);
			iuFile.deleteOnExit();
			OutputStream os = null;
			try
			{
				os = new FileOutputStream(iuFile);
				DownloadManager.readInto(url, os, null);
			}
			finally
			{
				try
				{
					os.close();
				}
				catch(IOException e)
				{
				}
			}
		}

		return new DerivedExternalFileEditorInput(input, iuFile, new Path(uri.getPath()).lastSegment(), url.toString());
	}

	/**
	 * An Editor Toolkit that provides support for RichFormEditor features:
	 * - An event bus that is convenient to use for broadcasted events. The editor toolkit is generally available throughout the editor and
	 * is a convenient place to pass around a handle to the bus. The use of an event bus decouples event sources and listeners.
	 * - hooks created form elements with event listeners that propagate events to the event bus.
	 * 
	 * TODO: Only text focus events are currently set up to be propagated
	 * 
	 * @author Henrik Lindberg
	 * 
	 */
	private class RichFormToolkit extends FormToolkit implements IEditEventBusProvider, IUndoOperationSupport
	{

		private FocusListener m_focusListener;
		public RichFormToolkit(Display display)
		{
			super(P2AuthoringUIPlugin.getDefault().getFormColors(display));
			m_focusListener = new FocusListener(){

				public void focusGained(FocusEvent e)
				{
					if(e.getSource() instanceof Text)
						System.err.print("Focus gained to "+ ((Text)e.getSource()).getText() + "\n");
					if(e.getSource() instanceof FormText)
						System.err.print("Focus gained to FormText\n");
					getEventBus().publishEvent(e);
				}

				public void focusLost(FocusEvent e)
				{
					if(e.getSource() instanceof Text)
						System.err.print("Focus lost from "+ ((Text)e.getSource()).getText() + "\n");
					if(e.getSource() instanceof FormText)
						System.err.print("Focus lost from FormText\n");
						
					getEventBus().publishEvent(e);					
				}
				
			};
		}

		public IEditorEventBus getEventBus()
		{
			return RichFormEditor.this.m_eventBus;
		}
		public IUndoContext getUndoContext()
		{
			// return this editors undo context
			return m_undoContext;
		}
		public IOperationHistory getOperationHistory()
		{
			// return the global one
			return OperationHistoryFactory.getOperationHistory();
		}
		@Override
		public Text createText(Composite parent, String value)
		{
			return createText(parent,value, SWT.NONE);
		}
		@Override
		public Text createText(Composite parent, String value, int style)
		{
			Text text = super.createText(parent, value, style);
			text.addFocusListener(m_focusListener);
			return text;
		}
		@Override
		public FormText createFormText(Composite parent, boolean trackFocus)
		{
			FormText text = super.createFormText(parent, trackFocus);
			text.addFocusListener(m_focusListener);
			return text;
		}

	}


	protected final String getTmpPrefix()
	{
		return m_tmpPrefix;
	}

	protected final void setTmpPrefix(String tmpPrefix)
	{
		m_tmpPrefix = tmpPrefix;
	}

	protected final String getTmpSuffix()
	{
		return m_tmpSuffix;
	}

	protected final void setTmpSuffix(String tmpSuffix)
	{
		m_tmpSuffix = tmpSuffix;
	}

	public boolean matches(IEditorReference editorRef, IEditorInput input)
	{
		IEditorPart part = (IEditorPart)editorRef.getPart(false);
		if(part != null)
		{
			IEditorInput editorInput = part.getEditorInput();
			if(editorInput != null)
			{
				if(editorInput.equals(input))
					return true;

				if(editorInput instanceof IDerivedEditorInput)
				{
					IEditorInput originalEditorInput = ((IDerivedEditorInput)editorInput).getOriginalInput();
					if(originalEditorInput.equals(input))
						return true;
				}
			}
		}

		return false;
	}

	@Override
	public void setInput(IEditorInput input)
	{
		super.setInput(input);
	}

	protected abstract void saveToPath(IPath path);

	protected boolean commitChanges()
	{
		super.commitPages(true);
		// TODO: now always return true... should check if something is still dirty
		return true;
	}

	protected Clipboard getClipboard()
	{
		return m_clipboard;
	}

	@Override
	public void doSave(IProgressMonitor monitor)
	{
		if(!commitChanges())
			return;

		IEditorInput input = getEditorInput();
		if(input == null)
			return;

		IPath path = (input instanceof ILocationProvider)
				? ((ILocationProvider)input).getPath(input)
				: ((IPathEditorInput)input).getPath();

		saveToPath(path);
	}

	@Override
	public void doSaveAs()
	{
		if(!commitChanges())
			return;

		IEditorInput input = getEditorInput();
		if(input == null)
			return;

		SaveAsDialog dialog = new SaveAsDialog(getSite().getShell());
		IFile original = (input instanceof IFileEditorInput)
				? ((IFileEditorInput)input).getFile()
				: null;
		if(original != null)
			dialog.setOriginalFile(original);

		if(dialog.open() == Window.CANCEL)
			return;

		IPath filePath = dialog.getResult();
		if(filePath == null)
			return;

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IFile file = workspace.getRoot().getFile(filePath);
		saveToPath(file.getLocation());
	}

	@Override
	public boolean isSaveAsAllowed()
	{
		return true;
	}

	/**
	 * Creates things needed before pages are added.
	 */
	@Override
	protected void createPages()
	{
		m_clipboard = new Clipboard(getContainer().getDisplay());
		super.createPages();

	}

	protected void createActions()
	{
		m_undoContext = new ObjectUndoContext(this);
		m_undoAction = new UndoActionHandler(getSite(), m_undoContext);
		m_redoAction = new RedoActionHandler(getSite(), m_undoContext);
	}

	public IAction getAction(String workbenchActionId)
	{
		// Return the actions for UNDO and REDO that were created in createActions.
		// These actions work against one undo context for the editor.

		if(ActionFactory.UNDO.getId().equals(workbenchActionId))
			return m_undoAction;
		if(ActionFactory.REDO.getId().equals(workbenchActionId))
			return m_redoAction;

		return null;
	}

	public boolean canPasteFromClipboard()
	{
		return canPaste(getClipboard());
	}
	
	public boolean canPaste(Clipboard clipboard)
	{
		IFormPage page = getActivePageInstance();
		if(page instanceof IGlobalActionPerformer)
		{
			return ((IGlobalActionPerformer)page).canPaste(clipboard);
		}
		return false;
		
	}
	public boolean canCopy(ISelection selection)
	{
		if(selection == null || selection.isEmpty())
		{
			IFormPage page = getActivePageInstance();
			if(page instanceof IGlobalActionPerformer)
			{
				return ((IGlobalActionPerformer)page).canCopy(selection);
			}
			return false;
		}
		if(selection instanceof IStructuredSelection)
			return !selection.isEmpty();
		if(selection instanceof ITextSelection)
		{
			ITextSelection textSelection = (ITextSelection)selection;
			return textSelection.getLength() > 0;
		}
		return false;
	}

	public boolean canCut(ISelection selection)
	{
		if(selection == null || selection.isEmpty())
		{
			IFormPage page = getActivePageInstance();
			if(page instanceof IGlobalActionPerformer)
			{
				return ((IGlobalActionPerformer)page).canCut(selection);
			}
			return false;
		}
		if(selection instanceof IStructuredSelection)
			return !selection.isEmpty();
		if(selection instanceof ITextSelection)
		{
			ITextSelection textSelection = (ITextSelection)selection;
			return textSelection.getLength() > 0;
		}
		return false;
	}
	/**
	 * Returns selection, or null if initializing and the editors site is null see {@link #getSite()}.
	 * @return null if initializing, or selection from selection provider from the editor's site.
	 */
	public ISelection getSelection()
	{
		IWorkbenchPartSite site = getSite();
		if(site == null)
			return null;
		ISelectionProvider provider = site.getSelectionProvider();
		if(provider == null)
			return null;
		return provider.getSelection();
	}

	public boolean doGlobalAction(String id)
	{
		// preserve selection
		ISelection selection = getSelection();
		boolean handled = getActivePageInstance() instanceof IGlobalActionPerformer ?
				((IGlobalActionPerformer)getActivePageInstance()).doGlobalAction(id)
				: false;
		if(!handled)
		{
			// FormPage page = getActivePageInstance();
			// if (page instanceof RichFormPage) {
			// if (id.equals(ActionFactory.UNDO.getId())) {
			// fInputContextManager.undo();
			// return;
			// }
			// if (id.equals(ActionFactory.REDO.getId())) {
			// fInputContextManager.redo();
			// return;
			// }
			
			// if not handled directly, perform copy/cut to clipboard here.
			//
			if(id.equals(ActionFactory.CUT.getId()) || id.equals(ActionFactory.COPY.getId()))
			{
				copyToClipboard(selection);
				handled = true;
			}
		}
		return handled;
	}

	/**
	 * Copies selection to clipboard - used as last default for copy and cut actions.
	 * @param selection
	 */
	private void copyToClipboard(ISelection selection)
	{
		Object[] objects = null;
		String textVersion = null;
		if(selection instanceof IStructuredSelection)
		{
			IStructuredSelection ssel = (IStructuredSelection)selection;
			if(ssel == null || ssel.size() == 0)
				return;
			objects = ssel.toArray();
			StringWriter writer = new StringWriter();
			PrintWriter pwriter = new PrintWriter(writer);
			Class<?> objClass = null;
			for(int i = 0; i < objects.length; i++)
			{
				Object obj = objects[i];
				if(objClass == null)
					objClass = obj.getClass();
				else if(objClass.equals(obj.getClass()) == false)
					return;
				// TODO: Handle writeable objects
				// if (obj instanceof IWritable)
				// {
				// // Add a customized delimiter in between all serialized
				// // objects to format the text representation
				// if ((i != 0) && (obj instanceof IWritableDelimiter))
				// {
				// ((IWritableDelimiter) obj).writeDelimeter(pwriter);
				// }
				//				((IWritable) obj).write("", pwriter); //$NON-NLS-1$
				// }
				else if(obj instanceof String)
				{
					// Delimiter is always a newline
					pwriter.println((String)obj);
				}
			}
			pwriter.flush();
			textVersion = writer.toString();
			try
			{
				pwriter.close();
				writer.close();
			}
			catch(IOException e)
			{
			}
		}
		else if(selection instanceof ITextSelection)
		{
			textVersion = ((ITextSelection)selection).getText();
		}
		if((textVersion == null || textVersion.length() == 0) && objects == null)
			return;
		// set the clipboard contents
		Object[] o = null;
		Transfer[] t = null;
		if(objects == null)
		{
			o = new Object[] { textVersion };
			t = new Transfer[] { TextTransfer.getInstance() };
		}
		// TODO: Transfer of model objects
		// else if (textVersion == null || textVersion.length() == 0) {
		// o = new Object[] {objects};
		// t = new Transfer[] {ModelDataTransfer.getInstance()};
		// }
		// else {
		// o = new Object[] {objects, textVersion};
		// t = new Transfer[] {ModelDataTransfer.getInstance(), TextTransfer.getInstance()};
		// }
		m_clipboard.setContents(o, t);
	}
	public void setSelection(ISelection selection) {
		getSite().getSelectionProvider().setSelection(selection);
//		getContributor().updateSelectableActions(selection);
	}
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException
	{
		super.init(site, input);
//		this.getSite().getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener(){
//
//			public void selectionChanged(SelectionChangedEvent event)
//			{
//				System.err.print("Selection changed 1\n");
//			}
//			
//		});
//		getSite().getPage().addPostSelectionListener(new ISelectionListener(){
//
//			public void selectionChanged(IWorkbenchPart part, ISelection selection)
//			{
//				System.err.print("Selection changed 2\n");				
//			}
//			
//		});
	}
}
