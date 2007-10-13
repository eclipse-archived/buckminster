package org.eclipse.buckminster.pde.test;

import java.io.File;

import org.eclipse.buckminster.core.test.AbstractTestCase;
import org.eclipse.buckminster.pde.tasks.JNLPGenerator;
import org.eclipse.buckminster.pde.tasks.JNLPSiteGenerator;
import org.eclipse.buckminster.sax.Utils;

public class JNLPGenTest extends AbstractTestCase
{
	public void testJNLPGeneration() throws Exception
	{
		File siteWorkspace = new File("/workspaces/Buck-3.2.x");
		File featureFile = new File(siteWorkspace, "features/org.eclipse.buckminster.jnlp.product/feature.xml");
		JNLPGenerator generator = new JNLPGenerator(featureFile);
		
		File jnlpFile = File.createTempFile("bucky_", ".jnlp");
		jnlpFile.deleteOnExit();

		generator.setCodeBase("http://www.tada.se/eng/buckminster/installer");
		generator.setHref("features/org.eclipse.buckminster.jnlp.product.jnlp");
		Utils.serialize(generator.generateJNLP(), System.out);
	}

	public void testJNLPSiteGeneration() throws Exception
	{
		File siteRoot = new File("/temp/buckminster/installer");
		JNLPSiteGenerator generator = new JNLPSiteGenerator(siteRoot);
		generator.run();
	}
}
