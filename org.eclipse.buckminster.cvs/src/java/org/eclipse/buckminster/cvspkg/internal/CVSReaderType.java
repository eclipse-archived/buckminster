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

import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.AbstractReaderType;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionConverter;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionSelectorType;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.team.core.TeamException;
import org.eclipse.team.internal.ccvs.core.CVSException;
import org.eclipse.team.internal.ccvs.core.CVSMessages;
import org.eclipse.team.internal.ccvs.core.CVSProviderPlugin;
import org.eclipse.team.internal.ccvs.core.CVSStatus;
import org.eclipse.team.internal.ccvs.core.CVSTag;
import org.eclipse.team.internal.ccvs.core.CVSTeamProvider;
import org.eclipse.team.internal.ccvs.core.ICVSFolder;
import org.eclipse.team.internal.ccvs.core.ICVSRepositoryLocation;
import org.eclipse.team.internal.ccvs.core.client.Command;
import org.eclipse.team.internal.ccvs.core.client.ConsoleListeners;
import org.eclipse.team.internal.ccvs.core.client.MTHandler;
import org.eclipse.team.internal.ccvs.core.client.ResponseHandler;
import org.eclipse.team.internal.ccvs.core.client.Session;
import org.eclipse.team.internal.ccvs.core.client.listeners.ICommandOutputListener;
import org.eclipse.team.internal.ccvs.core.connection.CVSRepositoryLocation;
import org.eclipse.team.internal.ccvs.core.connection.CVSServerException;
import org.eclipse.team.internal.ccvs.core.util.KnownRepositories;

/**
 * @author thhal
 */
@SuppressWarnings("restriction")
public class CVSReaderType extends AbstractReaderType
{
	public static final String LOCAL_LINE_END = System.getProperty("line.separator");

	// The constructors of Command.LocalOption are not public
	//
	static class MyLocalOption extends Command.LocalOption
	{
		MyLocalOption(String opt)
		{
			super(opt);
		}
	}

	public static final Command.LocalOption STDOUT = new MyLocalOption("-p");

	@Override
	public String convertFetchFactoryLocator(String fetchFactoryLocator, String componentName) throws CoreException
	{
		// The fetchFactoryLocator is in the form <CVSROOT>[,<PASSWORD>[,<PATH>[,<CVSPASSFILE>]]]
		//
		// The team repository expects <CVSROOT>[,<PATH>[,<PROJECT>[,<BRANCH>]]]
		//
		String[] parts = fetchFactoryLocator.split(",");
		if(parts.length < 1)
			throw new BuckminsterException("Illegal fetch factory locator");
		StringBuilder locator = new StringBuilder(parts[0]);

		locator.append(',');
		if(parts.length >= 3 && parts[2].length() > 0)
			locator.append(parts[2]);
		else
			locator.append(componentName);			
		return locator.toString();
	}

