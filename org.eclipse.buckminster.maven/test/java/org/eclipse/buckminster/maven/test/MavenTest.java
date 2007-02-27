/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.maven.test;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.test.AbstractTestCase;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * @author Thomas Hallgren
 *
 */
public class MavenTest extends AbstractTestCase
{
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
	}

	public void testTransitive() throws Exception
	{
		IResolver resolver = this.createResolver("org.apache.commons.net", null);
		BillOfMaterials bom = resolver.resolve(new NullProgressMonitor());
		CSpec csp = bom.getResolution().getCSpec();
		Utils.serialize(csp, System.out);
	}
}
