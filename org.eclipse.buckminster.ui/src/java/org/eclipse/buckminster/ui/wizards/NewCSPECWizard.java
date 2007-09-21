/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.wizards;

import java.io.InputStream;
import java.io.PrintStream;

import org.eclipse.buckminster.core.helpers.AccessibleByteArrayOutputStream;
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
	protected InputStream openContentStream(String containerName, String fileName)
	{
		String name = containerName;
		int lastSlash = name.lastIndexOf('/');
		if(lastSlash >= 0)
			name = name.substring(lastSlash + 1);

		AccessibleByteArrayOutputStream bld = new AccessibleByteArrayOutputStream();
		PrintStream contents = new PrintStream(bld);
		contents.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		contents.println("<cspec");
		contents.println("\txmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
		contents.println("\txmlns:bc=\"http://www.eclipse.org/buckminster/Common-1.0\"");
		contents.print("\txmlns=\"http://www.eclipse.org/buckminster/CSpec-1.0\" name=\"");
		contents.print(name);
		contents.println("\">");
		contents.println("\t<dependencies>");
		contents.println("\t\t<!-- Place your Dependencies here -->");
		contents.println("\t</dependencies>");
		contents.println("\t<generators>");
		contents.println("\t\t<!-- Place your Generators here -->");
		contents.println("\t</generators>");
		contents.println("\t<artifacts>");
		contents.println("\t\t<!-- Place your Artifacts here -->");
		contents.println("\t</artifacts>");
		contents.println("\t<actions>");
		contents.println("\t\t<!-- Place your Actions here -->");
		contents.println("\t</actions>");
		contents.println("\t<groups>");
		contents.println("\t\t<!-- Place your Groups here -->");
		contents.println("\t</groups>");
		contents.println("</cspec>");
		contents.flush();
		return bld.getInputStream();
	}
}
