package org.eclipse.buckminster.ant.materializer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * A class to add the possibility to uncompress tar files compressed with bzip2 algorithm
 * 
 * @author Guillaume CHATELET
 * 
 */
public class TBZ2Expander extends TarExpander
{
	@Override
	public void expand(InputStream inputs, File destinationFolder, IProgressMonitor monitor) throws CoreException
	{
		try
		{
			super.expand(new BZip2Decompressor().decompress(inputs, monitor), destinationFolder, monitor);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}
}
