package org.eclipse.buckminster.ant.materializer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tools.bzip2.CBZip2InputStream;
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
		/**
		 * according to the documentation
		 * http://api.dpml.net/org/apache/ant/1.7.0/org/apache/tools/bzip2/CBZip2InputStream.html we have to skip the
		 * two first bytes.
		 */
		try
		{
			inputs.read();
			inputs.read();
			super.expand(new CBZip2InputStream(inputs), destinationFolder, monitor);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}
}
