/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.osgi.util.ManifestElement;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;

/**
 * @author Thomas Hallgren
 *
 */
@SuppressWarnings("restriction")
public class BundleConsolidator extends VersionConsolidator
{
	private final Manifest m_manifest;

	public BundleConsolidator(File inputFile, File outputFile, File propertiesFile, String qualifier)
			throws IOException
	{
		super(outputFile, propertiesFile, qualifier);

		InputStream input = null;
		try
		{
			input = new BufferedInputStream(new FileInputStream(inputFile));
			m_manifest = new Manifest(input);
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	public void run() throws IOException
	{
		Attributes a = m_manifest.getMainAttributes();
		String symbolicName = a.getValue(Constants.BUNDLE_SYMBOLICNAME);
		if(symbolicName != null)
		{
			String id;
			try
			{
				ManifestElement[] elements = ManifestElement.parseHeader(Constants.BUNDLE_SYMBOLICNAME, symbolicName);
				id = elements[0].getValue();
			}
			catch(BundleException be)
			{
				throw new IOException(be.getMessage());
			}

			String version = a.getValue(Constants.BUNDLE_VERSION);
			if(version != null)
			{
				String newVersion = replaceQualifierInVersion(version, id);
				if(!version.equals(newVersion))
					a.put(new Attributes.Name(Constants.BUNDLE_VERSION), newVersion);
			}
		}

		OutputStream out = null;
		try
		{
			out = new BufferedOutputStream(new FileOutputStream(getOutputFile()));
			m_manifest.write(out);
		}
		finally
		{
			IOUtils.close(out);
		}
	}
}
