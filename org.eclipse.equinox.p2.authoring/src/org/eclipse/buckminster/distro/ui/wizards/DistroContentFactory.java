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
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.SAXException;

/**
 * This factory creates distro content and return input streams to generated content.
 */

public class DistroContentFactory
{
	private final static String INIT_COMPONENT_TYPE = "buckminster";
	
	private final static String INIT_VERSION_STRING = "1.0.0";
	
	private final static String INIT_VERSION_TYPE = "OSGi";
	
	/**
	 * Return an empty distro initialized from container name.
	 * @throws SAXException 
	 */
	public static InputStream openContentStream(String containerName, String fileName)
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
			throw new RuntimeException("Cannot create a new Buckminster Distro" +
					" Specification File", e);
		}
		
		return bld.getInputStream();
	}
	
}
