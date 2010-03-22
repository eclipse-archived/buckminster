package org.eclipse.buckminster.osgi.filter.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTest(FilterTests.suite());
		//$JUnit-END$
		return suite;
	}

}
