/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cvspkg;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.rmap.model.BidirectionalTransformer;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.VersionConverterDesc;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.cvspkg.internal.CVSSession;
import org.eclipse.buckminster.cvspkg.internal.FileSystemCopier;
import org.eclipse.buckminster.runtime.BuckminsterPreferences;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.team.internal.ccvs.core.CVSProviderPlugin;
import org.eclipse.team.internal.ccvs.core.CVSTag;
import org.eclipse.team.internal.ccvs.core.ICVSFolder;
import org.eclipse.team.internal.ccvs.core.ICVSRemoteFile;
import org.eclipse.team.internal.ccvs.core.ICVSRemoteFolder;
import org.eclipse.team.internal.ccvs.core.ICVSRepositoryLocation;
import org.eclipse.team.internal.ccvs.core.connection.CVSRepositoryLocation;
import org.eclipse.team.internal.ccvs.core.connection.CVSServerException;
import org.eclipse.team.internal.ccvs.core.resources.RemoteFolder;
import org.eclipse.team.internal.ccvs.core.resources.RemoteFolderSandbox;
import org.eclipse.team.internal.ccvs.core.resources.UpdateContentCachingService;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class CVSTest extends TestCase
{
	private static String DEAD_FILE = "LICENSE.cvsclient.txt";

	private static String EXISTING_FILE = "META-INF/MANIFEST.MF";

	private static String NON_EXISTING_FILE = "foobar.txt";

	private static String REPO_LOCATION = ":pserver:anonymous@dev.eclipse.org:/cvsroot/technology,org.eclipse.dash/org.eclipse.dash.siteassembler";
	private CVSSession m_session;

	@Override
	public void setUp() throws Exception
	{
		BuckminsterPreferences.setLogLevelConsole(Logger.DEBUG);
		BuckminsterPreferences.setLogLevelEclipseLogger(Logger.SILENT);
		m_session = new CVSSession(REPO_LOCATION);
	}

	public void testCheckOut() throws Exception
	{
		File destDir = FileUtils.createTempFolder("cvs", ".test");

		IProgressMonitor nullMonitor = new NullProgressMonitor();

		// Perform the checkout
		//
		CVSTag tag = CVSTag.DEFAULT;
		CVSRepositoryLocation location = (CVSRepositoryLocation)m_session.getLocation();
		ICVSFolder root = new RemoteFolder(null, location, m_session.getModuleName(), tag);
		ICVSFolder folder = UpdateContentCachingService.buildRemoteTree(location, root, tag, IResource.DEPTH_INFINITE,
				nullMonitor);
		FileSystemCopier copier = new FileSystemCopier(folder, new Path(destDir.toString()), nullMonitor);
		try
		{
			folder.accept(copier, true);
		}
		finally
		{
			copier.done();
		}
		assertTrue(new File(destDir, EXISTING_FILE).isFile());
		
		CorePlugin plugin = CorePlugin.getDefault();
		IReaderType rd = plugin.getReaderType("cvs");
		Date lastRemote = rd.getLastModification(REPO_LOCATION, null, nullMonitor);
		System.out.println("Last modified date " + lastRemote);
		Date lastLocal = rd.getLastModification(destDir, nullMonitor);
		assertEquals(lastRemote, lastLocal);
	}

	public void testRepositories()
	{
		CVSProviderPlugin cvsProvider = CVSProviderPlugin.getPlugin();
		ICVSRepositoryLocation[] locations = cvsProvider.getKnownRepositories();
		for(ICVSRepositoryLocation location : locations)
			System.out.println(location.getLocation(false));
	}

	public void testGetFile() throws Exception
	{
		this.readFile(EXISTING_FILE);
	}

	private void readFile(String fileName) throws Exception
	{
		IProgressMonitor monitor = new NullProgressMonitor();
		ICVSRemoteFolder root = new RemoteFolderSandbox(null, m_session.getLocation(), m_session.getModuleName(), null);
		ICVSRemoteFile cvsFile = (ICVSRemoteFile)root.getFile(fileName);
		InputStream in = null;
		OutputStream out = null;
		try
		{
			in = cvsFile.getContents(monitor);
			out = new ByteArrayOutputStream();
			if(FileUtils.copyFile(in, out, monitor) == 0)
				if(cvsFile.getLogEntry(monitor) == null)
					throw new FileNotFoundException();
		}
		catch(CVSServerException e)
		{
			throw new FileNotFoundException();
		}
		finally
		{
			IOUtils.close(in);
			IOUtils.close(out);
		}
	}

	public void testDeadFile() throws Exception
	{
		try
		{
			this.readFile(DEAD_FILE);
			assertTrue("Found dead file", false);
		}
		catch(FileNotFoundException e)
		{
		}
	}

	public void testNonExistentFile() throws Exception
	{
		try
		{
			this.readFile(NON_EXISTING_FILE);
			assertTrue("Found non existing file", false);
		}
		catch(FileNotFoundException e)
		{
		}
	}

	public void testTags() throws Exception
	{
		CorePlugin plugin = CorePlugin.getDefault();
		IReaderType rd = plugin.getReaderType("cvs");
		VersionConverterDesc vd = new VersionConverterDesc("tag", VersionFactory.OSGiType, new BidirectionalTransformer[] {
				new BidirectionalTransformer(
						Pattern.compile("REL(\\d+)_(\\d+)_(\\d+)([a-zA-Z]\\w*)"), "$1.$2.$3.$4",
						Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)\\.([a-zA-Z]\\w*)"), "REL$1_$2_$3$4"),
				new BidirectionalTransformer(
						Pattern.compile("REL(\\d+)_(\\d+)_([a-zA-Z]\\w*)"), "$1.$2.0.$3",
						Pattern.compile("(\\d+)\\.(\\d+)\\.0\\.([a-zA-Z]\\w*)"), "REL$1_$2_$3"),
				new BidirectionalTransformer(
						Pattern.compile("REL(\\d+)_(\\d+)_(\\d+)"), "$1.$2.$3",
						Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)"), "REL$1_$2_$3") });

		IComponentType unknown = plugin.getComponentType(IComponentType.UNKNOWN);
		Provider provider = new Provider(null, rd.getId(), new String[] { unknown.getId() }, vd, new Format(
				":pserver:anoncvs:foo@anoncvs.postgresql.org:/projects/cvsroot,pgsql/src/backend"), null, null, null, false, false, null, null);
		ComponentQueryBuilder cq = new ComponentQueryBuilder();
		cq.setRootRequest(new ComponentRequest("pgsql", null, "[8.0.0,8.0.4]", null));
		cq.setResourceMapURL(this.getClass().getResource("test.rmap").toString());
		ResolutionContext context = new ResolutionContext(cq.createComponentQuery());
		IVersionFinder versionFinder = rd.getVersionFinder(provider, unknown, context.getRootNodeQuery(), new NullProgressMonitor());
		try
		{
			System.out.println("[8.0.0,8.0.4] resulted in version: " + versionFinder.getBestVersion(new NullProgressMonitor()));
		}
		finally
		{
			versionFinder.close();
		}
	}
}
