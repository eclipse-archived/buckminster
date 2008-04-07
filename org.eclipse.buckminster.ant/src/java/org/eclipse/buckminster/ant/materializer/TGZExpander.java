package org.eclipse.buckminster.ant.materializer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class TGZExpander extends TarExpander
{
	@Override
	public void expand(InputStream inputs, File finalLocation, IProgressMonitor monitor) throws CoreException
	{
		try
		{
			super.expand(new GZIPInputStream(inputs), finalLocation, monitor);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}
}
