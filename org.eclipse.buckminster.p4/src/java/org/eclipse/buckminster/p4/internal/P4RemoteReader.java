/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.buckminster.core.helpers.FileHandle;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.reader.AbstractRemoteReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.p4.Messages;
import org.eclipse.buckminster.p4.internal.DepotObject.ViewEntry;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;


/**
 * @author Thomas Hallgren
 */
public class P4RemoteReader extends AbstractRemoteReader
{
	private Connection m_connection;
	private final DepotURI m_depotURI;
	private FileSpec.Specifier m_revision;

	public static FileSpec.Specifier getSpecifier(VersionMatch vm, Connection connection, String[] branchName) throws CoreException
	{
		FileSpec.Specifier rev = FileSpec.HEAD;
		VersionSelector branchOrTag = vm.getBranchOrTag();
		if(branchOrTag != null)
		{
			if(branchOrTag.getType() == VersionSelector.BRANCH)
				branchName[0] = branchOrTag.getName();
			else
				rev = new FileSpec.Label(branchOrTag.getName());
		}

		boolean specifierConflict = false;
		long changeNumber = vm.getRevision();
		Date timestamp = vm.getTimestamp();
		if(changeNumber != -1)
		{
			if(rev == FileSpec.HEAD && timestamp == null)
				rev = new FileSpec.ChangeNumber((int)changeNumber);
			else
				specifierConflict = true;
		}
		else if(timestamp != null)
		{
			if(rev == FileSpec.HEAD)
				rev = new FileSpec.Timestamp(timestamp.getTime(), connection.getConnectionInfo().getTimeZone());
			else
				specifierConflict = true;
		}
		
		if(specifierConflict)
			throw new IllegalArgumentException(Messages.tag_timestamp_and_change_number_are_mutually_exclusive);

		return rev;
	}

	public P4RemoteReader(IReaderType readerType, ProviderMatch providerMatch) throws CoreException
	{
		super(readerType, providerMatch);

		VersionMatch vm = providerMatch.getVersionMatch();
		String[] branchNameBin = new String[1];
		m_depotURI = new DepotURI(providerMatch.getRepositoryURI(), branchNameBin[0], providerMatch.getNodeQuery().getProperties());
		m_revision = getSpecifier(vm, getConnection(), branchNameBin);
	}

	public void dropClientEntry(IProgressMonitor monitor)
	throws CoreException
	{
		// Drop this component from the client spec and flush it.
		//
		monitor.beginTask(null, IProgressMonitor.UNKNOWN);
		try
		{
			boolean entryDropped = false;
			IPath depot = getDepotURI().getDepotPath();
			Connection conn = getConnection();
			ClientSpec client = conn.getClientSpec();
			ArrayList<ViewEntry> newSpec = new ArrayList<ViewEntry>();
			for(ViewEntry entry : client.getView())
			{
				if(DepotURI.pathEquals(depot, entry.getDepotPath()))
					entryDropped = true;
				else
					newSpec.add(entry);
			}

			if(entryDropped)
			{
				client.setView(newSpec.toArray(new ViewEntry[newSpec.size()]));
				client.commitChanges();
				conn.exec("flush", new String[] { "..." } ); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		finally
		{
			monitor.done();
		}
	}

	public Connection getConnection() throws BuckminsterException
	{
		if(m_connection == null)
			m_connection = new Connection(m_depotURI);
		return m_connection;
	}

	/**
	 * @return Returns the depot locator.
	 */
	public final DepotURI getDepotURI()
	{
		return m_depotURI;
	}

	public void innerMaterialize(IPath destination, IProgressMonitor monitor) throws CoreException
	{
		// A previous call to the P4ReaderType must have been made to ensure that the client
		// spec is set up correctly.
		//
		File destFile = destination.toFile();
		monitor.beginTask(null, 100);
		try
		{
			FileSpec fileSpec = new FileSpec(destination.append("..."), m_revision); //$NON-NLS-1$
			Connection conn = getConnection();
			boolean success = false;
			try
			{
				conn.exec("sync", new String[] { "-f", fileSpec.toString() } ); //$NON-NLS-1$ //$NON-NLS-2$
				MonitorUtils.worked(monitor, 100);
				success = true;
			}
			finally
			{
				if(!success)
				{
					IProgressMonitor cleanUp = new NullProgressMonitor();
					try
					{
						dropClientEntry(cleanUp);
					}
					catch(CoreException e)
					{
						// Ignore
					}
					FileUtils.deleteRecursive(destFile, cleanUp);
				}
			}
		}
		finally
		{
			monitor.done();
		}
	}

	@Override
	public String toString()
	{
		return m_depotURI.toString();
	}

	@Override
	protected FileHandle innerGetContents(String fileName, IProgressMonitor monitor) throws CoreException, IOException
	{
		// Obtain the client spec before we do anything else. This will ensure that the
		// spec is in sync with our preferences. The spec is cached in the reader type
		// and later used in materialization so there shouldn't be too much overhead.
		//
		((P4ReaderType)getReaderType()).getClient(m_depotURI);

		FileSpec fileSpec = new FileSpec(m_depotURI.getDepotPath().append(fileName), m_revision);
		DepotFile file = getConnection().getFile(fileSpec);
		if(file == null)
			throw new FileNotFoundException(fileName);

		File destFile = createTempFile();
		try
		{
			file.copyTo(destFile);
			FileHandle fh = new FileHandle(fileName, destFile, true);
			destFile = null;
			return fh;
		}
		finally
		{
			if(destFile != null)
				destFile.delete();
		}
	}
}

