/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.test.rmap;

import java.io.InputStream;
import java.net.URL;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.IResolverFactory;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.resolver.ResolverFactoryMaintainer;
import org.eclipse.buckminster.core.resolver.ResourceMapResolverFactory;
import org.eclipse.buckminster.core.rmap.model.ResourceMap;
import org.eclipse.buckminster.core.test.AbstractTestCase;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class RMapTestCase extends AbstractTestCase
{
	public RMapTestCase(String name)
	{
		super(name);
	}

	public static Test suite() throws Exception
	{
		TestSuite suite = new TestSuite();
		suite.addTest(new RMapTestCase("testLocal"));
		return suite;
	}

	public void testLocal() throws Exception
	{
		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		queryBld.setRootRequest(new ComponentRequest("buckminster.test.build_a", null, null));
		URL rmapURL = getClass().getResource("local_main.rmap");
		URL parentURL = URLUtils.getParentURL(rmapURL);
		queryBld.setResourceMapURL(rmapURL.toString());
		queryBld.getProperties().put("rmaps", parentURL.toString());
		ComponentQuery query = queryBld.createComponentQuery();
		ResolutionContext ctx = new ResolutionContext(query);
		MainResolver resolver = new MainResolver(ctx);
		resolver.resolve(new NullProgressMonitor());
	}

	public void testCQUERY2BOM() throws Exception
	{
		// Get the ComponentQuery
		//
		URL queryURL = new URL("http://www.eclipse.org/buckminster/samples/queries/actor_a.cquery");
		InputStream input = queryURL.openStream();
		ComponentQuery query = ComponentQuery.fromStream(queryURL, input, true);
		input.close();

		// Create a resolver that uses the RMAP from the query regardless, i.e.
		// just bluntly override anything that has been set through
		// extension points.
		//
		ResolverFactoryMaintainer.getInstance().setResolverFactories(
			new IResolverFactory[] { new ResourceMapResolverFactory() });

		// Resolve the query
		//
		IResolver resolver = new MainResolver(new ResolutionContext(query));
		BillOfMaterials bom = resolver.resolve(new NullProgressMonitor());

		// Print the XML of exported version of the BOM
		//
		Utils.serialize(bom, System.out);
		assertTrue("Resolution failed", bom.isFullyResolved());
	}

	public void testRMAP()
	throws Exception
	{
		IProgressMonitor nullMon = new NullProgressMonitor();
		IResolver resolver = createResolver("buckminster.test.simple_a", null);
		BillOfMaterials bom = resolver.resolve(nullMon);

		// Print the XML of exported version of the BOM
		//
		Utils.serialize(bom, System.out);

		assertTrue("Resolution failed", bom.isFullyResolved());
	}

	public void testRMAPSerialization() throws Exception
	{
		CorePlugin plugin = CorePlugin.getDefault();
		IParser<ResourceMap> rmapParser = plugin.getParserFactory().getResourceMapParser(true);
		ResourceMap rmap = rmapParser.parse("test.rmap", this.getClass().getResourceAsStream("test.rmap"));
		rmapParser.parse("generated.rmap", Utils.getInputStream(rmap));
	}
}

