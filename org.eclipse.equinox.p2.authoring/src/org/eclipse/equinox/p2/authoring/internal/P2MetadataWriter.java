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

package org.eclipse.equinox.p2.authoring.internal;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;


import org.eclipse.equinox.internal.p2.metadata.InstallableUnit;
import org.eclipse.equinox.internal.p2.metadata.repository.io.MetadataWriter;
import org.eclipse.equinox.internal.p2.persistence.XMLWriter;
import org.osgi.framework.Version;

/**
 * P2 Metadata Writer used to centralize writing of P2 metadata as API is likely to change.
 * 
 * @author Henrik Lindberg
 */
@SuppressWarnings("restriction")
public class P2MetadataWriter implements P2MetadataConstants
{
	public static void writeInstallableUnit(InstallableUnit iu, OutputStream output)
	{
		try
		{
			IUWriter writer = new IUWriter(output);
			writer.writeIU(iu);
		}
		catch(UnsupportedEncodingException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
	}

	public static InputStream getInputStream(InstallableUnit iu)
	{
		AccessibleByteArrayOutputStream bld = new AccessibleByteArrayOutputStream();
		P2MetadataWriter.writeInstallableUnit(iu, bld);
		return bld.getInputStream();
	}

	/**
	 * Write InstallableUnit in XML to output stream
	 * 
	 * @author Henrik Lindberg
	 * 
	 */
	private static class IUWriter extends MetadataWriter
	{
		public IUWriter(OutputStream output) throws UnsupportedEncodingException
		{
			super(output, new XMLWriter.ProcessingInstruction[] //
					{ XMLWriter.ProcessingInstruction.makeClassVersionInstruction(IU_METADATA_FORMAT,
							InstallableUnit.class, new Version(IU_METADATA_FORMAT_VERSION)) });
		}

		public void writeIU(InstallableUnit installableUnit)
		{
			start(INSTALLABLE_ELEMENT);
			attribute(VERSION_ATTRIBUTE, INSTALLABLE_VERSION);
			writeInstallableUnit(installableUnit);
			end(INSTALLABLE_ELEMENT);
			flush(); // must flush as printer writer is used internally
		}
	}

}
