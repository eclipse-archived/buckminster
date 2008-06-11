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

package org.eclipse.buckminster.distro.ui.editor.distro;

import org.eclipse.buckminster.ui.IDerivedEditorInput;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.p2.authoring.P2AuthoringUIPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * A multi page Eclipse form based editor for Distro.
 * 
 * @author Henrik Lindberg
 * 
 */
public class DistroEditor extends FormEditor implements IEditorMatchingStrategy
{

	public DistroEditor()
	{
	}

	@Override
	protected FormToolkit createToolkit(Display display)
	{
		// Create a toolkit that shares colors between editors.
		return new FormToolkit(P2AuthoringUIPlugin.getDefault().getFormColors(display));
		// Or use something like this to create the colors: 
		// ExamplesPlugin.getDefault().getFormColors(display));
	}

	@Override
	protected void addPages()
	{
		try
		{
			addPage(new WelcomePage(this));
			addPage(new FeedsPage(this));
			addPage(new FeedsPage2(this));
//			addPage(new ThirdPage(this));
//			addPage(new MasterDetailsPage(this));
//			addPage(new PageWithSubPages(this));
		}
		catch(PartInitException e)
		{
			//
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor)
	{
	}

	@Override
	public void doSaveAs()
	{
	}

	@Override
	public boolean isSaveAsAllowed()
	{
		return false;
	}

	public boolean matches(IEditorReference editorRef, IEditorInput input)
	{
		IEditorPart part = (IEditorPart)editorRef.getPart(false);
		if (part != null)
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

}


