/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.wizards;

import org.eclipse.ui.INewWizard;
import java.io.*;

/**
 * This is a new CSPEX wizard. Its role is to create a new file resource in the provided container. If the container
 * resource (a folder or a project) is selected in the workspace when the wizard is opened, it will accept it as the
 * target container. The wizard creates one file with the extension "cspex".
 */

public class NewCSPEXWizard extends NewBMFileWizard implements INewWizard
{
	/**
	 * Constructor for NewCSPEXWizard.
	 */
	public NewCSPEXWizard()
	{
		super();
	}

	/**
	 * Adding the page to the wizard.
	 */

	@Override
	public void addPages()
	{
		setPage(new NewCSPEXWizardPage(getSelection()));
	}

	/**
	 * We will initialize file contents with a sample text.
	 */

	@Override
	protected InputStream openContentStream()
	{
		StringBuffer contents = new StringBuffer(300);
		contents.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		contents.append("<cse:cspecExtension\n");
		contents.append("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
		contents.append("    xmlns:bc=\"http://www.eclipse.org/buckminster/Common-1.0\"\n");
		contents.append("    xmlns:cs=\"http://www.eclipse.org/buckminster/CSpec-1.0\"\n");
		contents.append("    <cs:dependencies>\n");
		contents.append("        <!-- Place your Dependencies here -->\n");
		contents.append("    </cs:dependencies>\n");
		contents.append("    <cs:actions>\n");
		contents.append("        <!-- Place your Actions here -->\n");
		contents.append("    </cs:actions>\n");
		contents.append("</cse:cspecExtension>\n");

		return new ByteArrayInputStream(contents.toString().getBytes());
	}
}
