package org.eclipse.buckminster.fetcher;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.buckminster.download.Installer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * {@link IResourceFetcher} that fetch a single file.
 * 
 * @author Guillaume CHATELET
 */
public class SimpleResourceFetcher extends AbstractResourceFetcher
{
	private final String m_filename;

	public SimpleResourceFetcher(URL url, String dir, String filename)
	{
		super(url, dir);
		m_filename = filename;
	}

	@Override
	public void consume(InputStream stream, IProgressMonitor monitor) throws IOException, CoreException
	{
		final Installer installer = Installer.getInstaller(m_filename, false);
		installer.install(stream, new File(getDestinationDir() + m_filename), monitor);
	}
}
