package org.eclipse.buckminster.ant.materializer;

import java.io.IOException;
import java.io.InputStream;

import org.apache.tools.bzip2.CBZip2InputStream;
import org.eclipse.buckminster.download.IDecompressor;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Bzip2 stream decompressor
 * 
 * @author Guillaume CHATELET
 * 
 */
public class BZip2Decompressor implements IDecompressor
{

	public InputStream decompress(InputStream input, IProgressMonitor monitor) throws IOException
	{
		/**
		 * according to the documentation
		 * http://api.dpml.net/org/apache/ant/1.7.0/org/apache/tools/bzip2/CBZip2InputStream.html we have to skip the
		 * two first bytes.
		 * 
		 * @author Guillaume CHATELET
		 */
		input.read();
		input.read();
		return new CBZip2InputStream(input);
	}

}
