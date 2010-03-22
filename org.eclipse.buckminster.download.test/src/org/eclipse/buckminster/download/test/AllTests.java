package org.eclipse.buckminster.download.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTest(TestFileTransfer.suite());
		suite.addTest(TestCache.suite());
		//$JUnit-END$
		return suite;
	}

}
