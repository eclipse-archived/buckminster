/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/
package org.eclipse.equinox.p2.authoring.wizards;

import java.io.InputStream;
import org.eclipse.buckminster.core.helpers.AccessibleByteArrayOutputStream;
import org.eclipse.buckminster.ui.wizards.NewBMFileWizard;
import org.eclipse.equinox.internal.p2.metadata.InstallableUnit;
import org.eclipse.equinox.p2.authoring.internal.P2MetadataFactory;
import org.eclipse.equinox.p2.authoring.internal.P2MetadataWriter;
import org.eclipse.ui.INewWizard;

/**
 * This wizard creates one file with the filename "*.iu" and opens an editor for it.
 */

@SuppressWarnings("restriction")
public class NewP2IUWizard extends NewBMFileWizard implements INewWizard
{
	public NewP2IUWizard()
	{
		super();
	}
	@Override
	public void addPages()
	{
		setPage(new NewP2IUPage(getSelection()));
	}

	/**
	 * Initializes file contents with a default Installable Unit content.
	 */
	@Override
	protected InputStream openContentStream(String containerName, String fileName)
	{
		String name = fileName;
		int lastPeriod = name.lastIndexOf('.'); //$NON-NLS-1$
		if(lastPeriod >= 0)
			name = name.substring(0, lastPeriod);
		// TODO: Should be named after the file name, not the container name
		// Create and write a default IU to a stream, and provide the output as input to the editor.
		InstallableUnit iu = P2MetadataFactory.createDefaultInstallableUnit(name);
		AccessibleByteArrayOutputStream bld = new AccessibleByteArrayOutputStream();
		P2MetadataWriter.writeInstallableUnit(iu, bld);
		return bld.getInputStream();
	}
}
