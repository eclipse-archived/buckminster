/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ui.internal;

import java.io.OutputStream;

import org.eclipse.buckminster.runtime.ILogReceiver;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IOConsoleOutputStream;

/**
 * @author ken1
 */
public class EclipseConsoleLogReceiver implements ILogReceiver
{
	public OutputStream start(String title, String type, boolean activateOnWrite, boolean errorStream)
	{
		IConsoleManager mgr = ConsolePlugin.getDefault().getConsoleManager();
		IOConsole ourConsole = null;
		for(IConsole console : mgr.getConsoles())
		{
			if(console instanceof IOConsole && title.equals(console.getName()) && type.equals(console.getType()))
			{
				ourConsole = (IOConsole)console;
				break;
			}
		}
		if(ourConsole == null)
		{
			ourConsole = new IOConsole(title, type, null);
			mgr.addConsoles(new IConsole[] { ourConsole });

			// Don't activate the console. It will be activated on
			// first write.
		}

		final IOConsoleOutputStream stream = ourConsole.newOutputStream();
		if(errorStream)
		{
			// Use red color to distinguish errors and warnings from normal output
			//
			final Display display = Display.getDefault();
			display.syncExec(new Runnable()
			{
				public void run()
				{
					stream.setColor(display.getSystemColor(SWT.COLOR_RED));
				}
			});
		}
		stream.setActivateOnWrite(activateOnWrite);
		return stream;
	}
}
