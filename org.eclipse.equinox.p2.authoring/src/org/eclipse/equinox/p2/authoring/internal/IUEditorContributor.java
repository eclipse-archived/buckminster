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

package org.eclipse.equinox.p2.authoring.internal;

import java.util.EventObject;

import org.eclipse.equinox.p2.authoring.InstallableUnitEditor;
import org.eclipse.equinox.p2.authoring.forms.RichFormEditor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.ide.IDEActionFactory;
import org.eclipse.ui.part.EditorActionBarContributor;
import org.eclipse.ui.texteditor.IUpdate;

/**
 * Action contributor for Installable Unit Editor
 * (TODO:  perhaps be reusable...)
 * @author Henrik Lindberg
 *
 */
public class IUEditorContributor extends EditorActionBarContributor
{
	private InstallableUnitEditor m_editor;

	private ISharedImages m_sharedImages;

	private CutAction m_cutAction;

	private CopyAction m_copyAction;

	private PasteAction m_pasteAction;
	
	private static final String[]  WORKBENCH_ACTION_IDS = {
		ActionFactory.DELETE.getId(),
		ActionFactory.UNDO.getId(),
		ActionFactory.REDO.getId(),
		ActionFactory.SELECT_ALL.getId(),
		ActionFactory.FIND.getId(),
		IDEActionFactory.BOOKMARK.getId(),
	};
	
	@Override
	public void init(IActionBars bars) {
		super.init(bars);
		makeActions();
	}

	private void makeActions()
	{
		m_cutAction = new CutAction();
		m_copyAction = new CopyAction();
		m_pasteAction = new PasteAction();		
	}

	@Override
	public void setActiveEditor(IEditorPart part)
	{
		m_editor = (InstallableUnitEditor)part;		
		hookGlobalActions(m_editor, getActionBars());
		m_editor.getEventBus().addListener(new IEditorListener(){

			public void notify(EventObject o)
			{
				if(o instanceof FocusEvent)
				{
					// Focus is gained or lost
					updateSelectableActions(m_editor.getSelection());
				}
			}
			
		});

	}

	private void hookGlobalActions(RichFormEditor editor, IActionBars actionBars)
	{
		// TODO: THIS IS KLUDGY ! Some actions added by getting them from the editor (undo/redo), and some
		// global actions are added here - this is UGLY - changes pending to building out undo/redo.
		for(int i = 0; i < WORKBENCH_ACTION_IDS.length;i++)
			actionBars.setGlobalActionHandler(WORKBENCH_ACTION_IDS[i], editor.getAction(WORKBENCH_ACTION_IDS[i]));
		actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), m_cutAction);
		actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), m_copyAction);
		actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), m_pasteAction);
	}
	
	class GlobalAction extends Action implements IUpdate {
		private String id;

		public GlobalAction(String id) {
			this.id = id;
		}

		@Override
		public void run() {
			m_editor.doGlobalAction(id);
			updateSelectableActions(m_editor.getSelection());
		}

		public void update() {
			getActionBars().updateActionBars();
		}
	}

	class ClipboardAction extends GlobalAction {
		public ClipboardAction(String id) {
			super(id);
			setEnabled(false);
		}

		public void selectionChanged(ISelection selection) {
		}

		public boolean isEditable() {
			if (m_editor == null)
				return false;
			return !m_editor.isReadOnly();
		}
	}

	class CutAction extends ClipboardAction {
		public CutAction() {
			super(ActionFactory.CUT.getId());
			setText("Cut");
			setImageDescriptor(getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
			setDisabledImageDescriptor(getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_CUT_DISABLED));
			setActionDefinitionId("org.eclipse.ui.edit.cut"); //$NON-NLS-1$
		}

		@Override
		public void selectionChanged(ISelection selection) {
			setEnabled(isEditable() && m_editor.canCut(selection));
		}
	}

	class CopyAction extends ClipboardAction {
		public CopyAction() {
			super(ActionFactory.COPY.getId());
			setText("Copy");
			setImageDescriptor(getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
			setDisabledImageDescriptor(getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
			setActionDefinitionId("org.eclipse.ui.edit.copy"); //$NON-NLS-1$
		}

		@Override
		public void selectionChanged(ISelection selection) {
			setEnabled(m_editor.canCopy(selection));
		}
	}

	class PasteAction extends ClipboardAction {
		public PasteAction() {
			super(ActionFactory.PASTE.getId());
			setText("Paste");
			setImageDescriptor(getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
			setDisabledImageDescriptor(getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
			setActionDefinitionId("org.eclipse.ui.edit.paste"); //$NON-NLS-1$
		}

		@Override
		public void selectionChanged(ISelection selection) {
			setEnabled(isEditable() && m_editor.canPasteFromClipboard());
		}
	}
	protected ISharedImages getSharedImages() {
		if (m_sharedImages == null)
			m_sharedImages = getPage().getWorkbenchWindow().getWorkbench().getSharedImages();
		return m_sharedImages;
	}
	public void updateSelectableActions(ISelection selection) {
		if (m_editor != null) {
			m_cutAction.selectionChanged(selection);
			m_copyAction.selectionChanged(selection);
			m_pasteAction.selectionChanged(selection);
		}
	}

}
