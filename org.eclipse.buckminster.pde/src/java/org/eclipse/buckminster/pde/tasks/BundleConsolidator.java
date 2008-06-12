/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionSyntaxException;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.util.ManifestElement;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;

/**
 * @author Thomas Hallgren
 *
 */
public class BundleConsolidator extends VersionConsolidator
{
	private final byte[] m_bytes;

	public BundleConsolidator(File inputFile, File outputFile, File propertiesFile, String qualifier)
			throws CoreException
	{
		super(outputFile, propertiesFile, qualifier);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		InputStream input = null;
		try
		{
			input = new FileInputStream(inputFile);
			IOUtils.copy(input, output, null);
		}
		catch(IOException e)
		{
			throw BuckminsterException.fromMessage("Unable to manifest from %s", inputFile);
		}
		finally
		{
			IOUtils.close(input);
		}
		m_bytes = output.toByteArray();
	}

	public void run() throws IOException
	{
		Manifest manifest = new Manifest(new ByteArrayInputStream(m_bytes));
		Attributes a = manifest.getMainAttributes();
		String symbolicName = a.getValue(Constants.BUNDLE_SYMBOLICNAME);
		boolean changed = false;
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

			String versionStr = a.getValue(Constants.BUNDLE_VERSION);
			if(versionStr != null)
			{
				try
				{
					IVersion version = VersionFactory.OSGiType.fromString(versionStr);
					ComponentIdentifier ci = new ComponentIdentifier(id, IComponentType.OSGI_BUNDLE, version);
					IVersion newVersion = replaceQualifier(ci, Collections.<ComponentIdentifier>emptyList());
	
					if(!(newVersion == null || version.equals(newVersion)))
					{
						a.put(new Attributes.Name(Constants.BUNDLE_VERSION), newVersion.toString());
						changed = true;
					}
				}
				catch(VersionSyntaxException e)
				{
				}
			}
		}

		OutputStream out = null;
		try
		{
			out = new FileOutputStream(getOutputFile());
			if(changed)
			{
				out = new BufferedOutputStream(out);
				manifest.write(out);
			}
			else
				out.write(m_bytes);
		}
		finally
		{
			IOUtils.close(out);
		}
	}
}
