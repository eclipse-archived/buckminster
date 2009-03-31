/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.wizards;

import java.io.InputStream;

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.helpers.AccessibleByteArrayOutputStream;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.ui.INewWizard;
import org.xml.sax.SAXException;

@SuppressWarnings("restriction")
public class NewCSPECWizard extends NewBMFileWizard implements INewWizard
{
	private final String INIT_COMPONENT_TYPE = "buckminster"; //$NON-NLS-1$

	private final String INIT_VERSION_STRING = "1.0.0"; //$NON-NLS-1$

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
	 * 
	 * @throws SAXException
	 */

	@Override
	protected InputStream openContentStream(String containerName, String fileName)
	{
		String name = containerName;
		int lastSlash = name.lastIndexOf('/');
		if(lastSlash >= 0)
			name = name.substring(lastSlash + 1);

		CSpecBuilder builder = new CSpecBuilder();
		builder.setName(name);
		builder.setComponentTypeID(INIT_COMPONENT_TYPE);
		builder.setVersion(Version.parseVersion(INIT_VERSION_STRING));

		CSpec cspec = new CSpec(builder);

		AccessibleByteArrayOutputStream bld = new AccessibleByteArrayOutputStream();
		try
		{
			Utils.serialize(cspec, bld);
		}
		catch(SAXException e)
		{
			throw new RuntimeException(Messages.cannot_create_a_new_buckminster_component_specification_file, e);
		}

		return bld.getInputStream();
	}
}
