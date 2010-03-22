package org.eclipse.buckminster.executor.test;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.buckminster.executor.test.actor.EnvironnementVariablesTest;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		// $JUnit-BEGIN$
		suite.addTestSuite(ShellCommandTest.class);
		suite.addTestSuite(EnvironnementVariablesTest.class);
		// $JUnit-END$
		return suite;
	}

}
