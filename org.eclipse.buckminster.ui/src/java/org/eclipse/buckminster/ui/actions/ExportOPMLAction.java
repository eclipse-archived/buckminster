/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ui.actions;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.buckminster.opml.model.OPML;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.xml.sax.SAXException;

public class ExportOPMLAction extends AbstractOPMLAction
{
	@Override
	protected void run(OPML opml, Shell shell)
	{
		FileDialog saveAs = new FileDialog(getShell(), SWT.SAVE);
		saveAs.setFileName("component.opml");
		String path = saveAs.open();
		
		if(path == null)
			return; // nothing entered.
		
		URI fileURI;
		try
		{
			fileURI = new URI("file", path, null);
		}
		catch(URISyntaxException e)
		{
			this.showError("Bad File Name", "The entered file name is not valid", e);
			return;
		}
		// Check if file exists
		File file = new File(fileURI);

		try
		{
			if(!file.createNewFile())
			{
				if(!showConfirm("Confirm Overwrite", "The file '"+file.toString()+"'"
						+" already exist. Do you want to overwrite?"))
					return;
			}
		}
		catch(IOException e)
		{
			showError("Could not create file", "An error occured when creating the file", e);
			return;
		}

		// Write to the file
		try
		{
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
			PrintWriter writer = new PrintWriter(stream);
			org.eclipse.buckminster.sax.Utils.serialize(opml, stream);
			writer.close();
		}
		catch(FileNotFoundException e)
		{
			showError("Write Error", "Error while opening file for writing",e);
		}
		catch(SAXException e)
		{
			showError("OPML Format Error", "Internal problem when generating OPML XML",e);
		}
	}
}
