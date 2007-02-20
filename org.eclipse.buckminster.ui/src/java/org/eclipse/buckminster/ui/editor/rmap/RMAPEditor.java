package org.eclipse.buckminster.ui.editor.rmap;

import org.eclipse.ui.editors.text.TextEditor;

public class RMAPEditor extends TextEditor
{
	private final ColorManager m_colorManager;

	public RMAPEditor()
	{
		super();
		m_colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(m_colorManager));
		setDocumentProvider(new XMLDocumentProvider());
	}

	@Override
	public void dispose()
	{
		m_colorManager.dispose();
		super.dispose();
	}

}
