/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.svn.test;

import java.io.File;
import java.net.URL;

import org.eclipse.buckminster.core.test.AbstractTestCase;
import org.tigris.subversion.subclipse.core.SVNProviderPlugin;
import org.tigris.subversion.svnclientadapter.ISVNClientAdapter;
import org.tigris.subversion.svnclientadapter.SVNRevision;
import org.tigris.subversion.svnclientadapter.SVNUrl;

public class TestSVNCSpecProvider extends AbstractTestCase
{
	@Override
	protected URL getRMAP()
	{
		return getClass().getResource("test.rmap");
	}

	@Override
	public void setUp() throws Exception
	{
		super.setUp();
	}

	public void testWeirdness() throws Exception
	{
		ISVNClientAdapter adapter = SVNProviderPlugin.getPlugin().createSVNClient();
		SVNUrl url = new SVNUrl("http://dev.eclipse.org/svnroot/stp/org.eclipse.stp.b2j/trunk/features/org.eclipse.stp.b2j.feature");
		SVNRevision revision = adapter.getInfo(url).getRevision();
		adapter.checkout(url, new File("/home/thhal/tmp/cotest"), revision, true);
	}
/*
	public void testResolutionPriority() throws Exception
	{
		IVersionDesignator designator = VersionFactory.createDesignator(VersionFactory.OSGiType, "[0.1.0,0.3.0)") ;
		ComponentRequest request = new ComponentRequest("org.eclipse.buckminster.cmdline", IComponentType.OSGI_BUNDLE, designator);

		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		queryBld.setRootRequest(request);
		queryBld.setResourceMapURL(getRMAP());

		AdvisorNodeBuilder node = new AdvisorNodeBuilder();
		node.setNamePattern(Pattern.compile("buckminster"));
		node.setUseTargetPlatform(false);

		// Look first at main, then at branch 3.2.x and finally at Europa_GA
		//
		node.setBranchTagPath(VersionSelector.fromPath("main,3.2.x,/Europa_GA"));
		node.setResolutionPrio(new int[] { AdvisorNode.PRIO_BRANCHTAG_PATH_INDEX, AdvisorNode.PRIO_VERSION_DESIGNATOR, AdvisorNode.PRIO_SPACE_PATH_INDEX });
		queryBld.addAdvisorNode(node);

		IResolver resolver = new MainResolver(new ResolutionContext(queryBld.createComponentQuery()));
		BillOfMaterials bom = resolver.resolve(new NullProgressMonitor());
		assertTrue("Resolve failed", bom.isFullyResolved());
		assertNull(bom.getResolution().getVersionMatch().getBranchOrTag());
		Utils.serialize(bom.getResolution(), System.out);
	}

	public void testBestVersionOnPath() throws Exception
	{
		IVersionDesignator designator = VersionFactory.createDesignator(VersionFactory.OSGiType, "[0.1.0,0.2.0)") ;
		ComponentRequest request = new ComponentRequest("org.eclipse.buckminster.cmdline", IComponentType.OSGI_BUNDLE, designator);

		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		queryBld.setRootRequest(request);
		queryBld.setResourceMapURL(getRMAP());

		AdvisorNodeBuilder node = new AdvisorNodeBuilder();
		node.setNamePattern(Pattern.compile("buckminster"));
		node.setUseTargetPlatform(false);

		// Look at branch 3.2.x, tag Europa_GA, and finally in the trunk (main)
		//
		node.setBranchTagPath(VersionSelector.fromPath("3.2.x,/Europa_GA,main"));
		queryBld.addAdvisorNode(node);

		IResolver resolver = new MainResolver(new ResolutionContext(queryBld.createComponentQuery()));
		BillOfMaterials bom = resolver.resolve(new NullProgressMonitor());
		assertTrue("Resolve failed", bom.isFullyResolved());
		assertTrue(bom.getResolution().getVersionMatch().getBranchOrTag().toString().equals("3.2.x"));
		Utils.serialize(bom.getResolution(), System.out);
	}

	public void testRevisionOnBranchProvider() throws Exception
	{
		IVersionDesignator designator = VersionFactory.createExplicitDesignator(VersionFactory.OSGiType, "1.2.2") ;
		ComponentRequest request = new ComponentRequest("org.tigris.subversion.subclipse.core", IComponentType.OSGI_BUNDLE, designator);

		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		queryBld.setRootRequest(request);
		queryBld.setResourceMapURL(getRMAP());

		// Test an advisor node that will force us to look at the 1.2.x branch
		// and at a revision of that branch where we will find version 1.2.2. It
		// just so happens that that will be revision
		//
		AdvisorNodeBuilder node = new AdvisorNodeBuilder();
		node.setNamePattern(Pattern.compile("subversion"));
		node.setUseTargetPlatform(false);
		node.setBranchTagPath(VersionSelector.fromPath("1.2.x"));
		node.setRevision(3191);
		queryBld.addAdvisorNode(node);

		IResolver resolver = new MainResolver(new ResolutionContext(queryBld.createComponentQuery()));
		BillOfMaterials bom = resolver.resolve(new NullProgressMonitor());
		assertTrue("Resolve failed", bom.isFullyResolved());
		Utils.serialize(bom.getResolution(), System.out);
	}

	public void testCSpecProvider()
	throws Exception
	{
		CorePlugin plugin = CorePlugin.getDefault();
		if(plugin == null)
			throw new Exception("This test must be run as a \"JUnit Plug-in Test\"");

		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		queryBld.setRootRequest(new ComponentRequest("org.eclipse.buckminster.svn", IComponentType.OSGI_BUNDLE, null));
		queryBld.setResourceMapURL(TestSVNCSpecProvider.class.getResource("test.rmap"));
		ComponentQuery query = queryBld.createComponentQuery();
		IResolver resolver = new MainResolver(new ResolutionContext(query));
		Provider provider = new Provider("svn", new String[] { IComponentType.OSGI_BUNDLE }, "http://subclipse.tigris.org/svn/subclipse/trunk/subclipse/core");
		IReaderType readerType = provider.getReaderType();
		IProgressMonitor nullMon = new NullProgressMonitor();
		IComponentReader reader = readerType.getReader(provider, plugin.getComponentType(IComponentType.OSGI_BUNDLE), resolver.getContext().getRootNodeQuery(), VersionMatch.DEFAULT, nullMon);

		IProjectDescription projDesc = ProjectDescReader.getProjectDescription(reader, nullMon);

		System.out.format("Found project named: %s%n", projDesc.getName());
		reader.close();

		String[] natures = projDesc.getNatureIds();
		for(String nature : natures)
			System.out.println(nature);
	} */
}
