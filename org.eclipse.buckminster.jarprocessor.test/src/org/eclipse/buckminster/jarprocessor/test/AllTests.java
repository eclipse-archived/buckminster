package org.eclipse.buckminster.jarprocessor.test;

import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		Buckminster.setHeadless();
		Logger.setConsoleLevelThreshold(Logger.DEBUG);
		Logger.setEclipseLoggerLevelThreshold(Logger.SILENT);
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(TestRecursivePack.class);
		//$JUnit-END$
		return suite;
	}

}
