package org.eclipse.buckminster.git.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.materializer.MaterializationJob;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;

public class MaterializerTest extends GitTestCase
{
	public void testMaterialize() throws Exception {
		IParser<BillOfMaterials> bomParser = CorePlugin.getDefault().getParserFactory().getBillOfMaterialsParser(true);
		File testOutputDirs = getTestOutputFolder("bomFiles");
		File bomFile = new File(testOutputDirs, "test.bom");
		InputStream bomInput = new FileInputStream(bomFile);
		BillOfMaterials bom = bomParser.parse(bomFile.getAbsolutePath(), bomInput);
		bomInput.close();

		// Create a default MSPEC
		//
		MaterializationSpecBuilder mspecBld = new MaterializationSpecBuilder();
		mspecBld.setURL(bomFile.toURI().toString());
		mspecBld.setName(bom.getViewName());
		mspecBld.setMaterializerID(IMaterializer.WORKSPACE);
		bom.addMaterializationNodes(mspecBld);
		MaterializationSpec mspec = mspecBld.createMaterializationSpec();

		MaterializationContext matCtx = new MaterializationContext(bom, mspec);
		File repositories = getTestData("repositories");
		matCtx.put("repositories", repositories.toURI().toString());
		matCtx.setContinueOnError(false);
		MaterializationJob.run(matCtx);
		assertTrue(matCtx.getStatus().getSeverity() == IStatus.OK);
		
		// Verify that projects exists and contain the expected files
		//
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject gitTest = wsRoot.getProject("git.test");
		assertTrue(gitTest.isOpen());
		IFile cspec = gitTest.getFile("buckminster.cspec");
		assertTrue(cspec.exists());

		IProject gitTest2 = wsRoot.getProject("git.test2");
		assertTrue(gitTest2.isOpen());
		cspec = gitTest2.getFile("buckminster.cspec");
		assertTrue(cspec.exists());
	}
}
