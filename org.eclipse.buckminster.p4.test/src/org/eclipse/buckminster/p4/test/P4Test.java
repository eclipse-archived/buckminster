/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.materializer.MaterializationJob;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.p4.internal.ClientSpec;
import org.eclipse.buckminster.p4.internal.Connection;
import org.eclipse.buckminster.p4.internal.ConnectionInfo;
import org.eclipse.buckminster.p4.internal.DepotFile;
import org.eclipse.buckminster.p4.internal.DepotFolder;
import org.eclipse.buckminster.p4.internal.FileSpec;
import org.eclipse.buckminster.p4.internal.Label;
import org.eclipse.buckminster.p4.preferences.Client;
import org.eclipse.buckminster.p4.preferences.P4Preferences;
import org.eclipse.buckminster.p4.preferences.Server;
import org.eclipse.buckminster.runtime.BuckminsterPreferences;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;

public class P4Test extends TestCase
{
	private final Connection m_connection;
	private final File m_tempDir;

	Map<String, String> m_env;

	public P4Test(String name, Connection connection, File tempDir)
	{
		super(name);
		m_connection = connection;
		m_tempDir = tempDir;
	}

	public static Test suite() throws Exception
	{
		final File tempDir = FileUtils.createTempFolder("p4-", ".test");
		BuckminsterPreferences.setLogLevelConsole(Logger.DEBUG);
		Map<String,String> scope = RMContext.getGlobalPropertyAdditions();
		P4Preferences prefs = P4Preferences.getInstance();
		Server server = prefs.addServer("public.perforce.com:1666");
		Client client = server.addClient("buckminster");
		server.save();

		Connection connection = new Connection(scope, client, server.getName());
		ClientSpec clientSpec = connection.getClientSpec();
		clientSpec.setRoot(Path.fromOSString(tempDir.toString()));
		clientSpec.commitChanges();

		client.setLocalRoot(tempDir.getAbsolutePath());
		client.save();

		TestSuite suite = new TestSuite()
		{
			@Override
			public void run(TestResult result)
			{
				super.run(result);
				delete(tempDir);
			}
		};
/*		suite.addTest(new P4Test("testInfo", connection, tempDir));
		suite.addTest(new P4Test("testClientSpec", connection, tempDir));
		suite.addTest(new P4Test("testDepots", connection, tempDir));
		suite.addTest(new P4Test("testFolders", connection, tempDir));
		suite.addTest(new P4Test("testLabels", connection, tempDir));
		suite.addTest(new P4Test("testDepotFile", connection, tempDir));
		suite.addTest(new P4Test("testLastChange", connection, tempDir)); */
		suite.addTest(new P4Test("testResolve", connection, tempDir));
		suite.addTest(new P4Test("testMaterialize", connection, tempDir));
		return suite;
	}

	public void testInfo()
	throws Exception
	{
		ConnectionInfo info = m_connection.getConnectionInfo();
		System.out.println(info.toString());
	}
	
	public void testClientSpec()
	throws Exception
	{
		ClientSpec client = m_connection.getClientSpec();
		for(ClientSpec.ViewEntry entry : client.getView())
			System.out.println(entry.toString());
		
	}

	public void testDepots()
	throws Exception
	{
		DepotFolder[] depots  = m_connection.getDepots();
		for(DepotFolder depot : depots)
		{
			System.out.println(depot.toString());
			DepotFolder[] folders = depot.getFolders(false);
			for(DepotFolder folder : folders)
			{
				System.out.print("    ");
				System.out.println(folder.toString());
			}
		}
	}

	public void testFolders()
	throws Exception
	{
		DepotFolder[] folders  = m_connection.getFolders(new Path("//public").append("*"), FileSpec.HEAD);
		for(DepotFolder folder : folders)
			System.out.println(folder.toString());
	}

	public void testLabels()
	throws Exception
	{
		Label[] labels  = m_connection.getLabels(new Path("//public/jam").append("..."));
		for(Label label : labels)
			System.out.println(label.getLabel());
		
		Label label = m_connection.getLabel("jam2-2-4");
		assertNotNull(label);
		System.out.println(label.getLabel());
		assertNull(m_connection.getLabel("jam-fubar"));
	}

	public void testDepotFile()
	throws Exception
	{
		String client = m_connection.getClientSpec().getClient();
		IPath filePath = new Path("//" +  client + "/public/index.html");
		DepotFile file = m_connection.getFile(new FileSpec(filePath, FileSpec.HEAD));
		assertNotNull(file);
		System.out.println(file.getDepotPath());
		System.out.println(file.getClientPath());
	}

	public void testLastChange()
	throws Exception
	{
		IPath path = new Path("//public/perforce/webkeeper");
		long number = m_connection.getLastChangeNumber(path, null);
		assertTrue(number > 0);
	}

	public void testResolve() throws Exception
	{
		URL cqueryURL = getClass().getResource("jam.cquery");
		ResolutionContext ctx = new ResolutionContext(ComponentQuery.fromURL(cqueryURL, true));
		MainResolver resolver = new MainResolver(ctx);
		BillOfMaterials bom = resolver.resolve(new NullProgressMonitor());
		assertTrue(bom.isFullyResolved());

		OutputStream bomFile = new BufferedOutputStream(new FileOutputStream(new File(m_tempDir, "jam.bom")));
		Utils.serialize(bom, bomFile);
		bomFile.close();
	}

	public void testMaterialize() throws Exception
	{
		IParser<BillOfMaterials> bomParser = CorePlugin.getDefault().getParserFactory().getBillOfMaterialsParser(true);
		File bomFile = new File(m_tempDir, "jam.bom");
		InputStream input = new BufferedInputStream(new FileInputStream(bomFile));
		BillOfMaterials bom = bomParser.parse(bomFile.getAbsolutePath(), input);
		input.close();

		MaterializationSpecBuilder mspecBld = new MaterializationSpecBuilder();
		mspecBld.setName(bom.getViewName());
		mspecBld.setURL(bomFile.toURI().toURL());
		mspecBld.setMaterializer(IMaterializer.FILE_SYSTEM);
		MaterializationContext ctx = new MaterializationContext(bom, mspecBld.createMaterializationSpec());
		MaterializationJob.run(ctx, true);
		assertTrue(ctx.getStatus().isOK());
	}

	static void delete(File file)
	{
		File[] files = file.listFiles();
		if(files != null)
			for(File child : files)
				delete(child);
		file.delete();
	}

}

