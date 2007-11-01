/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.materializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 *
 */
public class GZIPDecompressor extends AbstractExtension implements IDecompressor
{
	public InputStream decompress(InputStream input, IProgressMonitor monitor) throws IOException
	{
		return new GZIPInputStream(input);
	}
}
