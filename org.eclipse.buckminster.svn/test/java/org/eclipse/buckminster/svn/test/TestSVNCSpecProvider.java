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

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.ProjectDescReader;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.test.AbstractTestCase;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

public class TestSVNCSpecProvider extends AbstractTestCase
{
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
	}

	public void testProvider() throws Exception
	{
		IResolver resolver = this.createResolver("org.tigris.subversion.subclipse.core", KeyConstants.PLUGIN_CATEGORY);
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
		queryBld.setRootRequest(new ComponentRequest("org.eclipse.buckminster.svn", KeyConstants.PLUGIN_CATEGORY, null));
		queryBld.setResourceMapURL(TestSVNCSpecProvider.class.getResource("test.rmap"));
		ComponentQuery query = queryBld.createComponentQuery();
		IResolver resolver = new MainResolver(new ResolutionContext(query));

		Format vh = new Format("http://svn.tada.se/util/trunk/se.tada.util");
		Provider provider = new Provider("svn", "eclipse-project", null, null, vh, true, true, null);
		IReaderType readerType = provider.getReaderType();
		IProgressMonitor nullMon = new NullProgressMonitor();
		IComponentReader reader = readerType.getReader(provider, resolver.getContext().getRootNodeQuery(), VersionMatch.DEFAULT, nullMon);

		IProjectDescription projDesc = ProjectDescReader.getProjectDescription(reader, nullMon);

		System.out.format("Found project named: %s%n", projDesc.getName());
		reader.close();

		String[] natures = projDesc.getNatureIds();
		for(String nature : natures)
			System.out.println(nature);
	}
}
