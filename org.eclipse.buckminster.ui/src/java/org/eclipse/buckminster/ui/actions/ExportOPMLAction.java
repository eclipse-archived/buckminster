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
import org.eclipse.buckminster.ui.Messages;

import org.eclipse.osgi.util.NLS;
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
		saveAs.setFileName("component.opml"); //$NON-NLS-1$
		String path = saveAs.open();

		if(path == null)
			return; // nothing entered.

		URI fileURI;
		try
		{
			fileURI = new URI("file", path, null); //$NON-NLS-1$
		}
		catch(URISyntaxException e)
		{
			this.showError(Messages.bad_file_name, Messages.the_entered_file_name_is_not_valid, e);
			return;
		}
		// Check if file exists
		File file = new File(fileURI);

		try
		{
			if(!file.createNewFile())
			{
				if(!showConfirm(Messages.confirm_overwrite, NLS.bind(
						Messages.the_file_0_already_exists_overwrite_question, file.toString())))
					return;
			}
		}
		catch(IOException e)
		{
			showError(Messages.could_not_create_file, Messages.an_error_occured_when_creating_the_file, e);
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
			showError(Messages.write_error, Messages.error_while_opening_file_for_writing, e);
		}
		catch(SAXException e)
		{
			showError(Messages.opml_format_error, Messages.internal_problem_when_generating_opml_xml, e);
		}
	}
}
