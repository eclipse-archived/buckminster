package org.eclipse.buckminster.subversion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.helpers.FileHandle;
import org.eclipse.buckminster.core.reader.AbstractRemoteReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.osgi.util.NLS;

public abstract class GenericRemoteReader<SVNENTRY, REVISION> extends AbstractRemoteReader
{
	protected final ISubversionSession<SVNENTRY, REVISION> m_session;

	private final SVNENTRY[] m_topEntries;

	protected GenericRemoteReader(IReaderType readerType, ProviderMatch provider, IProgressMonitor monitor)
			throws CoreException
	{
		super(readerType, provider);
		VersionMatch vm = provider.getVersionMatch();
		VersionSelector branchOrTag = vm.getBranchOrTag();
		m_session = getSession(provider.getRepositoryURI(), branchOrTag, vm.getRevision(), vm.getTimestamp(),
				provider.getNodeQuery().getContext());
		m_topEntries = getTopEntries(monitor);
		if(m_topEntries.length == 0)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.unable_to_find_artifacts_at_0, m_session));
	}

	@Override
	final public void close()
	{
		m_session.close();
	}

	@Override
	final public String toString()
	{
		return m_session.toString();
	}

	abstract protected void fetchRemoteFile(URI url, REVISION revision, OutputStream output, IProgressMonitor subMonitor)
			throws Exception;

	/**
	 * Implemented by subclasses. Used to retrieve a particular a concrete session instance.
	 * 
	 * @param repositoryURI
	 * @param branchOrTag
	 * @param revision
	 * @param timestamp
	 * @param context
	 * @return
	 * @throws CoreException
	 */
	protected abstract ISubversionSession<SVNENTRY, REVISION> getSession(String repositoryURI,
			VersionSelector branchOrTag, long revision, Date timestamp, RMContext context) throws CoreException;

	abstract protected SVNENTRY[] getTopEntries(IProgressMonitor monitor) throws CoreException;

	@Override
	final protected FileHandle innerGetContents(String fileName, IProgressMonitor monitor) throws CoreException,
			IOException
	{
		Logger logger = CorePlugin.getLogger();
		IPath path = Path.fromPortableString(fileName);
		String topEntry = path.segment(0);

		boolean found = false;
		for(SVNENTRY dirEntry : m_topEntries)
		{
			if(topEntry.equals(m_session.getSvnEntryHelper().getEntryPath(dirEntry)))
			{
				found = true;
				break;
			}
		}

		URI url = m_session.getSVNUrl(fileName);
		String key = storeInCache(fileName);
		if(!found)
			throw new FileNotFoundException(key);
		// now copying file to temporary file
		OutputStream output = null;
		File destFile = null;
		try
		{
			logger.debug("Reading remote file %s", key); //$NON-NLS-1$
			destFile = createTempFile();
			output = new FileOutputStream(destFile);
			final REVISION revision = m_session.getRevision();
			fetchRemoteFile(url, revision, output, MonitorUtils.subMonitor(monitor, 10));
			IOUtils.close(output);
			if(destFile.length() == 0)
			{
				// Suspect file not found
				if(!remoteFileExists(url, revision, monitor))
				{
					logger.debug("Remote file not found: %s", key); //$NON-NLS-1$
					throw new FileNotFoundException(url.toString());
				}
			}
			final FileHandle fh = new FileHandle(fileName, destFile, true);
			destFile = null;
			return fh;
		}
		catch(CoreException e)
		{
			throw e;
		}
		catch(IOException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			final Throwable rootCause = SvnExceptionHandler.getRootCause(e);
			if(SvnExceptionHandler.hasSvnException(rootCause))
			{
				logger.debug("Remote file not found: %s", key); //$NON-NLS-1$
				throw new FileNotFoundException(key);
			}
			IOException ioe = new IOException(rootCause.getMessage());
			ioe.initCause(rootCause);
			throw ioe;
		}
		finally
		{
			IOUtils.close(output);
			if(destFile != null)
				destFile.delete();
			monitor.done();
		}
	}

	@Override
	final protected void innerGetMatchingRootFiles(Pattern pattern, List<FileHandle> files, IProgressMonitor monitor)
			throws CoreException, IOException
	{
		ArrayList<String> names = null;
		ISvnEntryHelper<SVNENTRY> helper = m_session.getSvnEntryHelper();
		for(SVNENTRY dirEntry : m_topEntries)
		{
			final String fileName = helper.getEntryPath(dirEntry);
			if(pattern.matcher(fileName).matches())
			{
				if(names == null)
					names = new ArrayList<String>();
				names.add(fileName);
			}
		}
		if(names == null)
			return;

		if(names.size() == 1)
			files.add(innerGetContents(names.get(0), monitor));
		else
		{
			monitor.beginTask(null, names.size() * 100);
			for(String name : names)
				files.add(innerGetContents(name, MonitorUtils.subMonitor(monitor, 100)));
			monitor.done();
		}
	}

	@Override
	final protected void innerList(List<String> files, IProgressMonitor monitor) throws CoreException
	{
		ISvnEntryHelper<SVNENTRY> helper = m_session.getSvnEntryHelper();
		for(SVNENTRY dirEntry : m_topEntries)
		{
			String fileName = helper.getEntryPath(dirEntry);
			if(helper.getEntryKind(dirEntry) == ISvnEntryHelper.DIR && !fileName.endsWith("/")) //$NON-NLS-1$
				fileName = fileName + '/'; //$NON-NLS-1$
			files.add(fileName);
		}
	}

	abstract protected boolean remoteFileExists(URI url, REVISION revision, IProgressMonitor monitor)
			throws CoreException;

	abstract protected String storeInCache(String fileName) throws CoreException;

}
