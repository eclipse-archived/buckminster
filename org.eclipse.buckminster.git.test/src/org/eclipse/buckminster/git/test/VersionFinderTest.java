package org.eclipse.buckminster.git.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.NullProgressMonitor;

public class VersionFinderTest extends GitTestCase
{
	public void testResolve() throws Exception {
		File cqueryFile = getTestData("test.cquery");
		File repositories = getTestData("repositories");

		ComponentQuery cquery = ComponentQuery.fromURL(cqueryFile.toURI().toURL(), null, true);
		ResolutionContext context = new ResolutionContext(cquery);
		context.put("repositories", repositories.toURI().toString());

		MainResolver resolver = new MainResolver(context);
		BillOfMaterials bom = resolver.resolve(new NullProgressMonitor());
		assertTrue(bom.isFullyResolved(context));
		
		File testOutputDirs = getTestOutputFolder("bomFiles");
		testOutputDirs.mkdirs();
		File bomFile = new File(testOutputDirs, "test.bom");
		OutputStream output = new FileOutputStream(bomFile);
		Utils.serialize(bom, output);
		output.close();
	}
}