	@Override
	public URL convertToURL(String repositoryLocator, IVersionSelector versionSelector) throws CoreException
	{
		try
		{
			CVSSession session = new CVSSession(repositoryLocator);
			ICVSRepositoryLocation location = session.getLocation();
			String fragment = versionSelector == null ? null : versionSelector.toString();
			StringBuilder query = new StringBuilder();
			query.append("repository=");
			query.append(location.getRootDirectory());
			String method = location.getMethod().getName();
			if(!method.equals("pserver"))
			{
				query.append(",method=");
				query.append(method);
			}

			String user = location.getUsername();
			if("anonymous".equals(user))
				user = null;

			IPath modulePath = new Path(session.getModuleName()).makeAbsolute();
			URI uri = new URI("cvs", user, location.getHost(), -1, modulePath.toPortableString(), query.toString(), fragment);
			return uri.toURL();
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public static CVSRepositoryLocation getLocationFromString(String repo) throws CVSException
	{
		CVSRepositoryLocation wanted = CVSRepositoryLocation.fromString(repo);
		String wantedUser = wanted.getUsername();
		for(ICVSRepositoryLocation known : CVSProviderPlugin.getPlugin().getKnownRepositories())
		{
			if(known.getHost().equals(wanted.getHost()) && known.getPort() == wanted.getPort()
					&& known.getRootDirectory().equals(wanted.getRootDirectory()))
			{
				String knownMethod = known.getMethod().getName();
				String wantedMethod = wanted.getMethod().getName();
				if(knownMethod.equals(wantedMethod) || ("extssh".equals(knownMethod) && "pserver".equals(wantedMethod)))
				{
					if(wantedUser == null || "anonymous".equals(wantedUser) || wantedUser.equals(known.getUsername()))
						return (CVSRepositoryLocation)known;
				}
			}
		}
		KnownRepositories.getInstance().addRepository(wanted, true);
		return wanted;
	}

	@Override
	public void prepareMaterialization(List<Materialization> mtr, RMContext context, IProgressMonitor monitor)
			throws CoreException
	{
		// FIXME: Guarantee that all destinations has at least two segments
		// (parent directory and module)
	}

	/**
	 * Executes a request and processes the responses.
	 * 
	 * @param session
	 *            the open CVS session
	 * @param listener
	 *            the command output listener, or null to discard all messages
	 * @param monitor
	 *            the progress monitor
	 * @return a status code indicating success or failure of the operation
	 */
	public static IStatus executeRequest(Session session, String requestId, final Writer out, final Writer err,
			IProgressMonitor monitor) throws CVSException
	{
		return executeRequest(session, requestId, new ICommandOutputListener()
		{
			public IStatus messageLine(String line, ICVSRepositoryLocation loc, ICVSFolder cRoot, IProgressMonitor m)
			{
				return writeLine(line, out);
			}

			public IStatus errorLine(String line, ICVSRepositoryLocation loc, ICVSFolder cRoot, IProgressMonitor m)
			{
				return writeLine(line, err);
			}
		}, monitor);

	}

	/**
	 * Executes a request and processes the responses.
	 * 
	 * @param session
	 *            the open CVS session
	 * @param listener
	 *            the command output listener, or null to discard all messages
	 * @param monitor
	 *            the progress monitor
	 * @return a status code indicating success or failure of the operation
	 */
	public static IStatus executeRequest(Session session, String requestId, ICommandOutputListener listener,
			IProgressMonitor monitor) throws CVSException
	{
		// send request
		//
		session.sendRequest(requestId);

		// This number can be tweaked if the monitor is judged to move too
		// quickly or too slowly. After some experimentation this is a good
		// number for both large projects (it doesn't move so quickly as to
		// give a false sense of speed) and smaller projects (it actually does
		// move some rather than remaining still and then jumping to 100).
		//
		final int TOTAL_WORK = 3000;
		monitor.beginTask(CVSMessages.Command_receivingResponses, TOTAL_WORK);
		monitor.subTask(CVSMessages.Command_receivingResponses);
		int halfWay = TOTAL_WORK / 2;
		int currentIncrement = 4;
		int nextProgress = currentIncrement;
		int worked = 0;

		// If the session is connected to a CVSNT server (1.11.1.1), we'll need
		// to do some special handling for
		// some errors. Unfortunately, CVSNT 1.11.1.1 will drop the connection
		// after so some functionality is
		// still effected
		//
		boolean isCVSNT = session.isCVSNT();

		session.clearErrors();
		for(;;)
		{
			// update monitor work amount
			if(--nextProgress <= 0)
			{
				MonitorUtils.worked(monitor, 1);
				worked++;
				if(worked >= halfWay)
				{
					// we have passed the current halfway point, so double the
					// increment and reset the halfway point.
					//
					currentIncrement *= 2;
					halfWay += (TOTAL_WORK - halfWay) / 2;
				}
				// reset the progress counter to another full increment
				//
				nextProgress = currentIncrement;
			}

			// retrieve a response line
			//
			String response = session.readLine();
			int spacePos = response.indexOf(' ');
			String argument;
			if(spacePos != -1)
			{
				argument = response.substring(spacePos + 1);
				response = response.substring(0, spacePos);
			}
			else
				argument = ""; //$NON-NLS-1$

			// handle completion responses
			//
			if(response.equals("ok"))
				break;

			if(response.equals("error") || (isCVSNT && response.equals("")))
			{
				argument = argument.trim();
				boolean serious = false;
				if(argument.length() == 0)
				{
					argument = requestId;
				}
				else
				{
					argument = NLS.bind(CVSMessages.Command_seriousServerError, new String[] { argument });
					if(!session.hasErrors())
						session.addError(new CVSStatus(IStatus.ERROR, CVSStatus.SERVER_ERROR, argument, (Throwable)null));
					serious = true;
				}

				if(!session.hasErrors())
				{
					session.addError(new CVSStatus(IStatus.ERROR, CVSStatus.SERVER_ERROR,
							CVSMessages.Command_noMoreInfoAvailable, (Throwable)null));
				}
				IStatus status = new MultiStatus(CVSProviderPlugin.ID, CVSStatus.SERVER_ERROR, session.getErrors(),
						argument, null);
				if(serious)
					throw new CVSServerException(status);

				// look for particularly bad errors in the accumulated
				// statii
				IStatus[] errors = session.getErrors();
				for(int i = 0; i < errors.length; i++)
				{
					IStatus s = errors[i];
					if(s.getCode() == CVSStatus.PROTOCOL_ERROR)
					{
						throw new CVSServerException(status);
					}
				}
				return status;
			}

			if(response.equals("MT"))
			{
				// Handle the MT response
				//
				MTHandler handler = (MTHandler)session.getResponseHandler(response);
				if(handler == null)
					throw new CVSException(new org.eclipse.core.runtime.Status(IStatus.ERROR, CVSProviderPlugin.ID,
							TeamException.IO_FAILED, NLS.bind(CVSMessages.Command_unsupportedResponse, new String[] {
									response, argument }), null));
				handler.handle(session, argument, monitor);

				// If a line is available, pass it on to the message listener
				// and console as if it were an M response
				//
				if(handler.isLineAvailable())
				{
					String line = handler.getLine();
					IStatus status = listener.messageLine(line, session.getCVSRepositoryLocation(), session
							.getLocalRoot(), monitor);
					session.addError(status); // The session ignores OK status
					ConsoleListeners.getInstance().messageLineReceived(session, line, status);

				}
				continue;
			}

			if(response.equals("M"))
			{
				IStatus status = listener.messageLine(argument, session.getCVSRepositoryLocation(), session
						.getLocalRoot(), monitor);
				session.addError(status); // The session ignores OK status
				ConsoleListeners.getInstance().messageLineReceived(session, argument, status);
				continue;
			}

			if(response.equals("E"))
			{
				IStatus status = listener.errorLine(argument, session.getCVSRepositoryLocation(), session
						.getLocalRoot(), monitor);
				session.addError(status); // The session ignores OK status
				ConsoleListeners.getInstance().errorLineReceived(session, argument, status);
				continue;
			}

			// handle other responses
			//
			ResponseHandler handler = session.getResponseHandler(response);
			if(handler == null)
				throw new CVSException(new Status(IStatus.ERROR, CVSProviderPlugin.ID, TeamException.IO_FAILED, NLS
						.bind(CVSMessages.Command_unsupportedResponse, new String[] { response, argument }), null));
			handler.handle(session, argument, monitor);
		}
		if(!session.hasErrors())
			return ICommandOutputListener.OK;

		return new MultiStatus(CVSProviderPlugin.ID, IStatus.INFO, session.getErrors(), NLS.bind(
				CVSMessages.Command_warnings, new String[] { requestId }), null);
	}

	@Override
	public Date getLastModification(String repositoryLocation, IVersionSelector versionSelector, IProgressMonitor monitor)
	throws CoreException
	{
		CVSSession session = null;
		try
		{
			session = new CVSSession(repositoryLocation);
			RepositoryMetaData metaData = RepositoryMetaData.getMetaData(session, getCVSTag(versionSelector, null, null), monitor);
			return metaData.getLastModification();
		}
		finally
		{
			if(session != null)
				session.close();
		}
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, NodeQuery nodeQuery, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new VersionFinder(provider, nodeQuery);
	}

	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new CVSReader(this, providerMatch);
	}

	private static IStatus writeLine(String line, Writer out)
	{
		try
		{
			out.write(line);
			out.write(LOCAL_LINE_END);
			return ICommandOutputListener.OK;
		}
		catch(IOException e)
		{
			return new Status(IStatus.ERROR, CVSProviderPlugin.ID, TeamException.IO_FAILED, e.getMessage(), e);
		}
	}

	@Override
	public void shareProject(IProject project, Resolution cr, RMContext context, IProgressMonitor monitor) throws CoreException
	{
		// Register the project with the CVSTeamProvider.
		//
		String cvsTypeID = CVSProviderPlugin.getTypeId();
		RepositoryProvider.map(project, cvsTypeID);
		((CVSTeamProvider)RepositoryProvider.getProvider(project, cvsTypeID)).setWatchEditEnabled(CVSProviderPlugin
				.getPlugin().isWatchEditEnabled());
	}

	static CVSTag getCVSTag(IVersionSelector versionSelector, IVersion version, IVersionConverter vc) throws CoreException
	{
		CVSTag tag = CVSTag.DEFAULT;
		if(versionSelector == null)
			return tag;
	
		switch(versionSelector.getType())
		{
		case TAG:
			tag = new CVSTag(versionSelector.getQualifier(), CVSTag.VERSION);
			break;
	
		case TIMESTAMP:
			tag = new CVSTag(new Date(versionSelector.getNumericQualifier()));
	
			// Fall through to LATEST
		case LATEST:
			if(!(version == null || version.isDefault() || vc == null))
				versionSelector = vc.createSelector(version);

			if(versionSelector.getType() == VersionSelectorType.LATEST && !versionSelector.isDefaultBranch())
				//
				// Pick branch from the version
				//
				tag = new CVSTag(versionSelector.getBranchName(), CVSTag.BRANCH);
			break;

		default:
			throw new BuckminsterException("CVSReader cannot understand fixed version selector " + versionSelector);
		}
		return tag;
	}
}
