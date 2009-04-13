package org.eclipse.buckminster.galileo.builder.test;

import java.io.File;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.buckminster.galileo.builder.Builder;
import org.eclipse.core.runtime.NullProgressMonitor;

public class AssemblerTests extends TestCase
{
	static private final File BUILD_MODEL = new File(new File(System.getProperty("user.home")),
			"workspaces/galileo-build/org.eclipse.galileo.build/galileo.build");

	public static Test suite() throws Exception
	{
		TestSuite suite = new TestSuite("Tests for org.eclipse.buckminster.galileo.assembler");
		// suite.addTest(new AssemblerTests("testBuild"));
		suite.addTest(new AssemblerTests("testBuildAndMirroring"));
		return suite;
	}

	public AssemblerTests(String testName)
	{
		super(testName);
	}

	public void testBuild() throws Exception
	{
		Builder b = new Builder();
		b.setBuildModelLocation(BUILD_MODEL);
		b.setUpdate(true);
		b.setVerifyOnly(true);
		b.run(new NullProgressMonitor());
	}

	public void testBuildAndMirroring() throws Exception
	{
		Builder b = new Builder();
		b.setBuildModelLocation(BUILD_MODEL);
		b.setUpdate(true);
		b.setVerifyOnly(false);
		b.run(new NullProgressMonitor());
	}
}
