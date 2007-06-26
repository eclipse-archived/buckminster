/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.wizards;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.ui.INewWizard;

public class NewCSPECWizard extends NewBMFileWizard implements INewWizard
{

	public NewCSPECWizard()
	{
		super();
	}
	/**
	 * Adding the page to the wizard.
	 */

	@Override
	public void addPages()
	{
		setPage(new NewCSPECWizardPage(getSelection()));
	}

	/**
	 * We will initialize file contents with a sample text.
	 */

	@Override
	protected InputStream openContentStream()
	{
		StringBuffer contents = new StringBuffer(300);
		contents.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		contents.append("<cspec\n");
		contents.append("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
		contents.append("    xmlns:bc=\"http://www.eclipse.org/buckminster/Common-1.0\"\n");
		contents.append("    xmlns=\"http://www.eclipse.org/buckminster/CSpec-1.0\"\n");
		contents.append("    <dependencies>\n");
		contents.append("        <!-- Place your Dependencies here -->\n");
		contents.append("    </dependencies>\n");
		contents.append("    <artifacts>\n");
		contents.append("        <!-- Place your Artifacts here -->\n");
		contents.append("    </artifacts>\n");
		contents.append("    <actions>\n");
		contents.append("        <!-- Place your Actions here -->\n");
		contents.append("    </actions>\n");
		contents.append("    <groups>\n");
		contents.append("        <!-- Place your Groups here -->\n");
		contents.append("    </groups>\n");
		contents.append("</cspec>\n");

		return new ByteArrayInputStream(contents.toString().getBytes());
	}

}
