/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.pde.test;

import java.util.regex.Pattern;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.query.builder.AdvisorNodeBuilder;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class RMapTest extends PDETestCase {
	public void testGetEclipseOSGIViaRMAP() throws Exception {
		try {
			this.getPlugin();
			ComponentRequest request = new ComponentRequest("org.eclipse.osgi", IComponentType.OSGI_BUNDLE, null); //$NON-NLS-1$

			ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
			queryBld.setRootRequest(request);
			queryBld.getDeclaredProperties().put("buckminster.download.source", "true");
			queryBld.setResourceMapURL(this.getClass().getResource("test.rmap").toString()); //$NON-NLS-1$

			AdvisorNodeBuilder nodeBld = queryBld.addAdvisorNode();
			nodeBld.setNamePattern(Pattern.compile("org\\.eclipse")); //$NON-NLS-1$
			nodeBld.setUseTargetPlatform(false);
			nodeBld.setBranchTagPath(new VersionSelector[] { VersionSelector.tag("R4_3_M5a") });
			ComponentQuery query = queryBld.createComponentQuery();

			IResolver resolver = new MainResolver(new ResolutionContext(query));
			BillOfMaterials bom = resolver.resolve(new NullProgressMonitor());
			assertTrue("Resolve failed", bom.isFullyResolved(resolver.getContext())); //$NON-NLS-1$
			Utils.serialize(bom.getResolution(), System.out);
		} catch (CoreException e) {
			e.printStackTrace();
			fail("Resolve failed: " + e.getMessage());
		}
	}
}
