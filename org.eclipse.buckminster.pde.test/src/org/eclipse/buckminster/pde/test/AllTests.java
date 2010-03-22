package org.eclipse.buckminster.pde.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests
{

	public static Test suite()
	{
		TestSuite suite = new TestSuite(AllTests.class.getName());
		// $JUnit-BEGIN$
		// suite.addTestSuite(JNLPGenTest.class);
		suite.addTestSuite(RMapTest.class);
		suite.addTestSuite(MapFileTest.class);
		suite.addTestSuite(PDEBuilderTest.class);
		// $JUnit-END$
		return suite;
	}

}
