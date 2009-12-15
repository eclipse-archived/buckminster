package org.eclipse.buckminster.git.test;

import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.materializer.MaterializationJob;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;

public class VersionFinderTest extends GitTestCase
{
	public void testResolve() throws Exception {
		ComponentQuery cquery = getComponentQuery();
		ResolutionContext context = new ResolutionContext(cquery);
		MainResolver resolver = new MainResolver(context);
		BillOfMaterials bom = resolver.resolve(new NullProgressMonitor());
		assertTrue(bom.isFullyResolved(context));

		// Create a default MSPEC
		//
		MaterializationSpecBuilder mspecBld = new MaterializationSpecBuilder();
		mspecBld.setURL("http://localhost/dummy.url");
		mspecBld.setName(bom.getViewName());
		mspecBld.setMaterializerID(IMaterializer.WORKSPACE);
		bom.addMaterializationNodes(mspecBld);
		MaterializationSpec mspec = mspecBld.createMaterializationSpec();

		MaterializationContext matCtx = new MaterializationContext(bom, mspec);
		matCtx.setContinueOnError(false);
		MaterializationJob.run(matCtx);
		assertTrue(matCtx.getStatus().getSeverity() == IStatus.OK);
	}
}
