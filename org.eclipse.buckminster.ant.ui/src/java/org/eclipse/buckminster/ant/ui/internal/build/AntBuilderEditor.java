/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ant.ui.internal.build;

import java.util.Map;

import org.eclipse.buckminster.ui.build.IBuilderEditor;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

/**
 * @author kolwing
 */
public class AntBuilderEditor implements IBuilderEditor
{
	public Map<String, String> edit(Shell shell, IProject project, String builderName, Map<String, String> args) throws CoreException
	{
		AntBuilderEditorDialog dlg = new AntBuilderEditorDialog(shell, builderName, args);
		if (dlg.open() == Window.OK)
			return dlg.getArgs();
		return null;
	}
}
