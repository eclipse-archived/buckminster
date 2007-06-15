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

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.reader.AbstractRemoteReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.p4.internal.DepotObject.ViewEntry;
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
	private FileSpec.Revision m_revision;

	public P4RemoteReader(IReaderType readerType, ProviderMatch providerMatch) throws CoreException
	{
		super(readerType, providerMatch);

		IVersionSelector vs = providerMatch.getVersionMatch().getFixedVersionSelector();
		switch(vs.getType())
		{
		case TAG:
			m_revision = new FileSpec.Label(vs.getQualifier());
			break;
		case CHANGE_NUMBER:
			m_revision = new FileSpec.ChangeNumber(Integer.parseInt(vs.getQualifier()));
			break;
		case TIMESTAMP:
			m_revision = new FileSpec.Timestamp(Long.parseLong(vs.getQualifier()), getConnection().getConnectionInfo().getTimeZone());
			break;
		default:
			m_revision = FileSpec.HEAD;
		}
		m_depotURI = new DepotURI(providerMatch.getRepositoryURI(), vs.getBranchName(), providerMatch.getNodeQuery().getProperties());
	}

	public boolean canMaterialize() throws BuckminsterException
	{
		return true;
	}

	@Override
	public void close()
	{
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
				conn.exec("flush", new String[] { "..." } );
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
			FileSpec fileSpec = new FileSpec(destination.append("..."), m_revision);
			Connection conn = getConnection();
			boolean success = false;
			try
			{
				conn.exec("sync", new String[] { "-f", fileSpec.toString() } );
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
	protected File innerGetContents(String fileName, boolean[] isTemporary, IProgressMonitor monitor) throws CoreException, IOException
	{
		// FIXME: Monitor stuff
		//
		
		// Obtain the client spec before we do anything else. This will ensure that the
		// spec is in sync with our preferences. The spec is cached in the reader type
		// and later used in materialization so there shouldn't be too much overhead.
		//
		((P4ReaderType)getReaderType()).getClient(m_depotURI);

		DepotFile file = getConnection().getFile(m_depotURI.getDepotPath().append(fileName).toPortableString());
		if(file == null)
			throw new FileNotFoundException(fileName);

		File destFile = createTempFile();
		try
		{
			file.copyTo(destFile);
			isTemporary[0] = true;
			return destFile;
		}
		finally
		{
			if(!isTemporary[0])
				destFile.delete();
		}
	}
}

