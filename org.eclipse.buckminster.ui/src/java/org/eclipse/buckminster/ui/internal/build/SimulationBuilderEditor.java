/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ui.internal.build;

import java.util.Map;

import org.eclipse.buckminster.core.build.SimulationBuilder;
import org.eclipse.buckminster.ui.build.IBuilderEditor;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

/**
 * @author kolwing
 */
public class SimulationBuilderEditor implements IBuilderEditor
{
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.buckminster.ui.build.IBuilderEditor#edit(java.util.Map)
	 */
	public Map<String, String> edit(Shell shell, IProject project, String builderName, Map<String, String> args) throws CoreException
	{
		int ticks = -1;
		try
		{
			ticks = Integer.parseInt(args.get(SimulationBuilder.TICKS_KEY).toString().trim());
		}
		catch(Exception e)
		{
			// ignore
		}
		if(ticks < SimulationBuilder.TICKS_MIN || ticks > SimulationBuilder.TICKS_MAX)
			ticks = SimulationBuilder.TICKS_DEFAULT;

		IInputValidator validator = new IInputValidator()
		{
			public String isValid(String text)
			{
				int v = Integer.parseInt(text);
				if(v < SimulationBuilder.TICKS_MIN)
					return "Minimum: " + SimulationBuilder.TICKS_MIN;
				if(v > SimulationBuilder.TICKS_MAX)
					return "Maximum: " + SimulationBuilder.TICKS_MAX;
				return null;
			}
		};
		InputDialog dlg = new InputDialog(shell, "Enter simulation tick count for " + builderName, "You can enter a value between "
				+ SimulationBuilder.TICKS_MIN + " and " + SimulationBuilder.TICKS_MAX, Integer.toString(ticks),
				validator);
		if(dlg.open() == Window.OK)
		{
			args.put(SimulationBuilder.TICKS_KEY, dlg.getValue());
			return args;
		}
		return null;
	}

}
