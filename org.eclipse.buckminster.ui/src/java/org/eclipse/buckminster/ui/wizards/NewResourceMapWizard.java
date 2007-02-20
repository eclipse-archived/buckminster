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
 * This is a new Resource Map File wizard. Its role is to create a new file resource in the provided container. If the
 * container resource (a folder or a project) is selected in the workspace when the wizard is opened, it will accept it
 * as the target container. The wizard creates one file with the extension "rmap".
 */

public class NewResourceMapWizard extends NewBMFileWizard implements INewWizard
{
	/**
	 * Constructor for NewResourceMapWizard.
	 */
	public NewResourceMapWizard()
	{
		super();
	}

	/**
	 * Adding the page to the wizard.
	 */

	@Override
	public void addPages()
	{
		setPage(new NewResourceMapWizardPage(getSelection()));
	}

	/**
	 * We will initialize file contents with a sample text.
	 */

	@Override
	protected InputStream openContentStream()
	{
		StringBuffer contents = new StringBuffer(300);
		contents.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		contents.append("<rm:rmap\n");
		contents.append("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
		contents.append("    xmlns:rm=\"http://www.eclipse.org/buckminster/RMap-1.0\"\n");
		contents.append("    xmlns:bc=\"http://www.eclipse.org/buckminster/Common-1.0\"\n");
		contents.append("    xmlns:mp=\"http://www.eclipse.org/buckminster/MavenProvider-1.0\"\n");
		contents.append("    xmlns:pp=\"http://www.eclipse.org/buckminster/PDEMapProvider-1.0\" />\n");
		contents.append("<!-- Place your RMAP content here -->\n");
		contents.append("</rm:rmap>\n");

		// String contents = "This is the initial file contents for *.rmap file that should be word-sorted in the
		// Preview page of the multi-page editor";
		return new ByteArrayInputStream(contents.toString().getBytes());
	}
}
