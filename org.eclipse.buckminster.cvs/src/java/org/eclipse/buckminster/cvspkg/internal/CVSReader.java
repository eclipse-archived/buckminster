/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cvspkg.internal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.reader.AbstractRemoteReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionConverter;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionSelectorType;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.team.internal.ccvs.core.CVSTag;
import org.eclipse.team.internal.ccvs.core.ICVSFolder;
import org.eclipse.team.internal.ccvs.core.client.Session;
import org.eclipse.team.internal.ccvs.core.client.listeners.ICommandOutputListener;
import org.eclipse.team.internal.ccvs.core.connection.CVSRepositoryLocation;
import org.eclipse.team.internal.ccvs.core.resources.RemoteFolder;
import org.eclipse.team.internal.ccvs.core.resources.UpdateContentCachingService;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class CVSReader extends AbstractRemoteReader
{
	private final CVSTag m_fixed;

	private final CVSSession m_session;

	private RepositoryMetaData m_metaData;

	public CVSReader(IReaderType readerType, ProviderMatch rInfo) throws CoreException
	{
		super(readerType, rInfo);
		m_session = new CVSSession(rInfo.getRepositoryURI());

		VersionMatch match = rInfo.getVersionMatch();
		IVersionSelector fixedVS = match.getFixedVersionSelector();

		CVSTag fixed = CVSTag.DEFAULT;
		Date fixedDate = null;

		switch(fixedVS.getType())
		{
		case TAG:
			fixed = new CVSTag(fixedVS.getQualifier(), CVSTag.VERSION);
			break;

		case TIMESTAMP:
			fixedDate = new Date(fixedVS.getNumericQualifier());
			fixed = new CVSTag(fixedDate);

			// Fall through to LATEST
		case LATEST:
			IVersion version = match.getVersion();
			if(version.isDefault())
				break;

			IVersionConverter vc = rInfo.getVersionConverter();
			if(vc == null)
				break;

			IVersionSelector vs = vc.createSelector(version);
			if(vs.getType() == VersionSelectorType.LATEST && !vs.isDefaultBranch())
				//
				// Pick branch from the version
				//
				fixed = new CVSTag(vs.getBranchName(), CVSTag.BRANCH);
			break;

		default:
			throw new BuckminsterException("CVSReader cannot understand fixed version selector " + fixedVS);
		}
		m_fixed = fixed;
	}

	@Override
	public void close()
	{
		m_session.close();
	}

	public boolean canMaterialize()
	{
		return true;
	}

	public void innerMaterialize(IPath destination, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 100);

		CVSRepositoryLocation cvsLocation = (CVSRepositoryLocation)m_session.getLocation();

		// We avoid using the date if it is the last date known for
		// the repository.
		//
		CVSTag tag = null;
		if(m_fixed.getType() == CVSTag.DATE)
		{
			if(getMetaData(MonitorUtils.subMonitor(monitor, 20)).getLastModification().compareTo(
				m_fixed.asDate()) > 0)
				tag = m_fixed;
		}
		else
		{
			tag = m_fixed;
			MonitorUtils.worked(monitor, 20);
		}

		MonitorUtils.testCancelStatus(monitor);
		ICVSFolder root = new RemoteFolder(null, cvsLocation, m_session.getModuleName(), tag);
		ICVSFolder folder = UpdateContentCachingService.buildRemoteTree(cvsLocation, root, tag,
			IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor, 90));
		FileSystemCopier copier = new FileSystemCopier(folder, destination, MonitorUtils.subMonitor(monitor,
			80));
		try
		{
			folder.accept(copier, true);
		}
		finally
		{
			copier.done();
			monitor.done();
		}
	}

	@Override
	public String toString()
	{
		return m_session.getRepository() + ',' + m_fixed.getName();
	}

	@Override
	protected File innerGetContents(String fileName, boolean[] isTemporary, IProgressMonitor monitor)
	throws CoreException,
		IOException
	{
		// Build the local options
		//
		monitor.beginTask(null, 2000);
		monitor.subTask("Retrieving " + fileName);
		Session session = m_session.getReaderSession(new SubProgressMonitor(monitor, 800,
			SubProgressMonitor.SUPPRESS_SUBTASK_LABEL));

		File tempFile = this.createTempFile();
		Writer out = new BufferedWriter(new FileWriter(tempFile));
		try
		{
			RepositoryMetaData metaData = getMetaData(MonitorUtils.subMonitor(monitor, 200));
			if(!metaData.hasMetaEntry(fileName))
				//
				// The repository has never seen this file
				//
				throw new FileNotFoundException(fileName);

			CVSTag tag = m_fixed;
			if(tag.getType() == CVSTag.DATE && metaData.getLastModification().compareTo(tag.asDate()) <= 0)
				tag = null;

			StringWriter err = new StringWriter();

			// Append correct tag unless it's CVSTag.DEFAULT
			//
			if(tag != null)
			{
				switch(tag.getType())
				{
				case CVSTag.DATE:
					session.sendArgument("-D");
					session.sendArgument(tag.getName());
					break;
				default:
					session.sendArgument("-r");
					session.sendArgument(tag.getName());
					break;
				}
			}
			session.sendArgument("-p");

			String fullName = m_session.getModuleName() + '/' + fileName;
			session.sendArgument(fullName);

			IStatus outcome = CVSReaderType.executeRequest(session, "co", out, err, new SubProgressMonitor(
				monitor, 1000, SubProgressMonitor.SUPPRESS_SUBTASK_LABEL));
			if(ICommandOutputListener.OK != outcome)
			{
				// All serious errors yield an exception. Not finding a file is not
				// serious according to CVS. It is to us though and that's what we think
				// happened here.
				//
				StringBuffer errBuff = err.getBuffer();

				// Strip trailing whitespace
				//
				int last = errBuff.length() - 1;
				while(last >= 0 && Character.isWhitespace(errBuff.charAt(last)))
					--last;
				++last;

				throw new FileNotFoundException(errBuff.substring(0, last));
			}
			out.close();
			out = null;

			if(tempFile.length() == 0)
			{
				// Empty file. That's suspicious since a CVS co -p will give us that
				// for files that are in the attic.
				//
				if(metaData.seenInAttic(fileName))
				{
					tempFile.delete();
					//
					// Assume that this version is indeed in the attic.
					//
					throw new FileNotFoundException(fileName);
				}
			}
			isTemporary[0] = true;
			return tempFile;
		}
		finally
		{
			IOUtils.close(out);
			monitor.done();
		}
	}

	private synchronized RepositoryMetaData getMetaData(IProgressMonitor monitor) throws CoreException
	{
		if(m_metaData == null)
		{
			CVSTag tag = (m_fixed.getType() == CVSTag.DATE) ? null : m_fixed;
			m_metaData = RepositoryMetaData.getMetaData(m_session, tag, monitor);
		}
		else
			MonitorUtils.complete(monitor);
		return m_metaData;
	}
}
