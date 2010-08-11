/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cvspkg.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.cvspkg.internal.CVSSession;
import org.eclipse.buckminster.cvspkg.internal.FileSystemCopier;
import org.eclipse.buckminster.model.common.util.BMProperties;
import org.eclipse.buckminster.runtime.BuckminsterPreferences;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.team.internal.ccvs.core.CVSException;
import org.eclipse.team.internal.ccvs.core.CVSProviderPlugin;
import org.eclipse.team.internal.ccvs.core.CVSTag;
import org.eclipse.team.internal.ccvs.core.ICVSFolder;
import org.eclipse.team.internal.ccvs.core.ICVSRemoteFile;
import org.eclipse.team.internal.ccvs.core.ICVSRepositoryLocation;
import org.eclipse.team.internal.ccvs.core.ICVSResource;
import org.eclipse.team.internal.ccvs.core.connection.CVSRepositoryLocation;
import org.eclipse.team.internal.ccvs.core.resources.RemoteFolder;
import org.eclipse.team.internal.ccvs.core.resources.UpdateContentCachingService;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class CVSTest extends TestCase
{
	private static String DEAD_FILE = "make/build.xml";

	private static String EXISTING_FILE = "META-INF/MANIFEST.MF";

	private static String NON_EXISTING_FILE = "foobar.txt";

	private static String REPO_LOCATION = ":pserver:anonymous@dev.eclipse.org:/cvsroot/technology,org.eclipse.dash/org.eclipse.dash.siteassembler";

	public static Test suite()
	{
		BuckminsterPreferences.setLogLevelConsole(Logger.DEBUG);
		BuckminsterPreferences.setLogLevelEclipseLogger(Logger.SILENT);
		TestSuite suite = new TestSuite();
		CVSSession session;
		try
		{
			session = new CVSSession(REPO_LOCATION);
		}
		catch(CoreException e)
		{
			fail(e.getMessage());
			return suite;
		}

		suite.addTest(new CVSTest("testRepositories", session));
		suite.addTest(new CVSTest("testGetFile", session));
		suite.addTest(new CVSTest("testCheckOut", session));
		suite.addTest(new CVSTest("testNonExistentFile", session));
		suite.addTest(new CVSTest("testDeadFile", session));
		// suite.addTest(new CVSTest("testTags", session));
		return suite;
	}

	private final CVSSession m_session;

	public CVSTest(String methodName, CVSSession session)
	{
		super(methodName);
		m_session = session;
	}

	public void testCheckOut() throws Exception
	{
		File destDir = IOUtils.createTempFolder("cvs", ".test", IOUtils.getTempRoot(BMProperties.getSystemProperties()));

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

	public void testDeadFile() throws Exception
	{
		try
		{
			readFile(DEAD_FILE);
			assertTrue("Found dead file", false);
		}
		catch(FileNotFoundException e)
		{
		}
	}

	public void testGetFile() throws Exception
	{
		readFile(EXISTING_FILE);
	}

	public void testNonExistentFile() throws Exception
	{
		try
		{
			readFile(NON_EXISTING_FILE);
			assertTrue("Found non existing file", false);
		}
		catch(FileNotFoundException e)
		{
		}
	}

	public void testRepositories()
	{
		CVSProviderPlugin cvsProvider = CVSProviderPlugin.getPlugin();
		ICVSRepositoryLocation[] locations = cvsProvider.getKnownRepositories();
		for(ICVSRepositoryLocation location : locations)
			System.out.println(location.getLocation(false));
	}

	private void readFile(String fileName) throws CoreException, IOException
	{
		// Build the local options
		//
		IProgressMonitor nullMon = new NullProgressMonitor();
		IPath filePath = Path.fromPortableString(fileName);
		InputStream in = null;
		OutputStream out = null;
		try
		{
			CVSTag tag = CVSTag.DEFAULT;
			IPath parentPath = Path.fromPortableString(m_session.getModuleName()).append(filePath.removeLastSegments(1));
			CVSRepositoryLocation cvsLocation = (CVSRepositoryLocation)m_session.getLocation();
			RemoteFolder folder = new RemoteFolder(null, cvsLocation, parentPath.toPortableString(), tag);
			folder = UpdateContentCachingService.buildRemoteTree(cvsLocation, folder, tag, IResource.DEPTH_ONE, nullMon);

			ICVSResource cvsFile;
			try
			{
				cvsFile = folder.getChild(filePath.lastSegment());
			}
			catch(CVSException e)
			{
				throw new FileNotFoundException(e.getMessage());
			}

			if(!(cvsFile instanceof ICVSRemoteFile))
				throw new FileNotFoundException(fileName + " appears to be a folder");

			in = ((ICVSRemoteFile)cvsFile).getContents(MonitorUtils.subMonitor(nullMon, 50));
			out = new ByteArrayOutputStream();
			IOUtils.copy(in, out, null);
		}
		finally
		{
			IOUtils.close(out);
			IOUtils.close(in);
		}
	}
}
