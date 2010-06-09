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

import java.net.URL;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.rmap.model.ResourceMap;
import org.eclipse.buckminster.core.test.AbstractTestCase;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class RMapTest extends AbstractTestCase {
	public void testLocal() throws Exception {
		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		queryBld.setRootRequest(new ComponentRequest("buckminster.test.build_a", null, null)); //$NON-NLS-1$
		URL rmapURL = getClass().getResource("/testData/rmaps/local_main.rmap"); //$NON-NLS-1$
		queryBld.setResourceMapURL(rmapURL.toString());
		ComponentQuery query = queryBld.createComponentQuery();
		ResolutionContext ctx = new ResolutionContext(query);
		MainResolver resolver = new MainResolver(ctx);
		resolver.resolve(new NullProgressMonitor());
	}

	public void testRMAPSerialization() throws Exception {
		CorePlugin plugin = CorePlugin.getDefault();
		IParser<ResourceMap> rmapParser = plugin.getParserFactory().getResourceMapParser(true);
		ResourceMap rmap = rmapParser.parse("test.rmap", this.getClass().getResourceAsStream("/testData/rmaps/local_main.rmap")); //$NON-NLS-1$ //$NON-NLS-2$
		rmapParser.parse("generated.rmap", Utils.getInputStream(rmap)); //$NON-NLS-1$
	}
}
