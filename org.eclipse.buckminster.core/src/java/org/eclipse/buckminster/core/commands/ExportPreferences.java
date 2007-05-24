/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.commands;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;

/**
 * @author Thomas Hallgren
 */
public class ExportPreferences extends AbstractPreferencesCommand
{
	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception
	{
		OutputStream output = null;
		File prefsFile = this.getFile();
		try
		{
			if(prefsFile == null)
				output = System.out;
			else
				output = new BufferedOutputStream(new FileOutputStream(prefsFile));

			Platform.getPreferencesService().exportPreferences(this.getNode(), this.getFilter(), output);
			return 0;
		}
		catch(IOException e)
		{
			throw new SimpleErrorExitException("Unable to open file: " + prefsFile);
		}
		finally
		{
			if(prefsFile != null)
				IOUtils.close(output);
		}
	}
}
