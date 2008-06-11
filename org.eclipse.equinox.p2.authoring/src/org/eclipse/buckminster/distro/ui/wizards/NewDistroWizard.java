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
package org.eclipse.buckminster.distro.ui.wizards;

import java.io.InputStream;

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.helpers.AccessibleByteArrayOutputStream;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.buckminster.ui.wizards.NewBMFileWizard;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.INewWizard;
import org.xml.sax.SAXException;

/**
 * This is a sample new wizard. Its role is to create a new file resource in the provided container. If the container
 * resource (a folder or a project) is selected in the workspace when the wizard is opened, it will accept it as the
 * target container. The wizard creates one file with the extension "distro". 
 * If a sample multi-page editor (also
 * available as a template) is registered for the same extension, it will be able to open it.
 */

public class NewDistroWizard extends NewBMFileWizard implements INewWizard
{
	private final String INIT_COMPONENT_TYPE = "buckminster"; //$NON-NLS-1$
	
	private final String INIT_VERSION_STRING = "1.0.0"; //$NON-NLS-1$
	
	private final String INIT_VERSION_TYPE = "OSGi"; //$NON-NLS-1$
	
	/**
	 * Constructor for NewDistroWizard.
	 */
	public NewDistroWizard()
	{
		super();
	}

	/**
	 * Adding the page to the wizard.
	 */

	@Override
	public void addPages()
	{
		setPage(new NewDistroWizardPage(getSelection()));
	}
	/**
	 * We will initialize file contents with a sample text.
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
		
		try
		{
			builder.setVersion(INIT_VERSION_STRING, INIT_VERSION_TYPE);
		}
		catch(CoreException e)
		{
			// initial version won't be set
		}
			
		CSpec cspec = new CSpec(builder);
		
		AccessibleByteArrayOutputStream bld = new AccessibleByteArrayOutputStream();
		try
		{
			Utils.serialize(cspec, bld);
		}
		catch(SAXException e)
		{
			throw new RuntimeException("Cannot create a new Buckminster Component Specification File", e);
		}
		
		return bld.getInputStream();
	}
	
}
