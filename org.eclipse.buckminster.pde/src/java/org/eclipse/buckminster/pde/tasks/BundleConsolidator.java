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
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.version.IQualifierGenerator;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
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
	private final byte[] m_bytes;

	public BundleConsolidator(File inputFile, File outputFile, File propertiesFile, String qualifier)
			throws IOException
	{
		super(outputFile, propertiesFile, qualifier);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		InputStream input = new FileInputStream(inputFile);
		try
		{
			IOUtils.copy(input, output);
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

			String version = a.getValue(Constants.BUNDLE_VERSION);
			if(version != null && version.endsWith(PROPERTY_QUALIFIER))
			{
				String newQualifier = getQualifierReplacement(version, id);
				String newVersion = version;
				if(newQualifier.startsWith(GENERATOR_PREFIX))
				{
					String generatorId = newQualifier.substring(GENERATOR_PREFIX.length());
					try
					{
						ComponentIdentifier cid = new ComponentIdentifier(symbolicName, KeyConstants.PLUGIN_CATEGORY, VersionFactory.OSGiType.fromString(version));
						IQualifierGenerator generator = CorePlugin.getDefault().getQualifierGenerator(generatorId);
						IVersion qualifiedVersion = generator.generateQualifier(AbstractActor.getActiveContext(), cid, new ComponentIdentifier[0]);
						if(qualifiedVersion != null)
							newVersion = qualifiedVersion.toString();
					}
					catch(CoreException e)
					{
						CorePlugin.getLogger().warning("Unable to qualify version", e);
					}
				}
				else
					newVersion = version.replaceFirst(PROPERTY_QUALIFIER, newQualifier);

				if(!version.equals(newVersion))
				{
					a.put(new Attributes.Name(Constants.BUNDLE_VERSION), newVersion);
					changed = true;
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
