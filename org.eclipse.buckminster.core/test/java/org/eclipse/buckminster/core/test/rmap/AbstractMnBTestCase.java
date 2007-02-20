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

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.test.AbstractTestCase;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;


/**
 * @author thhal
 */
public abstract class AbstractMnBTestCase extends AbstractTestCase
{
	protected ComponentQuery m_query;
	protected BillOfMaterials m_bom;

	@Override
	public void setUp()
	throws Exception
	{
		super.setUp();
		String componentName = this.getComponentName();
		IProgressMonitor nullMon = new NullProgressMonitor();
		ComponentQueryBuilder bld = new ComponentQueryBuilder();
		bld.setRootRequest(new ComponentRequest(componentName, null, null));
		bld.setResourceMapURL(RMapTestCase.class.getResource("test.rmap"));
		m_query = bld.createComponentQuery();
		IResolver resolver = new MainResolver(new RMContext(m_query));
		m_bom = resolver.resolve(nullMon);
	}
	public String getComponentName()
	{
		return "buckminster.test.simple_d";
	}
}

