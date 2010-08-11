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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.CatalogReaderType;
import org.eclipse.buckminster.core.reader.ITeamReaderType;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.reader.ReferenceInfo;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.cvspkg.Messages;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.util.IComponentReader;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.team.internal.ccvs.core.CVSException;
import org.eclipse.team.internal.ccvs.core.CVSProviderPlugin;
import org.eclipse.team.internal.ccvs.core.CVSTag;
import org.eclipse.team.internal.ccvs.core.CVSTeamProvider;
import org.eclipse.team.internal.ccvs.core.ICVSRemoteResource;
import org.eclipse.team.internal.ccvs.core.ICVSRepositoryLocation;
import org.eclipse.team.internal.ccvs.core.client.Command;
import org.eclipse.team.internal.ccvs.core.client.Session;
import org.eclipse.team.internal.ccvs.core.connection.CVSRepositoryLocation;
import org.eclipse.team.internal.ccvs.core.resources.CVSWorkspaceRoot;
import org.eclipse.team.internal.ccvs.core.syncinfo.ResourceSyncInfo;
import org.eclipse.team.internal.ccvs.core.util.KnownRepositories;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class CVSReaderType extends CatalogReaderType implements ITeamReaderType {
	// The constructors of Command.LocalOption are not public
	//
	static class MyLocalOption extends Command.LocalOption {
		MyLocalOption(String opt) {
			super(opt);
		}
	}

	public static final String LOCAL_LINE_END = System.getProperty("line.separator"); //$NON-NLS-1$

	public static final Command.LocalOption STDOUT = new MyLocalOption("-p"); //$NON-NLS-1$

	public static ICVSRepositoryLocation getDemotedLocation(ICVSRepositoryLocation known) throws CVSException {
		String knownMethod = known.getMethod().getName();
		for (ICVSRepositoryLocation wanted : CVSProviderPlugin.getPlugin().getKnownRepositories()) {
			if (known.getHost().equals(wanted.getHost()) && known.getPort() == wanted.getPort()
					&& known.getRootDirectory().equals(wanted.getRootDirectory())) {
				String wantedMethod = wanted.getMethod().getName();
				String wantedUser = wanted.getUsername();
				if (knownMethod.equals(wantedMethod) || ("extssh".equals(knownMethod) && "pserver".equals(wantedMethod))) //$NON-NLS-1$ //$NON-NLS-2$
				{
					if (wantedUser == null || "anonymous".equals(wantedUser) || wantedUser.equals(known.getUsername())) //$NON-NLS-1$
						return wanted;
				}
			}
		}
		return known;
	}

	public static CVSRepositoryLocation getLocationFromString(String repo) throws CVSException {
		CVSRepositoryLocation wanted = CVSRepositoryLocation.fromString(repo);
		String wantedUser = wanted.getUsername();
		for (ICVSRepositoryLocation known : CVSProviderPlugin.getPlugin().getKnownRepositories()) {
			if (known.getHost().equals(wanted.getHost()) && known.getPort() == wanted.getPort()
					&& known.getRootDirectory().equals(wanted.getRootDirectory())) {
				String knownMethod = known.getMethod().getName();
				String wantedMethod = wanted.getMethod().getName();
				if (knownMethod.equals(wantedMethod) || ("extssh".equals(knownMethod) && "pserver".equals(wantedMethod))) //$NON-NLS-1$ //$NON-NLS-2$
				{
					if (wantedUser == null || "anonymous".equals(wantedUser) || wantedUser.equals(known.getUsername())) //$NON-NLS-1$
						return (CVSRepositoryLocation) known;
				}
			}
		}
		KnownRepositories.getInstance().addRepository(wanted, true);
		return wanted;
	}

	static CVSTag getCVSTag(VersionMatch match) throws CoreException {
		CVSTag tag;
		VersionSelector selector = match.getBranchOrTag();
		Date timestamp;
		if (selector == null && (timestamp = match.getTimestamp()) != null)
			tag = new CVSTag(timestamp);
		else
			tag = getCVSTag(selector);
		return tag;
	}

	static CVSTag getCVSTag(VersionSelector selector) throws CoreException {
		CVSTag tag;
		if (selector == null)
			tag = CVSTag.DEFAULT;
		else
			tag = new CVSTag(selector.getName(), selector.getType() == VersionSelector.TAG ? CVSTag.VERSION : CVSTag.BRANCH);
		return tag;
	}

	@Override
	public String convertFetchFactoryLocator(Map<String, String> fetchFactoryLocator, String componentName) throws CoreException {
		String cvsRoot = fetchFactoryLocator.get("cvsRoot"); //$NON-NLS-1$
		if (cvsRoot == null)
			throw BuckminsterException.fromMessage(Messages.illegal_fetch_factory_locator);

		StringBuilder locator = new StringBuilder(cvsRoot);
		String path = fetchFactoryLocator.get("path"); //$NON-NLS-1$
		locator.append(',');
		if (path != null)
			locator.append(path);
		else
			locator.append(componentName);
		return locator.toString();
	}

	@Override
	public URL convertToURL(String repositoryLocator, VersionMatch versionMatch) throws CoreException {
		try {
			VersionSelector versionSelector = versionMatch.getBranchOrTag();
			CVSSession session = new CVSSession(repositoryLocator);
			ICVSRepositoryLocation location = session.getLocation();

			StringBuilder query = new StringBuilder();
			String method = location.getMethod().getName();
			if (!method.equals("pserver")) //$NON-NLS-1$
			{
				query.append("method="); //$NON-NLS-1$
				query.append(method);
			}
			if (versionSelector != null) {
				if (query.length() > 0)
					query.append('&');
				query.append("version"); //$NON-NLS-1$
				query.append(versionSelector.toString());
			}

			String user = location.getUsername();
			if ("anonymous".equals(user)) //$NON-NLS-1$
				user = null;

			IPath modulePath = new Path(session.getModuleName()).makeAbsolute();
			URI uri = new URI("cvs", user, location.getHost(), -1, location.getRootDirectory(), query.toString(), //$NON-NLS-1$
					modulePath.toPortableString());
			return uri.toURL();
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	public ReferenceInfo extractReferenceInfo(String reference) throws CoreException {
		StringTokenizer tokenizer = new StringTokenizer(reference, ","); //$NON-NLS-1$
		String version = tokenizer.nextToken();
		// If this is a newer version, then ignore it
		if (!version.equals("1.0")) //$NON-NLS-1$
			throw BuckminsterException.fromMessage("The cvs reader only understands version PSF project references of version 1.0"); //$NON-NLS-1$

		String repositoryLocation = tokenizer.nextToken();
		String module = tokenizer.nextToken();
		String projectName = tokenizer.nextToken();
		VersionSelector selector = null;
		if (tokenizer.hasMoreElements()) {
			String branchInfo = tokenizer.nextToken();
			if (!(branchInfo.length() == 0 || CVSTag.DEFAULT.toString().equals(branchInfo)))
				selector = VersionSelector.branch(branchInfo);
		}
		return new ReferenceInfo(repositoryLocation + ',' + module, selector, projectName);
	}

	@Override
	public URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException {
		return null;
	}

	@Override
	public Date getLastModification(File workingCopy, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, 30);
		File[] folders = workingCopy.listFiles();
		MonitorUtils.worked(monitor, 10);
		if (folders == null) {
			// Folder was not a folder after all
			//
			monitor.done();
			return null;
		}

		Date youngest = null;
		if (folders.length > 0) {
			IProgressMonitor subMon = MonitorUtils.subMonitor(monitor, 10);
			subMon.beginTask(null, folders.length * 10);
			for (File subFolder : folders) {
				Date ts = getLastModification(subFolder, MonitorUtils.subMonitor(subMon, 10));
				if (ts != null && (youngest == null || ts.compareTo(youngest) > 0))
					youngest = ts;
			}
			subMon.done();
		} else
			MonitorUtils.worked(monitor, 10);

		File entries = new File(new File(workingCopy, FileSystemCopier.CVS_DIRNAME), FileSystemCopier.ENTRIES);
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(entries));
			String line;
			while ((line = input.readLine()) != null) {
				try {
					ResourceSyncInfo info = new ResourceSyncInfo(line, null);
					Date ts = info.getTimeStamp();
					if (ts != null && (youngest == null || ts.compareTo(youngest) > 0))
						youngest = ts;
				} catch (CVSException e) {
				}
			}
			MonitorUtils.worked(monitor, 10);
		} catch (FileNotFoundException e) {
			// No Entries file present in this folder
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			IOUtils.close(input);
			monitor.done();
		}
		return youngest;
	}

	@Override
	public Date getLastModification(String repositoryLocation, VersionSelector versionSelector, IProgressMonitor monitor) throws CoreException {
		CVSSession session = null;
		try {
			session = new CVSSession(repositoryLocation);
			RepositoryMetaData metaData = RepositoryMetaData.getMetaData(session, getCVSTag(versionSelector), monitor);
			return metaData.getLastModification();
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException {
		MonitorUtils.complete(monitor);
		return new CVSReader(this, providerMatch);
	}

	@Override
	public String getRemoteLocation(File workingCopy, IProgressMonitor monitor) throws CoreException {
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
		IPath location = Path.fromOSString(workingCopy.toString());
		IResource resource = wsRoot.getContainerForLocation(location);
		if (resource == null)
			resource = wsRoot.getFileForLocation(location);

		if (resource == null)
			//
			// We only support workspace resources at this time
			//
			return null;

		ICVSRemoteResource cvsResource = CVSWorkspaceRoot.getRemoteResourceFor(resource);
		if (cvsResource == null)
			return null;

		return cvsResource.getRepository().getLocation(false) + ',' + cvsResource.getRepositoryRelativePath();
	}

	@Override
	public String getSourceReference(IResource resource, IProgressMonitor monitor) throws CoreException {
		ICVSRemoteResource cvsResource = CVSWorkspaceRoot.getRemoteResourceFor(resource);
		if (cvsResource == null)
			return null;

		String tag = null;
		ResourceSyncInfo syncInfo = cvsResource.getSyncInfo();
		if (syncInfo != null) {
			CVSTag cvsTag = syncInfo.getTag();
			if (cvsTag != null)
				tag = cvsTag.getName();
		}

		ICVSRepositoryLocation repo = cvsResource.getRepository();
		repo = getDemotedLocation(repo);
		return asReference(repo.getLocation(false), cvsResource.getRepositoryRelativePath(), resource.getProject().getName(), tag);
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery, IProgressMonitor monitor)
			throws CoreException {
		MonitorUtils.complete(monitor);
		return new VersionFinder(provider, ctype, nodeQuery);
	}

	@Override
	public void shareProject(IProject project, Resolution cr, RMContext context, IProgressMonitor monitor) throws CoreException {
		// Register the project with the CVSTeamProvider.
		//
		String cvsTypeID = CVSProviderPlugin.getTypeId();
		RepositoryProvider.map(project, cvsTypeID);
		((CVSTeamProvider) RepositoryProvider.getProvider(project, cvsTypeID))
				.setWatchEditEnabled(CVSProviderPlugin.getPlugin().isWatchEditEnabled());
	}

	@Override
	public IStatus tag(RepositoryProvider provider, IResource[] resources, Map<String, String> mappings, String tag, boolean recurse,
			IProgressMonitor progress) throws CVSException {
		CVSWorkspaceRoot root = ((CVSTeamProvider) provider).getCVSWorkspaceRoot();
		String mappedLocationString = mapLocation(mappings, root.getRemoteLocation());
		KnownRepositories knownRepositories = KnownRepositories.getInstance();
		boolean isUnknown = !knownRepositories.isKnownRepository(mappedLocationString);
		ICVSRepositoryLocation mappedLocation = knownRepositories.getRepository(mappedLocationString);

		Command.LocalOption[] commandOptions = Command.NO_LOCAL_OPTIONS;

		if (!recurse)
			commandOptions = Command.DO_NOT_RECURSE.addTo(commandOptions);

		// Build the argument list
		String[] arguments = buildResourceArguments(resources);

		// Execute the command
		progress.beginTask(null, 100);
		try {
			// if the repository is unknown, add it to the list of known
			// repositories list temporarily
			if (isUnknown)
				knownRepositories.addRepository(mappedLocation, false);

			Session session = new Session(mappedLocation, root.getLocalRoot());
			// Opening the session takes 20% of the time
			session.open(MonitorUtils.subMonitor(progress, 20), true);
			try {
				return Command.TAG.execute(session, Command.NO_GLOBAL_OPTIONS, commandOptions, new CVSTag(tag, CVSTag.VERSION), arguments, null,
						MonitorUtils.subMonitor(progress, 80));
			} finally {
				session.close();
			}
		} finally {
			try {
				// remove the repository from the list of known repositories if
				// it was not there before
				if (isUnknown)
					knownRepositories.disposeRepository(mappedLocation);
			} finally {
				progress.done();
			}
		}
	}

	/**
	 * Return the arguments to be passed to the tag command
	 */
	protected String[] buildResourceArguments(IResource[] resources) throws CVSException {
		String[] arguments = new String[resources.length];

		for (int i = 0; i < resources.length; ++i) {
			IPath cvsPath = resources[i].getFullPath().removeFirstSegments(1);
			arguments[i] = (cvsPath.segmentCount() == 0) ? Session.CURRENT_LOCAL_FOLDER : cvsPath.toString();
		}

		return arguments;
	}

	/**
	 * Return the repository location string resulting from the re-mapping of
	 * the given <code>originalLocation</code> according to the given
	 * <code>mappings</code>.
	 */
	protected String mapLocation(Map<String, String> mappings, ICVSRepositoryLocation originalLocation) throws CVSException {
		Properties locationConfiguration = new Properties();

		// the location mapping key string (which is expected to be a textual
		// representation of a repository location) should not contain any user
		// name or password part so we need to create a copy of the original
		// location to ensure that these fields are not present in its string
		// representation which we are going to use to lookup the mapping
		locationConfiguration.put("connection", originalLocation.getMethod().getName()); //$NON-NLS-1$
		locationConfiguration.put("host", originalLocation.getHost()); //$NON-NLS-1$
		locationConfiguration.put("port", Integer.toString(originalLocation.getPort())); //$NON-NLS-1$
		locationConfiguration.put("root", originalLocation.getRootDirectory()); //$NON-NLS-1$
		locationConfiguration.put("ecoding", originalLocation.getEncoding()); //$NON-NLS-1$

		// we need to set the user to an empty string to prevent an NPE in
		// CVSRepositoryLocation.fromProperties()
		locationConfiguration.put("user", ""); //$NON-NLS-1$ //$NON-NLS-2$

		String locationString = CVSRepositoryLocation.fromProperties(locationConfiguration).getLocation(false);

		locationString = mappings.get(locationString);
		if (locationString == null)
			return originalLocation.getLocation(false);

		return locationString;
	}

	/**
	 * Creates an SCMURL reference to the associated source.
	 * 
	 * @param repoLocation
	 * @param module
	 * @param projectName
	 * @return project reference string or <code>null</code> if none
	 */
	private String asReference(String repoLocation, String module, String projectName, String tagName) {
		// parse protocol, host, repository root from repoLocation
		String protocol = null;
		String host = null;
		String root = null;

		int at = repoLocation.indexOf('@');
		if (at < 0) {
			// should be a local protocol
			if (repoLocation.startsWith(":local:")) { //$NON-NLS-1$
				protocol = "local"; //$NON-NLS-1$
				root = repoLocation.substring(7);
			}
		} else if (at < (repoLocation.length() - 2)) {
			String serverRoot = repoLocation.substring(at + 1);
			String protocolUserPass = repoLocation.substring(0, at);
			int colon = serverRoot.indexOf(':');
			if (colon > 0) {
				host = serverRoot.substring(0, colon);
				if (colon < (serverRoot.length() - 2)) {
					root = serverRoot.substring(colon + 1);
				}
				if (protocolUserPass.startsWith(":")) { //$NON-NLS-1$
					colon = protocolUserPass.indexOf(':', 1);
					if (colon > 0) {
						protocol = protocolUserPass.substring(1, colon);
					}
				} else {
					// missing protocol, assume p-server
					protocol = "pserver"; //$NON-NLS-1$
				}
			}
		}

		if (protocol == null || root == null) {
			return null; // invalid syntax
		}

		// use '|' as separator if the root location uses a colon for a Windows
		// path
		String sep = ":"; //$NON-NLS-1$
		if (root.indexOf(':') >= 0) {
			sep = "|"; //$NON-NLS-1$
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("scm:cvs"); //$NON-NLS-1$
		buffer.append(sep);
		buffer.append(protocol);
		buffer.append(sep);
		if (host != null) {
			buffer.append(host);
			buffer.append(sep);
		}
		buffer.append(root);
		buffer.append(sep);
		buffer.append(module);

		Path modulePath = new Path(module);
		if (!modulePath.lastSegment().equals(projectName)) {
			buffer.append(";project=\""); //$NON-NLS-1$
			buffer.append(projectName);
			buffer.append('"');
		}

		if (tagName != null && !tagName.equals("HEAD")) { //$NON-NLS-1$
			buffer.append(";tag="); //$NON-NLS-1$
			buffer.append(tagName);
		}
		return buffer.toString();
	}
}
