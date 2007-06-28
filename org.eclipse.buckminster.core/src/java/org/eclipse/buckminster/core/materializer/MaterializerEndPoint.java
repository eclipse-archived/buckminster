/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.materializer;

import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 *
 */
public class MaterializerEndPoint
{
	private final IPath m_finalDestination;
	private final IDecompressor[] m_decompressors;
	private final IExpander m_expander;

	MaterializerEndPoint(IPath finalLocation, IDecompressor[] decompressors, IExpander expander)
	{
		m_finalDestination = finalLocation;
		m_decompressors = decompressors == null ? new IDecompressor[0] : decompressors;
		m_expander = expander;
	}

	public static MaterializerEndPoint create(IPath location, Resolution resolution, MaterializationContext ctx) throws CoreException
	{
		IDecompressor[][] decompressorsHandle = new IDecompressor[1][];
		IExpander[] expanderHandle = new IExpander[1];		
		ctx.processUnpack(resolution, decompressorsHandle, expanderHandle);
		return new MaterializerEndPoint(location, decompressorsHandle[0], expanderHandle[0]);
	}

	public IPath getFinalDestination()
	{
		return m_finalDestination;
	}

	public void unpack(InputStream input, IProgressMonitor monitor) throws IOException
	{
		int dcCount = m_decompressors.length;
		monitor.beginTask(null, 100 + dcCount * 100);
		if(dcCount > 0)
		{
			// We will want to close our IDecompressor instances
			// later on but we don't want to close the input that
			// was passed in. So we protect it here.
			//
			input = new FilterInputStream(input)
			{
				@Override
				public void close()
				{
				}
			};

			for(IDecompressor decompressor : m_decompressors)
				input = decompressor.decompress(input, MonitorUtils.subMonitor(monitor, 100));
		}

		OutputStream output = null;
		try
		{
			if(m_expander != null)
				m_expander.expand(input, m_finalDestination, MonitorUtils.subMonitor(monitor, 100));
			else
			{
				output = new FileOutputStream(m_finalDestination.toFile());
				FileUtils.copyFile(input, output, MonitorUtils.subMonitor(monitor, 100));
			}
		}
		finally
		{
			IOUtils.close(output);
			if(dcCount > 0)
				IOUtils.close(input);
			monitor.done();
		}
	}
}
