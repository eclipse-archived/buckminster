/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.wizards;

import org.eclipse.buckminster.core.helpers.AccessibleByteArrayOutputStream;
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
	protected InputStream openContentStream(String containerName, String fileName)
	{
		AccessibleByteArrayOutputStream bld = new AccessibleByteArrayOutputStream();
		PrintStream contents = new PrintStream(bld);
		contents.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); //$NON-NLS-1$
		contents.println("<cspecExtension"); //$NON-NLS-1$
		contents.println("\txmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""); //$NON-NLS-1$
		contents.println("\txmlns:bc=\"http://www.eclipse.org/buckminster/Common-1.0\""); //$NON-NLS-1$
		contents.println("\txmlns=\"http://www.eclipse.org/buckminster/CSpec-1.0\">"); //$NON-NLS-1$
		contents.println("\t<dependencies>"); //$NON-NLS-1$
		contents.println("\t\t<!-- Place your Dependencies here -->"); //$NON-NLS-1$
		contents.println("\t</dependencies>"); //$NON-NLS-1$
		contents.println("\t<generators>"); //$NON-NLS-1$
		contents.println("\t\t<!-- Place your Generators here -->"); //$NON-NLS-1$
		contents.println("\t</generators>"); //$NON-NLS-1$
		contents.println("\t<artifacts>"); //$NON-NLS-1$
		contents.println("\t\t<!-- Place your Artifacts here -->"); //$NON-NLS-1$
		contents.println("\t</artifacts>"); //$NON-NLS-1$
		contents.println("\t<actions>"); //$NON-NLS-1$
		contents.println("\t\t<!-- Place your Actions here -->"); //$NON-NLS-1$
		contents.println("\t</actions>"); //$NON-NLS-1$
		contents.println("\t<groups>"); //$NON-NLS-1$
		contents.println("\t\t<!-- Place your Groups here -->"); //$NON-NLS-1$
		contents.println("\t</groups>"); //$NON-NLS-1$
		contents.println("\t<alterDependencies>"); //$NON-NLS-1$
		contents.println("\t\t<!-- Place your Dependency alterations here -->"); //$NON-NLS-1$
		contents.println("\t</alterDependencies>"); //$NON-NLS-1$
		contents.println("\t<alterArtifacts>"); //$NON-NLS-1$
		contents.println("\t\t<!-- Place your Artifact alterations here -->"); //$NON-NLS-1$
		contents.println("\t</alterArtifacts>"); //$NON-NLS-1$
		contents.println("\t<alterActions>"); //$NON-NLS-1$
		contents.println("\t\t<!-- Place your Action alterations here -->"); //$NON-NLS-1$
		contents.println("\t</alterActions>"); //$NON-NLS-1$
		contents.println("\t<alterGroups>"); //$NON-NLS-1$
		contents.println("\t\t<!-- Place your Group alterations here -->"); //$NON-NLS-1$
		contents.println("\t</alterGroups>"); //$NON-NLS-1$
		contents.println("</cspecExtension>"); //$NON-NLS-1$
		contents.flush();

		return bld.getInputStream();
	}
}
