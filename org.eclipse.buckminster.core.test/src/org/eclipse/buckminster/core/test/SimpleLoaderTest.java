/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.test;

import junit.framework.TestCase;

import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.materializer.MaterializationJob;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * Abstract test case that will materialize a workspace with the test components
 * build_a, build_b, build_c, and build_d.
 * 
 * @author Thomas Hallgren
 */
public class SimpleLoaderTest extends TestCase {
	protected ComponentQuery query;

	protected IProgressMonitor nullMonitor = new NullProgressMonitor();

	@Override
	public void setUp() throws Exception {
		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		ComponentRequest request = CommonFactory.eINSTANCE.createComponentRequest();
		request.setId("buckminster.test.build_a");
		queryBld.setRootRequest(request);
		queryBld.setResourceMapURL(getClass().getResource("/testData/rmaps/local_main.rmap").toString());
		query = queryBld.createComponentQuery();
	}

	public void testMaterialize() throws Exception {
		ResolutionContext rctx = new ResolutionContext(query);
		IResolver resolver = new MainResolver(rctx);
		BillOfMaterials bom = resolver.resolve(nullMonitor);
		assertFalse(rctx.emitWarningAndErrorTags());
		MaterializationSpecBuilder mspecBuilder = new MaterializationSpecBuilder();
		mspecBuilder.setName(bom.getViewName());
		mspecBuilder.setMaterializerID(IMaterializer.WORKSPACE);
		MaterializationContext matCtx = new MaterializationContext(bom, mspecBuilder.createMaterializationSpec(), rctx);
		MaterializationJob.run(matCtx);
		assertFalse(matCtx.emitWarningAndErrorTags());
	}
}
