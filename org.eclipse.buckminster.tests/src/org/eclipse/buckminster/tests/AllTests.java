package org.eclipse.buckminster.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTest(org.eclipse.buckminster.core.test.AllTests.suite());

		// Disabled now since all tests were using the now disabled cvs repos at Eclipse
		// suite.addTest(org.eclipse.buckminster.cvspkg.test.AllTests.suite());

		suite.addTest(org.eclipse.buckminster.download.test.AllTests.suite());
		suite.addTest(org.eclipse.buckminster.executor.test.AllTests.suite());
		suite.addTest(org.eclipse.buckminster.git.test.AllTests.suite());
		suite.addTest(org.eclipse.buckminster.jarprocessor.test.AllTests.suite());
		suite.addTest(org.eclipse.buckminster.maven.test.AllTests.suite());
		suite.addTest(org.eclipse.buckminster.osgi.filter.test.AllTests.suite());
		suite.addTest(org.eclipse.buckminster.pde.test.AllTests.suite());
		//$JUnit-END$
		return suite;
	}

}
