package org.eclipse.buckminster.core.test;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.buckminster.core.test.command.CommandsTest;
import org.eclipse.buckminster.core.test.cspec.CSpecBuilderTest;
import org.eclipse.buckminster.core.test.cspec.CSpecParserTest;
import org.eclipse.buckminster.core.test.property.PropertyFormatTest;
import org.eclipse.buckminster.core.test.rmap.RMapTest;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		// $JUnit-BEGIN$
		suite.addTestSuite(SimpleLoaderTest.class);
		suite.addTestSuite(CommandsTest.class);
		suite.addTestSuite(CSpecBuilderTest.class);
		suite.addTestSuite(CSpecParserTest.class);
		suite.addTestSuite(PropertyFormatTest.class);
		suite.addTestSuite(RMapTest.class);
		// $JUnit-END$
		return suite;
	}

}
